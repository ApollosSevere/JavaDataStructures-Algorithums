package Trees.BinaryTrees.LinkedBinaryTree;

import java.util.ArrayList;
import java.util.Iterator;

import Lists.PositionalList.Interfaces.Position;
import Trees.BinaryTrees.AbstractBinaryTree;

// This is a Concrete implementation of a binary tree using a node-based, linked structure
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    /* ---------------- Nested Node Class ---------------- */
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        // Constructor: (constructs a node with the given element and neighbors)
        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }

        // Accessor Methods
        public E getElement() {return element;}
        public Node<E> getParent() {return parent;}
        public Node<E> getLeft() {return left;}
        public Node<E> getRight() {return right;}

        // Update Methods
        public void setElement(E e) {element = e;}
        public void setParent(Node<E> parentNode) { parent = parentNode;}
        public void setLeft(Node<E> leftChild) { left = leftChild;}
        public void setRight(Node<E> rightChild) {right = rightChild;}
    }


    /* ---------------- Factory Function to create Node ---------------- */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent , left, right);
    }



    /* ---------------- LinkedBinaryTree instance variables ---------------- */
    protected Node<E> root = null;
    private int size = 0;



    /* ---------------- Constructor ---------------- */
    public LinkedBinaryTree() {}



    /* ---------------- None-public Utility! ---------------- */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p;
        if (node.getParent() == node)
            throw new IllegalArgumentException("p isno longer in the tree");
        return node;
    }

    /* ---------------- Accessor Methods ---------------- */
    public int size() {return size;}

    public Position<E> root() {return root;}

    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getParent();
    }

    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getLeft();
    }
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return node.getRight();
    }



    /* ---------------- Update Methods ---------------- */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null,  null);
        size = 1;
        return root;
    }

    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null) 
            throw new IllegalArgumentException("p already has a left child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null) 
            throw new IllegalArgumentException("p already has a left child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }
    
    public E set(Position<E> p, E newValue) {   //Changed from set to replace
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(newValue);
        return temp;
    }


     /* ---------------- Advanced Update Methods Boi !!! ---------------- */
    public void attacth(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
    Node<E> node = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf!");
        size += t1.size() + t2.size();

        if (!t1.isEmpty()) {
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()) {
            t2.root.setParent(node);
            node.setLeft(t2.root);
            t2.root = null;
            t2.size = 0;
        }

    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2) 
            throw new IllegalArgumentException("p has two children boi, handle that with a diff type function my g");
        
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());

        // Top level Secured
        if (child != null) {
            child.setParent(node.getParent());
        }

        // Edge Case
        if (node == root) 
            root = child;
        else {
            // Mapping Parent to child where ever the discconnet was
            Node<E> parent = node.getParent();
            if (node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }

        // Finishing up (helping with garbade collection)
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(null);
        return temp;
    }

   
}
                                                    
