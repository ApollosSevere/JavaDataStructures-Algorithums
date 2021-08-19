package Lists.PositionalList;

import java.util.Iterator;

import LinkedList.Singly.LinkedListTemplate;
import Lists.ArrayLists.ArrayList;

public class driverPractice {
    public static void main(String[] args) {
        // LinkedPositionalList<int[]> list = new LinkedPositionalList<>();
        LinkedListTemplate<int[]> list = new LinkedListTemplate<int[]>();
        int[] three = new int[3];
        int position = 0;
        int j = 0;

        for (int i = 0; i < 15; i++) {

            if (i % 3 == 0) {
                // arr[position] = stater;
                System.out.println("Shipping ... ");
                j = 0;
                System.out.println("Position: " + position + " Array: " + three[0] + " " + three[1] + " " + three[2]);
                // arr[position] = three;
                for (int z = 0; z < three.length; z++) {
                    System.out.println(three[z]);
                }
                list.add(three);
                position++;
            }
            System.out.println("index for three: " + j);
            System.out.println("Packeting ... ");

            three[j] = i + 1;
            // System.out.println(three[0] + " " + three[1] + " " + three[2]);
            j++;

        }

        // Setting the ListIterator at a specified position
        // Iterator<int[]> list_Iter = list.iterator();

        // Iterating through the created list from the position
        // System.out.println("The list is as follows:");
        // while (list_Iter.hasNext()) {
        // int[] value = list_Iter.next();
        // System.out.println("[ ");
        // for (int i = 0; i < value.length; i++) {
        // System.out.println(value[i]);
        // }
        // System.out.println(" ]");
        // }

        int[] head = list.getHead();
        while (head != null) {
            int[] value = head;
            for (int i = 0; i < value.length; i++) {
                System.out.println(value[i]);
            }

        }
    }
}

// int[] data = {1, 2, 3};
// arr.add(0, data);
