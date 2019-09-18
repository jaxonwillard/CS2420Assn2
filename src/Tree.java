// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

class UnderflowException extends RuntimeException {
    /**
     * Construct this exception object.
     * @param message the error message.
     */
    public UnderflowException(String message) {
        super(message);
    }
}

public class Tree<E extends Comparable<? super E>> {
    final String ENDLINE = "\n";
    private BinaryNode root;  // Root of tree
    private BinaryNode curr;  // Last node accessed in tree
    private String treeName;     // Name of tree

    /**
     * Create an empty tree
     *
     * @param label Name of tree
     */
    public Tree(String label) {
        treeName = label;
        root = null;
    }

    /**
     * Create non ordered tree from list in preorder
     * @param arr    List of elements
     * @param label  Name of tree
     * @param height Maximum height of tree
     */
    public Tree(ArrayList<Integer> arr, String label, int height) {
        this.treeName = label;
        root = buildTree(arr, height, null);
    }

    /**
     * Create BST
     * @param arr   List of elements to be added
     * @param label Name of tree
     */
    public Tree(ArrayList<Integer> arr, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < arr.size(); i++) {
            bstInsert(arr.get(i));
        }

    }

    public Tree(Integer[] arr, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < arr.length; i++) {
            bstInsert(arr[i]);
        }
    }


    /**
     * Create BST from Array
     * @param arr   List of elements to be added
     * @param label Name of  tree
     */
    public Tree(int[] arr, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < arr.length; i++) {
            bstInsert(arr[i]);
        }
    }


    /**
     * Change name of tree
     * @param name new name of tree
     */
    public void changeName(String name) {
        this.treeName = name;
    }

    /**
     * Return a string displaying the tree contents as a tree with one node per line
     */
    @Override
    public String toString() {
        if (root == null)
            return (treeName + " Empty tree\n");
        else
//           return toString(this.root);
            System.out.println(this.treeName);
            return toString(this.root, "", "", "");
    }

    public String toString(BinaryNode node, String toReturn, String recLevel, String parentElement){
        recLevel = recLevel + " ";
        if(parentElement == ""){
            parentElement = "no parent";
        }
        if (node.right != null){
            toReturn = toString(node.right, toReturn, recLevel, node.element.toString());
        }
//        System.out.println(node.element);
        toReturn = toReturn + recLevel + node.element.toString() + "[" + parentElement + "]" + recLevel + "\n";
        if (node.left != null){
//            toStrong(node.left);
            toReturn = toString(node.left, toReturn, recLevel, node.element.toString());
        }
    return toReturn;
    }

    /**
     * Return a string displaying the tree contents as a single line
     */
    public String toString2() {
        if (root == null)
            return treeName + " Empty tree";
        else
            return treeName + " " + toString2(root);
    }

    /**
     * reverse left and right children recursively
     */
    public void flip(){
//        System.out.println("\n\n\n\n");
        flip(root);
    }

    public void flip(BinaryNode node) {
        if (node.right == null && node.left == null){
            return;
        }
        BinaryNode holder = node.left;
        node.left = node.right;
        node.right = holder;
        //flip(root);
    }

    /**
     * Find successor of "curr" node in tree
     * @return String representation of the successor
     */
    public String successor() {
        if (curr == null) curr = root;
        //curr = successor(curr);
        if (curr == null) return "null";
        else return curr.toString();
    }

    /**
     * Counts number of nodes in specifed level
     * @param level Level in tree, root is zero
     * @return count of number of nodes at specified level
     */
    public int nodesInLevel(int level) {
        return 0; //nodesInLevel(root, level);
    }

    /**
     * Print all paths from root to leaves
     */
    public void printAllPaths() {

    }

    /**
     * Print contents of levels in zig zag order starting at maxLevel
     * @param maxLevel
     */
    public void byLevelZigZag(int maxLevel) {

    }

    /**
     * Counts all non-null binary search trees embedded in tree
     * @return Count of embedded binary search trees
     */
    public Integer countBST() {
        if (root == null) return 0;
        return -1;
    }

    /**
     * Insert into a bst tree; duplicates are allowed
     * @param x the item to insert.
     */
    public void bstInsert(int x) {

        root = bstInsert(x, root, null);
    }

    /**
     * Determines if item is in tree
     * @param item the item to search for.
     * @return true if found.
     */
    public boolean contains(int item) {

        return bstContains(item, root);
    }

    /**
     * Remove all paths from tree that sum to less than given value
     * @param sum: minimum path sum allowed in final tree
     */
    public void pruneK(Integer sum) {
    }

    /**
     * Build tree given inOrder and preOrder traversals.  Each value is unique
     * @param inOrder  List of tree nodes in inorder
     * @param preOrder List of tree nodes in preorder
     */
    public void buildTreeTraversals(E[] inOrder, E[] preOrder) {
        root = null;
    }

    /**
     * Find the least common ancestor of two nodes
     * @param a first node
     * @param b second node
     * @return String representation of ancestor
     */
    public String lca(E a, E b) {
        BinaryNode ancestor = null;
//        if (a.compareTo(b) < 0) {
//            ancestor = lca(root, a, b);
//        } else {
//            ancestor = lca(root, b, a);
//        }
        if (ancestor == null) return "none";
        else return ancestor.toString();
    }

    /**
     * Balance the tree
     */
    public void balanceTree() {
        //root = balanceTree(root);
    }

    /**
     * In a BST, keep only nodes between range
     * @param a lowest value
     * @param b highest value
     */
    public void keepRange(E a, E b) {
    }

    //PRIVATE

    /**
     * Build a NON BST tree by preorder
     *
     * @param arr    nodes to be added
     * @param height maximum height of tree
     * @param parent parent of subtree to be created
     * @return new tree
     */
    private BinaryNode buildTree(ArrayList<Integer> arr, int height, BinaryNode parent) {
        if (arr.isEmpty()) return null;
        BinaryNode curr = new BinaryNode(arr.remove(0), null, null, parent);
        if (height > 0) {
            curr.left = buildTree(arr, height - 1, curr);
            curr.right = buildTree(arr, height - 1, curr);
        }
        return curr;
    }

    /**
     * Internal method to insert into a subtree.
     * In tree is balanced, this routine runs in O(log n)
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode bstInsert(int x, BinaryNode t, BinaryNode parent) {
        if (t == null)
            return new BinaryNode(x, null, null, parent);


        if (x < t.element) {
            t.left = bstInsert(x, t.left, t);
        } else {
            t.right = bstInsert(x, t.right, t);
        }

        return t;
    }


    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     *          SIDE EFFECT: Sets local variable curr to be the node that is found
     * @return node containing the matched item.
     */
    private boolean bstContains(int x, BinaryNode t) {
        curr = null;
        if (t == null)
            return false;

//        int compareResult = x.compareTo(t.element);

//        if (compareResult < 0)
        if (x < t.element)
            return bstContains(x, t.left);
        else if (x > t.element)
            return bstContains(x, t.right);
        else {
            curr = t;
            return true;    // Match
        }
    }


    /**
     * Internal method to return a string of items in the tree in order
     * This routine runs in O(??)
     * @param t the node that roots the subtree.
     */
    private String toString2(BinaryNode t) {
        if (t == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(toString2(t.left));
        sb.append(t.element.toString() + " ");
        sb.append(toString2(t.right));
        return sb.toString();
    }









    // Basic node stored in unbalanced binary  trees
    private static class BinaryNode {
        Integer element;            // The data in the node
        BinaryNode left;   // Left child
        BinaryNode right;  // Right child
        BinaryNode parent; //  Parent node

        // Constructors
        BinaryNode(int theElement) {
            this(theElement, null, null, null);
        }

        BinaryNode(int theElement, BinaryNode lt, BinaryNode rt, BinaryNode pt) {
            element = theElement;
            left = lt;
            right = rt;
            parent = pt;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node:");
            sb.append(element);
            if (parent == null) {
                sb.append("<>");
            } else {
                sb.append("<");
                sb.append(parent.element);
                sb.append(">");
            }

            return sb.toString();
        }

    }




}
