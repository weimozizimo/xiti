package datastructure;

/**
*@author weiyifei
*@description 平衡二叉树
*@date 2021/7/26
*/
public class BalanceBinaryTree {

    //根节点
    Node root;

    public BalanceBinaryTree() {
    }

    public void add(int val){
        if(root==null){
            root = new Node(val);
        }else {
            root.add(val);
        }
    }


}

class Node{

    //值
    private int val;

    //深度
    int deep;

    //左子树
    Node left;

    //右子树
    Node right;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
        this.deep = 1;
    }

    public int add(int num){

        int dp = 0 ;

        if(num==val){val = num;
            this.deep = deep+1;
            return this.deep;
        }
        else if(num<val){
            if(left==null){
                left = new Node(num);
                dp = left.deep+1;
            }else {
                dp = left.add(num)+1;
            }
            if(dp>deep){
                this.deep = dp;
            }
            return dp;
        }
        else{
            if(right==null){
                right = new Node(num);
                dp = right.deep+1;
            }else {
                dp = right.add(num)+1;
            }
            if(dp>deep){
                this.deep = dp;
            }
            return dp;
        }
    }


    public Node lSpin(){
        if(right!=null){
            Node re = right;
            Node tmp = right.left;
            right.left = this;
            this.right = tmp;
            return re;
        }else {
            return this;
        }
    }


}