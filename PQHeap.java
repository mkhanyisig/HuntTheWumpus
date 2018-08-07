// Mkhanyisi Gamedze
// CS231 Data Structures
// project 8 lab :  Priority Queues 


/*
    Whenever you remove a value from the priortiy queue, it should have the highest priority (as determined by the Comparator)
*/

import java.util.Comparator;
import java.util.ArrayList;

public class PQHeap<T>{
    private Comparator<T> comparator;
    private ArrayList<T> heap;   // array-list based heap
    private int size;
    
	//constructor
	public PQHeap( Comparator<T> comparator){
		this.size=0;
		this.comparator=comparator;
		this.heap= new ArrayList<T>();
	}
	
	public int size(){
		return this.size;
	}
    
    public ArrayList<T> getHeap(){
        return this.heap;
    }
    
    
    public int leftChild(int i){
        return 2*i + 1;
    }
    
    public int rightChild(int i){
        return 2*i + 2;
    }
    
    public int parent(int i){
        return (i-1)/2;
    }
	
    public boolean isEmpty(){
        return this.heap.isEmpty();
    }
    
    
	public void add(T obj){
        this.size++;
		if (this.size==0){
			// if heap is empty
			this.heap.add(obj);
		}
		else{
			this.heap.add(obj);
            int i=this.heap.size()-1;
            while( i > 0 && this.comparator.compare(this.heap.get(i),this.heap.get(parent(i))) > 0){
                swap(parent(i), i);
                i = parent(i);
            }
            
            
		}
        //System.out.println(this.heap);  // for testing
	}
	
    
	public T remove(){ //  removes and returns the highest priority element from the heap // memory lost each time this method is called
        if (heap.size()==0){
            return null;
        }
        else{
            T root = this.heap.get(0); // this is the highest priority element
            T lastelement=this.heap.get(this.size-1);
            
            // first step: replace root with last element in heap
            this.heap.set(0,lastelement);
            this.heap.set(this.size-1, root);
            
            // second: remove last element (high priority element)
            this.heap.remove(this.size-1); // memory lost here
            this.size--;
            
            // Swap/heapify until priority que heap condition is met
            SwapCompare(0);

            
            return root;
        }
        
    }
	
    public void swap(int CIndex, int PIndex){
        // get bucket values of these indexes
        T parent=this.heap.get(PIndex);
        T child=this.heap.get(CIndex);
        
        // swap parent for childIndex and child for parentIndex
        this.heap.set(PIndex, child);
        this.heap.set(CIndex, parent);
    }
    
   
    
    public void SwapCompare(int i){ //reshapes the heap, assuming it contains integers
        
        int l = leftChild(i);
        int r= rightChild(i);
        int max = i;
        
        //System.out.println("left:  "+l+"  right:  "+r+"   max:  "+max);
       
        // choose which to compare to
        int b;
        if (l<heap.size()){
            if (r<=heap.size()-1){
                //System.out.println("Size:  "+this.size+ "   heap size:  "+heap.size());
                
                if(this.comparator.compare(this.heap.get(l),this.heap.get(r)) > 0) {
                    b=l;
                }
                else{
                    b=r;
                }
            }
            else{
                b=l;
            }
        }
        else{ // break if child class is beyond scope of heap size
            // swap done
            //System.out.println(this.heap);
            return;
        }
        
        // recursive comparison till priority que condition met throughout
        if(this.comparator.compare(this.heap.get(b), this.heap.get(max)) > 0) {
            swap(b, max);
            
            max=b;
            //System.out.println("recursion");
            SwapCompare(max);
        }
        else{
                return;
        }
        
    }
     
    public ArrayList<T> printOut(){ //prints out the heap in an array
        ArrayList<T> a = new ArrayList<T>();
        for(int i = 0; i < this.heap.size()-1; i++){
            a.add(this.heap.get(i));
        }
        return a;
    }
    
    
    
    
    // main method
    public static void main( String[] args ) { //test method
        PQHeap<Integer> test = new PQHeap<Integer>(new TestIntComparator());
        System.out.println(test.size());
        test.add(5);
        test.add(6);
        test.add(2);
        System.out.println(test.size());
        System.out.println(test.getHeap());
        //System.out.println("removing the highest priority element (biggest): "+ test.remove() );
        System.out.println(test.size());
        
        for (int i=0;i<5;i++){
            test.add(i);
        }
        
        
        System.out.println(test.getHeap());
        System.out.println(test.remove());
        System.out.println(test.remove());
        
        
    }
    
    
    



}


class TestIntComparator implements Comparator<Integer> { //comparator
    // constructor
    public TestIntComparator() {;}
    
    // methods
    public int compare(Integer o1, Integer o2) {
        return o1-o2;
    }
}
