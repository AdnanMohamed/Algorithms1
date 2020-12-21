package DataStructures;

public class AVL<Key extends Comparable<Key>, Value> {
    private class Node {
        private int _height;
        private int _size;
        private Node _left, _right;
        private Key _key;
        private Value _val;

        public Node(Key k, Value v) {
            _key = k;
            _val = v;
            _size = 1;
        }
    }

    /** Returns the number of nodes of the tree
     *  rooted at x. returns 0 if x is null.
     */
    private int size(Node x) {
        if (x == null) return 0;
        return x._size;
    }

    private int height(Node x) {
        if (x == null) return -1;
        return x._height;
    }
    /** Returns the height difference between the x's right and left subtrees.
     *  Assumes x is NOT null.
     *  Return values:
     *  0 when left and right subtrees are of equal height.
     *  1 if right subtree's height is more by one than left subtree's height.
     *  2 if right subtree's height is more by two than left subtree's height.
     *  -1 if left subtree's height is more by one than left subtree's height.
     *  -2 if left subtree's height is more by two than left subtree's height.
     */
    private int height_difference(Node x) {
        assert x != null;
        return height(x._right) - height(x._left);
    }

    private Node _root;  // Root of the AVL tree.

    /** Returns the node with key k, or null if not found.
     *
     * @param x: root of an AVL tree. (possibly null)
     * @param k: key to search for.
     */
    private Node search(Node x, Key k) {
        if (x == null || x._key.equals(k))
            return x;
        int cmp = k.compareTo(x._key);
        if (cmp < 0) {
            // the node we are searching for has smaller key than x
            return search(x._left, k);
        }
        else {
            // the node we are searching for has larger key than x.
            return search(x._right, k);
        }
    }
    private Node insert(Node root, Key k, Value v) {
        if (root == null) return new Node(k, v);
        int cmp = k.compareTo(root._key);
        if (cmp < 0) {
            root._left = insert(root._left, k, v);
        }
        else if (cmp > 0) {
            root._right = insert(root._right, k, v);
        }
        else {
            root._val = v;
        }
        root._size = 1 + size(root._left) + size(root._right);
        root._height = 1 + Math.max(height(root._left), height(root._right));
        int height_load = height_difference(root);
        if (height_load == 2) {
            if (height(root._right._right) > height(root._right._left))
                root = lRotate(root);
            else
                root = rlRotate(root);

        }
        else if(height_load == -2){
            if (height(root._left._left) > height(root._left._right))
                root = rRotate(root);
            else
                root = lrRotate(root);
        }

        return root;
    }
    private Node lRotate(Node root){
        Node oldRoot = root;
        Node newRoot = oldRoot._right;
        oldRoot._right = newRoot._left;
        newRoot._left = oldRoot;
        updateSize(newRoot);
        updateHeight(newRoot);
//        newRoot._left._size = 1 + size(newRoot._left._left) + size(newRoot._left._right);
//        newRoot._size = 1 + size(newRoot._left) + size(newRoot._right);
//        newRoot._left._height = 1 + Math.max(height(newRoot._left._left), height(newRoot._left._right));
//        newRoot._height = 1 + Math.max(height(newRoot._left), height(newRoot._right));
        return newRoot;
    }
    private Node rRotate(Node root){
        Node oldRoot = root;
        Node newRoot = oldRoot._left;
        oldRoot._left = newRoot._right;
        newRoot._right = oldRoot;
        updateSize(newRoot);
        updateHeight(newRoot);
//        newRoot._right._size = 1 + size(newRoot._right._left) + size(newRoot._right._right);
//        newRoot._size = 1 + size(newRoot._left) + size(newRoot._right);
//        newRoot._right._height = 1 + Math.max(height(newRoot._right._left), height(newRoot._right._right));
//        newRoot._height = 1 + Math.max(height(newRoot._left), height(newRoot._right));
        return newRoot;
    }
    private Node lrRotate(Node root){
        swapNodes(root, root._left._right);

        // swap root.LRR and root.LRL
        Node tmpNode = root._left._right._right;
        root._left._right._right = root._left._right._left;
        root._left._right._left = tmpNode;

        // swap root.R and root.LRR
        tmpNode = root._right;
        root._right = root._left._right._right;
        root._left._right._right = tmpNode;

        // swap root.R and root.LR
        tmpNode = root._right;
        root._right = root._left._right;
        root._left._right = tmpNode;

        updateHeight(root);
        updateSize(root);

        return root;
    }
    private Node rlRotate(Node root){
        swapNodes(root, root._right._left);

        // swap root.RLL and root.RLR
        Node tmpNode = root._right._left._left;
        root._right._left._left = root._right._left._right;
        root._right._left._right = tmpNode;

        tmpNode = root._left;
        root._left = root._right._left._left;
        root._right._left._left = tmpNode;

        tmpNode = root._left;
        root._left = root._right._left;
        root._right._left = tmpNode;

        updateHeight(root);
        updateSize(root);

        return root;
    }
    private void swapNodes(Node x, Node y) {
        Key tmpKey = x._key;
        x._key = y._key;
        y._key = tmpKey;

        Value tmpVal = x._val;
        x._val = y._val;
        y._val = tmpVal;

        int tmpSize = x._size;
        x._size = y._size;
        y._size = tmpSize;

        int tmpHeight = x._height;
        x._height = y._height;
        y._height = tmpHeight;
    }
    private void updateSize(Node x) {
        if (x != null) {
            x._left._size = size(x._left._left) + size(x._left._right) + 1;
            x._right._size = size(x._right._left) + size(x._right._right) + 1;
            x._size = size(x._right) + size(x._left) + 1;
        }
    }
    private void updateHeight(Node x) {
        if (x!=null) {
            if (x._left != null) {
                x._left._height = 1 + Math.max(height(x._left._left), height(x._left._right));
            }
            if (x._right != null) {
                x._right._height = 1 + Math.max(height(x._right._left), height(x._right._right));
            }
            x._height = 1 + Math.max(height(x._left), height(x._right));
        }

    }

    private boolean isAVL(Node _root) {
        if (_root == null || isLeaf(_root)) return true;
        if(Math.abs(height_difference(_root)) >= 2) return false;
        return isAVL(_root._left) && isAVL(_root._right);
    }
    private boolean isLeaf(Node x) {
        assert x != null;
        return x._left == null && x._right == null;
    }

    public void insert(Key k, Value v) { _root = insert (_root, k, v); }
    public Value get(Key k) {
        Node x = search(_root, k);
        return x == null ? null : x._val;
    }
    public int size() { return size(_root); }

    // %%%%%%%%%  helps in debugging   %%%%%%%%%

    public boolean isAVL() {
        return isAVL(_root);
    }
    private void print(Node x, int lvl) {
        if (x != null) {
            print(x._right, lvl + 1);
            for (int i = 0; i < lvl; ++i) System.out.print("\t");
            System.out.println(x._key);
            print(x._left, lvl + 1);
        }
    }
    public void print() {
        print(_root, 0);
    }
}
