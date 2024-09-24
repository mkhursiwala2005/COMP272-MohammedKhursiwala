
/*
 * *** Mohammed Khursiwala / 002 ***
 *
 * Homework # 2 (Programming Assignment). This Java class defines a few basic
 * manipulation operations of a binary trees.
 *
 * ONLY MODIFY THIS FILE (NOT 'Main.Java')
 *
 */

import java.util.Queue;
import java.util.LinkedList;

/*
 * Class BinaryTree
 *
 * This class defines a binary tree object; it is a tree structure where every
 * node as at most two child nodes, which form the tree branches. That implies
 * that each node within the tree has a degree of 0, 1, or 2. A node of degree
 * zero (0) is called a terminal node, or leaf node.
 *
 * Each non-leaf node is often called a branch node, which will have  either one or
 * two children (a left and right child node). There is no order guarantee within
 * this basic binary tree object. Given that this binary object is NOT a Binary Search Tree (BST), there is
 * no guarantee on order in the tree.
 *
 * As just stated, the insert method does NOT guarantee the order within the tree, but
 * its logic attempts to follow the rules of BSTs -- meaning the insert method will traverse
 * the binary tree searching for a location to insert the new Node using traversal
 * logic similar to BSTs. But again, this is not a BST, so there is no guarantee that
 * the tree's order maintains that defined by a BST.
 *
 * Public methods:
 *  void deleteTree()      - deletes the tree.
 *  Node insert(int data)  - inserts a new node into the tree containing value 'data'.
 *  String preOrder()      - return the tree in 'preorder' traversal in a String object.
 *
 * The following methods you will complete:
 *  void replaceValue(int k, int l) - if data value 'k' is in tree, replace with data
 *                           value 'l'; for simplicity at the moment, do not re-organize
 *                           the tree based on new value which means this operation may
 *                           violate the binary tree definition.
 *  int findMin()          - returns the small data value stored in the tree.
 *  int nodesGT(int val)   - return the number of nodes in the tree that have a data value
 *                           greater than 'val'.
 *  double average()       - return the average data value of all data values stored in
 *                           the tree.
 */

public class BinaryTree {

    // Constructors
    public BinaryTree() {
        root = null;
    }
    public BinaryTree(Node node) {
        root = node;
    }

    /* 
     * Class Node
     *
     * The node object definition for each node of the bin ary tree.
     */

    static class Node {

        Node(int d) {
            data = d;
            left = null;
            right = null;
        }

        Node(int d, Node l, Node r) {
            data = d;
            left = l;
            right = r;
        }

        public int data;
        public Node left;
        public Node right;

    }   /* End Class Node */


    public Node root;

    public void deleteTree() {
        root = null;
    }

    public void replaceValue(int oldVal, int newVal) {
        replaceValueHelper(root, oldVal, newVal);
    }

    public int findMin() {
        return findMinHelper(root);
    }

    public int nodesGT(int val) {
        return nodesGTHelper(root, val);
    }


    /*
     * public method insert
     *
     * The method will insert a node into the binary tree containing the value
     * passed in as a parameter, 'data'. This insert routine maintains the
     * form of the binary tree which maintains teh property of a 'complete binary'
     * tree.
     *
     * The property basically implies that for every node in the tree:
     *   1) every node in the tree has 2 children, except for possibly the last level.
     *   2) and on the last level, all nodes are as far left as possible.
     *
     * There are no order properties of a basic binary tree.
     *
     * This method uses a breath first search of the binary tree to locate the
     * location of where to insert the new node. This approach basically starts at
     * the root, and searches level by level until the next free spot for the insertion.
     * This approach maintains the 'complete tree' property of the binary tree.
     */

    Node insert(int data) {

        Node tempNode = new Node(data);

        // If tree is empty, insert new node as the root.
        if (root == null)
            return root = tempNode;

        // Create a queue to do level order traversal
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // Do level order traversal
        while (!queue.isEmpty()) {
            Node front = queue.peek();

            if (front.left == null) {
                front.left = tempNode;
                break;
            } else if (front.right == null) {
                front.right = tempNode;
                break;
            } else {
                // If front node in queue has both left and right
                // children, remove it from the queue.

                queue.remove();
            }

            // Enqueue the left and right children of teh current node
            if (front.left != null)
                queue.add(front.left);

            if (front.right != null)
                queue.add(front.right);
        }

        return tempNode;

    } // End method insert


    /*
     * Public method preOrder()
     *
     * This method will generate a String object containing a copy of the tree's
     * data values in preorder traversal format. If tree is empty, and empty
     * String object (e.g., "") is returned. Else the String object contains
     * the data values, separated by a space.
     *
     * This public method is simply wrapper for the preOrderHelper private method
     * which does the actual work. The public wrapper method simply passes the root
     * of the tree to helper method.
     */
    
    public String preOrder() {
        return preOrderHelper(root);
    }

    public String preOrderHelper(Node node) {
        if (node == null) {
            return "";
        }
        return node.data + " " + preOrderHelper(node.left)
                + preOrderHelper(node.right);
    }


    /***********************************************************
     *
     * YOUR CODE GOES BELOW
     *
     * THERE IS NO NEED TO CHANGE ANY CODE ABOVE. DO NOT FORGET TO PLACE
     * YOUR NAME AND SECTION NUMBER AT THE TOP OF THE FILE.
     *
     * YOU ARE TO WRITE THE METHODS:
     *    - replaceValue
     *    - findMin
     *    - NodesGT
     *    - average
     *
     ***********************************************************/


    /*
     * private method replaceValueHelper
     *
     * This method will traverse the tree using a depth first search
     * approach, and for each node found with the value of 'oldVal',
     * replace it (update teh value in place), with the provided 'newVal'.
     *
     * Depth first search of the tree is based on recursion. This will result
     * in very few lines of code.
     *
     * Pseudocode:
     * check if a binary tree is empty, then do nothing and just return
     * Using preorder traversal, look at the parent/root node, left node, right node
     * check if parent/root node data value equal to oldVal
     * then set that node data equal to newVal
     * Using recursive approach,
     * call the method itself replaceValueHelper() for the left node
     * call the method itself replaceValueHelper() for the right node
     */

    private void replaceValueHelper(Node node, int oldVal, int newVal) {

        // ADD YOUR CODE HERE -- USE DEPTH FIRST SEARCH OF
        // BINARY TREE (WHICH IS BASED ON RECURSION)
        if (node == null) {
            return;
        }
        if (node.data == oldVal) {
            node.data = newVal;
        }
        replaceValueHelper(node.left,oldVal,newVal);
        replaceValueHelper(node.right,oldVal,newVal);
    }


    /*
     * private method findMinHelper()
     *
     * This method will traverse the tree using depth first search traversal and
     * return the minimum data value in the binary tree. If the tree is empty, the
     * value 'Integer.MAX_VALUE' is returned. Recall that this is not a binary
     * search Tree (BST), so it does not have the additional property that the
     * smaller data values always traverse the left child. So that implies all
     * node is this tree must be traversed.
     *
     * Depth first search of the tree is based on recursion. This will result
     * in very few lines of code.
     *
     * Pseudocode:
     * check if the tree is empty that is root node == null then return the max integer value
     * Using recursion and preorder traveral,
     * declare an int called leftNode and set it to recursive call of the method with parameter left node
     * declare an int called rightNode and set it to recursive call of the method with parameter right node
     * create an int called min
     * use a Math function that finds a between two values, find the minimum between left and right node
     * set it to min and return the minimum and between data value of root/parent node and min
     */

    private int findMinHelper(Node node) {

        // ADD YOUR CODE HERE -- USE DEPTH FIRST SEARCH OF
        // BINARY TREE (WHICH IS BASED ON RECURSION)
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        int leftNode = findMinHelper(node.left);
        int rightNode = findMinHelper(node.right);

        int min = Math.min(leftNode, rightNode);
        return Math.min(node.data, min);
    }


    /*
     * private method nodeGTHelper()
     *
     * This method will traverse the tree using depth first search traversal and
     * return a count on the number of nodes that contain a data value larger
     * than the parameter 'val'.
     *
     * If the tree is empty, return 0.
     *
     * Depth first search of the tree is based on recursion. This will result
     * in very few lines of code.
     *
     * Pseudocode:
     * if tree is empty, return 0
     * declare and initialize a count variable to 0
     * Using preorder traversal, check if data value of the node is greater than val
     * then increment count by 1
     * call the method itself for the left node and
     * if left node data value is greater than val, update the count variable
     * do the same thing for the right node
     * lastly return count
     */

    private int nodesGTHelper(Node node, int val) {

        // ADD YOUR CODE HERE -- USE DEPTH FIRST SEARCH OF
        // BINARY TREE (WHICH IS BASED ON RECURSION)
        // return -1; // RECALL, IF TREE IS EMPTY, RETURN -1
        // Base Case: if tree is empty that is if root node is null, return -1
        if (node == null) {
            return 0;
        }

        int count = 0;
        if (node.data > val) {
            count++;
        }
        count += nodesGTHelper(node.left, val);
        count += nodesGTHelper(node.right, val);

        return count;
    }


    /*
     * public method average()
     *
     * This method will traverse the tree using depth first search traversal and
     * return the average value contained in the binary tree. To easily perform a depth
     * first traversal, it invokes the helper method, averageHelper(), which is the
     * method that should be called recursively. If the tree is empty, 0 should be
     * returned.
     *
     * IMPORTANT NOTE:
     * The helper method should return an array of two integer values. In index
     * location [0] is the sum of all data values in the tree. And in index
     * location [1] is the count of nodes.
     *
     * As can be seen in the method average() immediately below, the returned average
     * value is calculated as "sum / count".
     *
     * Depth first search of the tree is based on recursion. This will result
     * in very few lines of code within the helper method.
     *
     * Pseudocode:
     * if tree is empty, return {0,0} an array, which has sum and count
     * declare and initialize an int variables sum and count to 0
     * Using preorder traversal,
     * add the node data value to the sum variable
     * declare an int array called leftSumCount and set it to method call itself for left node
     * declare an int array called rightSumCount and set it to method call itself for right node
     * add the leftSumCount[0] and rightSumCount[0], which holds the sum, and set it back to sum
     * update the count variable by adding leftSumCount[1] and rightSumCount[1], which holds the count
     * return an array, which has sum and count to calculate average
     */

    public double average() {
        int[] sumAndCount = averageHelper(root);
        return (double) sumAndCount[0] / sumAndCount[1];
    }

    private int[] averageHelper(Node n) {

        // ADD YOUR CODE HERE -- USE DEPTH FIRST SEARCH OF
        // BINARY TREE (WHICH IS BASED ON RECURSION)

        // RECALL, IF THE TREE IS EMPTY, RETURN 0 FOR BOTH THE SUM AND
        // COUNT LOCATIONS IN THE RETURNED ARRAY AS SHOWN BELOW, ELSE
        // THE 'SUM' IS RETURNED IN INDEX LOCATION 0, AND COUNT IS LOCATION 1
        if (n == null) {
           return new int[]{0,0};
        }
        int sum = 0, count = 0;
        sum += n.data;
        count++;
        int[] leftSumCount = averageHelper(n.left);
        int[] rightSumCount = averageHelper(n.right);
        sum += leftSumCount[0] + rightSumCount[0];
        count += leftSumCount[1] + rightSumCount[1];

        return new int[] {sum, count};
    }
}