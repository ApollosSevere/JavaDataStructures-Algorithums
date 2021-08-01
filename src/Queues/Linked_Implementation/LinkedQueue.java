package Queues.Linked_Implementation;

import LinkedList.Singly.LinkedListTemplate;
import Queues.Queue;

public class LinkedQueue<E> implements Queue<E> {
    private final LinkedListTemplate<E> list = new LinkedListTemplate<E>();
    public LinkedQueue() {};
    public int size() {return list.getSize();}
    public boolean isEmpty() {
       return list.isEmpty();
    }
    public E first() {return list.getHead();}

//    Main Bulk!!
    public void enqueue(E element) {list.addLast(element);}
    public E dequeue() { return list.removeFirst();}
}
