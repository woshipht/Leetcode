package LeetCode_101书.动态规划.基本动态规划_二维;

import java.util.Arrays;

/*
221. 最大正方形
在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。


示例 1：
输入：matrix =  [["1","0","1","0","0"],
                ["1","0","1","1","1"],
                ["1","1","1","1","1"],
                ["1","0","0","1","0"]]
输出：4

示例 2：
输入：matrix = [["0","1"],
               ["1","0"]]
输出：1

示例 3：
输入：matrix = [["0"]]
输出：0

提示：
m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] 为 '0' 或 '1'
 */
public class 最大正方形_221 {
    public static void main(String[] args){
        char[][] matrix = new char[][]{ {'1','0','1','0','0'},
                                        {'1','0','1','1','1'},
                                        {'1','1','1','1','1'},
                                        {'1','0','1','1','1'}};

        System.out.println("最大正方形面积为" + maximalSquare(matrix));
    }

    //想好用动态规划就想想动态规划五步曲!
    //1.确定dp[i][j]的下标以及dp值的含义：   dp[i][j]的值 代表以当前位置为正方形最右下角时，正方形最大的边长!
    //2.确定状态转移方程：               ① 如果满足dp[i][j-1]，dp[i-1][j]，dp[i-1][j-1]都大于K，那么证明一定dp[i][j]可以等于
    //                                   K+1,因为这4个位置如果都是1，一定可以组成一个更大的正方形！
    //3.dp数组的初始化状态：               所有i=0,j=0的地方，dp[i][j] = matrix[i][j]！
    //4.确定遍历顺序：                    分析状态转移方程可知当前值依赖左上角3个值来确定，所以递推顺序应该是从左上到右下
    //5.返回值:                          直接返回最大dp[i][j]的值的平方
    public static int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }

        int[][] dp = new int[matrix.length][matrix[0].length];
        int maxLength = 0;

        for(int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[0].length; j++){
                if(matrix[i][j] == '1'){
                    if(i==0 || j==0){
                        dp[i][j] = 1;
                    }else {
                        dp[i][j] = (Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1])+1);
                    }
                    maxLength = Math.max(maxLength,dp[i][j]);
                }
            }
        }

        return maxLength*maxLength;
    }
}
