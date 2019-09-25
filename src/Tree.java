// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import org.w3c.dom.Node;

import java.lang.reflect.Array;
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
        recLevel = recLevel + "-- ";
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
        curr = successor(curr);
        if (curr == null) return "null";
        else return curr.toString();
    }

    public BinaryNode successor(BinaryNode node){
        if (node.right != null){
            BinaryNode toReturn = node.right;
            return findMin(toReturn);
        }
        else{
            BinaryNode toReturn = null;
            BinaryNode ancestor = root;
            while (ancestor != curr){
                // possibly root
                if (curr.element < ancestor.element){
                    toReturn = ancestor;
                    ancestor = ancestor.left;
                }
                else{
                    ancestor = ancestor.right;
                }
            }
            return toReturn;
        }
    }

    /**
     * Counts number of nodes in specifed level
     * @param level Level in tree, root is zero
     * @return count of number of nodes at specified level
     */
    public int nodesInLevel(int level) {
        return nodesInLevel(root, level, 0, 0);
    }

    public int nodesInLevel(BinaryNode node, int level, int currLevel, int nodesNum){
        if (level == 0){
            return 1;
        }
        int toReturn = 0;
        if (currLevel == level - 1){
            if (node.right != null){
                nodesNum++;
            }
            if (node.left != null){
                nodesNum++;
            }
            return nodesNum;
        }
        if (node.right != null){
            toReturn = toReturn + nodesInLevel(node.right, level, currLevel + 1, nodesNum);
        }
        if (node.left != null){
            toReturn = toReturn + nodesInLevel(node.left, level, currLevel + 1, nodesNum);
        }
        return toReturn;
    }

    /**
     * Counts all non-null binary search trees embedded in tree
     * @return Count of embedded binary search trees
     */
    public String countBST(){
        return countBST(root, "");
    }
    private String countBST(BinaryNode node, String returnString) {
        if (node == root && countBST(node, true)){returnString = returnString + node.element + " ";}
        if (node.left != null && countBST(node.left, true))//
            returnString = returnString + node.left.element.toString() + " " + countBST(node.left, "");
        if (node.right != null && countBST(node.right, true)){
            returnString = returnString + node.right.element.toString() + " " + countBST(node.right, "");
        }
        return returnString;
    }
    private Boolean countBST(BinaryNode node, Boolean isTree){
        if (node.left != null && node.left.element >= node.element || node.right != null && node.right.element
                < node.element){
            return false;
        }
        if (node.left != null){
            isTree = countBST(node.left, isTree);}
        if (node.right != null){
            isTree = countBST(node.right, isTree);}

        return isTree;
    }

    /**
     * Remove all paths from tree that sum to less than given value
//     * @param sum: minimum path sum allowed in final tree
     */

    public BinaryNode findMin(){return findMin(root);}

    public BinaryNode findMin(BinaryNode minNode){
        if (minNode.left == null){return minNode;}
        return findMin(minNode.left);
    }

    public void remove(Integer element){
        if (find(element, root) == null){
            System.out.println("Value (" + element + ") does not exist in tree.");
            return;}

        else{
            BinaryNode node = find(element, root);
            BinaryNode parentNode = node.parent;
            if (node.left == null && node.right == null){
                if (parentNode.right == node){parentNode.right = null;}
                else{parentNode.left = null;}
            }
            else if (node.right != null && node.left != null){
                BinaryNode rightMin = findMin(node.right);
                BinaryNode rightMinRep = rightMin.right;
                node.element = rightMin.element;

                if (rightMin.parent.right == rightMin){rightMin.parent.right = rightMinRep;}
                else {rightMin.parent.right = rightMinRep;}
            }

            else if (node.left != null && node.right == null){
                if (parentNode.left == node){parentNode.left = node.left;}
                else{parentNode.right = node.right;}
            }
            else{
                if (parentNode.left == node){parentNode.left = node.left;}
                else{parentNode.right = node.right;}
            }
        }
    }

    public BinaryNode find(int sum){return find(sum, root);}

    private BinaryNode find(int x, BinaryNode t) {
        curr = null;
        if (t == null)
            return null;
//        int compareResult = x.compareTo(t.element);
//        if (compareResult < 0)
        if (x < t.element)
            return find(x, t.left);
        else if (x > t.element)
            return find(x, t.right);
        else {
            curr = t;
            return t;    // Match
        }
    }

    public void pruneK(BinaryNode node, Integer[] nodePaths, int pathLen, int limit) {


    }
    /**
     * Print all paths from root to leaves
     */
    public void printAllPaths() {
        int[] nodePaths = new int[9000];
        printAllPaths(root, nodePaths, 0);
    }

    private void printAllPaths(BinaryNode node, int[] nodePaths, int pathLen){
        if (node == null){
            System.out.println("Empty Tree");
            return;}
        nodePaths[pathLen] = node.element;
        pathLen++;
        if (node.left == null && node.right == null) {
            for (int i = 0; i < pathLen; i++) {
                System.out.print(nodePaths[i] + " ");
            }
            System.out.println(" ");}
        if (node.left != null){
            printAllPaths(node.left, nodePaths, pathLen);
        }
        if (node.right != null){
            printAllPaths(node.right, nodePaths, pathLen);
        }

    }

    /**
     * Print contents of levels in zig zag order starting at maxLevel
     * @param maxLevel
     */

    public  void byLevelZigZag(int maxLevel){byLevelZigZag(root, maxLevel);}

    private void byLevelZigZag(BinaryNode node, int maxLevel){
        boolean isEven = true;
        int height = getHeight(node);
        for (int i = 1; i <= height; i++){
            if (isEven){
                printEvens(node, i, maxLevel);
                isEven = false;
            }
            else {
                printOdds(node, i, maxLevel);
                isEven = true;
            }
        }
        System.out.println(" ");

    }

    private void printEvens(BinaryNode node, int currLevel, int maxLevel){
        if (node == null){return;}
        if(currLevel < maxLevel){
            printEvens(node.right, currLevel + 1, maxLevel);
            printEvens(node.left, currLevel + 1, maxLevel);
        }
        else if (currLevel == maxLevel){ System.out.print(node.element + " ");}
    }

    private void printOdds(BinaryNode node,int currLevel, int maxLevel){
        if (node == null){return;}
        if (currLevel < maxLevel){
            printOdds(node.left, currLevel + 1, maxLevel);
            printOdds(node.right, currLevel + 1, maxLevel);
        }
        else if (currLevel == maxLevel){
            System.out.print(node.element + " ");}
    }

    /**
     * Insert into a bst tree; duplicates are allowed
     * @param x the item to insert.
     */
    private void bstInsert(int x) {

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
     * Build tree given inOrder and preOrder traversals.  Each value is unique
     * @param inOrder  List of tree nodes in inorder
     * @param preOrder List of tree nodes in preorder
     */









    public void buildTreeTraversals(Integer[] inOrder, Integer[] preOrder) {

        root = null;







    }




















    /**
     * Find the least common ancestor of two nodes
     * @param a first node
     * @param b second node
     * @return String representation of ancestor
     */
    private BinaryNode lca(BinaryNode node, Integer a, Integer b) {
//        if (!(bstContains(a)) && !(bstContains(b))){return null;}
        if (!(bstContains(a))){return find(b);}
        if (!bstContains(b)){return find(a);}

        BinaryNode ancestor = node;
        if (bstContains(a, node) && bstContains(b, node)) {
            ancestor = node;
        }
        if (node.right != null && bstContains(a, node.right) && bstContains(b, node.right)) {
            ancestor = lca(node.right, a, b);
        }
        if (node.left != null && bstContains(a, node.left) && bstContains(b, node.left)) {
            ancestor = lca(node.left, a, b);
        }
        return ancestor;
    }

    public String lca(int a, int b) {
        if (lca(root, a, b) == null){return "Both values not found in tree.";}
        else{return lca(root, a, b).element.toString();}
    }

    /**
     * Balance the tree
     */
    public int getHeight(){return getHeight(root);}

    /**
     * getHeight WORKS!
     */
    private int getHeight(BinaryNode node){
        int leftHeight = -1;
        int rightHeight = -1;
        if (node.left != null){leftHeight = getHeight(node.left);}
        if (node.right != null){rightHeight = getHeight(node.right);}
        if (leftHeight > rightHeight){return leftHeight + 1;}
        else{return rightHeight + 1;}
    }

    public boolean isNodeBalanced(BinaryNode node){
//        node = root.right;
//        if (node == null){return true;}
        if(node.left != null && node.right != null){
            if ((getHeight(node.left) - getHeight(node.right) > 1 ||
                (getHeight(node.left) - getHeight(node.right) > -1))){return false;}}
        else if(node.left == null && node.right != null){
            if (getHeight(node.right) > 1){return false;}
        }
        else if(node.left != null){
            if (getHeight(node.left) > 1) {return false;}
        }
        return true;
    }

    public BinaryNode singleRight(BinaryNode node){
        BinaryNode leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        node.height = getHeight(node);
        leftNode.height = getHeight(node);
        return leftNode;
    }

    public BinaryNode singleLeft(BinaryNode node){
        BinaryNode rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        node.height = getHeight(node);
        rightNode.height = getHeight(node);
        return rightNode;
    }

    public BinaryNode doubleRight(BinaryNode node){
        node.left = singleLeft(node.left);
        return singleRight(node);
    }

    public BinaryNode doubleLeft(BinaryNode node){
        node.right = singleRight(node.right);
        return singleLeft(node);
    }

    public BinaryNode balanceTree(BinaryNode node){
        if (node.left == null && node.right == null){return node;}
        else if (node.left != null && node.right == null){balanceTree(node.left);}
        else if (node.left == null){balanceTree(node.right);}

        int nodeLeftHeight;
        int nodeRightHeight;
        int nodeLeftLeftHeight;
        int nodeLeftRightHeight;
        int nodeRightRightHeight;
        int nodeRightLeftHeight;
        if (node.left != null){
            nodeLeftHeight = node.left.height;
            if (node.left.left == null){nodeLeftLeftHeight = 0;} else{nodeLeftLeftHeight = node.left.left.height;}
            if (node.left.right == null){nodeLeftRightHeight = 0;} else{nodeLeftRightHeight = node.left.right.height;}
        }
        else {
            nodeLeftHeight = 0;
            nodeLeftLeftHeight = 0;
            nodeLeftRightHeight = 0;
        }
        if (node.right != null){
            nodeRightHeight = node.right.height;
            if (node.right.right == null){nodeRightRightHeight = 0;} else{nodeRightRightHeight = node.right.right.height;}
            if (node.right.left == null){nodeRightLeftHeight = 0;} else{nodeRightLeftHeight = node.right.left.height;}
        }
        else{
            nodeRightHeight = 0;
            nodeRightRightHeight = 0;
            nodeRightLeftHeight = 0;
        }

        if (nodeLeftHeight - nodeRightHeight > 1 ){
            if (nodeLeftLeftHeight >= nodeLeftRightHeight){
                root = singleRight(node);
            }
            else{root = doubleRight(node);}
        }
        else{
            if (nodeRightHeight - nodeLeftHeight > 1){
                if (nodeRightRightHeight >= nodeRightLeftHeight){
                    root = singleLeft(node);
                }
                else{
                    root = doubleLeft(node);
                }
            }
        }
//        root = balanceTree(root);
        updateHeights(root);
        return node;
    }

    public void balanceTree(){balanceTree(root);}












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
        updateHeights(root);
        return t;
    }

    private void updateHeights(BinaryNode node){
        node.height = getHeight(node);
        if (node.right == null && node.left == null){
            return;
        }
        else
            if (node.left != null){updateHeights(node.left);}
            if (node.right != null){updateHeights(node.right);}

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

    public boolean bstContains(int x){return bstContains(x, root);}

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
        int height;

        // Constructors
        BinaryNode(int theElement) { this(theElement, null, null, null);
            height = 0;}

        BinaryNode(int theElement, BinaryNode lt, BinaryNode rt, BinaryNode pt) {
            element = theElement;
            left = lt;
            right = rt;
            parent = pt;
            height = 0;
        }
//        private int getHeight(BinaryNode node){
//            int leftHeight = -1;
//            int rightHeight = -1;
//            if (node.left != null){leftHeight = getHeight(node.left);}
//            if (node.right != null){rightHeight = getHeight(node.right);}
//            if (leftHeight > rightHeight){return leftHeight + 1;}
//            else{return rightHeight + 1;}
//        }
        public int getHeight(){return this.height;}

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
