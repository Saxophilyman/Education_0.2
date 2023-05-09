import java.util.*;

public class Deque<T> {
    private LinkedList<T> linkedList;
    private int size;


    public Deque() {
        linkedList = new LinkedList<>();
    }

    public void addFront(T item) {
        linkedList.addLast(item);
        size++;
    }

    public void addTail(T item) {
        linkedList.addFirst(item);
        size++;
    }

    public T removeFront() {
        size--;
        return linkedList.removeLast();
    }

    public T removeTail() {
        size--;
        return linkedList.removeFirst();
    }

    public int size() {
        return size;
    }
}
