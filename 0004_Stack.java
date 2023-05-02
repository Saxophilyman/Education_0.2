import java.util.*;

public class Stack<T> {
    private T pop;
    private int count;
    private ArrayList<T> arrList;

    public Stack() {
        // инициализация внутреннего хранилища стека
        arrList = new ArrayList<>();
    }

    public int size() {
        return count;
    }

    public T pop() {
        if (count == 0) {
            return null;  // если стек пустой
        }
        count--;
        pop = arrList.get(count);
        arrList.remove(count);

        return pop;
    }

    public void push(T val) {
        arrList.add(val);
        count++;
    }

    public T peek() {
        if (count == 0) {
            return null;  // если стек пустой
        }
        return arrList.get(count - 1);
    }
}
