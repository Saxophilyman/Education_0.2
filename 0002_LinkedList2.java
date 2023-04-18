import java.util.*;

public class LinkedList2 {
    public Node head;
    public Node tail;

    public LinkedList2() {
        head = null;
        tail = null;
    }

    public void addInTail(Node _item) {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
    }

    public Node find(int _value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == _value)
                return node;
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = this.head;
        while (node != null) {
            if (node.value == _value) {
                nodes.add(node);
            }
            node = node.next;
        }
        return nodes;
    }

    public boolean remove(int _value) {
        Node node = this.head;
        if (node != null && node.value == _value && count() == 1) {
            this.head = null;
            this.tail = null;
            return true;
        }
        while (node != null) {
            if (node.value == _value && node.prev == null) {
                this.head = node.next;
                this.head.prev = null;
                return true;
            }
            if (node.value == _value && node.next == null) {
                node.prev.next = null;
                this.tail = node.prev;
                return true;
            }
            if (node.value == _value) {
                node.next.prev = node.prev;
                node.prev.next = node.next;
                if (node.prev.prev == null) {
                    this.head.next = node.next;
                }
                if (node.next.next == null) {
                    tail.prev = node.prev;
                }
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public void removeAll(int _value) {
        int count = count();
        for (int i = 0; i < count; i++) {
            remove(_value);
        }
    }

    public void clear() {
        this.head = null;
        this.tail = null;
    }

    public int count() {
        Node node = this.head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeAfter == null && count() == 0) {
            this.head = _nodeToInsert;
            this.tail = _nodeToInsert;
            return;
        }

        if (_nodeAfter == null) {
            this.head.prev = _nodeToInsert;
            _nodeToInsert.next = this.head;
            this.head = _nodeToInsert;
            if (this.tail == null) {
                this.tail = _nodeToInsert;
            }
            this.head.prev = null;
            return;
        }

        if (this.tail.prev == _nodeAfter) {
            this.tail.prev = _nodeToInsert;
        }
        if (_nodeAfter.next == null) {
            this.tail.prev = this.tail;
            this.tail = _nodeToInsert;
        }
        _nodeToInsert.next = _nodeAfter.next;
        _nodeToInsert.prev = _nodeAfter;
        _nodeAfter.next = _nodeToInsert;
        this.head.prev = null;
    }
}

class Node {
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value) {
        value = _value;
        next = null;
        prev = null;
    }
}
