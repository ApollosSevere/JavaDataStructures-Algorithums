package PriorityQueue;

import java.util.Comparator;

// import Lists.ArrayLists.ArrayList;
import java.util.*;
import PriorityQueue.AbstractClass.AbstractPriorityQueue;
import PriorityQueue.Interfaces.Entry;

// This is such an elegant data Structure!

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V>{

    /* ------------ Primary Collection of Priority Queue entries ------------- */
    protected ArrayList<Entry<K,V>> heap = new ArrayList<>();


    /* ------------ Constructors ------------- */
    public HeapPriorityQueue() {super();}
    public HeapPriorityQueue(Comparator<K> comp) {super(comp);}

    public HeapPriorityQueue(K[] keys, V[] values) {
        super();

        for (int j = 0; j < Math.min(keys.length, values.length); j++) {
            heap.add(new PQEntry<>(keys[j], values[j]));
        }
        heapify();
    }
    public HeapPriorityQueue(K[] keys, V[] values, Comparator<K> comp) {
        super(comp);
        for (int j = 0; j < Math.min(keys.length, values.length); j++) {
            heap.add(new PQEntry<>(keys[j], values[j]));
        }
        heapify();
    }
    

    /* ------------ Important Protected Utilities ------------- */
    protected void heapify() {
        int startIndex = parent(size() - 1);
        for (int j = startIndex; j >= 0; j--)
            downheap(j);
    }    //Yoooo --> Did we just make a sorting algorithum with just one function?


    /* ------------ Protected Utilities ------------- */
    protected int parent(int j) {return (j-1) / 2;}
    protected int left(int j) {return 2*j + 1;}
    protected int right(int j) {return 2*j + 2;}

    protected boolean hasLeft(int j) { return left(j) < heap.size(); }
    protected boolean hasRight(int j) { return right(j) < heap.size(); }

    protected void swap(int i, int j) {
        Entry<K,V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    protected void upheap(int j) {
        while (j > 0) {      // If we havent reached the root yet or if nothing broke inside the code
            int parent = parent(j);   //Remember parent sends you an integer!!
            if (compare(heap.get(j), heap.get(parent)) >= 0) break;  // Heap property verified 
            swap(j, parent);
            j = parent;
        }
    }
    
    protected void downheap(int j) {
        while (hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            
            if (hasRight(j)) {  //If a right child is present then we can go throught the troblue of comparing the left and right to see which one will be smaller!!
                int rightIndex = right(j);    //First grab the right index now
                if (compare(heap.get(rightIndex), heap.get(leftIndex)) < 0) 
                    smallChildIndex = rightIndex;
            }   // This whole section just works to switch up the smallChildIndex variable so we could use it below

            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0 ) break; //If the child is smaller than the parent then i want you to break!!
            swap(j, smallChildIndex);    // If not, then swap and run the loop again!!
            j = smallChildIndex;     //Remember that this is the young boi in view!!  J should equal that small child now because remeber our cursors position just wen down a level!!
        }
    }


    /* ------------ Important Update Methods ------------- */
    public int size() {return heap.size();}

    public Entry<K,V> min() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }

    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newest = new PQEntry<>(key, value);
        heap.add(newest);
        upheap(heap.size() - 1);
        return newest;
    }

    public Entry<K,V> removeMin() {
        if (heap.isEmpty()) return null;
        Entry<K,V> answer = heap.get(0);
        swap(0, heap.size() - 1);   // Remeber, when removing always switch the top with the bottom
        heap.remove(heap.size() - 1);  //Then remove the last element
        downheap(0);
        return answer;
    }


}
