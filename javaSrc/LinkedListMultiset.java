import java.io.PrintStream;

public class LinkedListMultiset<T> extends Multiset<T>
{
	private LinkedListNode<T> head;
	private LinkedListNode<T> tail;
	private int size = 0;
	
	public LinkedListMultiset() {
		head = null;
		tail = null;
		size = 0;
	} // end of LinkedListMultiset()
	
	public void add(T item) {
		LinkedListNode<T> node = new LinkedListNode<T>(item);
		
		if(head == null) {
			head = node;
			tail = node;
		} else {
			if(head.getValue().equals(item)) {
				head.increaseNodeCount();
			} else {
				LinkedListNode<T> newNode = head;
				while(newNode.getNext() != null) {
					if(newNode.getNext().getValue().equals(item)) {
						newNode.getNext().increaseNodeCount();
						size++;
						return;
					}
					newNode = newNode.getNext();
				}
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
	} // end of search()
	
	public void removeOne(T item) {
		LinkedListNode<T> node = head;
		
		if(head == null) {
		
		} else if(head.getValue().equals(item)) {
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
		
		if(head == null) {
		
		} else if(head.getValue().equals(item)) {
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
	
	@SuppressWarnings("hiding")
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
		} // end of class LinkedListNode
	}
} // end of class LinkedListMultiset
