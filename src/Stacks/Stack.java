package Stacks;

public interface Stack<E> {
/* This thing formally explains what our Stack should
*  doing! */
    int size();

    boolean isEmpty();

    void push(E e);

    E top();

    E pop();
}
