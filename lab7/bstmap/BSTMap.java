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

    private Node researchKey(Node node, K k){
        if (node == null){
            return null;
        }
        if (node.key.compareTo(k) == 0){
            return node;
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
            return researchKey(root, key).val;
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
        Node node = researchKey(root, key);
        V temp = node.val;
        Node parent = findParent(node);
        if (node.left == null || node.right == null)
            deleteSingleChildNode(node);
        else{
            Node child = findMaxChild(node);
            node.key = child.key;
            node.val = child.val;
            deleteSingleChildNode(child);
        }
        this.size -= 1;
        return temp;
    }

    private void deleteSingleChildNode(Node n) {
        Node node = researchKey(root, n.key);
        Node parent = findParent(node);
        if (parent == null){
            if (node.right != null)
                this.root = node.right;
            else
                this.root = node.left;
        }
        else if (node.right == null) {
            if (parent.key.compareTo(n.key) > 0)
                parent.left = node.left;
            else
                parent.right = node.left;
        }
        else if(node.left == null){
            if (parent.key.compareTo(n.key) > 0)
                parent.left = node.right;
            else
                parent.right = node.right;
        }
    }

    private Node findMaxChild(Node node) {
        Node p = node.left;
        while (p.right != null)
            p = p.right;
        return p;
    }

    private Node findParent(Node node) {
        Node cur = root;
        Node prev = cur;
        if (node.key.compareTo(root.key) == 0)
            return null;
        while ( cur != null){
            int cmp = cur.key.compareTo(node.key);
            if (cmp > 0) {
                prev = cur;
                cur = cur.left;
            }
            else if (cmp < 0) {
                prev = cur;
                cur = cur.right;
            }
            else
                return prev;
        }
        return null;
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
