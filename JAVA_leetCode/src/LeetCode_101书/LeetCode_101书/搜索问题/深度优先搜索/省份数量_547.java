package LeetCode_101书.搜索问题.深度优先搜索;

/*
547. 省份数量
有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
返回矩阵中 省份 的数量。

示例 1：
输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
输出：2

示例 2：
输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
输出：3


提示：
1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] 为 1 或 0
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
 */

public class 省份数量_547 {
    public static void main(String[] args) {
        int[][] isConnected = new int[][]{ {1,1,0},
                                           {1,1,0},
                                           {0,0,1}};

        System.out.println(findCircleNum(isConnected));
    }

    //主函数，遍历所有搜索位置，这道题相当于只有isConnected.length个位置，即只有这么多个城市，只要保证每个城市都进入过一次深度搜索遍历即可
    public static int findCircleNum(int[][] isConnected) {
        if(isConnected.length == 0 || isConnected[0].length == 0){
            return 0;
        }

        //是否进入过遍历过程？
        boolean[] isVisit = new boolean[isConnected.length];

        int count = 0;
        for(int i=0; i< isConnected.length; i++){
            //没有进入过遍历过程，那这个城市作为首节点进入遍历过程
            if(!isVisit[i]){
                dps(isConnected,i,isVisit);
                count++;
            }
        }
        return count;
    }

    public static void dps(int[][] isConnected, int i, boolean[] isVisit){
        for (int j=0; j<isConnected.length; j++){
            //没有进入过遍历过程，且该位置有相连关系，对这个城市相连的新城市再次进行遍历，如果城市已经曾经被考虑过，就不用再遍历了
            if(isConnected[i][j] == 1 && !isVisit[j]){
                isVisit[j] = true;
                dps(isConnected,j,isVisit);
            }
        }
    }
}
