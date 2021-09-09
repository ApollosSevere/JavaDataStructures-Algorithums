package Lists.ArrayLists;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Lists.ArrayLists.Interfaces.List;

public class ArrayList<E> implements List<E> {

    // Instance variables
    public static final int CAPACITY = 16;
    private E[] data;
    private int size = 0;

    // Constructors
    public ArrayList() {
        this(CAPACITY);
    } // Very interesting how this works!

    public ArrayList(int capacity) {
        data = (E[]) new Object[capacity];
    }

    /* ------------ Public Methods ------------- */
    // ADT's Utils
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Advanded Util Middleware!!
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n)
            throw new IndexOutOfBoundsException("Illeaga index: " + i);
    }

    /* ------------ Common Array Methods ------------ */
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    public E set(int i, E element) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E item = data[i];
        data[i] = element;
        return item;
    }

    /* ------------ Complex Array Methods ------------ */
    // Implementing essence of ADT !
    public void add(int i, E e) throws IndexOutOfBoundsException, IllegalStateException {
        checkIndex(i, size + 1);
        if (size == data.length) {
            resize(2 * data.length);
            throw new IllegalStateException("Array is full");
        }
        // Main thing you have to understand!
        for (int k = size - 1; k >= i; i--) {
            data[k + 1] = data[k];
        }
        data[i] = e;
        size++;

    }

    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E discard = data[i];
        for (int k = i; k < size - 1; i++) {
            data[k] = data[k + 1];
        }
        /*
         * Important, at this point, the last element still has its original value we
         * want to make sure that this get deleted by nulling it boi !!x
         */
        data[size - 1] = null;
        size--;
        return discard;
    }

    /* ------------ Implementing Dynamic Array ------------ */
    public void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity]; // Initaize the new array with the capcitcy the user needs
        for (int i = 0; i < size; i++) {
            newData[i] = data[i]; // Notice how there is no .append or .push method?
        }
        data = newData;
    }

    /*
     * ----------------- Implementing Nested ArrayIterator Class -----------------
     */

    /*
     * It is important to note that each instance containes an implicit reference to
     * the containing list, allowing it to access the list's members!! --> This is
     * dope (Fullstack Academy)
     */
    private class ArrayIterator implements Iterator<E> {
        private int j = 0; // Index of the next element to report
        private boolean removable = false; // can remove be called at this time?

        public boolean hasNext() {
            return j < size;
        } // size is a field of the outer instance!

        public E next() throws NoSuchElementException {
            if (j == size)
                throw new NoSuchElementException("No next element");
            removable = true; // This element can subsequently be removed
            E result = data[j];
            j++;
            return result; // This returns data[j] + increments it at the same time!
        }

        // Removes the element returned by most recent call to next!
        /*
         * Important: it throws illegalStateException if next has not yet been called +
         * if remove was already called since recent next
         */
        public void remove() throws IllegalStateException {
            if (!removable)
                throw new IllegalStateException("Nothing to remove!");
            ArrayList.this.remove(j - 1);
            j--;
            removable = false; // do not allow remove again until next is called!!
        }
    }

    public Iterator<E> iterator() {
        return new ArrayIterator();
    }
}
