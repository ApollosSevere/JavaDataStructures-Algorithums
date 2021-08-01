package Queues;

public interface Queue<E> {
//    Returns the number of elements in queue
    int size();
//    Test whether the queue is empty
    boolean isEmpty();
//    Inserts an element at the rear of the queue
    void enqueue(E e);
//    Returns, but does not remove, the first element
    E first();
//    Removes and return the first elements
    E dequeue();
}
