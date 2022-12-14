import java.io.Serializable;
/**
 * Build a binary tree and realize some basic traversal ways.
 * @author RongLian Yuan
 *
 */
public class BinaryTree implements Serializable {

    // -------------------------------------------------------------
    // DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
    // -------------------------------------------------------------

    // bad practice to have public inst. variables, but we want to test this...
    // Root of tree
    /**
 * the root of the tree.
 */
    public TreeNode root;
    /**
 * set the root of the tree to this root.
 * @param node set the root of the tree as this node.
 */
    public void setRoot(TreeNode node) {
        this.root = node;
    }
    // -------------------------------------------------------------
    // END OF PROVIDED "DO NOT EDIT" SECTION
    // -------------------------------------------------------------
    /**
 * basic node class used to build a linked list.
 * @author RongLian Yuan
 * 
 * @param <T> data type.
 */
    private static class Node<T> {
        /**
* Value of the node.
*/
        private T value;
        /**
 * Next node.
 */
        private Node<T> next;
        /**
 * set the value of the node.
 * @param value set the node value to this value.
 */
        private Node(T value) {
            this.value = value;
        }
        /**
 * get the value of the node.
 * @return the value.
 */
        private T getValue() {
            return value;
        }
    }
    /**
 * A basic linked list with add and remove functions.
 * @author RongLian Yuan
 *
 * @param <T> data type.
 */
    private class LinkList<T> {
        /**
 * The head of the list.
 */
        private Node<T> head = null;
        /**
 * The size of the list.        
 */
        private int size = 0;
        /**
 * add the node at the last of the list.
 * @param value add this value at the last of the list.
 */
        private void add(T value) {

            Node<T> currentNode = new Node<T>(value);
            if (head == null) {
                head = currentNode;

            } else {
                Node<T> tempe = head;
                while (tempe.next != null) {
                    tempe = tempe.next;
                }
                tempe.next = currentNode;
            }
            size++;
        }
        /**
 * remove the head of the list.
 * @return the node that removed.
 */
        private T remove() {
            if (head == null) {
                return null;
            } else {
                Node<T> remove = head;
                head = head.next;
                size--;

                return remove.value;
            }

        }

    }
    /**
 * Return the height of the tree.
 * @return height of the tree.
 */
    public int height() {
        // Return the height of the tree.
        // Return -1 for a null tree
        //
        // Hint: this is doable in _very_ few lines of code
        // if you choose to use recursion.
        //
        // O(H): H as the tree height
        if (root == null) {
            return -1;
        }
        if (getHeight(root) == 1) {
            return 0;
        } else {
            return getHeight(root);
        }

        // default return: change or remove as needed

    }
    /**
 * Get the the number of leaf nodes in the tree.
 * @return the number of the leaf nodes.
 */
    public int numLeaves() {
        // Return the number of leaf nodes in the tree.
        // Return zero for null trees.
        //
        // Hint: this is doable in _very_ few lines of code
        // if you choose to use recursion.
        //
        // O(N): N is the tree size

        return getNumberOfNode(root);

        // default return: change or remove as needed
    }
    /**
 * Return a string representation of the tree follow preOrder 
 * traversal to include all nodes.
 * @return String of the tree in preOrder order.
 */
    public String toStringPreOrder() {
        // Return a string representation of the tree
        // follow PRE-ORDER traversal to include all nodes.

        // Return empty string "" for null trees.
        // Use the toString() method of TreeNode class.
        // Check main method below for examples.

        // Hint: this is doable in _very_ few lines of code
        // if you choose to use recursion.

        return preOrderTree(root);

    }
    /**
 * Return a string representation of the tree follow IN-ORDER 
 * traversal to include all nodes.
 * @return String of the tree in inOrder order.
 */
    public String toStringInOrder() {
        // Return a string representation of the tree
        // follow IN-ORDER traversal to include all nodes.

        // Return empty string "" for null trees.
        // Use the toString() method of TreeNode class.
        // Check main method below for examples.

        // Hint: this is doable in _very_ few lines of code
        // if you choose to use recursion.

        return inOrderTree(root);
        // default return: change or remove as needed
    }
    /**
 * Return a string representation of the tree
 * follow LEVEL-ORDER traversal to include all nodes.
 * Using two linklist, one used as a queue and the other 
 * used to store the regular order of the node.
 * @return the String of the tree in level order.
 */
    public String toStringLevelOrder() {
        // Return a string representation of the tree
        // follow LEVEL-ORDER traversal to include all nodes.

        // Return empty string "" for null trees.
        // Use the toString() method of TreeNode class.
        // Check main method below for examples.

        // Hint: Remember that you can create a local class
        // to help you with this!

        // [Hint]Possible approach 1:
        // It is easy to make a priority queue into a FIFO queue
        // if you think a little bit about it. Reuse your priority
        // queue here to do the level-order traversal.

        // [Hint]Possible approach 2:
        // It is also easy to reuse the linked list class from
        // Project 2 to implement a FIFO queue and help with the
        // level-order traversal.

        LinkList<TreeNode> tree = new LinkList<TreeNode>();
        LinkList<TreeNode> list = new LinkList<TreeNode>();
        tree.add(root);

        while (tree.size > 0) {

            TreeNode current = tree.remove();
            list.add(current);

            if (current.left != null) {
                tree.add(current.left);
            }

            if (current.right != null) {
                tree.add(current.right);
            }

        }
        String myValue = "";
        Node<TreeNode> head = list.head;
        while (head != null) {
            myValue = myValue + head.getValue();
            head = head.next;
        }
        return myValue;

    }
    /**
 * Get the height of the tree.
 * @param root the root of the tree.
 * @return the height.
 */
    private int getHeight(TreeNode root) {
        TreeNode current = root;
        if (root == null) {
            return 0;
        } else {

            int leftHeight = getHeight(current.left);
            int rightHeight = getHeight(current.left);

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
    /**
 *  Return the number of leaf nodes in the tree.
 * @param root the root of the tree.
 * @return the number of leaf nodes.
 */
    private int getNumberOfNode(TreeNode root) {

        if (root == null) {
            return 0;
        } else {

            int leftLeaves = getNumberOfNode(root.left);
            int rightLeaves = getNumberOfNode(root.right);

            if (root.left == null && root.right == null) {
                return leftLeaves + rightLeaves + 1;
            } else {
                return leftLeaves + rightLeaves;
            }
        }
    }
    /**
 * Traversal of the tree in preOrder order.
 * @param root the root of the tree.
 * @return the value of the tree node in  preOrder value.
 */
    private String preOrderTree(TreeNode root) {

        String myValue = "";
        if (root == null) {
            return "";
        }
        myValue += root.toString();
        myValue += preOrderTree(root.left);
        myValue += preOrderTree(root.right);

        return myValue;

    }
    /**
 * Traversal of the tree in inOrder order.
 * @param root the root of the tree.
 * @return the value of the tree node in inOrder value.
 */
    private String inOrderTree(TreeNode root) {

        String myValue = "";
        if (root == null) {
            return "";
        }

        myValue += inOrderTree(root.left);
        myValue += root.toString();
        myValue += inOrderTree(root.right);

        return myValue;
    }

    // -------------------------------------------------------------
    // Main Method For Your Testing -- Edit all you want
    // -------------------------------------------------------------
    /**
 * main method used to test the functions in the class.
 * @param args args.
 */
    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();

        // a single-node tree
        tree.setRoot(new TreeNode(1, 'r'));

        if (tree.height() == 0 && tree.numLeaves() == 1 && tree.toStringPreOrder().equals("<r,1>")) {
            System.out.println("Yay1");
        }

        // set up a tree
        // r,1
        // / \
        // a,2 e,10
        // / \
        // b,3 c,4
        // \
        // d,5
        // Note: this tree is a general binary tree but not a Huffman tree.

        TreeNode node1 = new TreeNode(2, 'a');
        TreeNode node2 = new TreeNode(3, 'b');
        TreeNode node3 = new TreeNode(4, 'c');
        TreeNode node4 = new TreeNode(5, 'd');
        TreeNode node5 = new TreeNode(10, 'e');
        tree.root.setLeft(node1);
        tree.root.setRight(node5);
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);

        //tree basic features
        if (tree.root.left.right.count == 4 && tree.height() == 3 && tree.numLeaves() == 3) {
            System.out.println("Yay2");
        }

        // tree traverals
        if (tree.toStringPreOrder().equals("<r,1><a,2><b,3><c,4><d,5><e,10>")) {
            System.out.println("Yay3");
        }

        if (tree.toStringInOrder().equals("<b,3><a,2><c,4><d,5><r,1><e,10>")) {
            System.out.println("Yay4");
        }

        if (tree.toStringLevelOrder().equals("<r,1><a,2><e,10><b,3><c,4><d,5>")) {
            System.out.println("Yay5");
        }
    }
}
