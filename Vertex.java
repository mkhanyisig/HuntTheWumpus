// Mkhanyisi Gamedze
// CS231 Data Structures
// project 9 lab :  Graphs 

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.*;

public class Vertex extends Agent implements Comparable<Vertex>{
   //implements Comparable<Vertex>
    // fields
    private BSTMap<Direction,Vertex> edge;
    private Integer cost;
    private boolean marked;
    private ArrayList<Vertex> neighbors;
    public String name;
    private boolean visible;
    private boolean visited=false;
    
    
    // constructor
    public Vertex(){
        super(1,1);
        edge = new BSTMap<Direction,Vertex>(new distanceComp());
        cost=0;
        marked=false;
        neighbors= new ArrayList<Vertex>();
        visible=false;
        
        
    }
    
    // retrieve+modify dimension-location fields
    public int getX(){
        return this.col;
    }
    public int getY(){
        return this.row;
    }
    public void setX(int a){
        this.col=a;
    }
    public void setY(int a){
        this.row=a;
    }
    public void setName(String s){
        this.name=s;
    }
    
    public Integer getCost(){
        return this.cost;
    }
    public void setCost(Integer c){
        this.cost=c;
    }
    
    public void mark(){
        this.marked=true;
    }
    
    public void unmark(){
        this.marked=false;
    }
    
    public boolean marked(){
        return this.marked;
    }
    
    public void visible(){
        this.visible=true;
    }
    public void visit(){
        this.visited=true;
    }
    
    public boolean visited(){
        return this.visited;
    }
    public boolean isVisible(){
        return this.visible;
    }
    
    // add new connection (Connect vertex in a specific direction to the current )
    public void connect(Vertex other, Direction dir){
        
        edge.put(dir, other);
    }
    
    
    // compare
    public int compareTo(Vertex a){
        if (this.getCost() == a.getCost()){
            return 0;
        }
        else return (this.getCost()-a.getCost())/(Math.abs(this.getCost()-a.getCost()));
    }
    
    
    // since this is distance is the key, get(key) gives me the Vertex value of it
    public Vertex getNeighbor(Direction dir){
        return edge.get(dir);
    }
    
    public ArrayList<Vertex> getNeighbors(){
        ArrayList<Direction> keys=edge.getKeys();
        
        for (int i=0; i<keys.size();i++){
            // get direction and then get its key value(Vertex)
            neighbors.add(edge.get(keys.get(i)));
        }
        return neighbors;
    }
    
    // describe vertex
    // method to print out the number of neighbors, the cost, and the marked flag
    public String toString(){
        
        String s= "Neighbors :"+ this.edge.size()+"   cost:  "+this.cost+"   marked :"+this.marked;
        return s;
    }
    
    // draw method
    public void draw(Graphics g, int gridScale) {
        if (!this.visible)
            return;
        int xpos = this.getCol()*gridScale;
        int ypos = this.getRow()*gridScale;
        int border = 2;
        int half = (int) gridScale / 2;
        int eighth =  (int) gridScale / 8;
        int sixteenth =  (int) gridScale / 16;
        
        // draw rectangle for the walls of the cave
        if (this.cost <= 2)
            // wumpus is nearby
            g.setColor(Color.red);
        else
            // wumpus is not nearby
            g.setColor(Color.black);
        
        g.drawRect(xpos + border, ypos + border, gridScale - 2*border, gridScale - 2 * border);
        
        // draw doorways as boxes
        g.setColor(Color.black);
        if (this.edge.containsKey(Direction.NORTH))
            g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        if (this.edge.containsKey(Direction.SOUTH))
            g.fillRect(xpos + half - sixteenth, ypos + gridScale - (eighth + sixteenth), eighth, eighth + sixteenth);
        if (this.edge.containsKey(Direction.WEST))
            g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
        if (this.edge.containsKey(Direction.EAST))
            g.fillRect(xpos + gridScale - (eighth + sixteenth), ypos + half - sixteenth, eighth + sixteenth, eighth);
    }
    
    
    // main method (testing)
    public static void main (String[] args){
        
        // create objects
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
    
        // modify fields
        v1.setCost(4);
        v2.setCost(7);
        
        System.out.println(v1.getCost());
        v1.mark();;
        v2.mark();
        
        // test Direction enums
        System.out.println("Opposite of NORTH:  "+Direction.opposite(Direction.NORTH));
        System.out.println("Opposite of SOUTH:  "+Direction.opposite(Direction.SOUTH));
        System.out.println("Opposite of EAST:   "+Direction.opposite(Direction.EAST));
        System.out.println("Opposite of WEST:   "+Direction.opposite(Direction.WEST));
        
        //System.out.println("Comparing" +v1.compareTo(v2));
        
        // connect
        v1.connect(v2, Direction.EAST);
        v1.connect(v1, Direction.WEST);
        
        System.out.println(v1.toString());
        System.out.println(v2.toString());
    }

    

}

class distanceComp implements Comparator<Direction> {
    
    public distanceComp(){;}
    
    public int compare( Direction i1, Direction i2 ) {
        // returns negative number if i2 comes after i1 lexicographically
        
        return 0;
    }
}


//enum Direction{north, south, east, west}
