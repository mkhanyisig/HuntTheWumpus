// Mkhanyisi Gamedze
// CS231 Data Structures
// project 9 lab :  Graphs 

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.*;
import java.util.Random ;

public class HuntTheWumpus {
    
    // class fields
    private Graph field;
    private Landscape scape;
    private LandscapeDisplay disp;
    private Hunter hunter;
    private Wumpus wumpus;
    private ArrayList<Vertex> vertices;
    private GameState state;
    
    
    // constructor
    public HuntTheWumpus(){
        rand= new Random();
        
        
        this.scape = new Landscape(10, 10);
        this.display = new LandscapeDisplay(this.scape,64);
        vertices= new ArrayList<Vertex>();
        // add wumpus and randomly place it on graph
        wumpus=new Wumpus(new Vertex());
        wumpus.getWumpus().setX(rand.nextInt(10));  // randomly generate position for wumpus
        wumpus.getWumpus().setY(rand.nextInt(10));
        // add hunter
        hunter= new Hunter(new Vertex()); // hunter at (0,0)
        state=GameState.START; // start enum
    }
    
    // enum for game state
    private enum GameState { START,FIRE,WIN, DEFEAT, STOP, QUIT }
    
    // add all the vertices onto the ArrayList
    public void AddVertices(){
        for (Vertice v: vertexes){
            scape.add(v);
        }
    }
    
    // build a randomly connected graph
    
    
    //
    
    
    
    
    
    
    
    


}



