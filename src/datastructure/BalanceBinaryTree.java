package datastructure;

/**
*@author weiyifei
*@description 平衡二叉树
*@date 2021/7/26
*/
public class BalanceBinaryTree {

    //根节点
    private Node root;

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

        if(num==val){val = num;
            this.deep = 1;
            return 1;
        }
        else if(num<val){
            if(left==null){
                left = new Node(num);
                return 1;
            }
            int dp = left.add(num);
            if(dp+1>deep){
                this.deep = dp+1;
            }
            return dp+1;
        }
        else{
            if(right==null){
                right = new Node(num);
                return 1;
            }
            int dp = right.add(num);
            if(dp+1>deep){
                this.deep = dp+1;
            }
            return dp+1;
        }
    }




}