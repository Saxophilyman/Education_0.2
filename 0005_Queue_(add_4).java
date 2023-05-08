import java.util.*;

public class QueueByTwoStack<T> {
    private Stack<T> stack1;
    private Stack<T> stack2;
    private int count;


    public QueueByTwoStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        // инициализация внутреннего хранилища очереди
    }

    public void enqueue(T item) {
        stack1.push(item);
        count++;
        // вставка в хвост
    }

    public T dequeue() {
        if (count == 0) {
            return null;  // если стек пустой
        }
        count--;
        if (stack2.size() != 0) {
            return stack2.pop();
        }
        while (stack1.size() > 0) {
            stack2.push(stack1.pop());
        }
        // выдача из головы
        return stack2.pop(); // null если очередь пустая
    }

    public int size() {
        return count; // размер очереди
    }
}
