// Mkhanyisi Gamedze
// CS231 Data Structures
// project 9 lab :  Graphs 

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.*;

public class Graph {

    // fields
    private ArrayList<Vertex> vertexes;
    PQHeap<Vertex> VXHeap;
    
    
	// constructor 
	public Graph(){
        vertexes= new ArrayList<Vertex>();
	}
	
    
    // add  vertex to graph/grid
    public void add(Vertex v){
        String s="v";
        // add to graph if position x-y has no vertex
        if(this.vertexes.contains(v)==false){
            vertexes.add(v);
            s+=(vertexes.size()-1);
            v.name=s;
        }
        else{}
        
    }
	
    //
    public void addEdge(Vertex v1, Direction dir, Vertex v2){
        
        // add to graph (if necessary)
        add(v1);
        add(v2);
        
        // connect vertices
        v1.connect(v2, dir);
        v2.connect(v1, Direction.opposite(dir));
        

        
    }
    
    
	public int vertexCount(){
        return this.vertexes.size();
	}
    
    public ArrayList<Vertex> getVertexes(){
        return this.vertexes;
    }
    
    //  implements a single-source shortest-path algorithm for the Graph
    public void shortestPath(Vertex v0){
        
        // Initialize all vertices in G to be unmarked and have infinite cost
        // Create a priority queue, q, that orders vertices by lowest cost
        VXHeap= new PQHeap<Vertex>(new vertexComparator<Vertex>());
        
        for (int i=0; i<vertexes.size();i++){
            vertexes.get(i).unmark();
            vertexes.get(i).setCost(Integer.MAX_VALUE);  // infinity for me is just a really big number
        }
        
        // Set the cost of v0 to 0 and add it to q
        v0.setCost(0);
        VXHeap.add(v0);
        
        // while PQHeap: q is not empty:
        while (VXHeap.isEmpty()==false){
            //let v be the vertex in q with lowest cost
            //remove v from PQHeap: q
            Vertex v= VXHeap.remove();
            
            //mark v as visited
            v.mark();
            
            //for each vertex w that neighbors v:
            for (Vertex w: v.getNeighbors()){
                if ( w.marked()==false && v.getCost() + 1 < w.getCost()){
                    w.setCost(v.getCost()+1);
                    VXHeap.add(w);
                }
            }
        } 
    }
    
    
    // vertex comparator
    
    public static class vertexComparator<Vertex extends Comparable<Vertex>> implements Comparator<Vertex> {
        
        public vertexComparator(){
            
        }
        
        public int compare( Vertex a, Vertex b ) {
            return (a.compareTo(b));
        }
    }
     /*
    public static class vertexComparator<Vertex extends Comparable<Vertex>> implements Comparator<Vertex> {
        public int compare( Vertex a, Vertex b ) {
            return a.compareTo(b);
        }
    }
      */

    
    
    
    // main method (test)
    public static void main(String[] args){
        
        Graph test = new Graph();
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Vertex v3 = new Vertex();
        Vertex v4 = new Vertex();
        Vertex v5 = new Vertex();
        Vertex v6 = new Vertex();
        
        System.out.println("adding edges");
        // cross
        test.addEdge(v1, Direction.NORTH, v2);
        test.addEdge(v1, Direction.EAST, v3);
        test.addEdge(v1, Direction.SOUTH, v4);
        test.addEdge(v5, Direction.EAST, v1);
        test.addEdge(v4, Direction.EAST, v6);
        
        System.out.println("done");
        System.out.println("size"+test.getVertexes().size());
        test.shortestPath(v1);
        for(Vertex v: test.getVertexes()){
            System.out.println("Cost "+v.name+" : "+v.getCost());
        }
        
        
        /*
        Graph test = new Graph();
        Vertex v1 = new Vertex();
        Vertex v2 = new Vertex();
        Vertex v3 = new Vertex();
        Vertex v4 = new Vertex();
        Vertex v5 = new Vertex();
        Vertex v6 = new Vertex();
        
        test.addEdge(v1, Direction.EAST, v2);
        test.addEdge(v1, Direction.WEST, v3);
        test.addEdge(v4, Direction.NORTH, v2);
        test.addEdge(v4, Direction.WEST, v3);
        test.addEdge(v4, Direction.SOUTH, v5);
        test.shortestPath(v1);
        for(Vertex vtx: test.getVertexes()){
            System.out.println("cost :"+vtx.getCost());
        }
         */
        
        
    }	


}
