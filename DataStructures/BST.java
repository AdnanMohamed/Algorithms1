package DataStructures;

import java.util.Random;

public class BST<Key extends Comparable<Key>, Val> {
    private class Node {
        private Key _key;
        private Val _value;
        private int _size;
        private Node _left, _right, _parent;

        public Node(Key k, Val v, Node l, Node r, Node p) {
            _key = k;
            _value = v;
            _left = l;
            _right = r;
            _parent = p;
            _size = 1;
        }
        public Node(Key k, Val v) {
            _key = k; _value = v;
            _size = 1;
        }
    }

    private Node _root;

    private Node search(Node root, Key k) {
        if (root == null || root._key.equals(k))
            return root;
        if (k.compareTo(root._key) < 0) {
            // search the left sub-tree.
            return search(root._left, k);
        } else {
            // search the right sub-tree.
            return search(root._right, k);
        }
    }

    private Node min(Node root) {
        if (root == null || root._left == null)
            return root;
        return min(root._left);
    }
    private Node max(Node root) {
        if (root == null || root._right == null)
            return root;
        return min(root._right);
    }
    private Node successor(Node x) {
        assert x != null;

        // case1: x has right-subtree
        if (x._right != null) {
            // if x has a right sub-tree, then its successor is the minimum
            // in its right subtree. (follows from bst property and sucessor def.).
            return min(x._right);
        }
        // case2: x does NOT have right-subtree
        // So its successor y must be x's first parent
        // such that x is in the left branch of y.
        Node y = x._parent;
        while (y != null && y._right == x) {
            x = y;
            y = x._parent;
        }
        return y;
    }
    private Node predecessor(Node x) {
        assert x != null;
        // case1: x has left-subtree
        if (x._left != null) {
            // if x has a left sub-tree, then its predecessor is the maximum
            // in its left subtree. (follows from bst property and pred. def.).
            return max(x._left);
        }

        // case2: x does NOT have left-subtree
        // So its predecessor y must be x's first parent
        // such that x is in the right branch of y.
        Node y = x._parent;
        while (y != null && y._left == x) {
            x = y;
            y = x._parent;
        }
        return y;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        if (k == size(x._left)) return x;
        else if (k < size(x._left)) return select(x._left, k);
        else return select(x._right, k - size(x._left) - 1);
    }

    private int rank(Node x, Key k) {
        if (x == null) throw new IllegalArgumentException();
        if (x._key.equals(k)) {
            return size(x._left);
        }
        else if (x._key.compareTo(k) < 0) {
            return 1 + size(x._left) + rank(x._right, k);
        }
        else {
            return rank(x._left, k);
        }
    }

    private int size(Node x) {
        if (x==null)
            return 0;
        else
            return x._size;
    }

    private Node insert(Node root, Key k, Val v) {
        if (root == null) {
            return new Node(k, v);
        }
        if (root._key.equals(k)) {
            root._value = v;
        }
        if (k.compareTo(root._key) < 0) {
            root._left = insert(root._left, k, v);
            root._left._parent = root;
            root._size += root._left._size;
        } else {
            root._right = insert(root._right, k, v);
            root._right._parent = root;
            root._size += root._right._size;
        }
        root._size = 1 + size(root._left) + size(root._right);
        return root;
    }

    private Node deleteMin(Node root) {
        if (root == null) return null;
        if (root._left == null) return root._right;

        root._left = deleteMin(root._left);
        root._size = 1 + size(root._left) + size(root._right);
        return root;
    }

    private Node deleteMax(Node root) {
        if (root == null) return null;
        if (root._right == null) return root._left;

        root._right = deleteMin(root._right);
        root._size = 1 + size(root._left) + size(root._right);
        return root;
    }

    private Node delete(Node root, Key k) {
        if (root == null) return root;
        if (k.equals(root._key)) {
            if (isLeaf(root)) {
                return null;
            } else if (root._left == null) {
                return root._right;
            } else if (root._right == null) {
                return root._left;
            } else {
                Node successor = successor(root);
                root._value = successor._value;
                root._key = successor._key;
                root._right = deleteMin(root._right);
                if (root._right != null)
                    root._right._parent = root;
            }
        } else if (k.compareTo(root._key) < 0) {
            root._left = delete(root._left, k);
        } else {
            root._right = delete(root._right, k);
        }
        root._size = 1 + size(root._left) + size(root._right);
        return root;
    }

    private boolean isLeaf(Node node) {
        assert node != null;
        return node._size == 1;
    }

    public BST() {}

    public boolean isEmpty() { return _root == null; }
    public Key min() { return min(_root)._key; }
    public Key max() { return max(_root)._key; }
    public Val get(Key k) { return search(_root, k)._value; }
    public void insert(Key k, Val v) {
        _root = insert(_root, k, v);
    }
    public Key deleteMin() { return deleteMin(_root)._key; }
    public Key deleteMax() { return deleteMax(_root)._key; }
    public void delete(Key k) { _root = delete(_root, k);}

    public int rank(Key k) {
        assert !isEmpty();
        return rank(_root, k);
    }
    public Key select(int rank) {
        return select(_root, rank)._key;
    }

    public int size() { return _root == null ? 0 : _root._size; }

    // helps in debugging
    private void print(Node x, int lvl) {
        if (x != null) {
            print(x._right, lvl + 1);
            for (int i = 0; i < lvl; ++i) System.out.print("\t");
            System.out.println(x._key);
            print(x._left, lvl + 1);
        }
    }

    private boolean isBST(Node x) {
        if (x == null) return true;

        if (x._left != null && x._key.compareTo(x._left._key) < 0) return false;
        if (x._right != null && x._key.compareTo(x._right._key) > 0) return false;
        if (isBST(x._left) && isBST(x._right)) return true;
        else return false;
    }

    public void print() {
        print(_root, 0);
    }


    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        bst.insert(7, "Adnan7");
        bst.insert(8, "Adnan8");
        bst.insert(3, "Adnan3");
        bst.insert(6, "Adnan6");
        bst.insert(1, "Adnan1");
        bst.insert(56, "Adnan56");

    }
}
