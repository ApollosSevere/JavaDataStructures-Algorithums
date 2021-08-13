package Lists.PositionalList.Interfaces;

public interface PositionalList<E> {
    // Return the number of elements in the list
    int size();

    // Test whether the list is empty
    boolean isEmpty();

    // returns the first Position in the list (or null, if empty)
    Position<E> first();

    // Returns the last Position in the list (or null, if empthy)
    Position<E> last();

    

/*-------------- Important to understand!! -----------------*/ 

    // Returns the Position immediately before Positon p 
    Position<E> before(Position<E> p) throws IllegalArgumentException;

    // Return the Position immediately gter Poition p
    Position<E> after(Position<E> p) throws IllegalArgumentException;



    // Inserts element e at the front of the list and returns its new Postition
    Position<E> addFirst(E e);

    // Inserts element e at the back of the list and returns its new Position
    Position<E> addLast(E e);



    // Inserts element e immediately before Position p and returns its new Position
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;

    // Inserts element e immediately after Position p and returns its new Position
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;



    // Replaces the element stroed at Position p and returns the replace element
    E set(Position<E> p, E e) throws IllegalArgumentException;

    // Removes the element stored at Position p and returns it (invaldating p!)
    E remove(Position<E> p) throws IllegalArgumentException;
}
