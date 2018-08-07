/**
 * File: Landscape.java
 * Author: Mkhanyisi Gamedze
 * Date: 10/16/16
 * 
 *CS231 project 9; Hunt the wumpus
 */
 
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
 
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
//import java.lang.Object;
import java.awt.geom.RectangularShape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double; 

public class Landscape{
    
    /******** Class fields  */
    private Random gen;
    
    // dimension fields
    int width;
    int height;
	
    // agents list
    private DoublyLinkedList<Vertex> background;
    private DoublyLinkedList<Vertex> foreground;
    
    private Vertex[][] grid;
    private Graph field;
    private Hunter hunter;
    private Wumpus wumpus;
    
    

    
    //Constructor
    public Landscape(int w, int h){
    
    	// set dimensions
    	this.width=w;
    	this.height=h;
    	
        // intialize background and foreground
        background = new DoublyLinkedList<Vertex>();
    	foreground = new DoublyLinkedList<Vertex>();
    	
    	this.gen= new Random(); 
        
        // insert Vertexes to each space in grid, background agents
        grid= new Vertex[width][height];
        for (int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                Vertex v= new Vertex();
                v.set(i,j);
                grid[i][j]=v;
                this.addB(v);
                this.add(v);
            }
        }
        
        // connect all vertices, if there is vertice next to it in NESW directions
        for (int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                if (grid[i][j]!=null && grid[i][j-1]!=null  ){
                    field.addEdge(grid[i][j], Direction.NORTH, grid[i][j-1]);
                }
                if (grid[i][j]!=null && grid[i][j+1]!=null ){
                    field.addEdge(grid[i][j], Direction.SOUTH, grid[i][j+1]);
                }
                if (grid[i][j]!=null && grid[i+1][j]!=null){
                    field.addEdge(grid[i][j], Direction.EAST, grid[i+1][j]);
                }
                if (grid[i][j]!=null && grid[i-1][j]!=null){
                    field.addEdge(grid[i][j], Direction.WEST, grid[i-1][j]);
                }
            }
        }
        
    }
    
     /********* class methods */ 
    
    // retrieve + modify fields
    public void setW(int w){
    	this.width=w;
    }
    public void setH(int h){
    	this.height=h;
    }
    public int getW(){
    	return this.width;
    }
    public int getH(){
    	return this.height;
    }
    
    public DoublyLinkedList<Vertex> getBackground(){
        return this.background;
    }
    
    public DoublyLinkedList<Vertex> getForeground(){
        return this.foreground;
    }
    
    // clear
    public void reset(){
        background.clear();
        foreground.clear();
    }

	
	// describe checkout state
	public String toString(){
		String s=" ";
		return s;
	}
	
		
	public void addB(Vertex a){
		background.add(a);
	}
    
    public void add(Vertex a){
        foreground.add(a);
    }
	
	public void RemoveAgentB(Vertex a){
        if (a !=null){
            background.remove(a); // memory lost
        }
        else{} // do nothing
    }
	
    public void RemoveAgentF(Vertex a){
        if (a !=null){
            foreground.remove(a); // memory lost
        }
        else{} // do nothing
    }
    
    public ArrayList<Vertex> getAgents(){
        return background.toArrayList();
    }
    
    public ArrayList<Vertex> getAgentsF(){
        return foreground.toArrayList();
    }
    
    public void addHunter(Hunter h){
        this.hunter=h;
    }
    
    public void addWumpus(Wumpus w){
        this.wumpus=w;
    }
    
	// advances the landscape
	public void Update(){
      	
      		
	}
    
    public void draw( Graphics g, int gridScale ){ // paint all the vertices foreground + hunter
        
    	// draw all background visited vertices
        for( Vertex v: this.background){
            if (v.visited()==true){
                v.draw( g, gridScale );
            }
        }
        // draw all foreground visited vertices
        for( Vertex v: this.foreground){
            if (v.visited()==true){
                v.draw( g, gridScale );
            }
        }
        // draw hunter
        this.hunter.draw(g, gridScale);

        // if wumpus found, draw
        
        
        
    	
    	        
    }
    
    
    
    
    //main method
    public static void main(String argv[]) {
        int rows = 30;
        int cols = 20;
        Landscape scape = new Landscape(rows, cols);
        System.out.println("row: "+scape.getH());
        System.out.println("col: "+scape.getW());
        Random gen = new Random();
        
        
    }
    
    
    

}

