import java.util.*;

public class Stack<T> {
    private LinkedList<T> linkedList;

    public Stack() {
        linkedList = new LinkedList<>();
    }

    public int size() {
        return linkedList.size();
    }

    public T pop() {
        return linkedList.pollFirst();
    }

    public void push(T val) {
        linkedList.addFirst(val);
    }

    public T peek() {
        return linkedList.peekFirst(); 
    }
}
