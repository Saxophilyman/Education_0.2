import java.util.*;

public class LinkedList2 {
    public Node head;
    public Node tail;
    public int failCount;
    int size;

    public LinkedList2() {
        head = null;
        tail = null;
        failCount = 0;
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
        Node node = head.next;
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
        while (node != null && failCount < 1001) {
            if (node.value == _value) {
                nodes.add(node);
            }
            node = node.next;
            failCount++;
        }
        failCount = 0;
        return nodes;
    }

    public boolean remove(int _value) {
        if (this.head == null || this.tail == null) {
            return false;
        }
        if (this.head == this.tail) {
            if (_value == this.head.value) {
                clear();
                return true;
            }
            return false;
        }

        Node node = this.head;

        while (node != null) {
            if (_value == node.value) {
                if (node.prev != null) {
                    node.prev.next = node.next;
                } else {
                    this.head = node.next;
                    if (this.head != null)
                        this.head.prev = null;
                }
                if (node.next != null) {
                    node.next.prev = node.prev;
                } else {
                    this.tail = node.prev;
                }
                size--;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public void removeAll(int _value) {
        Node node = this.head;
        Node prev = null;

        if (node != null && count() == 1 && node.value == _value) {
            clear();
            return;
        }
        if (node != null && count() == 1) {
            return;
        }

        while (node != null && failCount < 1001) {
            if (node.value == _value && prev == null) {
                node.next.prev = null;
                this.head = node.next;
                size--;
            } else if (node.value == _value) {
                node.next.prev = prev;
                node.prev.next = node.next;
                size--;
            }

            node = node.next;
            if (node.next == null) {
                if (node.value == _value && count() == 1) {
                    clear();
                    return;
                }
                if (node.value == _value) {
                    node.prev.next = null;
                    this.tail = node.prev;
                    size--;
                }
                return;
            }
            prev = node.prev;
            failCount++;
        }
        failCount = 0;
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public int count() {
       return size;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
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
