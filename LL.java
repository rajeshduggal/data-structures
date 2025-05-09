
public class LL<T> {
    private Node head;
    private Node tail;
    private int size;

    LL() {
        this.size = 0;
    }

    LL(String str) {
        String[] pairs = str.split(",");
    
        // Return a head node containing a Character value of the first character of str.
        
        Character firstChar = (pairs[0].split(" ")[0]).charAt(0);
        this.head = new Node<Character>(firstChar);
        this.size = 1;

        char[] arr;
        for (String srcdest : pairs) {
            arr = srcdest.toCharArray();
            size += link((T) Character.valueOf(arr[0]), (T) Character.valueOf(arr[2]));
        }
    }

    public void insertFirst(T value) {
        Node<T> n = new Node<>(value);
        n.next = head;
        head = n;
        if (tail == null) tail = head;
        size++;
    }

    class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) { this.value = value; }
        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
        public Node(char charAt) {
            this.value = (T) Character.valueOf(charAt);
        }
        @Override
        public String toString() {
            return "["+value+"]";
        }
    }

    private Node find(T value) {
        int c = 0; // The list might be cycling, so just traverse the first 20.
        Node currentNode = head;
        while (currentNode != null && c < 20) {
            if (value.equals(currentNode.value)) return currentNode;
            currentNode = currentNode.next;
            c++;
        }
        return null;
    }

    // Returns how many new nodes were created
    public int link(T src, T dest) {
        int newNodesCreated = 0;
        Node srcNode = find(src);
        if (srcNode == null) {
            srcNode = new Node<T>(src);
            newNodesCreated++;
            head = srcNode;
        }
        Node destNode = find(dest);
        if (destNode == null) {
            destNode = new Node<T>(dest);
            newNodesCreated++;
        }
        srcNode.next = destNode;
        return newNodesCreated;
    }

    @Override
    public String toString() {
        int c = 0; // The list might be cycling, so just traverse the first 20.
        Node currentNode = this.head;
        StringBuilder sb = new StringBuilder();
        while (currentNode != null && c < 20) {
            sb.append( currentNode + " -> ");
            currentNode = currentNode.next;
            c++;
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    public boolean hasCycle() {
        Node fast = head.next;
        Node slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}
