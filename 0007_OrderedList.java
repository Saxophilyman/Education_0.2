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
        Node<T> node = this.head;
        while (node != null) {
            if (!node.value.equals(val)) {
                node = node.next;
                continue;
            }
            if (node.next != null && node.prev != null) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                return;
            }
            if (node.prev == null && node.next == null) {
                this.head = null;
                this.tail = null;
                return;
            }
            if (node.prev == null) {
                node.next.prev = null;
                this.head = node.next;
            }
            if (node.next == null) {
                node.prev.next = null;
                this.tail = node.prev;
            }
            return;
        }
        return;
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
