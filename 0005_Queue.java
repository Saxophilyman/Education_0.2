import java.util.*;

public class Queue<T> {
    private LinkedList<T> list;
    private int count;

    public Queue() {
        list = new LinkedList<>();
    }

    public void enqueue(T item) {
        list.addFirst(item);
        count++;
    }

    public T dequeue() {
        if (count == 0) {
            return null; 
        }
        count--;
        return list.pollLast(); 
    }

    public int size() {
        return count; 
    }
}
