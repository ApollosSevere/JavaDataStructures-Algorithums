package PriorityQueue;

import java.util.Comparator;

import Lists.PositionalList.LinkedPositionalList;
import Lists.PositionalList.Interfaces.Position;
import PriorityQueue.AbstractClass.AbstractPriorityQueue;
import PriorityQueue.Interfaces.Entry;

public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    
    // Primary collection of priority queue entries
    private LinkedPositionalList<Entry<K,V>> list = new LinkedPositionalList<>();

    
    /* ------------ Constructors ------------- */
    public UnsortedPriorityQueue() {super();}
    public UnsortedPriorityQueue(Comparator<K> comp) { super(comp); }


    /* ------------ Utilities ------------- */
    private Position<Entry<K,V>> findMin() {      // This is using the conventional find Max or Min function
        Position<Entry<K,V>> small = list.first();

        for (Position<Entry<K,V>> walk : list.positions()) 
            if (compare(walk.getElement(), small.getElement()) < 0 )
                small = walk;

        return small;
    }

    /* ------------ Update Methods ------------- */
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newest = new PQEntry<>(key, value);
        list.addLast(newest);
        return newest;
    }

    public Entry<K,V> min() {
        if (list.isEmpty()) return null;
        return findMin().getElement();
    }

    public Entry<K,V> removeMin() {
        if (list.isEmpty()) return null;
        return list.remove(findMin());
    }
    
    public int size() {return list.size();}
}
