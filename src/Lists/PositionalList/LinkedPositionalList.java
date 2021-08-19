package Lists.PositionalList;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Lists.PositionalList.Interfaces.Position;
import Lists.PositionalList.Interfaces.PositionalList;

public class LinkedPositionalList<E> implements PositionalList<E>  {

    /* ------------- Nested Node Cursor ---------------- */ 
    private static class Node<E> implements Position<E>{
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(Node<E> p, E e, Node<E> n) {
            element = e; 
            prev = p;
            next = n;
        }

        public E getElement() throws IllegalStateException {
            if (next == null)      // convention for defunct node (next should always have a value because trailer present!)
                throw new IllegalStateException("Position no longer valid");
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }
        public Node<E> getNext() {
            return next;
        }
        public void setElement(E e) {
            element = e;
        }
        public void setPrev(Node<E> p) {
           prev = p;
        }
        public void setNext(Node<E> n) {
            next = n;
         }
    }



    /* ------------- Instance Variables ---------------- */ 
    private Node<E> header;
    private Node<E> trialer;
    private int size = 0;

    // Construtor for new empty list
    public LinkedPositionalList() {
        header = new Node<E>(null, null, null);
        trialer = new Node<E> (header, null, null);
        header.setNext(trialer);
    }



    /* ------------- Important Private Utilities ------------- */ 
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
        Node<E> node = (Node<E>) p;      // ----> Magic Happenening ??  You have to learn privately what this is about!!
        if (node.getNext() == null) 
            throw new IllegalArgumentException("p is no longer in the list!");
        return node;
    }
    private Position<E> position(Node<E> node) {
        if (node == header || node == trialer) 
            return null;      // Do not expose user to the sentinels!
        return node;
    }

    


    /* ------------- Accessor Methods ------------- */ 
    public int size() {return size;}       // You see how the size variable is protected!
    public boolean isEmpty() {return size == 0;}
    
    public Position<E> first() {
        return position(header.getNext());
    }
    public Position<E> last() {
        return position(trialer.getPrev());
    }

    //Private Utilities *Bulk Important Feature!  // More Advanced Methodologies
    private Position<E> addBetween(Node<E> pred, E e, Node<E> succ) {
        Node<E> newest = new Node<>(pred, e, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }


    public Position<E> addFirst(E e) {
        return addBetween(header, e, header.getNext());
    }
    public Position<E> addLast(E e) {
        return addBetween(trialer.getPrev(), e, trialer);
    }


    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(node.getPrev(), e, node);
    }
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(node, e, node.getNext());
    }
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    } 

 
    // Important Remove Function
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predessor = node.getPrev();
        Node<E> successor = node.getNext();
        predessor.setNext(successor);
        successor.setPrev(predessor);
        size--;
        E answer = node.getElement();
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return answer;
    }





     /* ----------------- Implementing Complicated Iterator Crap! ----------------- */ 
    //  private class PositionIterator implements Iterator<Position<E>> {
    //     private Position<E> cursor = first();    //Position of the first element to report
    //     private Position<E> recent = null;       //Position of the last reported elemtent
        
    //     // Test whether the iterator has a next Object
    //     public boolean hasNext() {return (cursor != null);}
    //     // Returns the next position in the iterator
    //     public Position<E> next() throws NoSuchElementException {
    //         if (cursor == null) throw new NoSuchElementException("Nothing Left!");
    //         recent = cursor;
    //         cursor = after(cursor);
    //         return recent;
    //     }
    //  }

     // ---------------- nested PositionIterator class ----------------
     private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first(); // position of the next element to report
        private Position<E> recent = null; // position of last reported element
        // /∗∗ Tests whether the iterator has a next object. */

        public boolean hasNext() {
            return (cursor != null);
        }

        // /∗∗ Returns the next position in the iterator. ∗/
        public Position<E> next() throws NoSuchElementException {
            if (cursor == null)
                throw new NoSuchElementException("nothing left");
            recent = cursor; // element at this position might later be removed
            cursor = after(cursor);
            return recent;
        }

        // /∗∗ Removes the element returned by most recent call to next. ∗/
        public void remove() throws IllegalStateException {
            if (recent == null)
                throw new IllegalStateException("nothing to remove");
            LinkedPositionalList.this.remove(recent); // remove from outer list
            recent = null; // do not allow remove again until next is called19

        }

    }// ------------ end of nested PositionIterator class ------------

    // ---------------- nested PositionIterable class ----------------
    private class PositionIterable implements Iterable<Position<E>> {
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    } // ------------ end of nested PositionIterable class ------------

    // /∗∗ Returns an iterable representation of the list's positions. ∗/
    public Iterable<Position<E>> positions() {
        return new PositionIterable(); // create a new instance of the inner class
    }

    // ---------------- nested ElementIterator class ----------------
    // /∗ This class adapts the iteration produced bpositions() to return elements.
    // ∗/
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = new PositionIterator();

        public boolean hasNext() {
            return posIterator.hasNext();
        }

        public E next() {
            return posIterator.next().getElement();
        } // return element!

        public void remove() {
            posIterator.remove();
        }
    }

    // /∗∗ Returns an iterator of the elements stored in the list. ∗/
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
}
