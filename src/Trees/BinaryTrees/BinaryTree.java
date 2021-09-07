package Trees.BinaryTrees;

import Lists.PositionalList.Interfaces.Position;
import Trees.Tree;

public interface BinaryTree<E> extends Tree<E> {
    Position<E> left(Position<E> p) throws IllegalArgumentException;

    Position<E> right(Position<E> p) throws IllegalArgumentException;

    Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}
