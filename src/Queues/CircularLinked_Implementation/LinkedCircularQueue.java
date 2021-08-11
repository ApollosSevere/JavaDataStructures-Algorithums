package Queues.CircularLinked_Implementation;

import LinkedList.Circular.CircularlyLinkedList;

public class LinkedCircularQueue<E> implements CircularQueue<E>{
    /* Begginning Set Up for ADT */
    private CircularlyLinkedList<E> list = new CircularlyLinkedList<>();
    public LinkedCircularQueue() {};

    /* Interface Completion */ 
    public int size() {return list.getSize();}
    public boolean isEmpty() {return list.isEmpty();} 
    public E first() {return list.first();}

    // Main Bulk
    public void enqueue(E element) {list.addLast(element);}
    public E dequeue() {return list.removeFirst();}
    public void rotate() {list.rotate();}
}
