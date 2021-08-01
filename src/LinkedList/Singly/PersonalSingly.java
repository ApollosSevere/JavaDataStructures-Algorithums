package LinkedList.Singly;

/* Objective: Finish make the linked list --> Then make a Stack with it!! */

public class PersonalSingly<E> {
    private static class Node<E> {
        private E element;
        private Node<E> next;
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }
        public E getElement() {return element;}
        public Node<E> getNext() {return next;}
        public void setNext(Node<E> n) {next = n;}
    }

//    Now, the logical instance variables you need is a reference to the Head
//    the tail, and the size (which will save a lot of computing time!)
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;
    public PersonalSingly() {}   //Start off as an empty data structure!


    //Access Methods:
    public int getSize() {return size;}
    public boolean isEmpty() {return size == 0;}
    public E first() {
        if (isEmpty()) return null;
        return tail.getElement();
    }
    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }


//   Update Methods
/* This right here is one of the most important methods!! */
    public void addFirst(E e) {
        //First create the new Head, then set its next to the current head
        head = new Node<E>(e, head);
        //Now, check special case if that's the only one
        if (size == 0) tail = head;
        size++;

    }
}










