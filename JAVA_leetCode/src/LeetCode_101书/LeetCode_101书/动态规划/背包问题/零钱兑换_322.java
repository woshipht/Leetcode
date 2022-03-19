package LeetCode_101书.动态规划.背包问题;

import java.util.Arrays;

/*
322. 零钱兑换
给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
你可以认为每种硬币的数量是无限的。

示例 1：
输入：coins = [1, 2, 5], amount = 11
输出：3
解释：11 = 5 + 5 + 1

示例 2：
输入：coins = [2], amount = 3
输出：-1

示例 3：
输入：coins = [1], amount = 0
输出：0

提示：
1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104
 */
public class 零钱兑换_322 {
    public static void main(String[] args){
        int[] coins = new int[]{3, 1, 5};
        int amount = 11;

        System.out.println(coinChange(coins,amount));
    }

    public static int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount+1];
        Arrays.fill(dp,Integer.MAX_VALUE-1);
        dp[0] = 0;

        //这个的逻辑是，背包思想，如果我一开始只读进一个数coins[i-1]，讨论只有这一个数时，要实现所有容量情况下需要的次数！
        //            则每一个dp[i]的值都取决于前面的dp[i-coins[i-1]]+1,即当进来数是 coins[i-1] 时，要实现这个dp[i]只能通过 dp[i-coins[i-1]] 这唯一一个方法
        //            而当读进新的一个数coins[i]后，dp[i]原本只能通过 dp[i-coins[i-1]] 这唯一一个方法，但是现在多了一个方法 dp[i-coins[i]] ！！！
        //            所以有的dp[i]会被优化！
        for(int i=1; i<=coins.length; i++){
            for(int j=coins[i-1]; j<=amount; j++){
                dp[j] = Math.min(dp[j], dp[j-coins[i-1]]+1);
            }
            System.out.println(Arrays.toString(dp));
        }

        //这个的逻辑是：背包思想，我每次都读取所有的数，但是容量是从0开始一直增加到max(dp[0]~dp[max])
        //            则每一个dp[i]的值都取决于前面的dp[i-coins[j]]+1，即可以有很多种方法能到dp[i]，选其中最小的一个，在它的基础上+1，这样dp[i]就是最小的
        //            每个dp[i]计算出来之后就是最佳值
//        for (int i = 1; i <= amount; i++) {
//            for (int j = 0; j < coins.length; j++) {
//                if (coins[j] <= i) {
//                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
//                }
//            }
//            System.out.println(Arrays.toString(dp));
//        }

        return dp[amount]>amount? -1:dp[amount];
    }
}
