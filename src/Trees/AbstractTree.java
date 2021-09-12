package Trees;

import Lists.PositionalList.Interfaces.Position;
import Queues.Queue;
import Queues.SinglyLinked_Implementation.SinglyLinkedQueue;

import java.util.ArrayList;
import java.util.Iterator;
// import Lists.ArrayLists.Interfaces.List;
import java.util.List;

public abstract class AbstractTree<E> implements Tree<E> {
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }

    public boolean isExternal(Position<E> p) {
        return numChildren(p) > 0;
    }

    public boolean isRoot(Position<E> p) {
        return p == root();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // *This measures from the ground up (from spesific Node!)!!
    public int depth(Position<E> p) {
        if (isRoot(p))
            return 0;
        else
            return 1 + depth(parent(p));
    }

    // This gives you the overall hieght of the Tree
    public int hieght(Position<E> p) {
        int h = 0;
        for (Position<E> c : children(p))
            h = Math.max(h, 1 + hieght(c));
        return h;
    }



     /* --------------------- Nested ElemetIterator class --------------------- */

    //  This class adapts the iteration produce by psitions() to return elements
    private class ElemetIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();   //Remember that the value returns from positions() is an ArrayList which has an iterator method my boi !!
        public boolean hasNext() {return posIterator.hasNext();}
        public E next() {return posIterator.next().getElement();}
        public void remove() {posIterator.remove();}
    }
    public Iterator<E> iterator() {return new ElemetIterator();}

    public Iterable<Position<E>> positions() {return preorder();}



    /* --------------------- Dopeness: Tree Traversal Algorithums --------------------- */

    //        PRE-ORDER TRAVERSAL
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for (Position<E> c : children(p))
            preorderSubtree(c, snapshot);
    }
    // Returns an iterable collection of positions of the tree, reported in preorder
    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            preorderSubtree(root(), snapshot);
        return snapshot;
    }


     //        POST-ORDER TRAVERSAL
     private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
         for (Position<E> c : children(p)) 
             postorderSubtree(c, snapshot);
         snapshot.add(p);
     }
     public Iterable<Position<E>> postorder() {
         List<Position<E>> snapshot = new ArrayList<>(2);
         if (!isEmpty()) 
            postorderSubtree(root(), snapshot);
        return snapshot;
     }


    //  Breadth-First Search
    public Iterable<Position<E>> breadthfirst() {
        // Always start off with the prized thing your are returning!!
        List<Position<E>> snapshot = new ArrayList<>();
        // If there are no elements in the tree, you will just recieve an empty list boi !!
        if (!isEmpty()) {
            Queue<Position<E>> fringe = new SinglyLinkedQueue<>();
            fringe.enqueue(root());

            while (!fringe.isEmpty()) {
                Position<E> p = fringe.dequeue();
                // Here, not only are you adding the root to the snapshot, but
                // you also have a mechnisum to adding the children in there at the
                // same time to keep things going: --> And thats really the beauty in using
                // the queue!!
                snapshot.add(p);
                for (Position<E> c : children(p))
                    fringe.enqueue(c);
            }
        } 
        return snapshot;
    }



 /* --------------------- Tree Traversal Practical Use-Cases --------------------- */
    private static String spaces(int n) {
        String result = "";
        for (int i = 0; i < n; i++)
            result += " ";
        return result;
    }
    public static <E> void printPreorderIndent(Tree<E> T, Position<E> p, int d) {
        System.out.println(spaces(2 * d) + p.getElement());
        for (Position<E> c : T.children(p))
            printPreorderIndent(T, c, d + 1);
    }
}
