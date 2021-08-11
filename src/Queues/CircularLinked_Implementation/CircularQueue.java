package Queues.CircularLinked_Implementation;

import Queues.Queue;

public interface CircularQueue<E> extends Queue<E> {
    void rotate();
}
