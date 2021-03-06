package LeetCode_101书.搜索问题.深度优先搜索;

/*
695. 岛屿的最大面积
给你一个大小为 m x n 的二进制矩阵 grid 。
岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
岛屿的面积是岛上值为 1 的单元格的数目。
计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。

示例 1：
输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],
            [0,0,0,0,0,0,0,1,1,1,0,0,0],
            [0,1,1,0,1,0,0,0,0,0,0,0,0],
            [0,1,0,0,1,1,0,0,1,0,1,0,0],
            [0,1,0,0,1,1,0,0,1,1,1,0,0],
            [0,0,0,0,0,0,0,0,0,0,1,0,0],
            [0,0,0,0,0,0,0,1,1,1,0,0,0],
            [0,0,0,0,0,0,0,1,1,0,0,0,0]]
输出：6
解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1

示例 2：
输入：grid = [[0,0,0,0,0,0,0,0]]
输出：0

提示：
m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] 为 0 或 1
 */
public class 岛屿的最大面积_695 {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};

        System.out.println(maxAreaOfIsland(grid));
    }

    /*深度优先算法：   深度优先搜索类型的题可以分为主函数和辅函数，主函数用于遍历所有的搜索位置，判断是否可以开始搜索，
                    如果可以即在辅函数进行搜索。 辅函数则负责深度优先搜索的递归调用

                    在辅函数里，一个一定要注意的点是辅函数内递归搜索时，边界条件的判定。边界判定一般有两种写法，

                    一种是先判定是否越界，只有在合法的情况下才进行下一步搜索（即判断放在调用递归函数前）；
                    另一种是不管三七二十一先进行下一步搜索，待下一步搜索开始时再判断是否合法（即判断放在辅函数第一行）

     */
    //主函数，遍历所有搜索位置
    public static int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        int max_area = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 1) {
                    max_area = Math.max(max_area, dfs(grid, i, j));
                }
            }
        }
        return max_area;
    }

    //辅函数：先进行下一步搜索，待下一步搜索开始时再判断是否合法（即判断放在辅函数第一行）
    //      这道题中，我们只要检测到grid[w][l]位置是1，证明这有个岛，然后对该位置周围4个方向的位置检测，是否有岛，没有或者到边界就返回，
    //      如果有岛，再次重复这个过程，检测这个新岛的4个方向，一直到所有检测的岛的4个方向都没有1为止！
    public static int dfs(int[][] grid, int w, int l) {
        if (w < 0 || w >= grid.length || l < 0 || l >= grid[0].length || grid[w][l] == 0) {
            return 0;
        }
        grid[w][l] = 0;

        return 1 + dfs(grid, w + 1, l) + dfs(grid, w - 1, l) + dfs(grid, w, l + 1) + dfs(grid, w, l - 1);
    }
}
