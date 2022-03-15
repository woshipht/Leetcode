package LeetCode_101书.动态规划.背包问题;

/*
474. 一和零
给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。

示例 1：
输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
输出：4
解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3

示例 2：
输入：strs = ["10", "0", "1"], m = 1, n = 1
输出：2
解释：最大的子集是 {"0", "1"} ，所以答案是 2

提示：
1 <= strs.length <= 600
1 <= strs[i].length <= 100
strs[i] 仅由 '0' 和 '1' 组成
1 <= m, n <= 100
*/

public class 一和零_474 {
    public static void main(String[] args){
        String[] strs = {"11111","100","1101","1101","11000"};
        int m=5;
        int n=7;

        System.out.println(findMaxForm(strs,m,n));
    }
/*想好用动态规划就想想动态规划五步曲!
    本题为背包问题！
    1.确定dp[i][j][k]的下标以及dp值的含义：   当一共有strs[0] ~ strs[i-1]个字符串时，0的数量小于j，1的数量小于k时，最大子集长度

    2.确定状态转移方程：     判断条件1：是否新进来的字符串strs[i-1]中 0 的数量大于 j
                               1：如果新进来的字符串strs[i-1]中0的数量大于j，证明这个字符串不能放进子集中，否则会直接超出界限！
                                      于是在这个情况等效于，新进来的字符串strs[i-1]其实对比它进来之前没有影响
                                      则 dp[i][j][k] = dp[i-1][j][k] (num[i]因为不能放进来，所以和 i-1 时的状态是保持一致的)

                               2：如果新进来的字符串strs[i-1]中0的数量小于j，证明这个字符串可能放进子集中！

                          判断条件2：是否新进来的字符串strs[i-1]中 1 的数量大于 k
                               1：如果新进来的字符串strs[i-1]中1的数量大于k，证明这个字符串不能放进子集中，否则会直接超出界限！
                                      于是在这个情况等效于，新进来的字符串strs[i-1]其实对比它进来之前没有影响
                                      则 dp[i][j][k] = dp[i-1][j][k] (num[i]因为不能放进来，所以和 i-1 时的状态是保持一致的)

                               2：如果新进来的字符串strs[i-1]中1的数量小于k，证明这个字符串可能放进子集中！

                          判断条件3：如果上述情况确定，是否将这个数放进来
                               1.不放
                               2.放

                          综上:8种情况：
                                情况①：判断条件1.2(true)，判断条件2.2(true)，判断条件3.2(true)，
                                       这个新加的字符串strs[i-1]放进来，等效于 在原本dp[i][j][k] 的状态，变为 dp[i+1][j+(0数量)][k+(1数量)]，且加1
                                       即 dp[i][j][k] = dp[i-1][j-(0数量)][k-(1数量)] + 1;
                                情况②：判断条件1.2(true)，判断条件2.2(true)，判断条件3.1(false)， dp[i][j][k] = dp[i-1][j][k]
                                       情况1和2要综合考虑

                                情况③：判断条件1.1(false)，则不用考虑2和3，dp[i][j][k] = dp[i-1][j][k]
                                情况④：判断条件1.2(true)，判断条件2.1(false)，则不用考虑3，dp[i][j][k] = dp[i-1][j][k]

    3.dp数组的初始化状态：             dp[0][0][0]意味着一个数都不放，0数量为0，1数量为0的最大子集长度，为0

    4.确定遍历顺序：                  由于dp[i][j][k]的值都需要前面数据确定
                                    所以三个循环应该都是从左到右，

    5.返回值:                        因为是需要放入所有数时，能否相加起来等于target，所以返回 dp[num.length-1][m][n]
     */

    public static int findMaxForm(String[] strs, int m, int n) {
        if(strs.length==0) return 0;

        int[][][] dp = new int[strs.length+1][m+1][n+1];
        for(int i=1; i<=strs.length; i++){
            int num1 = numOfOne(strs[i-1]);
            int num0 = strs[i-1].length()-num1;
            System.out.println(num0+" "+num1);
            for(int j=0; j<=m; j++){
                for(int k=0; k<=n; k++){
                    if(num0 <= j && num1 <= k){
                        dp[i][j][k] = Math.max(dp[i-1][j-num0][k-num1]+1,dp[i-1][j][k]);
                    }else {
                        dp[i][j][k] = dp[i-1][j][k];
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    public static int numOfOne(String str){
        int numOf1 = 0;
        for (int i=0; i<str.length(); i++){
            if(str.charAt(i) == '1'){
                numOf1++;
            }
        }
        return numOf1;
    }
}
