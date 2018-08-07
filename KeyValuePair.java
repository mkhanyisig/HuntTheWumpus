/**
 * File: KeyValuePair.java
 * Author: Mkhanyisi Gamedze
 * Date: 29/11/2016
 * 
 *CS231 project 7; Binary Search Trees as Maps 
 */
 
 import java.util.Map;

public class KeyValuePair<K, V> {
    private K key;
    private V value;

    
 	public KeyValuePair( K k, V v ){
 		this.key=k;
 		this.value=v;
 	}
 	
 	public K getKey(){
 		return this.key;
 	}
 	
 	public void setKey( K k ){
 		this.key=k;
 	}
 	
 	public V getValue(){
 		return this.value;
 	}
 	
 	public void setValue( V v ){
 		this.value=v;
 	}
 	
 	public String toString() {
 		String s=this.key+" "+this.value;
 		return s;
 	}
 	
 	public V lookup(K lkey) {
 		if (this.key==lkey){
 			return this.value;
 		}
 		else{
 			return null;
 		}
 	}
 	
 	// simple test of main method
	public static void main(String[] args) {
	 	System.out.println("hello");
	 	KeyValuePair<Integer,String> test= new  KeyValuePair(1, "Mkhanyisi");
	 	System.out.println("\n "+test.getValue());
	 	System.out.println("\n "+test.getKey());
	 	test.setKey(231);
	 	test.setValue("Data structures & algorithms");
	 	System.out.println("\n "+test.toString());	
	}

 
 
 }
 
 

