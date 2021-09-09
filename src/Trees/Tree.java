package Trees;

import java.util.Iterator;

import Lists.PositionalList.Interfaces.Position;

/* Whlie we are doing this, just try to understand the essence of why we are using poistions instead of the actual values! */ 

/* Come on man! We have to understand whats going on here throughly!! */ 

public interface Tree<E> {
    Position<E> root(); 
    Position<E> parent(Position<E> p) throws IllegalArgumentException;
    Iterable<Position<E>> children(Position<E> P) throws IllegalArgumentException;

    int numChildren(Position<E> p) throws IllegalArgumentException;
    boolean isInternal(Position<E> p) throws IllegalArgumentException;
    boolean isExternal(Position<E> p) throws IllegalArgumentException;
    boolean isRoot(Position<E> p) throws IllegalArgumentException;

    int size();
    boolean isEmpty();
    // Iterator<E> iterator();
    // Iterable<Position<E>> positions();
}

// public interface Tree<E> extends Iterable<E> {
//     Position<E> root(); 
//     Position<E> parent(Position<E> p) throws IllegalArgumentException;
//     Iterable<Position<E>> children(Position<E> P) throws IllegalArgumentException;

//     int numChildren(Position<E> p) throws IllegalArgumentException;
//     boolean isInternal(Position<E> p) throws IllegalArgumentException;
//     boolean isExternal(Position<E> p) throws IllegalArgumentException;
//     boolean isRoot(Position<E> p) throws IllegalArgumentException;

//     int size();
//     boolean isEmpty();
//     Iterator<E> iterator();
//     Iterable<Position<E>> positions();
// }
