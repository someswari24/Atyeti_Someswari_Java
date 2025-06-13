package linkedlist.reverseLinkedlist;

public class RecursiveApproach {
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(10);
        head.next=new ListNode(50);
        head.next.next=new ListNode(20);
        head.next.next.next=new ListNode(30);
        head.next.next.next.next=new ListNode(40);

        System.out.print("Linked list:");
        printList(head);

        RecursiveApproach reverseLinkedlist=new RecursiveApproach();

        ListNode reverse=reverseLinkedlist.reverseListRecursive(head);

        System.out.print("Reversed Linked List:");
        printList(reverse);
    }
}
