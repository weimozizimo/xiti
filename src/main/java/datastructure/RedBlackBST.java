package datastructure;

import java.util.Scanner;

/**
 * @Description 红黑树
 * @Author weiyifei
 * @date 2022/6/10
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean Red = true;

    private static final boolean Black = false;

    private Node root;


    private class Node {
        Key key;
        Value value;
        Node left, right, parent;
        int N;
        boolean color;

        public Node(Key key, Value value, Node parent, int n, boolean color) {
            this.key = key;
            this.value = value;
            N = n;
            this.color = color;
            this.parent = parent;
        }

    }

    public RedBlackBST() {

    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == Red;
    }

    //左旋
    Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        if(h.right!=null){
            h.right.parent = h;
        }
        x.parent = h.parent;
        h.parent = x;
        x.left = h;
        if(x.left!=null){
            x.left.parent = x;
        }
        if(x.parent!=null){
            int cmp = x.parent.key.compareTo(x.key);
            if(cmp>0) x.parent.left = x;
            else x.parent.right = x;
        }
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        show();
        return x;
    }

    //右旋
    Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        if(h.left!=null){
            h.left.parent = h;
        }
        x.parent = h.parent;
        h.parent = x;
        x.right = h;
        if(x.right!=null){
            x.right.parent = x;
        }
        if(x.parent!=null){
            int cmp = x.parent.key.compareTo(x.key);
            if(cmp>0) x.parent.left = x;
            else x.parent.right = x;
        }
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        show();
        return x;
    }

    private void put(Key key, Value val) {
        root = put(root, null, key, val);
        root.color = Black;
    }

    private Node put(Node h, Node p, Key key, Value val) {
        if (h == null) {
            return new Node(key, val, p, 1, Red);
        }
        //当插入的时候，发现路径上有-4节点，
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        //递归搜索树，直到找到相同的key，修改，或者搜索到底层，进行插入。
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, h, key, val);
        else if (cmp > 0) h.right = put(h.right, h, key, val);
        else h.value = val;

        //判断红黑，进行旋转，调整树的平衡
        if (isRed(h.right) && isRed(h.right.right)) {
            h.right.color = h.color;
            h.color = Red;
            h = rotateLeft(h);
        }
        if (isRed(h.right) && isRed(h.right.left)) {
            //RL问题，先右旋,将问题转换为RR
            h.right = rotateRight(h.right);
            //变色
            h.right.color = h.color;
            h.color = Red;
            //再左旋
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h.left.color = h.color;
            h.color = Red;
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.left.right)) {
            //LR问题，先左旋，将问题转换为LL问题
            h.left = rotateLeft(h.left);
            //变色
            h.left.color = h.color;
            h.color = Red;
            //再右旋
            h = rotateRight(h);
        }

        h.N = size(h.left) + size(h.right) + 1;

        return h;
    }

    private boolean del(Key key) {
        if (null != key) {
            if (null != root) {
                return del(key, root, null);
            }
        }
        return false;
    }

    private boolean del(Key key, Node cur, Node parent) {
        if (null != cur) {
            int cmp = key.compareTo(cur.key);
            //如果key>当前节点，向右检索
            if (cmp > 0) {
                return del(key, cur.right, cur);
            }
            //如果key<当前节点，想左检索
            if (cmp < 0) {
                return del(key, cur.left, cur);
            }
            //当前节点就是要删除的节点
            if (cmp == 0) {
                //删除的节点有两个子节点
                if (null != cur.left && null != cur.right) {
                    delTowChildren(cur);
                    return true;
                }else {
                    //如果没有子节点，直接修复平衡，然后删除
                    if(null==cur.left&&null==cur.right){
                        delLeafFix(cur);
                        if(cur.key.compareTo(parent.key)>0){
                            parent.right = null;
                        }else {
                            parent.left = null;
                        }
                        return true;
                    }else { //如果有一个子节点
                        dleOneChildNode(cur);
                        return true;
                    }

                }
            }

        }
        return false;
    }

    //当被删除节点有两个子节点的情况下的处理
    private void delTowChildren(Node cur) {
        //获取后继节点用于替换被删除节点
        Node replacement = successor(cur);
        //替换节点的左右子节点都是null
        if (null == replacement.right && null == replacement.left) {
            delLeafNode(cur, replacement);
        }else {//用于替换的节点，会存在拥有一个子节点的情况
            cur.key = replacement.key;
            cur.value = replacement.value;
            dleOneChildNode(replacement);
        }
    }


    private void dleOneChildNode(Node delNode) {
        //使用后继节点进行代替
        Node replacement = (null==delNode.left)?delNode.right:delNode.left;
        delLeafNode(delNode,replacement);
    }

    private void delLeafNode(Node cur, Node replacement) {
        cur.key = replacement.key;
        cur.value = replacement.value;
        //进行修复
        delLeafFix(replacement);
        //然后删除
        if (replacement == replacement.parent.right) {
            replacement.parent.right = null;
        } else {
            replacement.parent.left = null;
        }
    }

    /**
     * @return void
     * @Description 删除叶子节点后的修复
     * @Author weiyifei
     */
    private void delLeafFix(Node delNode) {
        //只处理删除节点是黑色的情况，如果是红色则直接删除即可
        while (delNode != root && Black == delNode.color) {
            Node par = delNode.parent;
            Node bro = getBrother(delNode);

            if (delNode.key.compareTo(par.key) > 0) { //被删除节点是右叶子节点
                //如果叶子节点是黑色且不是root，那么他的兄弟节点必然存在，这是由红黑树5定律决定的
                if (Red == bro.color) { //如果兄弟节点是红色,可以推断出其必有两个黑色子节点
                    bro.color = Black;
                    par.color = Red;
                    rotateRight(par);
                } else {
                    if (null == bro.left && null == bro.right) {//如果兄弟节点也是叶子节点，那么直接让兄弟和父节点构成一个-3节点即可
                        bro.color = Red;
                        delNode = par;
                    } else {
                        if (null != bro.left && Red == bro.left.color) {
                            //两种情况，一种是兄弟节点红色， 且有两个节点
                            //另一种是兄弟节点黑色，只有左节点
                            //这两种的处理方式相同
                            bro.color = par.color;
                            par.color = Black;
                            bro.left.color = Black;
                            rotateRight(par);
                            break;
                        } else { //兄弟节点黑色， 但是只有右子节点
                            //这里对bro进行左旋加变色，将情况转换为上面的情况，再进行处理
                            bro.right.color = Black;
                            bro.color = Red;
                            rotateLeft(bro);
                        }
                    }
                }
            } else { //删除的是左节点，和上面方式相同
                if (Red == bro.color) {
                    bro.color = Black;
                    par.color = Red;
                    rotateLeft(par);
                } else {
                    if (null == bro.left && null == bro.right) {
                        bro.color = Red;
                        delNode = par;
                    } else {
                        if (null != bro.right && Red == bro.right.color) {
                            bro.color = par.color;
                            par.color = Black;
                            bro.right.color = Black;
                            rotateLeft(par);
                            break;
                        } else {
                            bro.left.color = Black;
                            bro.color = Red;
                            rotateRight(bro);
                        }
                    }

                }

            }
        }
        delNode.color = Black;
    }

    private void inOrderTraveral(){
        inOrderTraveral(root);
    }

    //中序遍历
    private void inOrderTraveral(Node node){
        if(node==null){
            return;
        }
        inOrderTraveral(node.left);
        System.out.println(node.key+"");
        inOrderTraveral(node.right);
    }


    public static void main(String[] args) {
        RedBlackBST<Integer, Integer> bst = new RedBlackBST<>();
        //使用create方法构造红黑树
        bst.creat(0,1,2,3,4,5,6,7,8,13,12,14,11);
        bst.show();
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请选择操作");
            System.out.println("1.插入\n2.删除\n3.中序遍历\n4.输出树形结构");
            int i = scanner.nextInt();
            switch (i){
                case 1:
                    System.out.println("请输入要插入的key");
                    int num = scanner.nextInt();
                    bst.put(num,0);
                    System.out.println("插入完成");
                    System.out.println("当前树如下");
                    bst.show();
                    break;
                case 2:
                    System.out.println("请输入要删除的key");
                    int key = scanner.nextInt();
                    bst.del(key);
                    System.out.println("删除之后的树");
                    bst.show();
                    break;
                case 3:
                    System.out.println("中序遍历结果");
                    bst.inOrderTraveral();
                    break;
                case 4:
                    System.out.println("树结构如下");
                    bst.show();
                    break;
                default:
                    System.out.println("输入错误请重新选择");
            }
        }
    }

    private void creat(Value value,Key ... nums) {
        for (Key num : nums) {
            put(num,value);
            show();
        }
    }

    /**
     * @return datastructure.RedBlackBST<Key, Value>.Node
     * @Description 获取的兄弟节点
     * @Author weiyifei
     */
    private Node getBrother(Node node) {
        if (null == node) {
            return node;
        }
        Node par = node.parent;
        if (null == par) {
            return null;
        }
        int cmp = node.key.compareTo(par.key);
        if (cmp > 0) return par.left;
        else return par.right;
    }

    //寻找被删除节点的后继节点，即，查找红黑树中数据值大于该节点的最小节点
    private Node successor(Node node) {
        if (node == null) {
            return null;
        }
        Node p = node.right;
        while (null != p.left) {
            p = p.left;
        }
        return p;
    }


    //颜色转换，专门用哦过来处理一个节点的两个红色节点的颜色转换问题
    void flipColors(Node x) {
        x.color = Red;
        x.left.color = Black;
        x.right.color = Black;
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    // 用于获得树的层数
    public int getTreeDepth(Node root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    private void writeArray(Node currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = currNode.key+""+(currNode.color==Red?"r":"b");

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }

    public void show(){
        show(root);
    }

    public void show(Node root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i ++) {
            for (int j = 0; j < arrayWidth; j ++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth/ 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line: res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i ++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2: line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }

}
