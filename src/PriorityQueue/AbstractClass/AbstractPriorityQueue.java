package PriorityQueue.AbstractClass;

import java.util.Comparator;

import PriorityQueue.Interfaces.Entry;
import PriorityQueue.Interfaces.PriorityQueue;

public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V> {
    
    /* ------------ Nested PQEntry Class ------------- */
    protected static class PQEntry<K,V> implements Entry<K,V> {
        private K k;
        private V v;
        public PQEntry(K key, V value) {
            k = key;
            v = value;
        }  //First: Function in a nested Node class

        // Methods of Entry Interface
        public K getKey() {return k;}
        public V getValue() {return v;}

        // Utilites not xposed as part of the Entry Interface
        protected void setKey(K key) {k = key;}
        protected void setValue(V value) {v = value;}
    }

    /* ------------ Default Comparator ------------- */
    public class DefaultComparator<E> implements Comparator<E> {
        public int compare(E a, E b) throws ClassCastException{
            return ((Comparable<E>) a).compareTo(b);
        }
    }


    /* ------------ Instance Vaiables ------------- */
    private Comparator<K> comp;
    protected AbstractPriorityQueue(Comparator<K> c) {comp = c;}
    protected AbstractPriorityQueue() { new DefaultComparator<K>(); }


    /* ------------ Methods ------------- */
    protected int compare(Entry<K,V> a, Entry<K,V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }   //Method for comparing two entries

    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (comp.compare(key, key) == 0); // This checks if key can be compared to itself
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    public boolean isEmpty() {return size() == 0;}

}
