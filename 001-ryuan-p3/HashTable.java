import java.util.NoSuchElementException;
import java.util.Iterator;
import java.io.Serializable;
/**
 * Build the hashtable used to realize put, get, rehash functions.
 * @author RongLian Yuan
 *
 * @param <K> used as key type.
 * @param <V> used as value type.
 */
public class HashTable<K, V> implements Serializable {

    // -------------------------------------------------------------
    // DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
    // -------------------------------------------------------------
    /**
 * Entry with k,v used to stire the key and value in later
 * hashtable.
 * @author 15409
 *
 * @param <K> as key.
 * @param <V> as v.
 */
    private class TableEntry<K, V> implements Serializable {
        /**
         * the key used to store.
         */
        private K key;
        /**
         * the value to store.
         */
        private V value;
        /**
 * create the entry with key and value.
 * @param key used as key.
 * @param value used as value.
 */
        public TableEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        /**
 * get the key of the entry.
 * @return key.
 */
        public K getKey() {
            return key;
        }
        /**
 * get the value of the entry.
 * @return value.
 */
        public V getValue() {
            return value;
        }
        /**
 * get the key and value as string format.
 * @return to string key value.
 */
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }
    /**
 * array of the hashtable with data type k,v.
 */
    private TableEntry<K, V>[] storage;

    // -------------------------------------------------------------
    // END OF PROVIDED "DO NOT EDIT" SECTION
    // -------------------------------------------------------------

    // ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
    /**
 * the size of the table.
 */
    private int size;
    /**
 * create a tomb entry use null as key and value.
 */
    final private TableEntry<K, V> tomb = new TableEntry<K, V>(null, null);
    /**
 * Create a hash table with k keys and v values.
 * @param size the size of the table which assume bigger than 2.
 */
    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        // Create a hash table where the initial storage
        // is size and K keys can be mapped to V values.
        // You may assume size is >= 2
        storage = new TableEntry[size];

    }
    /**
 * return how big the storage is.
 * @return the length of the array.
 */
    public int getCapacity() {
        // return how big the storage is
        // O(1)

        return storage.length; // default return: change or remove as needed
    }
    /**
 * get the number of elements in the table.
 * @return the number of elements.
 */
    public int size() {
        // return the number of elements in the table
        // O(1)

        return size; // default return: change or remove as needed
    }
    /**
 * put the value at the key location and use linear probing idea.
 * If the table is used more than 80%, double size the table and
 * rehash all data.
 * @param key use this key to store value
 * @param val the value needed to be stored.
 * @return ture if store successfully and return false if store failed.
 */
    public boolean put(K key, V val) {

        // Place value val at the location of key.
        // Use linear probing if that location is in use.

        // Return false w/o updating the table if
        // either key or val is null; otherwise return true.

        // Hint: Make a TableEntry to store in storage
        // and use the absolute value of key.hashCode() for
        // the probe start. You may also need to ensure
        // a valid index that is within bound.

        // If the key already exists in the table
        // replace the current value with val.

        // If the key isn't in the table and the table
        // is >= 80% full, rehash to ensure the table is
        // expanded to twice the size and add in the key,val.

        // Worst case: O(n), Average case: O(1)

        if (key == null || val == null) {
            return false;
        } else {
            TableEntry<K, V> entry = new TableEntry<K, V>(key, val);
            int hash = key.hashCode();
            hash = Math.abs(hash);

            int index = hash % (storage.length);

            if (storage[index] == null || storage[index] == tomb) {
                storage[index] = entry;
                size++;

                if (size >= storage.length * 0.8) {
                    rehash(getCapacity() * 2);
                }

                return true;
            } else if (storage[index].getKey().equals(key)) {
                storage[index] = entry;
                return true;
            } else {
                int newIndex = (index + 1) % (storage.length);
                while (newIndex != index) {
                    if (storage[newIndex] == null || storage[index] == tomb) {
                        storage[newIndex] = entry;
                        size++;

                        if (size >= storage.length * 0.8) {
                            rehash(getCapacity() * 2);
                        }

                        return true;
                    } else if (storage[newIndex].getKey().equals(key)) {
                        storage[newIndex] = entry;
                        return true;
                    } else {
                        newIndex = (newIndex + 1) % (storage.length);
                    }
                }
            }

            return true;
        }
    }
    /**
 * get the value with the given key from the table. If
 * the key does not exist, return null.
 * @param key get the value with this key.
 * @return the value with the key and return null if there is no such key.
 */
    public V get(K key) {
        // Given a key, return the value from the table.

        // If the key is not in the table, return null.

        // Worst case: O(n), Average case: O(1)

        int hash = key.hashCode();
        hash = Math.abs(hash);
        int index = hash % (storage.length);
        if (storage[index] == null) {
            return null;
        } else if (storage[index].getKey().equals(key)) {

            return storage[index].getValue();
        }

        int newIndex = (index + 1) % (storage.length);
        while (newIndex != index) {
            if (storage[newIndex] == tomb) {
                newIndex = (newIndex + 1) % (storage.length);
                continue;
            }
            if (storage[newIndex] == null) {
                break;
            } else if (storage[newIndex].getKey().equals(key)) {
                return storage[newIndex].getValue();
            } else {
                newIndex = (newIndex + 1) % (storage.length);
            }
        }
        return null;

        // default return: change or remove as needed

    }
    /**
 * check if the hashtable exist data before.
 * @param loc the location of the hashtable
 * @return ture if there is a tomb here and return false if there is not a tomb here before.
 */
    public boolean isTombstone(int loc) {
        // this is a helper method needed for printing

        // return whether or not there is a tombstone at the
        // given index

        // O(1)

        if (storage[loc] == null) {
            return false;
        }
        if (storage[loc] == tomb) {
            return true;
        }
        return false;
        // default return: change or remove as needed
    }
    /**
 * rehash the hashtable if given the new size of the 
 * table.
 * @param size the new size given to do the rehash.
 * @return return true means rehash successfully and return false means failed.
 */
    @SuppressWarnings("unchecked")
    public boolean rehash(int size) {
        // Increase or decrease the size of the storage,
        // rehashing all values.

        // If the new size won't fit all the elements,
        // return false and do not rehash. Return true
        // if you were able to rehash.

        if (size < this.size) {
            return false;
        } else {
            TableEntry<K, V>[] oldStorage = storage;
            storage = new TableEntry[size];
            TableEntry<K, V> oldEntry;
            for (int i = 0; i < oldStorage.length; i++) {
                if (oldStorage[i] != null) {

                    oldEntry = oldStorage[i];
                    K key2 = oldEntry.getKey();
                    V value2 = oldEntry.getValue();

                    TableEntry<K, V> entry2 = new TableEntry<K, V>(key2, value2);
                    int hash2 = key2.hashCode();
                    hash2 = Math.abs(hash2);

                    int index2 = hash2 % (storage.length);

                    if (storage[index2] == null) {
                        storage[index2] = entry2;

                    } else if (storage[index2].getKey().equals(key2)) {
                        storage[index2] = entry2;

                    } else {
                        int newIndex2 = (index2 + 1) % (storage.length);
                        while (newIndex2 != index2) {
                            if (storage[newIndex2] == null) {
                                storage[newIndex2] = entry2;
                                break;

                            } else if (storage[newIndex2].getKey().equals(key2)) {
                                storage[newIndex2] = entry2;
                                break;

                            } else {
                                newIndex2 = (newIndex2 + 1) % (storage.length);
                            }
                        }
                    }

                }
            }

            return true;
        }

        // default return: change or remove as needed
    }
    /**
 * remove the given key from the table. If the key not
 * exist, return null.
 * @param key remove the value associate with this key
 * @return the value that removed.
 */
    public V remove(K key) {
        // Remove the given key (and associated value)
        // from the table. Return the value removed.
        // If the key is not in the table, return null.

        // Hint 1: Remember to leave a tombstone!
        // Hint 2: Does it matter what a tombstone is?
        // Yes and no... You need to be able to tell
        // the difference between an empty spot and
        // a tombstone and you also need to be able
        // to tell the difference between a "real"
        // element and a tombstone.

        // Worst case: O(n), Average case: O(1)

        int hash = key.hashCode();
        hash = Math.abs(hash);
        int index = hash % (storage.length);

        if (storage[index] == null) {
            return null;
        } else if (storage[index].getKey().equals(key)) {
            TableEntry<K, V> entry = storage[index];

            storage[index] = tomb;
            size--;
            return entry.getValue();

        }
        int newIndex = (index + 1) % (storage.length);
        while (newIndex != index) {
            if (storage[newIndex] == null) {
                newIndex = (newIndex + 1) % (storage.length);
                continue;
            } else if (storage[newIndex].getKey().equals(key)) {
                TableEntry<K, V> entry = storage[index];

                storage[newIndex] = tomb;

                size--;
                return entry.getValue();
            } else {
                newIndex = (newIndex + 1) % (storage.length);
            }

        }

        return null; // default return: change or remove as needed

    }

    // -------------------------------------------------------------
    // PROVIDED METHODS BELOW
    // DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
    // -------------------------------------------------------------
    /**
 * get the not null hashtable part.
 * @return the not tomb hashtable part.
 */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && !isTombstone(i)) {
                s.append(storage[i] + "\n");
            }
        }
        return s.toString().trim();
    }
    /**
 * to string the hashtable seperated by [ ].
 * @return the string of the hashtable.
 */
    public String toStringDebug() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < storage.length; i++) {
            if (!isTombstone(i)) {
                s.append("[" + i + "]: " + storage[i] + "\n");
            } else {
                s.append("[" + i + "]: tombstone\n");
            }

        }
        return s.toString().trim();
    }

    // -------------------------------------------------------------
    // END OF PROVIDED METHODS SECTION
    // -------------------------------------------------------------

    // -------------------------------------------------------------
    // TESTING CODE
    // -------------------------------------------------------------
    /**
 * main method for testing, edit as much as you want.
 * @param args args.
 */
    public static void main(String[] args) {
        // main method for testing, edit as much as you want
        HashTable<String, Integer> ht1 = new HashTable<>(10);
        HashTable<Integer, Character> ht2 = new HashTable<>(5);

        // initialize
        if (ht1.getCapacity() == 10 && ht2.getCapacity() == 5 && ht1.size() == 0 && ht2.size() == 0) {
            System.out.println("Yay 1");
        }

        // put
        ht1.put("a", 1); // "a".hashCode = 97
        ht1.put("b", 1); // "b".hashCode = 98
        ht1.put("b", 2); // update
        ht1.put("b", 3);

        // System.out.println(ht1);
        // System.out.println(ht1.toStringDebug());

        if (ht1.toString().equals("a:1\nb:3") && ht1.toStringDebug().equals(
                "[0]: null\n[1]: null\n[2]: null\n[3]: null\n[4]: null\n[5]: null\n[6]: null\n[7]: a:1\n[8]: b:3\n[9]: null")) {
            System.out.println("Yay 2");
        }

        if (!ht1.put(null, 0) && ht1.getCapacity() == 10 && ht1.size() == 2 && ht1.get("a") == 1 && ht1.get("b") == 3) {
            System.out.println("Yay 3");
        }

        // put with collision
        ht2.put(12, 'A');
        ht2.put(22, 'B');
        ht2.put(37, 'C');
        // System.out.println(ht2.toStringDebug());
        ht2.put(47, 'D');

        // System.out.println(ht2);
        // System.out.println(ht2.toStringDebug());

        if (ht2.getCapacity() == 10 && ht2.size() == 4 && ht2.get(1) == null && ht2.get(12) == 'A' && ht2.get(22) == 'B'
                && ht2.get(37) == 'C' && ht2.get(47) == 'D') {
            System.out.println("Yay 4");
        }

        if (ht2.toString().equals("12:A\n22:B\n47:D\n37:C") && ht2.toStringDebug().equals(
                "[0]: null\n[1]: null\n[2]: 12:A\n[3]: 22:B\n[4]: null\n[5]: null\n[6]: null\n[7]: 47:D\n[8]: 37:C\n[9]: null")) {
            System.out.println("Yay 5");
        }

        // remove
        HashTable<String, Integer> ht3 = new HashTable<>(2);
        ht3.put("apple", 1); // hashCode: 93029210

        if (ht3.remove("apple") == 1 && ht3.remove("banana") == null && ht3.toString().equals("")
                && ht3.toStringDebug().equals("[0]: tombstone\n[1]: null")) {
            ht3.put("B", 1);
            if (ht3.toString().equals("B:1") && ht3.toStringDebug().equals("[0]: B:1\n[1]: null")) {
                System.out.println("Yay 6");
            }
        }

        // rehash
        if (!ht2.rehash(2) && ht2.size() == 4 && ht2.getCapacity() == 10) {
            System.out.println("Yay 7");
        }

        if (ht2.rehash(7) && ht2.size() == 4 && ht2.getCapacity() == 7) {
            System.out.println("Yay 8");
        }
        // System.out.println(ht2);
        // System.out.println(ht2.toStringDebug());

        if (ht2.toString().equals("22:B\n37:C\n12:A\n47:D") && ht2.toStringDebug()
                .equals("[0]: null\n[1]: 22:B\n[2]: 37:C\n[3]: null\n[4]: null\n[5]: 12:A\n[6]: 47:D")) {
            System.out.println("Yay 9");
        }

    }

}