package bstmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private Node root;
    private int size;

    public BSTMap() {
        clear();
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null)
            return false;
        return researchKey(root, key) != null;
    }

    private V researchKey(Node node, K k){
        if (node == null){
            return null;
        }
        if (node.key == k){
            return node.val;
        }
        else if (node.key.compareTo(k) > 0){
            return researchKey(node.left, k);
        }
        else if (node.key.compareTo(k) < 0)
            return researchKey(node.right, k);
        return null;
    }


    @Override
    public V get(K key) {
        if ( !containsKey(key) )
            return null;
        else{
            return researchKey(root, key);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        root = setItem(root, key, value);
        size += 1;
    }
    private Node setItem(Node n, K key, V val){
        if (n == null){
            return new Node(key, val);
        }
        if (n.key.compareTo(key) == 0){
            n.val = val;
        }
        else if (n.key.compareTo(key) > 0){
            n.left = setItem(n.left, key, val);
        }
        else if (n.key.compareTo(key) < 0)
            n.right = setItem(n.right, key, val);
        return n;
    }

    private void midOrder(Node node, ArrayList<K> L){
        if (node == null)
            return;
        midOrder(node.left, L);
        L.add(node.key);
        midOrder(node.right, L);
    }

    @Override
    public Set<K> keySet() {
        ArrayList<K> L = new ArrayList<>();
        midOrder(root, L);
        return Set.copyOf(L);
    }

    public void printInOrder(){
        for(K i : this){
            System.out.println(i);
        }
    }


    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        ArrayList<K> L = new ArrayList<>();
        midOrder(root, L);
        return L.iterator();
    }

    private class Node{
        private K key;
        private V val;
        private Node left;
        private Node right;
        private Node(K key, V val){
            this.key = key;
            this.val = val;
            this.left = null;
            this.right = null;
        }

    }

}
