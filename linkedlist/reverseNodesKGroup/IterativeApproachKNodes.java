package linkedlist.reverseNodesKGroup;

public class IterativeApproachKNodes {
    public ListNode reverseKGroup(ListNode head,int k){
        if(head==null || k==1) return  head;

        ListNode dummy=new ListNode(0);
        dummy.next=head;

        ListNode prevNode=dummy;
        ListNode current=head;

        while (true){
            ListNode kth=getKthNode(current,k);
            if (kth==null) break;

            ListNode grpNext=kth.next;
            ListNode previous=grpNext;
            ListNode temp=current;

            while (temp != grpNext){
                ListNode next=temp.next;
                temp.next=previous;
                previous=temp;
                temp=next;
            }

            ListNode nextGroupStart=prevNode.next;
            prevNode.next=kth;
            prevNode=nextGroupStart;

            current=grpNext;
        }

        return dummy.next;
    }

    private ListNode getKthNode(ListNode current, int k) {
        while (current != null && k > 1) {
            current = current.next;
            k--;
        }
        return current;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        IterativeApproachKNodes iterativeApproach = new IterativeApproachKNodes();
        int k = 3;
        ListNode result = iterativeApproach.reverseKGroup(head, k);

        printList(result);
    }

}

