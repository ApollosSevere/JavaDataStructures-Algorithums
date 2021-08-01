package LinkedList.Singly;

/* ----------->>>>>  Structure to Immediately Creating a linked list  <<<<<----------- */
public class LinkedListTemplate<E> {

    /* ---------- Nested Node Class ------------ */
    private static class Node<E> {
        private final E element;
        private Node<E> next;
        //        Constructor!
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }
        //         Give me element stored in this particular Node!
        public E getElement() {return element;}
        //         Tell me whats the next object this Node reference!
        public Node<E> getNext() {return next;}
        //         Allow me to set the next Object this Node reference
        public void setNext (Node<E> n) {next = n;}
    }

    /* ---------- Instance Variables for Linked Object ------------ */
//    *Important, always have a reference to the Head, Tail, and size of Linked list!!
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;
    //    Constructor() Initialized with nothing Boi !
    public LinkedListTemplate() {}


    /* ---------- Access Methods! ------------ */
    public E getHead() {return head.getElement();}
    public E getTail() {return tail.getElement();}
    public int getSize() {return size;}
    public boolean isEmpty() {return size == 0;}


    /* ---------- Update Methods! (Main Logic) ------------ */
    public void add(E e) {
        head = new Node<E>(e, head);
        if (size == 0) {tail = head;}
        size++;
    }
    public void addLast(E e) {
        Node<E> newest = new Node<E>(e, null);
//        Logically safety measure here;
        if (isEmpty()) {head = newest;}
        tail.setNext(newest);
        tail = newest;
        size++;
    }
    //    Last but not least (Needed to advance other Data Structures)
    public E removeFirst() {
        if (isEmpty()) return null;
        E oldMan = head.getElement();
        head = head.getNext();
        size--;
//        Logics Here
        if (size == 0) tail = null;
        return oldMan;
    }


    /* ---------- Now can add Fancy Stuff!------------ */
//    Link Traversals O(n)
//    Reverse Linked list
//    yotta yotta yotta ...
    public String show() {
        String result = "";
        Node<E> current = head;
        while (current.next != null) {
            System.out.println("Over Running?");
            result += current.getElement();
            result += " --> ";
            current = current.getNext();
        }
        result += "null";
        return result;
    }

}
