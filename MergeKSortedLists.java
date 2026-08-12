/**
 * Merge K sorted lists
 * 
 * Given an array of k linked-lists lists, each linked-list is sorted in
 * ascending order. Merge all the linked-lists into one sorted linked-list.
 * e.g. input: "1,4,5;1,3,4;2,6", output: [1,1,2,3,4,4,5,6]
 * 
 * @param args command line arg: e.g. "1,4,5;1,3,4;2,6"
 */
void main(String[] args) {
    String[] listStrs = args[0].split(";");
    ListNode[] lists = new ListNode[listStrs.length];
    
    // Create the ListNode[] lists
    for(int i = 0; i < listStrs.length; i++) {
        lists[i] = buildList(listStrs[i]);
    }
    
    ListNode merged = mergeKLists(lists);
    IO.println(listToString(merged));
}


/**
 * 
 * @param listOfInts the comma separated list of ints (e.g. 1,3,5) to parse into a linkedin list.
 * @return the ListNode head of the linked list.
 */
ListNode buildList(String listOfInts) {
    if (listOfInts.isEmpty()) return null;

    String[] intStrArr = listOfInts.split(",");
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;
    
    for (String intStr: intStrArr) {
        curr.next = new ListNode(Integer.parseInt(intStr));
        curr = curr.next;
    }
    return dummy.next;
}

/**
 * Build a String representation of the linked list starting at the head ListNode.
 * 
 * @param head the head ListNode of the linked list.
 * @return the String representation of the linked list
 */
String listToString(ListNode head) {
    StringBuilder sb = new StringBuilder("[");
    ListNode curr = head;
    while (curr != null) {
        sb.append(curr.val);
        if (curr.next != null) sb.append(",");
        curr = curr.next;
    }
    sb.append("]");
    return sb.toString();
}

ListNode mergeKLists(ListNode[] lists) {
    // Queue ordered by the ListNode val field
    PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> a.val - b.val);
    
    // Load the heads of each list into the queue.
    for(ListNode node : lists) {
        if (node != null) pq.offer(node);
    }
    
    ListNode returnListHead = new ListNode(0);
    ListNode curr = returnListHead;

    // Keep popping the smallest val from the lists in the queue,
    // and then push the next ListNode into the queue (if there is a next node).
    while(!pq.isEmpty()) {
        ListNode node = pq.poll();
        curr.next = node;
        curr = curr.next;
        if (node.next != null) pq.offer(node.next);
    }
    return returnListHead.next;
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}