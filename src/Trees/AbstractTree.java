package Trees;

import Lists.PositionalList.Interfaces.Position;

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

    /*
     * This is a crazy dope Algorithum bruh, we have to break this down with the
     * white bored
     */
    // This gives you the overall hieght of the Tree
    public int hieght(Position<E> p) {
        int h = 0;
        for (Position<E> c : children(p))
            h = Math.max(h, 1 + hieght(c));
        return h;
    }
}
