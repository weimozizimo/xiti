package solution.solution16;

import net.sf.jsqlparser.expression.operators.relational.InExpression;

public class Solution {
    public static void main(String[] args) {
        ListNode node = add(1);


        node = swapPairs(node);

        while (node!=null){
            System.out.println(node.val);
            node = node.next;
        }


    }


    public static ListNode add(int n){
        if(n==3){
            return new ListNode(n);
        }else {
            ListNode nod = new ListNode(n);
            nod.next = add(n+1);
            return nod;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public static ListNode swapPairs(ListNode head) {

        if(head!=null&&head.next!=null){
            ListNode temp = null;
            if(head.next.next==null){
                temp = head.next;
            }else {
                temp = head.next;
                temp.next = swapPairs(temp.next);
            }
           head.next = temp.next;
           temp.next = head;
           return temp;
        }else {
            return head;
        }
    }
}
