import java.io.PrintStream;
import java.util.*;

public class BstMultiset<T> extends Multiset<T>
{
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int EMPTY_TREE = -1;

	protected Node<T> mRoot;
	
	private class Node<T> {
		private T item;
		private int count;
		private Node<T> mLeft, mRight;
		
		public Node(T item) {
			this.item = item;
			mLeft = null;
			mRight = null;
			count = ONE;
		}

		public T getItem() {
			return this.item;
		} //end of getItem()

		public void changeItem(T item) {
			this.item = item;
		}
		
		public int getCount() {
			return this.count;
		} //end of getCount()
		
		public void changeCount(int count) {
			this.count = count;
		}
		
		public void incCount() {
			this.count++;
		} //end of incCount()
		
		public void decCount() {
			this.count--;
		} //end of decCount()
		
		/*
		public void setLeft(Node<T> n) {
			this.mLeft = n;
		}
		
		public void setRight(Node<T> n) {
			this.mRight = n;
		}
		*/
	}//end of Node<T> class
	
	public BstMultiset() {
		// Implement me!
		mRoot = null;
	} // end of BstMultiset()
	
	public void nodePrint(Node<T> n) {
		System.out.printf("%s | %d\n", n.getItem(), n.getCount());
	}
	
	public boolean isEmpty(Node<T> n) {
		if (n == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	public int nodeCompare(T item, Node<T> x) {
		return ((String)x.getItem()).compareTo((String)item);
	}
	*/
	
	public Node<T> min() {
		Node<T> mMin = mRoot;
		return min(mMin);
	}
	
	protected Node<T> min(Node<T> root) {
		Node<T> mMin = root;
		while (mMin.mLeft != null) {
			mMin = mMin.mLeft;
		}
		return mMin;
	}
	
	public void add(T item) {
		// Implement me!
		if (item == null) {
			throw new IllegalArgumentException
			("tried to call add() with null item");
		}
		mRoot = add(mRoot, item);
	} // end of add()
	
	public Node<T> add(Node<T> root, T item) {
		//check if root is empty
		/*int (cmp = ...*/
		if (isEmpty(root)) {
			root = new Node<T>(item);
		}
		else if(((String)root.getItem()).compareTo((String)item) > ZERO) {
			root.mLeft = add(root.mLeft, item);
		}
		else if(((String)root.getItem()).compareTo((String)item) < ZERO) {
			root.mRight = add(root.mRight, item);
		}
		else /* if(cmp == ZERO */ {
			root.incCount();
		}
		return root;
	}
	public int search(T item) {
		// Implement me!
		int mSearch = search(mRoot, item);
		// default return, please override when you implement this method
		return mSearch;
	} // end of add()

	public int search(Node<T> root, T item) {
		/*int cmp = ...*/
		int mSearch = ZERO;
		/* Check if the root exists */
		if (isEmpty(root)) {
			return mSearch;
		} else {
			if (root.getItem().equals(item)) {
				return root.getCount();
			} else if (((String)root.getItem()).compareTo((String)item) > ZERO /*&& root.mLeft != null*/) {
				mSearch = search(root.mLeft, item);
			} else if (((String)root.getItem()).compareTo((String)item) < ZERO /*&& root.mRight != null*/) {
				mSearch = search(root.mRight, item);
			}
		}
		return mSearch;
	}

	public void removeOne(T item) {
		int src = search(item);
		// Implement me!
		if (src < ONE) {
			return;
		}
		else if (src == ONE) {
			//if there is only one instance of the item, it would be the same as remove all
			removeAll(item);
		}
		else if (src > ONE) {
			mRoot = removeOne(mRoot, item);
		}
	} // end of removeOne()
	
	protected Node<T> removeOne(Node<T> root, T item) {
		/*int cmp = ...*/
		if(((String)root.getItem()).compareTo((String)item) > ZERO) {
			root.mLeft = removeOne(root.mLeft, item);
		}
		else if(((String)root.getItem()).compareTo((String)item) < ZERO) {
			root.mRight = removeOne(root.mRight, item);
		}
		else /* if(nodeCompare(item, root) == ZERO */ {
			root.decCount();
		}
		return root;
	}
	
	public void removeAll(T item) {
		// Implement me!
		int src = search(item);
		if (src < ONE) {
			return;
		}
		else {
			mRoot = removeAll(mRoot, item);
		}
		
	} // end of removeAll()

	protected Node<T> removeAll(Node<T> root, T item) {
		/*int ((String)root.getItem()).compareTo((String)item) = nodeCompare(item, root);*/
		if(((String)root.getItem()).compareTo((String)item) > ZERO) {
			root.mLeft = removeAll(root.mLeft, item);
		}
		else if(((String)root.getItem()).compareTo((String)item) < ZERO) {
			root.mRight = removeAll(root.mRight, item);
		}
		else /* if(cmp == ZERO */ {
			//CASE 1 & CASE 2 covered by these first 2 if statements (referencing lecture)
			//swap not-null child with the current node (CASE 2). 
			//if both children are null, then the current node is simply made null (CASE 1)
			if (root.mRight == null) {
				root = root.mLeft;
			}
			else if (root.mLeft == null) {
				root = root.mRight;
			}
			//CASE 3
			//swap current node's contents with min node in its right subtree
			//then delete the node we swapped values from
			else {
				Node<T> t = min(root.mRight);
				root.changeItem(t.getItem());
				root.changeCount(t.getCount());
				root.mRight = removeAll(root.mRight, root.getItem());
			}
		}
		return root;
	}

	public void print(PrintStream out) {
		// Implement me!
		//System.out.println("in");
		//inorder(mRoot);
		//System.out.println("pre");
		preorder(mRoot);
		//System.out.println("post");
		System.out.println("");
	} // end of print()
/*
	protected void inorder(Node<T> root) {
		if (isEmpty(root)) {
			return;
		}
		
		inorder(root.mLeft);
		nodePrint(root);
		inorder(root.mRight);
	} //end of inorder()
*/
	protected void preorder(Node<T> root) {
		if (isEmpty(root)) {
			return;
		}
		nodePrint(root);
		preorder(root.mLeft);
		preorder(root.mRight);
	} //end of preorder()
/*
	protected void postorder (Node<T> root) {
		if (isEmpty(root)) {
			return;
		}
		postorder(root.mLeft);
		postorder(root.mRight);
		nodePrint(root);
	} //end of postorder()
*/

} // end of class BstMultiset
