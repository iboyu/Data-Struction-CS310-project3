import java.io.*;
/**
 * This class use to realize the huffman function with decode and
 * encode functions.
 * @author RongLian Yuan
 *
 */
class Huffman implements Serializable {
    // Note: We define this class (and a couple of other classes of
    // this project) as Serializable in order to be able to save
    // the Huffman Object into a file for encoding/decoding.
    // (See main method below for details.)
    // You do not need to do anything special in your implementation
    // for this. When a serializable object gets output into a file,
    // "transient" members will be skipped.

    // -------------------------------------------------------------
    // DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
    // -------------------------------------------------------------

    // default length used to create hashtables
    /**
     * set default length used to create hashtables.
     */
    public static final int DEFAULT_TABLE_LENGTH = 11;

    // original input string to encode
    /**
     * original input string to encode.
     */
    private transient String inputContents = null;

    // hashtable used to count the frequencies of input characters
    /**
     * build hashtable used to count the frequencies of input characters.
     */
    private transient HashTable<Character, Integer> counts = new HashTable<Character, Integer>(DEFAULT_TABLE_LENGTH);

    // priority queue used to build huffman tree
    /**
     * build priority queue used to build huffman tree.
     */
    private transient PriorityQueue<TreeNode> queue = new PriorityQueue<>();

    // huffman tree
    /**
     * build a huffman tre.
     */
    private BinaryTree huffmanTree = new BinaryTree();

    // hashtable used to record the encoding for input characters
    /**
     * hashtable used to record the encoding for input characters.
     */
    private HashTable<Character, String> encodings = new HashTable<>(DEFAULT_TABLE_LENGTH);

    // setters and getters to help testing
    /**
     * setters and getters to help testing.
     * @param counts set the hashtable.
     */
    public void setCounts(HashTable<Character, Integer> counts) {
        this.counts = counts;
    }
    /**
 * get the hashtable.
 * @return this hashtable.
 */
    public HashTable<Character, Integer> getCounts() {
        return counts;
    }
    /**
 * set the queue as this queue.
 * @param queue use this quese to set the queue.
 */
    public void setQueue(PriorityQueue<TreeNode> queue) {
        this.queue = queue;
    }
    /**
 * get the priority queue.
 * @return this queue.
 */
    public PriorityQueue<TreeNode> getQueue() {
        return queue;
    }
    /**
 * Set the tree as input huffman tree.
 * @param huffmanTree use this binary tree as input.
 */
    public void setTree(BinaryTree huffmanTree) {
        this.huffmanTree = huffmanTree;
    }
    /**
 * get the huffman tree.
 * @return huffman tree.
 */
    public BinaryTree getTree() {
        return huffmanTree;
    }
    /**
 * get the encodings.
 * @return the result of encodings.
 */
    public HashTable<Character, String> getEncodings() {
        return encodings;
    }

    // provided methods for encoding
    // generate the encoding result from the huffman tree
    // if you have constructed a correct huffman tree,
    // this would work...
    /**
     * generate the encoding result from the huffman tree.
     */
    public void computeEncodings() {
        computeEncodings(huffmanTree.root, "");
    }

    // recursive helper method for encoding
    /**
     * recursive helper method for encoding.
     * @param currentLoc the tree node that start.
     * @param encoding the answer of the string started with blank.
     */
    private void computeEncodings(TreeNode currentLoc, String encoding) {
        if (currentLoc.character != null) {
            this.encodings.put(currentLoc.character, encoding);
        } else {
            computeEncodings(currentLoc.left, encoding + "0");
            computeEncodings(currentLoc.right, encoding + "1");
        }
    }

    // Use the encoding hashtable to generate a string of
    // 0's and 1's as the encoding of the input.
    // The input might have multiple characters.
    /**
     * Use the encoding hashtable to generate a string of 0's and 1's as the 
     * encoding of the input.The input might have multiple characters.
     * @param input use as a string to encode.
     * @return the string of the answer.
     */
    public String encode(String input) {

        StringBuffer output = new StringBuffer();

        for (char ch : input.toCharArray()) {
            output.append(this.encodings.get(ch));

        }

        return output.toString();

    }

    // After encodings are computed, encode inputContents
    /**
     * After encodings are computed, encode inputContents.
     * @return the string of the encodings.
     */
    public String encode() {

        StringBuffer output = new StringBuffer();

        for (char ch : inputContents.toCharArray()) {
            output.append(this.encodings.get(ch));
        }

        return output.toString();

    }

    // -------------------------------------------------------------
    // END OF PROVIDED "DO NOT EDIT" SECTION
    // -------------------------------------------------------------

    // ADD MORE PRIVATE MEMBERS HERE IF NEEDED!

    /**
 * constructor of this class make some initializations.
 * @param input make this input to a string and start to analyze.
 */
    public Huffman(String input) {
        // Constructor

        // Set inputContents to be input.
        // Perform other initializations if needed.

        inputContents = input;

    }
    /**
 * count how many times a character repeated in a string and 
 * store the times and this character in a hashtable.
 */
    public void createCounts() {
        // Step 1 of Huffman's algorithm:
        // Count the number of occurrences of each character
        // in inputContents; store the result in hashtable counts.

        // Always start with an empty hashtable.

        int repeat = 1;

        // ahkjc324!4^#mmasad
        // aaababccccc

        char charArray[] = inputContents.toCharArray();

        for (int i = 0; i < inputContents.length(); i++) {

            if (counts.get(charArray[i]) == null) {
                counts.put(charArray[i], 1);

            } else {
                repeat = counts.get(charArray[i]) + 1;
                counts.put(charArray[i], repeat);

            }

        }

    }
    /**
 * according to the input, make a queue of the repeat characters,
 * based on the priority queue rule.And add it into queue.
 */
    public void initQueue() {
        // Step 2 of Huffman's algorithm:
        // For each character from inputContents, use the
        // frequency information from hashtable counts
        // (constructed in step 1) to create one leaf
        // TreeNode. Add the node into the priority queue.

        // Follow the original order of inputContents to
        // process characters and add nodes in one by one.
        // Make sure no duplicates are added into the priority
        // queue: only one node for each character.
        // Hint: use contains() of PriorityQueue class; or use
        // a separate HashTable to help you to avoid duplicates.

        // Always start with an empty priority queue.

        HashTable<Character, Integer> myCount = new HashTable<Character, Integer>(DEFAULT_TABLE_LENGTH);
        int repeat = 1;

        char charArray[] = inputContents.toCharArray();

        for (int i = 0; i < inputContents.length(); i++) {

            if (myCount.get(charArray[i]) == null) {
                myCount.put(charArray[i], 1);
            } else {
                repeat = myCount.get(charArray[i]) + 1;
                myCount.put(charArray[i], repeat);

            }

        }

        for (int i = 0; i < inputContents.length(); i++) {

            int counts = myCount.get(charArray[i]);

            TreeNode myNode = new TreeNode(counts, charArray[i]);
            if (!queue.contains(myNode)) {
                queue.add(myNode);
            }
        }
    }
    /**
 * build a tree based on the priority queue.
 */
    public void buildTree() {
        // Step 3 of Huffman's algorithm:
        // Starting form the priority queue initialized in Step 2,
        // merge nodes together into a single Huffman encoding tree.

        // You can assume that the queue has at least two leaf nodes to start.

        while (queue.size() > 1) {

            TreeNode current1 = queue.remove();
            TreeNode current2 = queue.remove();
            int weight = current1.count + current2.count;
            TreeNode parent = new TreeNode(weight);
            parent.left = current1;
            parent.right = current2;

            queue.add(parent);

        }

        huffmanTree.setRoot(queue.remove());

    }
    /**
 * use input as a string and break input as characters and store them 
 * into an array. According the 0 and 1 to decode it.
 * @param input input string.
 * @return string of the answer.
 */
    public String decode(String input) {
        // Step 4 of Huffman's algorithm:
        // Use the constructed Huffman tree from step 3 to decode
        // the input string of 1s and 0s.
        // The input string might contain the encodings of
        // more than one character.

        // Hints:
        // (1) To break the string into a character array (char[]), use:
        // input.toCharArray()
        // (2) To get the numeric value of a character, use:
        // Character.getNumericValue(ch)
        // (3) Remember to start over at the root when you find a
        // valid character.

        String answer = "";
        char letter0 = '0';
        char letter1 = '1';
        char charArray[] = input.toCharArray();
        TreeNode current = huffmanTree.root;

        for (int i = 0; i < charArray.length; i++) {

            if (charArray[i] == letter0) {
                current = current.left;
            }
            if (charArray[i] == letter1) {
                current = current.right;
            }

            if (current.left == null && current.right == null) {
                answer = answer + current.character;
                current = huffmanTree.root;
            }

        }

        return answer; // default return: change or remove as needed

    }

    // -------------------------------------------------------------
    // PROVIDED TESTING CODE: FEEL FREE TO EDIT
    // -------------------------------------------------------------
    /**
     * used to test the basic functions of the class.
     */
    public static void testMain() {

        Huffman huff = new Huffman("cabbeadcdcdcdbbd");


        // step 1: count frequency
        huff.createCounts();
        HashTable<Character, Integer> counts = huff.getCounts();
        // System.out.println(counts);
        // System.out.println(counts.toStringDebug());

        if (counts.size() == 5 && counts.get('a') == 2 && counts.get('e') == 1
                && counts.toString().equals("c:4\nd:5\ne:1\na:2\nb:4")) {
            System.out.println("Yay 1");
        }

        // step 2: initialize priority queue with leaf nodes
        huff.initQueue();
        PriorityQueue<TreeNode> queue = huff.getQueue();
        // System.out.println(queue);
        if (queue.size() == 5 && queue.element().character == 'e' && queue.element().count == 1) {
            System.out.println("Yay 2");
        }

        if (queue.toString().equals("<e,1> <a,2> <b,4> <c,4> <d,5>")) {
            System.out.println("Yay 3");
        }

        // step 3: build huffman tree with the help of priority queue
        huff.buildTree();
        BinaryTree tree = huff.getTree();
        if (tree.root.count == 16 && tree.root.left.count == 7 & tree.root.right.count == 9) {
            System.out.println("Yay 4");
        }

        // System.out.println(tree.toStringPreOrder());
        if (tree.toStringPreOrder().equals("<null,16><null,7><null,3><e,1><a,2><b,4><null,9><c,4><d,5>")) {
            System.out.println("Yay 5");
        }

        // step 4: encoding and decoding
        huff.computeEncodings();
        // System.out.println(huff.getEncodings());

        if (huff.decode("1000101").equals("cab") && huff.encode("cab").equals("1000101")) {
            System.out.println("Yay 6");
        }

    }
    // -------------------------------------------------------------
    // END OF TESTING CODE
    // -------------------------------------------------------------

    // -------------------------------------------------------------
    // DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
    // -------------------------------------------------------------
    // --------------------------------------------------------------------------------
    // How to run:
    // - To run testMain: java Huffman
    // - To encode: java Huffman -e fileToEncode encodedOutputFile
    // HuffmanObjectOutputFile
    // - To decode: java Huffman -d fileToDecode decodedOutputFile
    // HuffmanObjectInputFile
    // --------------------------------------------------------------------------------
    /**
 * testing of Huffman's algorithm.
 * @param args args.
 */
    public static void main(String[] args) {
        // no command-line args: provided testing of Huffman's algorithm
        if (args.length == 0) {
            testMain();
            return;
        }

        // with command-line args: file I/O for encoding/decoding
        if (args[0].equals("-e") && (args.length < 4 || args.length > 4)) {
            System.out.println("Usage: java Huffman -e fileToEncode encodedOutputFile HuffmanObjectOutputFile");
            return;
        } else if (args[0].equals("-d") && (args.length < 4 || args.length > 4)) {
            System.out.println("Usage: java Huffman -d fileToDecode decodedOutputFile HuffmanObjectInputFile");
            return;
        } else if (!args[0].equals("-d") && !args[0].equals("-e")) {
            System.out.println("Usage: java Huffman -[e|d]");
            return;
        }

        String fileAsString;
        Huffman huff;
        try {
            switch (args[0]) {
                case "-e": // encoding

                    // read in fileToEncode
                    fileAsString = getFileContents(args[1]);
                    // System.out.println(fileAsString);

                    // Huffman's algorithm
                    huff = new Huffman(fileAsString);
                    huff.createCounts(); // step 1
                    // System.out.println(hTree.counts);
                    huff.initQueue(); // step 2
                    // System.out.println(hTree.queue);
                    huff.buildTree(); // step 3

                    huff.computeEncodings();

                    // encoding
                    String encoding = huff.encode();
                    // System.out.println("Encoded: " + encoding);

                    // output encoded contents as a sequence of bits into file
                    writeEncodedMessage(encoding, args[2]);

                    // output Huffman object into file
                    writeEncodedObject(huff, args[3]);
                    break;

                case "-d": // decoding

                    // read in from file and construct Huffman object
                    /**
* read in from file and construct Huffman object.
*/
                    huff = getEncodedObject(args[3]);

                    // read in from file the encoded bits and
                    // convert into a string (with only characters '0' and '1')
                    /**
* convert into a string (with only characters '0' and '1').
*/
                    fileAsString = getFileBinaryContents(args[1]);
                    // System.out.println(fileAsString);

                    // decoding
                    /**
* decoding.
*/
                    String decodedMessage = huff.decode(fileAsString);

                    // output decoded contents into file
                    /**
* output decoded contents into file.
*/
                    writeDecodedMessage(decodedMessage, args[2]);
                    break;
            }
        } catch (IOException e) {
            System.out.println("Problem reading or writing to specified file");
            System.out.println(e.toString());
        }

    }

    // output a Huffman Object to a file
    /**
     * output a Huffman Object to a file.
     * @param huff the content of huffman.
     * @param filename get the file name.
     * @throws IOException throw exception.
     */
    public static void writeEncodedObject(Huffman huff, String filename) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))) {
            output.writeObject(huff);
        }
    }

    // read from a file and create a Huffman Object based on the file contents
    /**
     * read from a file and create a Huffman Object based on the file contents.
     * @param filename get the file name.
     * @return huffman content.
     * @throws IOException throw exception.
     */
    public static Huffman getEncodedObject(String filename) throws IOException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename))) {
            return (Huffman) input.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Can not read class from provided file.");
        }
    }

    // read the encoding result (as a string of 0's and 1's) from a file
    /**
     * read the encoding result (as a string of 0's and 1's) from a file.
     * @param filename get the file name.
     * @return the content with the file name.
     * @throws IOException throw exception.
     */
    public static String getFileBinaryContents(String filename) throws IOException {
        StringBuffer fileContents = new StringBuffer();
        try (BitInputStream bs = new BitInputStream(new FileInputStream(filename), true)) {
            while (bs.hasNextBit()) {
                fileContents.append(bs.readBit());
            }
        }
        return fileContents.toString();
    }

    // output the encoding result (a string of 0's and 1's) as a bit sequence into a
    // file
    /**
     * output the encoding result (a string of 0's and 1's) as a bit sequence into a file.
     * @param message get messages.
     * @param filename get file name.
     * @throws IOException throw exception.
     */
    public static void writeEncodedMessage(String message, String filename) throws IOException {
        try (BitOutputStream bs = new BitOutputStream(new FileOutputStream(filename), true)) {
            bs.writeBits(message);
        }
    }

    // read from file and return file contents as a string
    /**
     * read from file and return file contents as a string.
     * @param filename get the file name.
     * @return the file content as string.
     * @throws IOException throw exception.
     */
    public static String getFileContents(String filename) throws IOException {
        StringBuffer fileContents = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String input = br.readLine();
            fileContents.append(input);
            input = br.readLine();

            while (input != null) {
                fileContents.append("\n" + input);
                input = br.readLine();
            }
        }

        return fileContents.toString();
    }

    // out put message as a sequence of bits to file
    /**
     * out put message as a sequence of bits to file.
     * @param message String message.
     * @param filename String filename.
     * @throws IOException throw exception.
     */
    public static void writeDecodedMessage(String message, String filename) throws IOException {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(filename))) {
            br.write(message);
        }
    }

    // -------------------------------------------------------------
    // END OF PROVIDED "DO NOT EDIT" SECTION
    // -------------------------------------------------------------

}