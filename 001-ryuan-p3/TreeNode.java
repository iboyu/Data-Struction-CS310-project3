
//-------------------------------------------------------------
// DO NOT EDIT ANYTHING FOR THIS CLASS EXCEPT TO ADD JAVADOCS
//-------------------------------------------------------------
import java.io.Serializable;

//Tree node used in a binary huffman tree
/**
 * This is the help class that can help us to create
 * a tree.
 * @author 15409
 *
 */
public class TreeNode implements Serializable, Comparable<TreeNode> {

    // bad practice to have public inst. variables,
    // but we want to test this more easily...

    // count for the character (leaf node) or
    // total of counts from both children (internal node)
    /**
     * the total of counts from both children.
     */
    public int count;

    // character represented by this node
    // internal node: keep character to be null
    /**
     * the character represented by the node.
     */
    public Character character = null;

    // children links
    /**
     * the children in the tree of left and right.
     */
    public TreeNode left, right;
    /**
 * Set the number in the node.
 * @param count use as the number.
 */
    public TreeNode(int count) {
        this.count = count;
    }
    /**
 * construct a node with number and character.
 * @param count use as number.
 * @param character use as character.
 */
    public TreeNode(int count, Character character) {
        this.count = count;
        this.character = character;
    }
    /**
 * Set the left child.
 * @param left use this as left of the children.
 */
    public void setLeft(TreeNode left) {
        this.left = left;
    }
    /**
 * Set the right child.
 * @param right use this as the right of the children.
 */
    public void setRight(TreeNode right) {
        this.right = right;
    }
    /**
 * Compare the other node with the this node, and 
 * return the result to decide where should put the 
 * node. 
 * @param otherNode use as to compare the node and
 * @return where should put the node.
 */
    public int compareTo(TreeNode otherNode) {
        if (this.count - otherNode.count != 0) {
            return (this.count - otherNode.count); // compare count
        } else {
            if (this.character != null && otherNode.character != null) {// use char to break the tie
                return (this.character - otherNode.character);
                // same character + same count would be a tie
            } else {
                return (this.count - otherNode.count);
                // null + same count would be a tie
            }
        }
    }
    /**
 * rewrite equal method.
 */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TreeNode)) {
            return false;
        }
        TreeNode otherNode = (TreeNode) o;
        return (this.compareTo(otherNode) == 0);
    }
    /**
 * To string method used to print.
 * @return the string of the count.
 */
    public String toString() {
        return "<" + this.character + "," + this.count + ">";
    }
}