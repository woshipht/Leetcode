package LeetCode_101书.动态规划.基本动态规划_二维;

import java.util.Arrays;

/*
542. 01 矩阵

给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
两个相邻元素间的距离为 1 。

示例 1：
输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
输出：[[0,0,0],[0,1,0],[0,0,0]]

示例 2：
输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
输出：[[0,0,0],[0,1,0],[1,2,1]]


提示：

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
mat 中至少有一个 0
 */
public class 零一矩阵_542 {
    public static void main(String[] args){
        int[][] mat= new int[][]{{1,1,1},{0,0,1},{0,0,0}};

        System.out.println("最佳0距离矩阵为: ");
        for(int[] a : updateMatrix(mat)){
            System.out.println(Arrays.toString(a));
        }
    }

    //想好用动态规划就想想动态规划五步曲!
    //1.确定dp[i][j]的下标以及dp值的含义：  dp[i][j]的值 代表当前位置到最近的一个0的位置的距离!
    //2.确定状态转移方程：                 ① 如果mat[i][j]本身等于0,那么dp[i][j] = 0；
    //                                  ② 如果mat[i][j]本身等于1,那么该位置到最近的一个0的位置的距离为 四周的四个位置
    //                                     即dp[i-1][j],dp[i+1][j],dp[i][j+1],dp[i][j-1] 的值中最小的那个+1
    //                                  ③ 我们需要判断上下左右四个方向，但是每次遍历只能选择2个方向，于是我们需要2次遍历
    //                                  ④ 一直保留所有计算中，最小的那个值作为dp[i][j]
    //3.dp数组的初始化状态：               由于距离的取值中，0是有效数值，所以默认值要避开0；我们默认所有距离一开始的值为MAX-1(由于会发生+1操作，不-1会数组越界)
    //4.确定遍历顺序：                    分析状态转移方程可知当前值依赖上下左右的值来确定，所以递推顺序应该是从左上到右下，然后从右下到左上
    //5.返回值:                          直接返回dp
    public static int[][] updateMatrix(int[][] mat) {
        int[][] dp = new int[mat.length][mat[0].length];
        for(int[] a : dp){
            Arrays.fill(a,Integer.MAX_VALUE-1);
        }

        //左上到右下，即只能从左边和上面两个方向找0
        for(int i=0; i<mat.length; i++){
            for(int j=0; j<mat[0].length; j++){
                if(mat[i][j] == 0){
                    dp[i][j]=0;
                }else {
                    if(j>0){
                        dp[i][j] = dp[i][j] < dp[i][j-1]+1 ? dp[i][j] : dp[i][j-1]+1;
                    }
                    if(i>0){
                        dp[i][j] = dp[i][j] < dp[i-1][j]+1 ? dp[i][j] : dp[i-1][j]+1;
                    }
                }
            }
        }

        //右下到左上，即只能从右边和下面两个方向找0
        for(int i=mat.length-1; i>=0; i--){
            for(int j=mat[0].length-1; j>=0; j--){
                if(mat[i][j] == 0){
                    dp[i][j]=0;
                }else {
                    if(j < mat[0].length-1){
                        dp[i][j] = dp[i][j] < dp[i][j+1]+1 ? dp[i][j] : dp[i][j+1]+1;
                    }
                    if(i < mat.length-1){
                        dp[i][j] = dp[i][j] < dp[i+1][j]+1 ? dp[i][j] : dp[i+1][j]+1;
                    }
                }
            }
        }

        return dp;
    }
}
