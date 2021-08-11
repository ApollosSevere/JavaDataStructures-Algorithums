package Queues.SinglyLinked_Implementation;

import LinkedList.Singly.LinkedListTemplate;
import Queues.Queue;

public class SinglyLinkedQueue<E> implements Queue<E> {
    private final LinkedListTemplate<E> list = new LinkedListTemplate<E>();
    public SinglyLinkedQueue() {};
    public int size() {return list.getSize();}
    public boolean isEmpty() {
       return list.isEmpty();
    }
    public E first() {return list.getHead();}

//    Main Bulk!!
    public void enqueue(E element) {list.addLast(element);}
    public E dequeue() { return list.removeFirst();}
}
