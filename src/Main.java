import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Main{

// Test program
public static void main(String[] args) {
        long seed = 436543;
        Random generator = new Random(seed);  // Don't use a seed if you want the numbers to be different each time
final String ENDLINE = "\n";

        int val = 60;
final int SIZE = 8;

        Integer[] v1 = {25, 10, 60, 55, 58, 56, 14, 63, 8, 50, 6, 9};
        ArrayList<Integer> v2 = new ArrayList<Integer>();
        for (int i = 0; i < SIZE * 2; i++) {
        int t = generator.nextInt(200);
        v2.add(t);
        }
        v2.add(val);
        Integer[] v3 = {200, 15, 3, 65, 83, 70, 90};
        ArrayList<Integer> v4 = new ArrayList<Integer>(Arrays.asList(v3));
        Integer[] v = {21, 8, 5, 6, 7, 19, 10, 40, 43, 52, 12, 60};
        ArrayList<Integer> v5 = new ArrayList<Integer>(Arrays.asList(v));
        Integer[] inorder = {4, 2, 1, 7, 5, 8, 3, 6};
        Integer[] preorder = {1, 2, 4, 3, 5, 7, 8, 6};


        Tree<Integer> tree1 = new Tree<Integer>(v1, "Tree1:");
        Tree<Integer> tree2 = new Tree<Integer>(v2, "Tree2:");
        Tree<Integer> tree3 = new Tree<Integer>(v3, "Tree3:");
        Tree<Integer> treeA = new Tree<Integer>(v4, "TreeA:", 2);
        Tree<Integer> treeB = new Tree<Integer>(v5, "TreeB", 3);
        Tree<Integer> treeC = new Tree<Integer>("TreeC");
//        System.out.println(tree1.toString2());
//        System.out.println(tree1.toString());
//
//        System.out.println(tree1.toString2());
//
//        System.out.println(treeA.toString());
//
//        treeA.flip();
//        System.out.println("Now flipped" + treeA.toString());
//
//        System.out.println(tree2.toString());
//        tree2.contains(val);  //Sets the current node inside the tree6 class.
//        int succCount = 5;  // how many successors do you want to see?
//        System.out.println("In Tree2, starting at " + val + ENDLINE);
//        for (int i = 0; i < succCount; i++) {
//        System.out.println("The next successor is " + tree2.successor());
//        }

//        System.out.println(tree1.toString());
//        for (int mylevel = 0; mylevel < SIZE; mylevel += 2) {
//        System.out.println("Number nodes at level " + mylevel + " is " + tree1.nodesInLevel(mylevel));
//        }
//        System.out.println("All paths from tree1");
//        tree1.printAllPaths();
//        System.out.println(tree2.toString());
//        System.out.print("Tree1 byLevelZigZag: ");
//        tree1.byLevelZigZag(5);
//        System.out.print("Tree2 byLevelZigZag (3): ");
//        tree2.byLevelZigZag(3);
//        treeA.flip();
//        System.out.println(treeA.toString());
//        System.out.println("treeA Contains BST: " + treeA.countBST());
//
//        System.out.println(treeB.toString());
//        System.out.println("treeB Contains BST: " + treeB.countBST());

//        treeB.pruneK(60);
//        treeB.changeName("treeB after pruning 60");
//        System.out.println(treeB.toString());
//        treeA.pruneK(220);
//        treeA.changeName("treeA after pruning 220");
//        System.out.println(treeA.toString());

        treeC.buildTreeTraversals(inorder, preorder);
        treeC.changeName("Tree C built from inorder and preorder traversals");
        System.out.println(treeC.toString());
//
//        System.out.println(tree1.toString());
//        System.out.println("tree1 Least Common Ancestor of (56,61) " + tree1.lca(56, 61) + ENDLINE);
////
//        System.out.println("tree1 Least Common Ancestor of (6,25) " + tree1.lca(6, 25) + ENDLINE);
//        System.out.println(treeA.toString());
//        System.out.println(treeA.isNodeBalanced(treeA.getRoot()));

//        System.out.println(tree3.toString());
//        tree3.balanceTree();
//        tree3.changeName("tree3 after balancing");
//        System.out.println(tree3.toString());

//        System.out.println(tree1.toString());
//        tree1.keepRange(10, 50);
//        tree1.changeName("tree1 after keeping only nodes between 10 and 50");
//        System.out.println(tree1.toString());
//        tree3.keepRange(3, 85);
//        tree3.changeName("tree3 after keeping only nodes between 3  and 85");
//        System.out.println(tree3.toString());


        }

        }