import java.util.*;

public class LinkedList2
{
	public Node head;
	public Node tail;

	public LinkedList2()
	{
		head = null;
		tail = null;
	}

	public void addInTail(Node _item)
	{
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

	public Node find(int _value)
	{
		Node node = this.head;

		while (node != null) {
			 if (node.value == _value) return node;
			 node = node.next;
		}

		return null;
	}

	public ArrayList<Node> findAll(int _value)
	{
		ArrayList<Node> nodes = new ArrayList<Node>();

		Node node = this.head;

		while (node != null) {
			if (node.value == _value) nodes.add(node);
			node = node.next;
		}
		return nodes;
	}

	public boolean remove(int _value)
	{
		Node node = this.head;
		
		while (node != null) {
			
			if (node.value != _value) {
				node = node.next;
				continue;
			}
			
			if (node.next != null && node.prev != null)  {
				node.prev.next = node.next;
				node.next.prev = node.prev;
				node = node.next;
				return true;
			}
			
			if (node.prev == null && node.next == null) {
				this.head = null;
				this.tail = null;
				node = node.next;
				return true;
			}
			
			if (node.prev == null) {
				node.next.prev = null;
				this.head = node.next;
			}
			
			if (node.next == null) {
				node.prev.next = null;
				this.tail = node.prev;
			}
			
			return true;
			
		}
		
		return false; 
	}

	public void removeAll(int _value)
	{

		Node node = this.head;

		while (node != null) {

			if (node.value != _value) {
			 	node = node.next;
			 	continue;
			}

			if (node.prev != null && node.next != null) {
			 	node.prev.next = node.next;
				node.next.prev = node.prev;
			 	node = node.next;
			 	continue;
			}

			if (node.prev == null && node.next == null) {
				this.head = null;
				this.tail = null;
				break;
			}

			if (node.prev == null) {
				node.next.prev = null;
				this.head = node.next;
			}

			if (node.next == null) {
				node.prev.next = null;
				this.tail = node.prev;
			}
			node = node.next;
		}


	}

	public void clear()
	{
		this.head = null;
		this.tail = null;
	}

	public int count()
	{
		int count = 0;
		
		Node node = this.head;
		
		while (node != null) {
			++count;
			node = node.next;
		}
		 
		return count; 
	}

	public void insertAfter(Node _nodeAfter, Node _nodeToInsert)
	{
		
		_nodeToInsert.prev = _nodeAfter;
		
		if (_nodeAfter == null && this.tail == null) {
			_nodeToInsert.next =  this.head;
			this.head = _nodeToInsert;
			this.tail = _nodeToInsert;
			return;
		}
		
		if (_nodeAfter == null && this.tail != null) {
			_nodeToInsert.next =  this.head;
			this.head = _nodeToInsert;
			return;
		}
		
		if (_nodeAfter.next == null) {
			_nodeAfter.next = _nodeToInsert;
			this.tail = _nodeToInsert;
			return;
		}
		
		_nodeToInsert.next  = _nodeAfter.next;
		_nodeToInsert.next.prev = _nodeToInsert;
		_nodeAfter.next = _nodeToInsert;

		
	}
}

class Node
{
     public int value;
     public Node next;
     public Node prev;

     public Node(int _value) 
     { 
       value = _value; 
       next = null;
       prev = null;
     }
}
