package LinkedList.Doubly;


public class DoublyLinkedList<E> {

    /* ---------------- Nested Node Class ------------------ */
    private static class Node<E>{
        private E element;
        private Node<E> prev;
        private Node<E> next;
        
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() {return element;}
        public Node<E> getPrev() {return prev;}
        public Node<E> getNext() {return next;}
        public void setPrev(Node<E> p) {prev = p;}
        public void setNext(Node<E> n) {next = n;}
    }


    /* ---------------- Instance Variables ------------------ */
    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;
    // Constructs a New empty List (Start off configuration)
    public DoublyLinkedList() {
        header = new Node<>(null, null, null);       //Ok this makes sense because you have to wait for trailer to be created!!
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }
    

    /* ---------------- Access Methods! ------------------ */
    public int size() {return size;}
    public boolean isEmpty() {return size == 0;}
    public E frist() {
        if (isEmpty()) {return null;}
        return header.getNext().getElement();
    }
    public E last() {
        if (isEmpty()) {return null;}
        return trailer.getPrev().getElement();
    }


    /* ---------------- Update Methods! ------------------ */
    // Public Update Methods
    public void addFirst(E e) {
        addBetween(e, header, header.getNext());
    }
    public void addLast(E e) {
        addBetween(e, trailer.getPrev(), trailer);
    }
    public E removeFirst() {
        if(isEmpty()) return null;
        return remove(header.getNext());
    }
    public E removeLast() {
        if(isEmpty()) return null;
        return remove(trailer.getPrev());
    }

    // Private Update Methods
    private void addBetween(E e, Node<E> predecssor, Node<E> successor){
        // create and link a new Node
        Node<E> newest = new Node<>(e, predecssor, successor);
        predecssor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }
    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }
}
