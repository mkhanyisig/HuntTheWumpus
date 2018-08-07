// Mkhanyisi Gamedze
// CS231 Data Structures
// project 9 lab :  Graphs 

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.*;

public class Hunter extends Agent {

    private Vertex location;
    private Direction orientation;
    private boolean shoot;
    
    /*
    // image fields
    BufferedImage hunterU;
    BufferedImage hunterD;
    BufferedImage hunterL;
    BufferedImage hunterR;
    */
     
    // constructor
    public Hunter(Vertex location){
        super(location.getX(), location.getY());
        this.location=location;
        location.set(0,0);
        this.orientation=Direction.EAST;
        location.visible();
        location.visit();  // mark vertex as visited
        
    }
    
    // retrieve
    public Vertex getLocation(){
        return this.location;
    }
    
    /*
    // static method to read hunter images
    static {
        try{
            hunterU = ImageIO.read(new File("hunterU.png"));
            hunterD = ImageIO.read(new File("hunterD.png"));
            hunterL = ImageIO.read(new File("hunterL.png"));
            hunterR = ImageIO.read(new File("hunterR.png"));
            //wumpus = ImageIO.read(new File("wumpus.png"));
        }
        catch(IOException e){};
    }

     */
    
    
    
    
    // draw method
    public void draw(Graphics g, int scale) {
        int xpos = this.location.getX()*scale;
        int ypos = this.location.getY()*scale;
        int border = 10;
        /*
        switch(this.orientation){ // directioon hunter is facing
            case NORTH:{
                g.drawImage(hunterU,xpos + border, ypos+border, null);
            }break;
            case SOUTH:{
                g.drawImage(hunterD,xpos + border, ypos+border, null);
            }break;
            case WEST:{
                g.drawImage(hunterL,xpos + border, ypos+border, null);
            }break;
            default: {
                g.drawImage(hunterR,xpos + border, ypos+border, null);
            }break;
        }
         */
        /*
        if(this.shoot){
            //g.drawImage(target, xpos-8, ypos-8, null);
        }
         */
    }


}
