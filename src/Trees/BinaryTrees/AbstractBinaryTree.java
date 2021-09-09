package Trees.BinaryTrees;

import java.util.ArrayList;
import Lists.PositionalList.Interfaces.Position;
import Trees.AbstractTree;
import java.util.List;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    public Position<E> sibling(Position<E> p) {
        // Returns the Position of p's sibling (Remember, we are working with a binary
        // tree here!!)
        Position<E> parent = parent(p);
        if (parent == null)
            return null;
        if (p == left(parent))
            return right(parent);
        else
            return left(parent);
    }

    // Returns the number of children of Position p (Remember the count can only be
    // at most 2, becasue this is a binary search tree!)
    public int numChildren(Position<E> p) {
        int count = 0;
        if (left(p) != null)
            count++;
        if (right(p) != null)
            count++;
        return count;
    }

    public Iterable<Position<E>> children(Position<E> p) {
        List<Position<E>> snapshot = new ArrayList<>(2);
        if (left(p) != null)
            snapshot.add(left(p));
        if (right(p) != null)
            snapshot.add(right(p));
        return snapshot;
    }
}
