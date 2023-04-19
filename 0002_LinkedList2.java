import java.util.*;

public class LinkedList2 {
    public Node head;
    public Node tail;

    private int size;

    public LinkedList2() {
        head = null;
        tail = null;
        size = 0;
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
        size++;
    }

    public Node find(int _value) {
        if (head == null || tail == null)
            return null;

        if (head.value == _value) {
            return head;
        }

        Node left = head.next;
        while (left != null) {
            if (left.value == _value)
                return left;

            left = left.next;
        }

        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();

        if (head == null || tail == null)
            return nodes;

        Node left = head;

        while (left != null) {
            if (_value == left.value)
                nodes.add(left);
            left = left.next;
        }

        return nodes;
    }

    public boolean remove(int _value) {
        if (head == null || tail == null) {
            return false;
        }

        if (head == tail) {
            if (_value == head.value) {
                clear();
                return true;
            }
            return false;
        }

        Node left = head;

        while (left != null) {
            if (_value == left.value) {
                if (left.prev != null) {
                    left.prev.next = left.next;
                } else {
                    head = left.next;
                    if (head != null)
                        head.prev = null;
                }
                if (left.next != null) {
                    left.next.prev = left.prev;
                } else {
                    tail = left.prev;
                }

                size--;
                return true;
            }

            left = left.next;
        }
        return false;
    }

    public void removeAll(int _value) {
        if (head == null || tail == null) {
            return;
        }

        if (head == tail) {
            if (_value == head.value)
                clear();
            return;
        }

        Node left = head;

        while (left != null) {
            if (_value == left.value) {
                if (left.prev != null) {
                    left.prev.next = left.next;
                } else {
                    head = left.next;
                    if (head != null) {
                        head.prev = null;
                    }
                }
                if (left.next != null) {
                    left.next.prev = left.prev;
                } else {
                    tail = left.prev;
                }

                size--;
            }

            left = left.next;
        }
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int count() {
        return size;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        // здесь будет ваш код вставки узла после заданного узла

        // если _nodeAfter = null
        // добавьте новый элемент первым в списке
        if (_nodeAfter == null && count() == 0) {
            this.head = _nodeToInsert;
            this.tail = _nodeToInsert;
            size++;
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
            size++;
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
        size++;
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
