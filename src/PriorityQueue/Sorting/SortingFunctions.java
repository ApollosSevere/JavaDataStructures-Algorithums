package PriorityQueue.Sorting;

import Lists.PositionalList.Interfaces.PositionalList;
import PriorityQueue.Interfaces.PriorityQueue;

public class SortingFunctions {
    
    public static <E> void pqSort(PositionalList<E> S, PriorityQueue<E,?> P) {
        int n = S.size();

        for (int j = 0; j < n; j++) {
            E element = S.remove(S.first());
            P.insert(element, null);
        }

        for (int i = 0; i < n; i++) {
            E element = P.removeMin().getKey();
            S.addLast(element);
        }
    }

}
