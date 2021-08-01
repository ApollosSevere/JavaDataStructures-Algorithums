package Queues.Array_Implementation;

import Queues.Queue;

public class ArrayQueue<E> implements Queue<E> {
    //    Instance variables
    private E[] data;
    private int f = 0;
    private int size = 0;

//    Constructors
    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

//    Methods
    public boolean isEmpty() {return size == 0;}
    public int size() {return size;}
//    Returns but does not remove first
    public E first() {
        if (isEmpty()) return null;
        return data[f];
    }

//    Inserts an element at the rear of the queue (Remember, we doing this the cool way boi!)
    public void enqueue(E e) throws IllegalStateException {
        if (size == data.length) throw new IllegalStateException("Queue is full boi !!");
//        Allows them thangs to go in the front of the line if no one be up in that jawn boi !
//        This is allow you to put the elements all the way in the front no matter where it is!
        int available = (f + size) % data.length;
        data[available] = e;
        size++;
    }
//    Removes and tetuns the first elelment of the queue (null if empty)
    public E dequeue() {
        if (isEmpty()) return null;
        E answer = data[f];
        data[f] = null;
        f = (f + 1) % data.length;
        size--;
        return answer;
    }


}
