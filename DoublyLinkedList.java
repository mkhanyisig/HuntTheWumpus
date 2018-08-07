/**
 * File: DoublyLinkedList.java
 * Author: Mkhanyisi Gamedze
 * Date: 10/16/13
 * 
 *CS231 project 6; Doubly-Linked Lists 
 */
 
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
 
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.LinkedList;

public class DoublyLinkedList<T> implements Iterable<T>{

	// Class fields
	public Node head;
	public Node tail;
	public T container;
	public int listSize;
		
	
	public DoublyLinkedList(){
		// list has nothing in it intially, no nodes attached
		this.head=null;
		this.tail=null;
		// container=null;
		this.listSize=0;
	}
	
	
	// The Node class that stores elements and the Node pointers
	private class Node{
		Node next;
		Node prev;
		T container;
		
		public Node(T item){
			this.next=this.prev=null;
			this.container=item;	
		}
		
		public T getThing() {return this.container;}
		public void setContents(T item) {this.container = item;}
		public Node getNext()  {return this.next;}
		public void setNext(Node n) {this.next = n;}
		public Node getPrev()  {return this.prev;}
		public void setPrev(Node n) {this.prev = n;}
	}
	
	public void clear(){
		// same as default/constructor
		this.head=this.tail=null;
		this.listSize=0;
	}
	
	public int size(){
		return this.listSize;
	}
	
	public void add(T item){
			// add new node at head/top
			Node n = new Node(item);
            Node oldHead;
        
			if(this.head == null) {
					// if head is null, the new node is head+tail
					this.head = n;
					this.tail = n;
			}
			else {
					// connect new node to old head and then set head pointer to new node
					this.head.setPrev(n); // upward arrow
                    n.setNext(this.head); // downward arrow
					this.head=n;
                
			}
			this.listSize++;

	}
	
	
	
	
	
	// Backward iterator, same as old iterator but starts from bottom and moves up
	private class LLBackwardIterator implements Iterator<T>{
		Node nxt;
		Node previous;
		T cur;
		public Node ptr; /* point at current node during traversal */
		
		public LLBackwardIterator(Node tail){
			
			ptr=tail;
			
		}
		
		// ensure that next Node exists before iteration
		public boolean hasNext(){
			// just simply return whether this statement is true or false
            return ptr.getPrev()!=null;
			
		}
		
		
		
		// Move ptr to next Next, then return bucket value
		public T next(){
            cur=ptr.getThing();
            ptr=ptr.getPrev();
			return cur ;
		}
		
		// return the current Thing (T) I am working with /* this method never works when I call it in Landscape, I wonder why? */
		public T current(){
			return ptr.getThing() ;
		}
		
		
		// does nothing
		public void remove(){}
		
	}
	
	// iterator class that traverses through list
	/* my iterator , for forward iteration is similar to my backward iterator, but it never works when I call it in this class main method or elesewhere */
	private class LLIterator implements Iterator<T>{
		Node nxt;
        T cur;
		public Node ptr; /* point at current node during traversal */
		
		public LLIterator(Node head){		
			ptr=head; // not necessarily head  of list (this.head), but is reference node
		}
		
		public boolean hasNext(){
			// just simply return whether this statement is true or false
			/* System.out.println("has next works"); //used to debugg */
			return ptr.getNext()!=null;
		}
		
		public T current(){
            //System.out.println("current works");  /*this never prints */
			return ptr.getThing() ;
		}
		
		public T next(){
            T cur= ptr.getThing();
            ptr=ptr.getNext();
            //System.out.println("next works");
            return cur ;
		}
		
		// does nothing
		public void remove(){}
	
	}
	
	
	
	/* loop through node and get content then add to arraylist, order of items is from top to bottom */
	public ArrayList<T> toArrayList(){
		
		ArrayList<T> collection= new ArrayList<T>(this.listSize);
		
		
		for (T i: this){ /* enhanced for loop to add all elements of linked list onto arraylist */
			collection.add(i);
		}
		
		return collection;
	}
	
	/* return shuffled ArrayList of T contents */
	public ArrayList<T> toShuffledList(){
		
		ArrayList<T> list= new ArrayList<T>(this.listSize);
		
		for (T i: this){ /* enhanced for loop to add all elements of linked list onto arraylist */
			list.add(i);
		}
		
		Collections.shuffle(list);
		return list;
	}
	
	
	
	// Return a new LLIterator pointing to the head of the list
    public Iterator<T> iterator() {
        return new LLIterator( this.head );
    }
    
    // Return a new LLBackwardIterator pointing to the head of the list
    public Iterator<T> backward_iterator()  {
        return new LLBackwardIterator( this.tail );
   	}
	
	
	/**************** new methods for project 6 */
	
	public void remove(T thing){
		Node ptr=this.head;
		this.listSize--;
		if (this.head==null){
			return;
		}
		for (int i=0; i<this.listSize;i++){
			if(ptr.getThing()==thing){
				if (ptr==this.head){
					this.head=this.head.getPrev();
					this.head.setNext(null); 
				}
				else if (ptr==this.tail){
					this.tail=this.tail.getNext();
					this.head.setPrev(null);
				}
				else{
					ptr.getNext().setPrev(ptr.getPrev());
					ptr.getPrev().setNext(ptr.getNext());	
				}
			} 
			
			else{
				ptr=ptr.getPrev(); // get previous and make it  new pointer ( loop down till we find thing we looking for)
			}
			if (ptr==null){
				break;
			}
			
		}		
	
	}
	// memory loss here for all statement, as previous reference to removed node is now null
	
	// insert at the start (tail) of the list /* Code similar to Bruce's HW 05's offer method */
	public void offer(T item) {
				Node n = new Node(item);
				if(this.tail == null) { // special case
						this.tail = n;
						this.head = n;
						
				}
				else {
						this.tail.setNext(n);
                        n.setPrev(this.tail);
						this.tail = n;
				}
				this.listSize++;
				return;
	}
	
	// remove from the start (bottom) of the list /* Code similar to Bruce's HW 05's poll method */
		public T poll() {
				if(tail != null) {
						Node n = this.tail;
						this.tail = this.tail.getPrev();
						if(this.tail == null) {
								this.head = null;
						}
						else {
								this.tail.setNext(null);
						}
						this.listSize--;
						return n.getThing();
				}
				return null;
		}
	
	// element
	public T element(){
		return this.tail.getThing();
	}
	
	public String toString(){
		String s=" ";
		return s;
	}

	
	
	
	public static void main(String[] args) {
		
		DoublyLinkedList<Integer> llist = new DoublyLinkedList<Integer>();
		
		
		llist.add(5);
		llist.add(10);
		llist.add(20);
		llist.add(25);
		llist.add(30);
		llist.add(200);
		
		
		System.out.printf("\n before clearing, size="+ llist.size());
		
		llist.clear();
		
		System.out.printf("\n After clearing, doubly linked list now empty, size= "+ llist.size());
		
		
		for (int i=0; i<20; i+=2) {
			llist.add(i);
		}
		
        /*
		//
		LinkedList<String> linkedList = new LinkedList<String>();
		linkedList.add("Paypal");
		linkedList.add("Google");
		linkedList.add("Yahoo");
		linkedList.add("IBM");
		linkedList.add("Facebook");
		//
		ListIterator<String> listIterator = linkedList.listIterator();
		while (listIterator.hasNext()) {
			System.out.println(listIterator.next());
		}
		//
		for (String item: linkedList) {
			System.out.printf("thing \n"+ item);
		}
		//
        */

		
		
		System.out.printf("\n After setting "+ llist.size());
		
		for (Integer item: llist) {
			System.out.printf("   \n   "+ item);
		}
		
		
		
		/* this did not work, the for loop was giving me issues, a bettter method suggestion would be appreciated */
		/*
		// testing toArraylist method
		ArrayList<Integer> alist = llist.toArrayList();
		System.out.printf("\nAfter copying %d\n", alist.size());
		
		for(Integer item: alist) {
			System.out.printf("thing %d\n", item);
		}							
		
		
		alist = llist.toShuffledList();	
		
		
		System.out.printf("\nAfter copying %d\n", alist.size() );
		System.out.printf("\n The Shuffled list contains, in regular order: \n" );
		for(Integer item: alist) {
			System.out.printf("thing %d\n", item);
		}
		*/
		
        
		// last iteration to test backward iteration
		System.out.println( "\n iterating backward" + " for list of size: "+llist.size() );
        Iterator<Integer> bi =  llist.backward_iterator();
        while (bi.hasNext()) {
            System.out.println( bi.next() );
            //System.out.println( bi.current() );
        }
        
        // last iteration to test forward iteration
		System.out.println( "\n iterating forward" + " for list of size: "+llist.size() );
        Iterator<Integer> fi =  llist.iterator();
        while (fi.hasNext()) {
            System.out.println( fi.next() );
        }
        
        
        
		
		
		
	}
	
	
			
}	
		
	
	
	
	
