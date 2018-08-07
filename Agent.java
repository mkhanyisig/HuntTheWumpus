/**
 * File: Agent.java
 * Author: Mkhanyisi Gamedze
 * Date: 10/16/13
 * 
 *CS231 project 5; Doubly-Linked Lists 
 */
 
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
 
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

public class Agent {
	
	public int col;
	public int row;
	
	public Agent(int r, int c){
		this.col=c;
		this.row=r;	
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getCol(){
		return this.col;
	}
    
    public void set(int x,int y){
        col=x;
        row=y;
    }
	
	public void setRow( int newRow ){
		this.row=newRow ; // memory whenever his method is called for changing location of agent already on landscape
	}
	
	public void setCol( int newCol ){
		this.col=newCol; // memory whenever his method is called for changing location of agent already on landscape
	}
	
	public String toString(){
		String s= "( "+ this.row+", "+this.col +" )";
		return s;
	}
	
	/* does nothing for now *//*
	public void updateState( Landscape scape ){	}
	*/
	
	/* does nothing for now */
	public void draw(Graphics g){ }
	
	// main method
	public static void main(String[] args) {
		Agent g= new Agent(5,5);
		
		System.out.println("The initial co-ordinates (row, col):" +g.toString());
		
		g.setRow(10);
		g.setCol(15);
		
		
		System.out.println("New updated co-ordinates :" +g.toString());
	
	}
	
}
