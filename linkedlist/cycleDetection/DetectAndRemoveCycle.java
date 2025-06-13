package linkedlist.cycleDetection;

public class DetectAndRemoveCycle {

    public static void detectAndRemoveCycle(ListNode head) {
        ListNode slow = head, fast = head;

        // Detecting cycle using Floyd’s algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                System.out.println("Cycle detected.");
                removeCycle(head, slow); // Step 2 & 3: Remove the cycle
                return;
            }
        }

        System.out.println("No cycle detected.");
    }

    private static void removeCycle(ListNode head, ListNode meetingPoint) {
        ListNode ptr1 = head;
        ListNode ptr2 = meetingPoint;

        // to find the start of the loop
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        ListNode temp = ptr1;
        while (temp.next != ptr1) {
            temp = temp.next;
        }

        temp.next = null;
        System.out.println("Cycle removed.");
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.value + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // creating a cycle: node 5 -> node 3
        head.next.next.next.next.next = head.next.next;

        detectAndRemoveCycle(head);

        System.out.println("After removing of cycle from the linked list:");
        printList(head);
    }
}
