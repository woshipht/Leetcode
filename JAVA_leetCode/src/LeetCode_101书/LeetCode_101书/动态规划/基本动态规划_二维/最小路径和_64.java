package LeetCode_101书.动态规划.基本动态规划_二维;

/*
64. 最小路径和

给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
说明：每次只能向下或者向右移动一步。

示例 1：
输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。

示例 2：
输入：grid = [[1,2,3],[4,5,6]]
输出：12

提示：
m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 100
 */
public class 最小路径和_64 {
    public static void main(String[] args){
        int[][] grid= new int[][]{{1,2,3},{4,5,6}};

        System.out.println("最短路径长度为" + minPathSum(grid));
    }

    //想好用动态规划就想想动态规划五步曲!
    //1.确定dp[i][j]的下标以及dp值的含义：   dp[i][j]的值 代表到这个位置时，走过的最短的路径长度!
    //2.确定状态转移方程：                ① 由于只能向下或者向右移动，所以只需要考虑 dp[i][j] 等于 dp[i-1][j] 或者 dp[i][j-1]中最小的一个
    //                                    + grid[i][j]
    //3.dp数组的初始化状态：               在 dp[0][0]处由于没有dp[i-1][j] 和 dp[i][j-1]，故 dp[0][0] = grid[0][0]
    //                                  同理： dp[i][0] = dp[i-1][0] + grid[i][0], dp[0][j] = dp[0][j-1] + grid[0][j]
    //4.确定遍历顺序：                    分析状态转移方程可知当前值依赖左上2个值来确定，所以递推顺序应该是从左上到右下
    //5.返回值:                          直接返回dp[grid.length-1][grid[0].length-1]
    public static int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];

        for(int i=0; i<grid.length; i++){
            for (int j=0; j<grid[0].length; j++){
                if(i==0 && j==0){
                    dp[0][0] = grid[0][0];
                }else if(i==0){
                    dp[0][j] = dp[0][j-1] + grid[0][j];
                }else if(j==0){
                    dp[i][0] = dp[i-1][0] + grid[i][0];
                }else {
                    dp[i][j] = dp[i-1][j] < dp[i][j-1] ? dp[i-1][j]+grid[i][j] : dp[i][j-1]+grid[i][j];
                }
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }
}
