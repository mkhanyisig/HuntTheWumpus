// Mkhanyisi Gamedze
// CS231 Data Structures
// project 9 lab :  Graphs 

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.*;

public class Wumpus extends Agent {
	
	
	private Vertex home;
    private Direction orientation;
    private boolean killed;
    private boolean win;
    private String state;
    
    // constructor
    public Wumpus(Vertex home){ // invisivle init
        super(home.getX(), home.getY());
        this.home=home;
        this.orientation=Direction.EAST;
        this.killed=false;
        this.win=false;
    }
    
    // retrieve
    public Vertex getWumpus(){
        return this.home;
    }
    
    public void killWumpus(){
        this.killed=true;
        this.home.visible();
        this.state="Wumpus killed ";
    }
    
    public void eatHunter(){
        this.win=true;
        this.home.visible();

        this.state="Wumpus kills hunter ";
    }
    
    // does nothing
    public void updateState(Landscape land){
        
    }
    
    
    // draw wumpus
    public void draw(Graphics g, int scale) {
        if(!killed && !win){
            return;
        }
        
        int xpos = this.home.getX()*scale;
        int ypos = this.home.getY()*scale;
        int border = scale/4;
        if(this.killed == true){
            g.setColor(new Color(255, 0, 0)); // red
        }
        else{
            g.setColor(new Color(0, 255, 0)); // green - kills hunter
        }
        g.fillRect(xpos + border, ypos + border, scale/2, scale/2);
    }




}