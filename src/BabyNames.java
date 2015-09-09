
// Copy paste this Java Template and save it as "BabyNames.java"
import java.util.*;
import java.io.*;
import java.util.NoSuchElementException;

// write your matric number here: A0102800A
// write your name here: Suranjana Sengupta
// write list of collaborators here: Akshat Dubey
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class BabyNames {
	private BST TGirl;
	private BST TBoy;
	private Map<String, Integer> babyMap;

	public BabyNames() {
		TBoy = new BST();
		TGirl = new BST();
		babyMap = new HashMap<String, Integer>();
	}

	void AddSuggestion(String babyName, int genderSuitability) {

		if (genderSuitability == 1) {
			TBoy.insert(babyName);
		} else if (genderSuitability == 2) {
			TGirl.insert(babyName);
		}
		babyMap.put(babyName, genderSuitability);
	}

	void RemoveSuggestion(String babyName) {
		if (babyMap.containsKey(babyName)) {
			if (babyMap.get(babyName) == 1) {
				TBoy.delete(babyName);
			} else if (babyMap.get(babyName) == 2) {
				TGirl.delete(babyName);
			}
		}
	}

	int Query(String START, String END, int genderPreference) {
		int ans = 0;

		int boyCtr = 0, girlCtr = 0;

		boyCtr = TBoy.findNamesInRange(START, END);
		girlCtr = TGirl.findNamesInRange(START, END);

		if (genderPreference == 1) {
			ans = boyCtr;
		} else if (genderPreference == 2) {
			ans = girlCtr;
		} else if (genderPreference == 0) {
			ans = boyCtr + girlCtr;
		}

		return ans;
	}

	void run() throws Exception {
		// do not alter this method to avoid unnecessary errors with the
		// automated judging
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if (command == 0) // end of input
				break;
			else if (command == 1) // AddSuggestion
				AddSuggestion(st.nextToken(), Integer.parseInt(st.nextToken()));
			else if (command == 2) // RemoveSuggestion
				RemoveSuggestion(st.nextToken());
			else // if (command == 3) // Query
				pr.println(Query(st.nextToken(), // START
						st.nextToken(), // END
						Integer.parseInt(st.nextToken()))); // GENDER
		}
		pr.close();
	}

	public static void main(String[] args) throws Exception {
		// do not alter this method to avoid unnecessary errors with the
		// automated judging
		BabyNames ps2 = new BabyNames();
		ps2.run();
	}
}

// Every vertex in this BST is a Java Class
class BSTVertex {

	BSTVertex(String name) {
		parent = left = right = null;
		height = 0;
		size = 1;
		this.name = name;
	}

	// all these attributes remain public to slightly simplify the code
	public BSTVertex parent, left, right;
	public int key;
	public int height; // will be used in AVL lecture
	public String name;
	public int size;
}

// This is just a sample implementation
// There are other ways to implement BST concepts...
class BST {
	protected BSTVertex root;

	protected BSTVertex search(BSTVertex T, String v) {
		if (T == null)
			return T; // not found
		else if (T.name.compareTo(v) == 0)
			return T; // found
		else if (T.name.compareTo(v) < 0)
			return search(T.right, v); // search to the right
		else
			return search(T.left, v); // search to the left
	}

	protected BSTVertex insert(BSTVertex T, String v) {
		if (T == null) {
			BSTVertex node = new BSTVertex(v);
			return node; // insertion point is found
		}

		if (v.compareTo(T.name) > 0) { // search to the right
			T.right = insert(T.right, v);
			T.right.parent = T;
			if (checkBalance(getHeight(T.left), getHeight(T.right)) == -2) {
				if (v.compareTo(T.right.name) > 0) {
					T = rotateLeft(T);
				} else {
					T.right = rotateRight(T.right);
					T = rotateLeft(T);
				}
			}

		} else { // search to the left
			T.left = insert(T.left, v);
			T.left.parent = T;
			if (checkBalance(getHeight(T.left), getHeight(T.right)) == 2) {
				if (v.compareTo(T.left.name) < 0) {
					T = rotateRight(T);
				} else {
					T.left = rotateLeft(T.left);
					T = rotateRight(T);
				}
			}
		}

		T.height = findMax(getHeight(T.left), getHeight(T.right)) + 1;
		T.size = 1 + getSize(T.left) + getSize(T.right);

		return T; // return the updated BST
	}

	private int findMax(int lHeight, int rHeight) {
		return Math.max(lHeight, rHeight);
	}

	private int checkBalance(int leftHeight, int rightHeight) {
		return (leftHeight - rightHeight);
	}

	private BSTVertex rotateRight(BSTVertex t2) {
		BSTVertex t1 = t2.left;
		if (t2.parent != null) {
			t1.parent = t2.parent;
		}
		t2.parent = t1;
		t2.left = t1.right;
		if (t1.right != null) {
			t1.right.parent = t2;
		}
		t1.right = t2;

		t1.height = findMax(getHeight(t1.left), getHeight(t1.right)) + 1;
		t2.height = findMax(getHeight(t2.left), getHeight(t2.right)) + 1;

		t1.size = 1 + getSize(t1.left) + getSize(t1.right);
		t2.size = 1 + getSize(t2.left) + getSize(t2.right);
		return t1;
	}

	private BSTVertex rotateLeft(BSTVertex t1) {
		BSTVertex t2 = t1.right;
		if (t1.parent != null) {
			t2.parent = t1.parent;
		}
		t1.parent = t2;
		t1.right = t2.left;
		if (t2.left != null) {
			t2.left.parent = t1;
		}
		t2.left = t1;

		t1.height = findMax(getHeight(t1.left), getHeight(t1.right)) + 1;
		t2.height = findMax(getHeight(t2.left), getHeight(t2.right)) + 1;

		t1.size = 1 + getSize(t1.left) + getSize(t1.right);
		t2.size = 1 + getSize(t2.left) + getSize(t2.right);
		return t2;
	}

	protected int rank(String KEY, BSTVertex T) {
		if (T == null) {
			return 0;
		}
		int cmp = KEY.compareTo(T.name);
		if (cmp < 0) { // less than T
			return rank(KEY, T.left);
		} else if (cmp > 0) { // greater than T
			return (1 + getSize(T.left) + rank(KEY, T.right));
		} else { // equal to T
			return getSize(T.left);
		}
	}

	public int findNamesInRange(String START, String END) {
		int startRank = rank(START, root);
		int endRank = rank(END, root);
		return (endRank - startRank);
	}

	protected void inorder(BSTVertex T) {
		if (T == null)
			return;
		inorder(T.left); // recursively go to the left
		System.out.printf(" %d", T.key); // visit this BST node
		inorder(T.right); // recursively go to the right
	}

	public int getSize(BSTVertex T) {
		if (T == null) {
			return 0;
		}
		return T.size;
	}

	// Example of Java error handling mechanism
	/*
	 * // old code, returns -1 when there is no minimum (the BST is empty)
	 * protected int findMin(BSTVertex T) { if (T == null) return -1; // not
	 * found else if (T.left == null) return T.key; // this is the min else
	 * return findMin(T.left); // go to the left }
	 */

	protected String findMin(BSTVertex T) {
		if (T == null)
			throw new NoSuchElementException("BST is empty, no minimum");
		else if (T.left == null)
			return T.name; // this is the min
		else
			return findMin(T.left); // go to the left
	}

	protected String findMax(BSTVertex T) {
		if (T == null)
			throw new NoSuchElementException("BST is empty, no maximum");
		else if (T.right == null)
			return T.name; // this is the max
		else
			return findMax(T.right); // go to the right
	}

	protected String successor(BSTVertex T) {
		if (T.right != null) // this subtree has right subtree
			return findMin(T.right); // the successor is the minimum of right
										// subtree
		else {
			BSTVertex par = T.parent;
			BSTVertex cur = T;
			// if par(ent) is not root and cur(rent) is its right children
			while ((par != null) && (cur == par.right)) {
				cur = par; // continue moving up
				par = cur.parent;
			}
			return par == null ? null : par.name; // this is the successor of T
		}
	}

	protected String predecessor(BSTVertex T) {
		if (T.left != null) // this subtree has left subtree
			return findMax(T.left); // the predecessor is the maximum of left
									// subtree
		else {
			BSTVertex par = T.parent;
			BSTVertex cur = T;
			// if par(ent) is not root and cur(rent) is its left children
			while ((par != null) && (cur == par.left)) {
				cur = par; // continue moving up
				par = cur.parent;
			}
			return par == null ? null : par.name; // this is the successor of T
		}
	}

	protected BSTVertex delete(BSTVertex T, String v) {
		if (T == null)
			return T; // cannot find the item to be deleted

		if (T.name.compareTo(v) == 0) { // this is the node to be deleted
			if (T.left == null && T.right == null) // this is a leaf
				T = null; // simply erase this node
			else if (T.left == null && T.right != null) { // only one child at
															// right
				BSTVertex temp = T;
				T.right.parent = T.parent;
				T = T.right; // bypass T
				temp = null;
			} else if (T.left != null && T.right == null) { // only one child at
															// left
				BSTVertex temp = T;
				T.left.parent = T.parent;
				T = T.left; // bypass T
				temp = null;
			} else { // has two children, find successor
				String successorV = successor(v);
				T.name = successorV; // replace this key with the successor's
										// key
				T.right = delete(T.right, successorV); // delete the old
														// successorV
			}
		} else if (T.name.compareTo(v) < 0) // search to the right
			T.right = delete(T.right, v);
		else // search to the left
			T.left = delete(T.left, v);

		if (T != null) {
			if (checkBalance(getHeight(T.left), getHeight(T.right)) == -2) {
				if (v.compareTo(T.right.name) > 0) {
					T = rotateLeft(T);
				} else {
					T.right = rotateRight(T.right);
					T = rotateLeft(T);
				}
			} else if (checkBalance(getHeight(T.left), getHeight(T.right)) == 2) {
				if (v.compareTo(T.left.name) < 0) {
					T = rotateRight(T);
				} else {
					T.left = rotateLeft(T.left);
					T = rotateRight(T);
				}
			}
			T.height = findMax(getHeight(T.left), getHeight(T.right)) + 1;
			T.size = 1 + getSize(T.left) + getSize(T.right);
		}

		return T; // return the updated BST
	}

	public BST() {
		root = null;
	}

	public String search(String v) {
		BSTVertex res = search(root, v);
		return res == null ? null : res.name;
	}

	public void insert(String v) {
		root = insert(root, v);
	}

	public void inorder() {
		inorder(root);
		System.out.println();
	}

	public String findMin() {
		return findMin(root);
	}

	public String findMax() {
		return findMax(root);
	}

	public String successor(String v) {
		BSTVertex vPos = search(root, v);
		if (vPos == null) {
			return null;
		} else {
			return successor(vPos);
		}
	}

	public String predecessor(String v) {
		BSTVertex vPos = search(root, v);
		return vPos == null ? null : predecessor(vPos);
	}

	public void delete(String v) {
		root = delete(root, v);
	}

	// will be used in AVL lecture
	protected int getHeight(BSTVertex T) {
		if (T == null)
			return -1;
		else
			return T.height;
	}

	public int getHeight() {
		return getHeight(root);
	}

}
