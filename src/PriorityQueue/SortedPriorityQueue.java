package PriorityQueue;

import java.util.Comparator;

import Lists.PositionalList.LinkedPositionalList;
import Lists.PositionalList.Interfaces.Position;
import Lists.PositionalList.Interfaces.PositionalList;
import PriorityQueue.AbstractClass.AbstractPriorityQueue;
import PriorityQueue.Interfaces.Entry;

public class SortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    
    // Remember, always have a primary collection with Queue
    private PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();

    /* ------------ Constructors ------------- */
    public SortedPriorityQueue() {super();}
    public SortedPriorityQueue(Comparator<K> comp ) {super(comp);}


    /* ------------ Important Update Methods ------------- */
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newest = new PQEntry<>(key, value);
        Position<Entry<K,V>> walk = list.last();

        while (walk != null && compare(newest, walk.getElement()) == -1)
            walk = list.before(walk);    // Remember, with a Positional list, you do not have to move all the other elements to add a new element!!
        if (walk == null)
            list.addFirst(newest);
        else 
            list.addAfter(walk, newest);  // At this point, the walk is on the first element from the end to be smaller then current/newest
                                          // This is why you add the current/newest right after that first smaller than index!
        return newest;
    }

    public Entry<K,V> min() {
        if (list.isEmpty()) return null;
        return list.first().getElement();
    }

    public Entry<K,V> removeMin() {
        if (list.isEmpty()) return null;
        return list.remove(list.first());
    }

    public int size() {return list.size();}
}
