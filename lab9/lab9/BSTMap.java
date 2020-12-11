package lab9;

import java.util.*;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author lambdafate
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        int result = key.compareTo(p.key);
        if (result == 0) {
            return p.value;
        } else if (result < 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            root = new Node(key, value);
            return root;
        }
        int result = key.compareTo(p.key);
        // find this node
        if (result == 0) {
            p.value = value;
            return p;
        }
        // insert left subtree
        if (result < 0) {
            if (p.left == null) {
                p.left = new Node(key, value);
                return p;
            }
            return putHelper(key, value, p.left);
        }
        // insert right subtree
        if (p.right == null) {
            p.right = new Node(key, value);
            return p;
        }
        return putHelper(key, value, p.right);
    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        Node node = putHelper(key, value, root);
        if (size == 0 || node.key.compareTo(key) != 0) {
            size += 1;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> ret = new HashSet<>();
        if (root == null) {
            return ret;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.size() > 0) {
            Node node = queue.poll();
            ret.add(node.key);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return ret;
    }

    /* 返回删除key节点后的tree */
    private Node removeHelper(K key, Node node) {
        if (node == null) {
            return null;
        }
        int result = key.compareTo(node.key);
        if (result == 0) {
            Node subtree = node.left;
            if (node.right != null) {
                Node tmp = node.right;
                while (tmp.left != null) {
                    tmp = tmp.left;
                }
                tmp.left = subtree;
                subtree = node.right;
            }
            return subtree;
        } else if (result < 0) {
            node.left = removeHelper(key, node.left);
        } else {
            node.right = removeHelper(key, node.right);
        }
        return node;
    }


    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        V value = get(key);
        if (value != null) {
            root = removeHelper(key, root);
            size -= 1;
        }
        return value;
    }

    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value.  Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V v = get(key);
        if (v != null && v.equals(value)) {
            root = removeHelper(key, root);
            size -= 1;
            return value;
        }
        return null;
    }

    private class BSTMapIterator implements Iterator<K> {
        private Stack<Node> stack;
        private Node work;

        private BSTMapIterator() {
            stack = new Stack<>();
            work = root;
        }

        @Override
        public boolean hasNext() {
            return work != null || stack.size() > 0;
        }

        @Override
        public K next() {
            while (work != null) {
                stack.add(work);
                work = work.left;
            }
            Node ret = stack.pop();
            work = ret.right;
            return ret.key;
        }
    }


    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }
}
