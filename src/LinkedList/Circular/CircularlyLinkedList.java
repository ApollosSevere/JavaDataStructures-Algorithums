package LinkedList.Circular;


/* ----------->>>>>  Structure to Immediately Creating a linked list  <<<<<----------- */
public class CircularlyLinkedList<E> {

    /* ---------- Nested Node Class ------------ */
//    Nested Node Class: Identical to that of the SinglyLinkedList class!!
    private static class Node<E> {
        private final E element;
        private Node<E> next;
        public Node (E e, Node<E> n) {
            element = e;
            next = n;
        }
        public E getElement() {
            return element;
        }
        public Node<E> getNext() {
            return next;
        }
        public void setNext(Node<E> n) {next = n;}
    }


    /* ---------- Circularly Instance Variables ------------ */
    private Node<E> tail = null;      //Only need to store tail not head!
    private int size = 0;
    public CircularlyLinkedList() {}


    /* ---------- Access Methods ------------ */
    public int getSize() {return size;}
    public boolean isEmpty() {return size == 0;}
    public E first() {          //Returns first but does not remove
        if (isEmpty()) return null;
        return tail.getNext().getElement();
    }
    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }


    /* ---------- Important Update Methods ------------ */
    public void addFirst(E element) {
        if (size == 0) {
            tail = new Node<> (element, null);
            tail.setNext(tail);           // Link to itself circularly boi !!
        }
        Node<E> newest = new Node<>(element, tail.getNext());
        tail.setNext(newest);
        size++;
    }
    public void addLast(E element) {
        addFirst(element);          //Add it in the mix regularly then just update the new tail!!
        tail = tail.getNext();      //Bruh, this is just a dope way to do it!!
    } 
    public E removeFirst() {
        if (isEmpty()) return null;
        Node<E> head = tail.getNext();      //Create the head element so you could use it for comparison in the next logical statment!!
        if (head == tail) tail = null;      //Kill the list because there probaly was 1 item in there anyway!
        else tail.setNext(head.getNext());      //Burh, this is just so brillant (This is the meat of this function!!)
        size--;
        return head.getElement();
    }
    public void rotate() {
        if (tail != null) {       //There would be no need to run this func if 1. the list is empty 2. if there is only one element in the list!!
            tail = tail.getNext();       //Here, the old head becomes the new tail !!
        }
    }
}
