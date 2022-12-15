# Data-Structure-II-Java-project3
Huffman Coding  

Overview:  
There are two major components to this project:  
1. Implementing three supporting data structures:  
a. A hash table using Linear Probing  
b. A priority queue implemented as a sorted Singly Linked List  
c. A Binary Tree with linked nodes  
2. Using those data structures to implement Huffman’s algorithm, including:  
a. Constructing a Huffman Tree  
b. Traversing the tree for encoding/decoding  

Implementation/Classes:  
This project will be built using a number of classes representing the component pieces used in Huffman’s algorithm. Here
we provide a description of these classes. Template files are provided for each class in the project package and those
contain further comments and additional details.  
• HashTable (HashTable.java): The implementation of a hash table using linear probing. This class will be used
to help counting the occurrences of incoming characters as well as recording the encoding results.  
• PriorityQueue (PriorityQueue.java): The implementation of a priority queue as a sorted singly linked list. This
class will be used to help constructing the Huffman tree. Do NOT use any dummy nodes in the linked list. For
items added into the priority queue with the same priority level, the dequeue order should be FIFO.  
• TreeNode (TreeNode.java): The implementation of a binary tree node with links to its children. This class is
provided to you and you should NOT change the file except for adding JavaDoc.  
• BinaryTree (BinaryTree.java): The implementation of a binary tree that supports some basic tree operations.
The Huffman tree will be implemented as a binary tree with each leaf node corresponding to one input char.  
• Huffman (Huffman.java): The driver program of Huffman’s algorithm. We will follow the steps as discussed
above to implement Huffman Tree construction and support encoding/decoding. This is also the file that will deal
with file input/output using different streams.  
• BitInputStream (BitInputStream.java) and BitOutputStream (BitOutputStream.java): The stream classes
that provide the facility to read and write individual bits from/to a file. These two classes are provided to you and
you should NOT change the file.  
