package Stacks;

import LinkedList.Singly.LinkedListTemplate;

public class LinkedStack<E> implements Stack<E> {
    private final LinkedListTemplate<E> list =  new LinkedListTemplate<E>();
    public LinkedStack() {}
    public int size() {return list.getSize();}
    public boolean isEmpty() {return list.isEmpty();}


//    Now, here we implement the main functionality of a Stack!
    public void push(E element) {list.add(element);}
    public E pop() {return list.removeFirst();}


//    Accessor!
    public E top() {return list.getHead();}
    public String show() {return list.show();}

}



