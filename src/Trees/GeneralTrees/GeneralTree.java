package Trees.GeneralTrees;

import Lists.PositionalList.Interfaces.Position;
import java.util.*;

// TODO: Make some driver code to test this out!

public class GeneralTree<E>  {
    
    /* ---------------- Nested Node Class ---------------- */
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private List<Position<E>> children;

        public Node(E e) {
            e = element;
            children = new ArrayList<>();
        }

        public E getElement() {return element;}
        public Node<E> getParent() {return parent;}
        public List<Position<E>> getChildren() {return children;}
    }

    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid Position Type!!");
        Node<E> node = (Node<E>) p;  //Important!! Safe Casting
        if (node.getParent() == node) 
            throw new IllegalArgumentException("p is no longer in the tree!");
        return node;
    }

    public Iterable<Position<E>> children(Position<E> p) {
        Node<E> node = validate(p);
        return node.getChildren(); 
    }
}


// 2 Things 
/* 
  * 1. Notice how the imports on others have been directly from java.util
  * 2. You can safe cast if you know what elem it is boi !!
*/ 