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
        if (_nodeAfter == null && _nodeToInsert == null) {
            clear();
            return;
        }

        if (_nodeAfter == null) {
            if (head != null) {
                final Node temp = head.next;
                head = _nodeToInsert;
                head.next = temp;
                size++;
            } else {
                addInTail(_nodeToInsert);
            }
            return;
        }

        if (_nodeAfter == tail && _nodeToInsert != null) {
            addInTail(_nodeToInsert);
            return;
        }


        final Node next = _nodeAfter.next;
        if (next != null) {
            _nodeAfter.next.prev = _nodeToInsert;
            _nodeAfter.next = _nodeToInsert;

            if (_nodeToInsert != null) {
                _nodeToInsert.prev = _nodeAfter;
                _nodeToInsert.next = next;
                size++;
            } else {
                tail = _nodeAfter;
                tail.next = null;

                int newSize = 0;
                Node node = head;

                while (node != null) {
                    newSize++;
                    node = node.next;
                }

                size = newSize;
            }
        }
    }

    public void insertAsFirst(Node _nodeToInsert) {
        insertAfter(null, _nodeToInsert);
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
