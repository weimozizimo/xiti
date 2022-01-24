package solution.solution15;


public class Solution {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(-10);
        node1.next = new ListNode(-10);
        node1.next.next = new ListNode(-9);
        node1.next.next.next = new ListNode(-4);
        node1.next.next.next.next = new ListNode(1);
        node1.next.next.next.next = new ListNode(6);
        node1.next.next.next.next.next = new ListNode(6);

        ListNode node2 = new ListNode(-7);
//        node2.next = new ListNode(2);
//        node2.next.next = new ListNode(4);

        ListNode add = add(node1, node2);

        while (add.next==null){
            System.out.println(add.val);
            add = add.next;
        }
    }

    public static ListNode add(ListNode source,ListNode in){
        if(in.val>=source.val){
            if(source.next==null){
                source.next = in;
                return source;
            } else{
                source.next = add(in,source.next);;
                return source;
            }
        }else{
            if(in.next==null){
                in.next = source;
                return in;
            } else{
                return add(in,source);
            }
        }
    }


    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

  }
}

