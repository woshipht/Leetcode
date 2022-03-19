package LeetCode_101书.树.树的递归;

/*
543. 二叉树的直径
给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。

示例 :
给定二叉树

          1
         / \
        2   3
       / \
      4   5
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 */
public class 二叉树的直径_543 {
    static int total = 0;

    public static void main(String[] args) {
        TreeNode node_4 = new TreeNode(4);
        TreeNode node_5 = new TreeNode(5);
        TreeNode node_2 = new TreeNode(2,node_4,node_5);
        TreeNode node_3 = new TreeNode(3);
        TreeNode node_1 = new TreeNode(1,node_2,node_3);
        System.out.println(diameterOfBinaryTree(node_1));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /*核心思路：  其实就是深度优先搜索！

                记录一个节点的左节点中最长的长度，以及右节点中最长的长度，比较左右长度选最长的一个交给该节点的父节点.(它是父节点的左右子节点)
                这样的话对一个节点来说，经过这个节点的最长路径长度就 等于 它左节点最长的长度 + 它右节点最长的长度

                因为深度优先搜索会遍历所有节点，所以我们只需要选择 经过每一个节点的最长路径长度 中最大的一个就可以了，故需要一个全局变量！
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        dfs(root);

        return total;
    }

    public static int dfs(TreeNode root){
        if(root == null) return 0;

        int leftLength = dfs(root.left);
        int rightLength = dfs(root.right);

        total = Math.max(total,leftLength+rightLength);

        return Math.max(leftLength,rightLength)+1;
    }
}
