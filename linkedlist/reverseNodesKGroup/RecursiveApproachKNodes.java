package linkedlist.reverseNodesKGroup;

public class RecursiveApproachKNodes {
    public static ListNode reverseKGroup(ListNode head, int k) {

        if (head == null) return null;

        ListNode node = head;
        for (int i = 0; i < k; i++) {
            if (node == null) return head;
            node = node.next;
        }

        ListNode previous = null, current = head, next = null;
        for (int i = 0; i < k; i++) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        head.next = reverseKGroup(current, k);

        return previous;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.value);
            if (head.next != null) System.out.print(" → ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(50);
        head.next = new ListNode(20);
        head.next.next = new ListNode(46);
        head.next.next.next = new ListNode(99);
        head.next.next.next.next = new ListNode(2);

        int k = 2;

        System.out.println("Original List:");
        printList(head);

        ListNode result = reverseKGroup(head, k);

        System.out.println("Reversed in groups of " + k + ":");
        printList(result);
    }
}

