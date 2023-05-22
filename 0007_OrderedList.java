import java.util.*;

public class OrderedList<T> {
    public Node<T> head, tail;
    private boolean _ascending;
    private int count = 0;

    public OrderedList(boolean asc) {
        head = null;
        tail = null;
        _ascending = asc;
    }

    public int compare(T v1, T v2) {
        if (v1 instanceof String) {
            ((String) v1).trim();
            ((String) v2).trim();
            return (((String) v1).trim()).compareTo(((String) v2).trim());
        }
        if (v1 instanceof Integer) {
            return ((Integer) v1).compareTo((Integer) v2);
        }
        return 0000000;
        // -1 если v1 < v2
        // 0 если v1 == v2
        // +1 если v1 > v2
    }

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (count() == 0) {
            this.head = node;
            this.tail = node;
            count++;
            return;
        }
        Node<T> takeNode = this.head;
        for (int i = 1; i <= count; i++) {
            if (i == 1 && _ascending && (compare(node.value, takeNode.value) == -1 || compare(node.value, takeNode.value) == 0)) {
                this.head = node;
                node.next = takeNode;
                takeNode.prev = node;
                count++;
                return;
            }
            if (i == count && !_ascending && (compare(node.value, takeNode.value) == 1 || compare(node.value, takeNode.value) == 0)) {
                this.tail = node;
                node.prev = takeNode;
                takeNode.next = node;
                count++;
                return;
            }
            if (_ascending && (compare(node.value, takeNode.value) == -1 || compare(node.value, takeNode.value) == 0)) {
                takeNode.prev.next = node;
                node.next = takeNode;
                node.prev = takeNode.prev;
                takeNode.prev = node;
                count++;
                return;
            }
            if ((_ascending && compare(node.value, takeNode.value) == 1) || (!_ascending && compare(node.value, takeNode.value) == -1)) {
                if (takeNode.next == null) {
                    break;
                }
                takeNode = takeNode.next;
            }
            if (!_ascending && (compare(node.value, takeNode.value) == 1 || compare(node.value, takeNode.value) == 0)) {
                takeNode.next.prev = node;
                node.prev = takeNode;
                node.next = takeNode.next;
                takeNode.next = node;
                count++;
                return;
            }
        }
        if (_ascending) {
            this.tail = node;
            node.prev = takeNode;
            takeNode.next = node;
        } else {
            this.head = node;
            node.next = takeNode;
            takeNode.prev = node;
        }
        count++;
        return;
    }

    public Node<T> find(T val) {
        if (head == null)
            return null;

        if (head.value.equals(val))
            return head;
        if (tail.value.equals(val))
            return tail;

        Node node = null;
        if (_ascending)
            node = tail.prev;
        if (!_ascending)
            node = head.next;

        while (node != null) {
            int compareRes = compare(val, (T) node.value);
            if (compareRes == 0)
                return node;
            if (compareRes == 1)
                break;
            if (_ascending)
                node = node.prev;
            if (!_ascending)
                node = node.next;
        }
        return null;
    }

    public void delete(T val) {
        if (head == null)
            return;

        if (head.value.equals(val)) {
            deleteFromHead();
            return;
        }

        if (tail.value.equals(val)) {
            deleteFromTail();
            return;
        }

        Node left = head.next;
        Node right = tail.prev;

        for (int i = 0; i < size / 2; i++) {
            if (left.value.equals(val)) {
                left.prev.next = left.next;
                left.next.prev = left.prev;

                size--;
                return;
            }
            if (right.value.equals(val)) {
                right.prev.next = right.next;
                right.next.prev = right.prev;

                right.next = null;
                right.prev = null;

                size--;

                return;
            }

            left = left.next;
            right = right.prev;
        }
    }

    private void deleteFromHead() {
        if (size == 1) {
            clear(_ascending);
            return;
        }

        head = head.next;
        if (head != null)
            head.prev = null;
        size--;
    }

    private void deleteFromTail() {
        if (size == 1) {
            clear(_ascending);
            return;
        }
        tail = tail.prev;
        tail.next = null;
        size--;
    }
    
    

    public void clear(boolean asc) {
        _ascending = asc;
        head = null;
        tail = null;
        count = 0;
    }

    public int count() {
        return count;
    }

    ArrayList<Node<T>> getAll() {
        ArrayList<Node<T>> r = new ArrayList<Node<T>>();
        Node<T> node = head;
        while (node != null) {
            r.add(node);
            node = node.next;
        }
        return r;
    }
}

class Node<T> {
    public T value;
    public Node<T> next;
    public Node prev;

    public Node(T _value) {
        value = _value;
        next = null;
        prev = null;
    }
}
