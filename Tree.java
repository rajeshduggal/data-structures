
public class Tree<T> {
    private Node root;
    private int size = 0;

    Tree() {
    }

    Tree(String str) {
        String[] pairs = str.split(",");
    
        Integer rootNode = Integer.valueOf(pairs[0].split(" ")[0]);
        this.root = new Node<Integer>(rootNode);
        this.size = 1;

        String[] arr;
        for (String srcdest : pairs) {
            arr = srcdest.split(" ");
            size += link((T) Integer.valueOf(arr[0]), (T) Integer.valueOf(arr[1]));
        }
    }

    class Node<T> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        public Node(T value) { this.value = value; }
        public Node(T value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
        
        @Override
        public String toString() {
            return "["+value+"]";
        }
    }

    public boolean isValidIntegerBST() {
        return isValidIntegerBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidIntegerBST(Node node, Integer min, Integer max) {
        if (node == null) return true;
        if ((Integer)node.value < min || (Integer)node.value > max) {
            System.out.println( "This node isn't within range: " + node );
            return false;
        }
        return isValidIntegerBST(node.left, min, (Integer)node.value - 1) && isValidIntegerBST(node.right, (Integer)node.value + 1, max);
    }

    private Node find(T value) {
        return find(root, value);
    }

    private Node find(Node fromNode, T value) {
        Node foundNode = null;
        if (value.equals(fromNode.value)) {
            return fromNode;}
        else {
            if (fromNode.left != null) {
                foundNode = find(fromNode.left, value);
            } else if (fromNode.right != null) {
                foundNode = find(fromNode.right, value);
            }
        }
        return foundNode;
    }

    // Returns how many new nodes were created
    public int link(T src, T dest) {
        int newNodesCreated = 0;
        Node srcNode = find(src);
        if (srcNode == null) {
            srcNode = new Node<T>(src);
            newNodesCreated++;
            root = srcNode;
        }
        Node destNode = find(dest);
        if (destNode == null) {
            destNode = new Node<T>(dest);
            newNodesCreated++;
        }
        if (srcNode.left == null) {
            srcNode.left = destNode;
        } else {
            srcNode.right = destNode;
        }
        return newNodesCreated;
    }
}
