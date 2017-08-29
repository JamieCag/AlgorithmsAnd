import java.io.PrintStream;

public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	private LinkedListNode<T> head;
	private LinkedListNode<T> tail;
	private int size = 0;
	
	// constructor for the doubly linked list
	public SortedLinkedListMultiset() {
		head = null;
		tail = null;
		size = 0;
	} // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		// create a new node that takes in input
		LinkedListNode<T> node = new LinkedListNode<T>(item);
		
		// check if the head is null, if it is assign head and tail to node
		if(head == null) {
			head = node;
			tail = node;
		} // check to see if the value of the head compares alphabetically to
		  // to the input (item) and if its a positive number insert here.
		else if(((String)head.getValue()).compareTo((String)item) > 0) {
			LinkedListNode<T> newNode = head;
			node.setNext(newNode);
			newNode.setPrev(node);
			head = node;
		} else {
			// check to see if the head value is the same as the input value.
			// In which case, just add to the node's count.
			if(head.getValue().equals(item)) {
				head.increaseNodeCount();
			} // Otherwise create a new node instance and assign it to the head.
			  // Loop through the list
			else {
				LinkedListNode<T> newNode = head;
				while(newNode.getNext() != null) {
					// if instance already found increase node count
					if(newNode.getNext().getValue().equals(item)) {
						newNode.getNext().increaseNodeCount();
						size++;
						return;
					} // if instance value compares alphabetically to the input then
					  // commence sorted insertion.
					else if(((String)newNode.getNext().getValue()).compareTo((String)item) > 0) {
						if(newNode.getNext() == tail) {
							node.setNext(newNode.getNext());
							node.setPrev(newNode);
							newNode.setNext(node);
							newNode.getNext().setPrev(node);
							tail = newNode.getNext();
						} else {
							node.setPrev(newNode);
							node.setNext(newNode.getNext());
							newNode.setNext(node);
							newNode.getNext().setPrev(node);
						}
						size++;
						return;
					}
					newNode = newNode.getNext();
				}
				// insert into the tail
				newNode.setNext(node);
				node.setPrev(newNode);
				tail = node;
			}
		}
		size++;
	} // end of add()
	
	
	public int search(T item) {
		int sCount = 0;
		LinkedListNode<T> node = head;
		
		while(node != null) {
			if(node.getValue().equals(item)) {
				sCount = node.getCount();
				return sCount;
			}
			node = node.getNext();
		}
		
		return sCount;
	} // end of add()
	
	
	public void removeOne(T item) {
		LinkedListNode<T> node = head;
		
		if(head.getValue().equals(item)) {
			if(head.getCount() > 1) {
				head.reduceNodeCount();
			} else {
				head = head.getNext();
				node.setNext(null);
				node = null;
			}
			size--;
		} else {
			while(node.getNext() != null) {
				if(node.getNext().getValue().equals(item)) {
					if(node.getNext().getCount() > 1) {
						node.getNext().reduceNodeCount();
					} else {
						LinkedListNode<T> temp = node.getNext();
						node.setNext(temp.getNext());
						temp.setNext(null);
					}
					break;
				}
				node = node.getNext();
			}
			size--;
		}
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		LinkedListNode<T> node = head;
		
		if(head.getValue().equals(item)) {
			head = head.getNext();
			node.setNext(null);
			size--;
		} else {
			while(node.getNext() != null) {
				if(node.getNext().getValue().equals(item)) {
					LinkedListNode<T> temp = node.getNext();
					node.setNext(temp.getNext());
					temp.setNext(null);
					break;
				}
				node = node.getNext();
			}
			size--;
		}
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		LinkedListNode<T> node = head;
		while(node != null) {
			out.println(node.getValue() + " | " + node.getCount());
			node = node.getNext();
		}
	} // end of print()
	
	private class LinkedListNode<T> {
		
		private int count;
		private T value;
		private LinkedListNode<T> next;
		private LinkedListNode<T> prev;
		
		public LinkedListNode(T value) {
			this.value = value;
			next = null;
			prev = null;
			this.count = 1;
		}
		
		public void increaseNodeCount() {
			this.count++;
		}
		
		public void reduceNodeCount() {
			this.count--;
		}
		
		public T getValue() {
			return this.value;
		}
		
		public int getCount() {
			return this.count;
		}
		
		public LinkedListNode<T> getNext() {
			return this.next;
		}
		
		public LinkedListNode<T> getPrev() {
			return this.prev;
		}
		
		public void setNext(LinkedListNode<T> next) {
			this.next = next;
		}
		
		public void setPrev(LinkedListNode<T> prev) {
			this.prev = prev;
		} 
	} // end of class LinkedListNode
	
} // end of class SortedLinkedListMultiset