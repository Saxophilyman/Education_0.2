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
		Node<T> newNode = new Node<T>(value);
		if (head == null) {
			head = newNode;
			tail = newNode;
            count++;
			return;
		}

		Node<T> node = head;

		while (node != null) {
			int compared = compare(node.value, newNode.value);

			if (_ascending) {
				switch (compared) {
				case -1: {
					
					if (node.equals(tail)) {
						newNode.prev = node;
						node.next = newNode;
						tail = newNode;
                        count++;
						return;
					}
					node = node.next;

					continue;
				}
				case 0: {
					newNode.next = node;
					newNode.prev = node.prev;
					node.prev = newNode;
					if (newNode.prev == null) {
						head = newNode;
                        count++;
						return;
					}
					newNode.prev.next = newNode;
                    count++;
					return;
				}
				case 1: {					
					newNode.next = node;
					newNode.prev = node.prev;
					node.prev = newNode;
					if (newNode.prev == null) {
						head = newNode;
                        count++;
						return;
					}
					newNode.prev.next = newNode;
                    count++;
					return;					
				}
				}
			}
			switch (compared) {
			case 1: {
				
				if (node.equals(tail)) {
					newNode.prev = node;
					node.next = newNode;
					tail = newNode;
                    count++;
					return;
				}
				node = node.next;

				continue;
			}
			case 0: {
				newNode.next = node;
				newNode.prev = node.prev;
				node.prev = newNode;
				if (newNode.prev == null) {
					head = newNode;
                    count++;
					return;
				}
				newNode.prev.next = newNode;
                count++;
				return;
			}
			case -1: {					
				newNode.next = node;
				newNode.prev = node.prev;
				node.prev = newNode;
				if (newNode.prev == null) {
					head = newNode;
                    count++;
					return;
				}
				newNode.prev.next = newNode;
                count++;
				return;
			}
			}
			node = node.next;
		}
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

        for (int i = 0; i < count / 2; i++) {
            if (left.value.equals(val)) {
                left.prev.next = left.next;
                left.next.prev = left.prev;

                count--;
                return;
            }
            if (right.value.equals(val)) {
                right.prev.next = right.next;
                right.next.prev = right.prev;

                right.next = null;
                right.prev = null;

                count--;

                return;
            }

            left = left.next;
            right = right.prev;
        }
    }

    private void deleteFromHead() {
        if (count == 1) {
            clear(_ascending);
            return;
        }

        head = head.next;
        if (head != null)
            head.prev = null;
        count--;
    }

    private void deleteFromTail() {
        if (count == 1) {
            clear(_ascending);
            return;
        }
        tail = tail.prev;
        tail.next = null;
        count--;
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
