package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    private int size = 0;

    @Override
    public void clear() {
        size = 0;
        len = 16;
        buckets = createTable(len);
    }

    @Override
    public boolean containsKey(K key) {
        return find(key) != null;
    }

    private int getKeyIndex(K k){
        return (k.hashCode() & 0x7fffffff) % len;
    }

    private Node find(K key){
        Collection<Node> C = buckets[getKeyIndex(key)];
        if (C != null) {
            for (Node i : C) {
                if (i.key.equals(key))
                    return i;
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        Node n = find(key);
        if (n != null)
            return n.value;
        else
            return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        if ((double)(size / len) >= loadfactor)
            resize(len * 2);
        int i = getKeyIndex(key);
        if (buckets[i] == null){
            buckets[i] = createBucket();
            buckets[i].add(createNode(key, value));
            size += 1;
        }
        else{
            Node n = find(key);
            if (n == null) {
                buckets[i].add(createNode(key, value));
                size += 1;
            }
            else
                n.value = value;
        }
    }

    private void resize(int size){
        MyHashMap<K, V> temp = new MyHashMap<>(size);
        for (K i : this){
            temp.put(i, this.get(i));
        }
        this.len = size;
        this.buckets = temp.buckets;
    }

    @Override
    public Set<K> keySet() {
        ArrayList<K> L = new ArrayList<>();
        for (Collection<Node> i : buckets)
        {
            if (i != null){
                for (Node j : i){
                    L.add(j.key);
                }
            }
        }
        return new HashSet<>(L);
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key))
            return null;
        else{
            V retval = get(key);
            buckets[getKeyIndex(key)].remove(find(key));
            size -= 1;
            return retval;
        }
    }

    @Override
    public V remove(K key, V value) {
        if (get(key) == value){
            size -= 1;
            return remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int len;
    private double loadfactor;

    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        len = 16;
        size = 0;
        loadfactor = 0.75;
        buckets = createTable(len);
    }

    public MyHashMap(int initialSize) {
        len = initialSize;
        size = 0;
        loadfactor = 0.75;
        buckets = createTable(len);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        len = initialSize;
        size = 0;
        loadfactor = maxLoad;
        buckets = createTable(len);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}
