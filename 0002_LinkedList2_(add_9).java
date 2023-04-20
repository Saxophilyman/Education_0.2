package linkedlist2;

import java.util.*;

public LinkedList2WithDummyNode() {
        this.dummyHead = new DummyNode();
        this.dummyTail = new DummyNode();
        dummyTail.prev = dummyHead;
    }

    public void addInTail(Node _item) {
        dummyTail.prev.next = _item;
        _item.prev = dummyTail.prev;
        _item.next = dummyTail;
        dummyTail.prev = _item;
    }

    public Node find(int _value) {
        Node node = dummyHead.next;
        while (node != null && node != dummyTail) {
            if (node.value == _value) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        final ArrayList<Node> nodes = new ArrayList<>();
        Node node = dummyHead.next;
        while (node != null && node != dummyTail) {
            if (node.value == _value) {
                nodes.add(node);
            }
            node = node.next;
        }
        return nodes;
    }

    public boolean remove(int _value) {
        Node node = dummyHead.next;
        while (node != null && node != dummyTail) {
            if (node.value == _value) {
                node.next.prev = node.prev;
                node.prev.next = node.next;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public void removeAll(int _value) {
        Node node = dummyHead.next;
        while (node != null && node != dummyTail) {
            if (node.value == _value) {
                node.next.prev = node.prev;
                node.prev.next = node.next;
            }
            node = node.next;
        }
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeAfter == null && count() == 0) {
            addInTail(_nodeToInsert);
            return;
        }

        if (_nodeAfter == null) {
            dummyHead.next.prev = _nodeToInsert;
            _nodeToInsert.next = dummyHead.next;
            dummyHead.next = _nodeToInsert;
            _nodeToInsert.prev = dummyHead;
            return;
        }

        _nodeAfter.next.prev = _nodeToInsert;
        _nodeToInsert.next = _nodeAfter.next;
        _nodeToInsert.prev = _nodeAfter;
        _nodeAfter.next = _nodeToInsert;
    }


    public void clear() {
        dummyHead.next = null;
        dummyTail.prev = null;
    }


    public int count() {
        Node node = dummyHead.next;
        int count = 0;
        while (node != null && node != dummyTail) {
            count++;
            node = node.next;
        }
        return count;
    }
}

public class Node {
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value) {
        value = _value;
        next = null;
        prev = null;
    }
}

public class DummyNode extends Node{
    private static final int zero = 0;

    public DummyNode() {
        super(zero);
    }
}
