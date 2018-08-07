/* Stephanie's code used intially
* Mkhanyisi Gamedze
* CS231: Data structures  & Algorithms
* Fall 2016: Project 7
* Binary Search Trees as Maps
*/



import java.util.ArrayList;
import java.util.Comparator;

public class BSTMap<K,V> implements MyMap<K,V>  {
    
    private TreeNode<K,V> root;
    private Comparator<K> comparator;
    private int size=0; 
    private ArrayList<KeyValuePair<K,V>> preOrderList;
    private ArrayList<KeyValuePair<K,V>> inOrderList;
    private ArrayList<K> Keys;
    private int currentDepth, depth;
    
    public BSTMap( Comparator<K> comparator ) {
        this.comparator = comparator;
        this.root= null; // intialized to null
        this.preOrderList= new ArrayList<KeyValuePair<K,V>>(); // intialise this
		this.inOrderList= new ArrayList<KeyValuePair<K,V>>(); // intialise this
        this.Keys= new ArrayList<K>(); // intialise this
        
        // WARNING: THIS CODE IS INCOMPLETE. You still need to initialize the root.
        // and code that initializes root
        this.currentDepth=this.depth=0;
        
        
    }    
    // implementing MyMap interface methods
    public void put(K k, V v)  {
        this.root = insert(this.root, k,v);
        this.size++;
    }
    
    private TreeNode<K, V> insert(TreeNode<K, V> n, K k, V v) {
        if (n == null) {
            return new TreeNode<K, V> (k, v,  null, null); // if no root, make new node be the root
        }
        
       	if (n.getKey() == k ) {
        	n.getPair().setValue(v);
        	this.size--;
        	return n;
        }
       	
       	
        
        if (this.comparator.compare(k, n.getKey())<0) {
            // add key to the left subtree
            n.setLeft( insert(n.getLeft(), k,v) );
            return n;
        }
        
        else {
            // add key to the right subtree
            n.setRight( insert(n.getRight(), k,v) );
            return n;
        }
    }
    
    // Returns true if the map contains a key-value pair with the given key
    // search tree to see if it contains our key
    public boolean containsKey(K k) {
        return search(root, k);
    }
    private boolean search(TreeNode<K, V> n, K k) {
        if (n == null) { // not root, it is null. Then no way this key is in an empty tree
            return false;
        }
        
        if (n.getKey().equals(k)) { // if it is in root
            return true;
        }
        
        if (this.comparator.compare(k, n.getKey())<0) {
            // key < this node's key; look in left subtree
            return search(n.getLeft(), k);
        }
        
        else {
            // key > this node's key; look in right subtree
            return search(n.getRight(), k);
        }
    }
  
    
    // Returns the value associated with the given key.
    // If that key is not in the map, then it returns null.
    // search tree to see if it contains our key, return its value
    public V get(K k) {
        return get(this.root, k);
    }
    
    private V get(TreeNode<K, V> n, K k) {
        if (n == null) { // not root, it is null. Then no way this key is in an empty tree
            return null;
        }
        
        if (n.getKey().equals(k)) { // if it is in root
            return n.getValue();
        }
        
        if (this.comparator.compare(k, n.getKey())<0) {
            // key < this node's key; look in left subtree
            return get(n.getLeft(), k);
        }
        
        else{
            // key > this node's key; look in right subtree
            return get(n.getRight(), k);
        }
        
    }
    
    //----------------
    
    public TreeNode<K, V>  getNode(K k) {
        return getNode(this.root, k);
    }
    
    private TreeNode<K, V>  getNode(TreeNode<K, V> n, K k) {
        if (n == null) { // not root, it is null. Then no way this key is in an empty tree
            return null;
        }
        
        if (n.getKey().equals(k)) { // if it is in root
            return n;
        }
        
        if (this.comparator.compare(k, n.getKey())<0) {
            // key < this node's key; look in left subtree
            return getNode(n.getLeft(), k);
        }
        
        else{
            // key > this node's key; look in right subtree
            return getNode(n.getRight(), k);
        }
        
    }

    
    
    
    
    
    
    
    
    
    //---------------
    
    
    //--------------------------------------------------------
    // Returns an array list of all the keys in the map.
    public void preorderKeys(TreeNode<K,V> root) { // assuming tree is already well built
        
        if(root !=  null) {
            this.Keys.add(root.getKey());
            //recursion
            preorderKeys(root.getLeft());
            preorderKeys(root.getRight());
        }
    }
    
    // return the pre order arraylist of key-value pairs
    public ArrayList<K> getKeys(){
        this.Keys= new ArrayList<K>(); // reset this // memory loss
        
        preorderKeys(this.root); // create the pre-order arrayList, through this recursive method
        
        return this.Keys;
    }
    //--------------------------------------------
    //---------------------  pre order arraylist of key-value pairs  ---------------------------//
    // pre order traversal
    // Recursive Solution
    public void preorder(TreeNode<K,V> root) { // assuming tree is already well built
        
        if(root !=  null) {
            this.preOrderList.add(root.getPair());
            //recursion
            preorder(root.getLeft());
            preorder(root.getRight());
        }
    }
    
    // return the pre order arraylist of key-value pairs
    public ArrayList<KeyValuePair<K,V>> getPairs(){
        this.preOrderList= new ArrayList<KeyValuePair<K,V>>(); // reset this // memory loss
        
        preorder(this.root); // create the pre-order arrayList, through this recursive method
        
        return this.preOrderList;
    }
    
    //------------------------------------------------ In order array-list of key-value pairs ----------//
   
    public void inOrder(TreeNode<K,V> root) { // returns nothing, creates in-order arraylist on field
        if(root !=  null) {
            inOrder(root.getLeft());
            // add value to arraylist
            this.inOrderList.add(root.getPair());
            inOrder(root.getRight());
        }
    }
    
    // return the in-order arraylist of key-value pairs
    public ArrayList<KeyValuePair<K,V>> getIOPairs(){
        this.inOrderList= new ArrayList<KeyValuePair<K,V>>(); // reset this // memory loss
        
        inOrder(this.root); // create the in-order arrayList, through this recursive method
        
        return this.inOrderList;
    }
    //----
    
	// return the pre order arraylist of key-value pairs
    public ArrayList<K> getIOKeys(){
        this.Keys= new ArrayList<K>(); // reset this // memory loss
        
        ArrayList<KeyValuePair<K,V>> pairs= getPairs();
        
        for (int i=0; i<pairs.size()/2;i++){
        	this.Keys.add(pairs.get(i).getKey());
        	System.out.println(this.Keys.size());
        }
        
        
        
        
        //inorderKeys(this.root); // create the pre-order arrayList, through this recursive method
        
        return this.Keys;
    }
    
    
    
   //--------------------------------------------------------------------------//
    
    
     // removing a Treenode key, deleting. No need for value here, makes no difference, as nodes are sorted accoring to keys
    public void delete(K k) {
        root = delete(root, k);
    }
    private K smallest(TreeNode<K,V> n){
        // precondition: n is not null
        // postcondition: return the smallest value in the subtree rooted at n. This achieved when smallest has null point, indicating that no other value is smaller than it
        if (n.getLeft() == null) {
            return n.getKey();
        }
        else {
            return smallest(n.getLeft());
        }
    }
    
    private TreeNode<K,V> delete(TreeNode<K,V> n, K k) {
        if (n == null) {
            return null;
        }
        if (k.equals(n.getKey())) { // we have found our node n associated with the key
            // n is the node to be removed
            if (n.getLeft() == null && n.getRight() == null) {
                return null; // key is not there, nothing to remove
            }
            if (n.getLeft() == null) {
                return n.getRight();
            }
            if (n.getRight() == null) {
                return n.getLeft();
            }
            
            // if we get here, then n has 2 children
            K smallVal = smallest(n.getRight());
            n.getPair().setKey(smallVal); // memory lost
            n.setRight( delete(n.getRight(), smallVal) ); // memory lost
            return n; 
        }
        
        else if (this.comparator.compare(k, n.getKey())<0) {
            n.setLeft( delete(n.getLeft(), k) ); // memory lost
            return n;
        }
        
        else {
            n.setRight( delete(n.getRight(),k) ); // memory lost
            return n;
        }
    }
    
    
    
    
    
    
    
    // Returns the number of key-value pairs in the map.
    public int size(){
    	return this.size;
    }
        
   
    
    
    
    public int depth(){
    	return 1;
    }
    
	
	
	
	public int depth(TreeNode<K,V> n) {
    if (n != null) {
        this.currentDepth++;
         
        // record total depth if current depth is greater than total depth 
        if (currentDepth > depth) {
            depth = currentDepth;
        }
 
        // recursively traverse entire tree
        depth(n.getLeft());
        depth(n.getRight());
         
        // decrement as we traverse up the binary tree
        currentDepth--;
    }
    return depth;
	}
	
	
	
	
	
	
	
	
	
    
    
    
    
    
    public static void main( String[] args ) {
       
       
        System.out.println( "test code" );
        BSTMap<String,Integer> map = new BSTMap<String,Integer>(new AscendingStringComparator());
        map.put( "B", 2 );
        map.put( "A", 1);
        map.put( "D", 2 );
        map.put( "C", 2 );
        //map.printInOrder();
        System.out.println();
        System.out.println( "Has A: " + map.containsKey( "A" ) );
        System.out.println( "Has G: " + map.containsKey( "G" ) );
        map.put( "D", 3 );
        System.out.println( "D now has value " + map.get( "D" ) );
        System.out.println( "The tree has " + map.size() + " elements" );
        System.out.println( "The tree has " + map.depth() + " levels" );
        ArrayList<KeyValuePair<String,Integer>> pairs  = map.getPairs();
        System.out.println( "In useful order: " );
        System.out.println( pairs );
        ArrayList<String> keys  = map.getKeys();
        System.out.println( keys );
        System.out.println( "deleting 'A' ");
        map.delete("A");
        keys  = map.getKeys();
        System.out.println( keys );
        
    }
}

//---------------------------------------

//--------------------------------------- Tree node class --------------------//

class TreeNode<K,V> {
    private KeyValuePair<K,V> pair;
    private TreeNode<K,V> left;
    private TreeNode<K,V> right;
    private K key ;
    private V value ;
	
	// constructor
    public TreeNode( K this_key, V this_val, TreeNode<K,V> l, TreeNode<K,V> r ) {
        pair= new KeyValuePair(this_key, this_val  ); // intialize Key-Value pair
        this.left=l;
        this.right=r;
        this.key=this_key;
        this.value=this_val;
    }
    
    // Methods to support insert, contains, printing, getPairs, etc.
  
    /* methods */
  
    // access fields
    public TreeNode<K,V>  getLeft(){
        return this.left;
    }
    public TreeNode<K,V>  getRight(){
  		return this.right;	
    }
    public KeyValuePair<K,V> getPair(){
  		return this.pair; // get Key-value pair
    }
    public K getKey(){
        return this.key;
    }
    public V getValue(){
        return this.value;
    }
    public V getValue(K k){ // given key, get value
        return this.value;
    }
  
    // modify fields
    public void setRight(TreeNode<K,V> new_r){
  		this.right=new_r;
    }
    public void setLeft(TreeNode<K,V> new_l){
		this.left=new_l;
    }
    public void setPair(KeyValuePair<K,V> new_pair){
  		this.pair=new_pair;
    }
    /*
    //--------------------
    // search tree to see if it contains our key
    public boolean containsKey(K k) {
        return search(root, k);
    }
    private boolean search(TreeNode<K, V> n, K k) {
        if (n == null) { // not root, it is null. Then no way this key is in an empty tree
            return false;
        }
        
        if (n.getKey().equals(k)) { // if it is in root
            return true;
        }
        
        if (key.compareTo(n.getKey()) < 0) {
            // key < this node's key; look in left subtree
            return search(n.getLeft(), k);
        }
        
        else {
            // key > this node's key; look in right subtree
            return search(n.getRight(), k);
        }
    }
    
    
     //--------------------
    // search tree to see if it contains our key, return its value
    public V get(K k) {
        return get(root, k);
    }
    
    private V get(TreeNode<K, V> n, K k) {
        if (n == null) { // not root, it is null. Then no way this key is in an empty tree
            return null;
        }
        
        if (n.getKey().equals(k)) { // if it is in root
            return n.getValue();
        }
        
        if (key.compareTo(n.getKey()) < 0) {
            // key < this node's key; look in left subtree
            return get(n.getLeft(), k);
        }
        
        else {
            // key > this node's key; look in right subtree
            return get(n.getRight(), k);
        }
        
        return null;
    }
  
    //--------------------
    
    // add key-value pair to TreeNode
    public void insert(K k, V v)  {
        this.root = insert(this.root, k,v);
    }
    
    private TreeNode<K, V> insert(TreeNode<K, V> n, K k, V v) {
        if (n == null) {
            return new TreeNode<K, V> (k, v,  null, null); // if no root, make new node be the root
        }
        
       	if (n.getKey().equals(k)) {
        	n.setValue(v);
        	return n;
        }
       	
       	
        else if (k.compareTo(n.getKey()) < 0) {
            // add key to the left subtree
            n.setLeft( insert(n.getLeft(), k,v) );
            return n;
        }
        
        else {
            // add key to the right subtree
            n.setRight( insert(n.getRight(), k,v) );
            return n;
        }
    }
    
    // removing a Treenode key, deleting. No need for value here, makes no difference, as nodes are sorted accoring to keys
    public void delete(K k) {
        root = delete(root, k);
    }
    private K smallest(TreeNode<Key,Value> n){
        // precondition: n is not null
        // postcondition: return the smallest value in the subtree rooted at n. This achieved when smallest has null point, indicating that no other value is smaller than it
        if (n.getLeft() == null) {
            return n.getKey();
        }
        else {
            return smallest(n.getLeft());
        }
    }
    
    private TreeNode<Key,Value> delete(TreeNode<Key,Value> n, Key k) {
        if (n == null) {
            return null;
        }
        if (k.equals(n.getKey())) { // we have found our node n associated with the key
            // n is the node to be removed
            if (n.getLeft() == null && n.getRight() == null) {
                return null; // key is not there, nothing to remove
            }
            if (n.getLeft() == null) {
                return n.getRight();
            }
            if (n.getRight() == null) {
                return n.getLeft();
            }
            
            // if we get here, then n has 2 children
            Key smallVal = smallest(n.getRight());
            n.setKey(smallVal);
            n.setRight( delete(n.getRight(), smallVal) );
            return n; 
        }
        
        else if (k.compareTo(n.getKey()) < 0) {
            n.setLeft( delete(n.getLeft(), k) );
            return n;
        }
        
        else {
            n.setRight( delete(n.getRight()) );
            return n;
        }
    }
    
  
  
    // compare to current node, the key given
    public boolean containsKey( Key k){
  	return this.pair.getKey()==k; // if key is this value, then it returns true. One node contains one key
  }
  
  
  
  */

} // end class TreeNode


class AscendingStringComparator implements Comparator<String> {
    public int compare( String i1, String i2 ) {
        // returns negative number if i2 comes after i1 lexicographically
        return i1.compareTo(i2);
    }
}
//---------------------------------
