import java.util.NoSuchElementException;
import java.util.Iterator;
/**
 * This class is used to build a priority queue which has
 * add, remove and other basic functions.
 * @author RongLian Yuan
 *
 * @param <T> datatype.
 */
public class PriorityQueue<T extends Comparable<T>> implements Iterable<T> {

    // -------------------------------------------------------------
    // DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
    // -------------------------------------------------------------
    /**
 * the first node in the queue.
 */
    private Node<T> head = null;

    // provided linked list node class
    /**
 * linked list node class.
 * @author 15409
 *
 * @param <T> data type.
 */
    private static class Node<T> {
        /**
 * the value of the node in the list.
 */
        private T value;
        /**
 * next means the next node in the list.
 */
        private Node<T> next;
        /**
* get the value of this value.
* @param value get the value;
*/
        public Node(T value) {
            this.value = value;
        }
    }

    // provided toString() method using the iterator
    /**
     * provided toString() method using the iterator.
     * @return string after trim.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        for (T value : this) {
            builder.append(value);
            builder.append(" ");
        }
        return builder.toString().trim();
    }

    // provided iterator, if your code is working, this should
    // work too...
    /**
     * Iterator method with next and hasnext method.
     * @return new iterator.
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = head;

            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T val = current.value;
                current = current.next;
                return val;
            }

            public boolean hasNext() {
                return (current != null);
            }
        };
    }

    // -------------------------------------------------------------
    // END OF PROVIDED "DO NOT EDIT" SECTION
    // -------------------------------------------------------------

    // ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
    /**
     * size decides the number of elements in the priority queue.
     */
    private int size;
    /**
 * constructor. Initialization.
 */
    public PriorityQueue() {
        // Constructor
        // initializing members if needed
        size = 0;

    }
    /**
 * return the number of elements in the priority queue.
 * @return the number of elements in the queue.
 */
    public int size() {
        // Return the number of elements in the priority queue
        // O(1)
        return size; // default return: change or remove as needed
    }
    /**
 * Add a value into the priority queue. Using compareTo method and 
 * decide which should put first. Every add time, compare all values
 * in the queue to make sure the location of the value.
 * @param value insert this value in the queue.
 */
    public void add(T value) {
        // Add a value into the priority queue. Use the value
        // as its priority.

        // The priority queue must be organized as a sorted singly
        // linked list. No dummy nodes.

        // Hint: you will need to decide a way to store/sort the values
        // so that the remove/element methods can also meet the required
        // behavior and big-O in time. Do check the requirements of
        // remove()/element() below before you code this method.

        // O(n) where n is the number of items in queue.

        Node<T> currentNode = new Node<T>(value);
        if (size == 0) {
            head = currentNode;
            size++;
            return;
        }
        if (size == 1) {
            int result = compare(value, head.value);
            if (result < 0) {
                currentNode.next = head;
                head = currentNode;
                size++;
                return;
            }
            if (result >= 0) {
                head.next = currentNode;
                size++;
                return;
            }

        } else {

            Node<T> find = head;

            while (find != null) {

                int result = compare(value, find.value);

                if (result < 0 && find.next != null) {
                    currentNode.next = head;
                    head = currentNode;
                    size++;
                    break;
                }

                if (result == 0) {
                    currentNode.next = find.next;
                    find.next = currentNode;
                    size++;
                    break;
                }

                if (result > 0) {
                    if (find.next != null) {
                        int result1 = compare(value, find.next.value);

                        if (result1 < 0) {
                            currentNode.next = find.next;
                            find.next = currentNode;
                            size++;
                            break;
                        }
                        if (result1 > 0) {

                            find = find.next;

                        }
                        if (result1 == 0) {
                            currentNode.next = find.next;
                            find.next = currentNode;
                            size++;
                            break;
                        }
                    } else {
                        find.next = currentNode;

                        size++;
                        break;
                    }

                }

            }
        }

    }
    /**
 * Remove and return the value with the first priority value.
 * @return the value that removed.
 */
    public T remove() {
        // Remove and return the value with the minimal priority value.
        // If two or more items are of the same priority, keep their order
        // as FIFO, i.e. the one that was added earlier should be removed first.
        // Check main() below for examples.

        // Throw NoSuchElementException if queue is empty.
        // Use this _exact_ error message for the exception
        // (quotes are not part of the message):
        // "Priority queue empty!"

        // O(1)
        if (head == null) {
            throw new NoSuchElementException("Priority queue empty!");
        }

        Node<T> remove = head;
        head = head.next;
        size--;

        return remove.value; // default return: change or remove as needed

    }
    /**
 * Return (but do not remove) the value with the first priority value.
 * @return the first value of the priority value.
 */
    public T element() {
        // Return (but do not remove) the value with the minimal priority value.
        // If two or more items are of the same priority, keep the order
        // as FIFO, i.e. the one that was added earlier should be reported.
        // Check main() below for examples.

        // Throw NoSuchElementException if queue is empty.
        // Use this _exact_ error message for the exception
        // (quotes are not part of the message):
        // "Priority queue empty!"

        // O(1)

        if (head == null) {
            throw new NoSuchElementException("Priority queue empty!");
        }

        return head.value; // default return: change or remove as needed

    }
    /**
 * Return true if value is present in queue.
 * @param value figure out whether the queue has this value.
 * @return true if the queue has and return false if the queue does have.
 */
    public boolean contains(T value) {
        // Return true if value is present in queue;
        // return false otherwise.

        // Hint: remember to use .equals() for comparison.

        // O(n) where n is the number of items in queue.
        Node<T> match = head;
        while (head != null) {

            if (match.value.equals(value)) {
                return true;
            } else {
                match = match.next;
                if (match == null) {
                    return false;
                }
            }

        }

        return false; // default return: change or remove as needed

    }
    /**
 * used to compare value 1 and value 2, with the output, get
 * the order of the value.
 * @param value1 value1 need to be compare.
 * @param value2 value2 need to be compare.
 * @return the integer value which means who put first and who put next.
 */
    private int compare(T value1, T value2) {
        int result;

        result = value1.compareTo(value2);

        return result;
    }

    // -------------------------------------------------------------
    // Main Method For Your Testing -- Edit all you want
    // -------------------------------------------------------------
    /**
 * main method used to test some basic functions.
 * @param args args.
 */
    public static void main(String[] args) {
        PriorityQueue<Character> letters = new PriorityQueue<>();

        // add/size/element/contains
        String chars = "MASON";
        for (int i = 0; i < 5; i++) {
            letters.add(chars.charAt(i));
        }

        // System.out.println(letters.toString());

        if (letters.size() == 5 && letters.element() == 'A' && letters.contains('O') && !letters.contains('B')) {
            System.out.println("Yay 1");
        }

        // remove
        if (letters.remove() == 'A' && letters.size() == 4 && letters.element() == 'M') {
            System.out.println("Yay 2");
        }

        // sequence of add/remove
        PriorityQueue<Integer> nums = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            int val = (i * i) % 17;
            nums.add(val);
        }
        boolean ok = nums.toString().trim().equals("0 1 2 4 8 9 13 13 15 16");
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int val = nums.remove();
            output.append(val);
            output.append(" ");
        }
        if (ok && output.toString().trim().equals("0 1 2 4 8 9 13 13 15 16")) {
            System.out.println("Yay 3");
        }

        // values added with the same priority are kept in FIFO order
        PriorityQueue<String> msgs = new PriorityQueue<>();
        String msg1 = new String("Hello");
        String msg2 = new String("Hello");
        msgs.add(msg1);
        msgs.add(chars);
        msgs.add(msg2);
        if (msgs.toString().trim().equals("Hello Hello MASON") && msgs.contains(msg1) && msgs.contains(msg2)
                && msgs.element() == msg1 && msgs.remove() != msg2) { // use of "==" is intentional here
            System.out.println("Yay 4");
        }

    }

}