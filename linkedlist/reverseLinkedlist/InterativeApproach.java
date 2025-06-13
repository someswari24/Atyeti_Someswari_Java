package linkedlist.reverseLinkedlist;

public class InterativeApproach {
    public ListNode reverseLinkedList(ListNode head){
        ListNode prevNode=null;
        ListNode current=head;

        while (current!=null){
            ListNode temp=current.next;
            current.next=prevNode;
            prevNode=current;
            current=temp;
        }

        return prevNode;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(10);
        head.next=new ListNode(50);
        head.next.next=new ListNode(20);
        head.next.next.next=new ListNode(30);
        head.next.next.next.next=new ListNode(40);


        InterativeApproach reverseLinkedlist=new InterativeApproach();

        ListNode reverse=reverseLinkedlist.reverseLinkedList(head);

        while (reverse!=null){
            System.out.print(reverse.value+" ");
            reverse=reverse.next;
        }
    }
}
