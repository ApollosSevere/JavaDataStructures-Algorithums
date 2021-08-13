package Lists.ArrayLists;

import javax.xml.crypto.Data;

import Lists.ArrayLists.Interfaces.List;

public class ArrayList<E> implements List<E> {
    
    // Instance variables
    public static final int CAPACITY = 16;
    private E[] data;
    private int size = 0;

    // Constructors
    public ArrayList() {this(CAPACITY);}    //Very interesting how this works!
    public ArrayList(int capacity) {
        data = (E[]) new Object[capacity];
    }

    
    /* ------------ Public Methods ------------- */ 
    // ADT's Utils
    public int size() {return size;}
    public boolean isEmpty() {return size == 0;}
    // Advanded Util Middleware!!
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException{
        if (i < 0 || i >= n ) 
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
        for (int k = size-1; k >= i; i-- ) {
            data[k + 1] = data[k];
        }
        data[i] = e;
        size++;
    
    }
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E discard = data[i];
        for (int k = i; k < size-1; i++) {
            data[k] = data[k + 1];
        }
        /*Important, at this point, the last element still has its original value
          we want to make sure that this get deleted by nulling it boi !!x*/
        data[size-1] = null;    
        size--;
        return discard;
    }
    

    /* ------------ Implementing Dynamic Array ------------ */ 
    public void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity];   //Initaize the new array with the capcitcy the user needs
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];       //Notice how there is no .append or .push method? 
        }
        data = newData;
    }
}
