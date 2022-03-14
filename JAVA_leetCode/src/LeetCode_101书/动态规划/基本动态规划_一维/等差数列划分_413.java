package LeetCode_101书.动态规划.基本动态规划_一维;

/*
413. 等差数列划分
如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。

例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。

子数组 是数组中的一个连续序列。

示例 1：
输入：nums = [1,2,3,4]
输出：3
解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。

示例 2：
输入：nums = [1]
输出：0

提示：
1 <= nums.length <= 5000
-1000 <= nums[i] <= 1000

 */

public class 等差数列划分_413 {
    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,4};
        System.out.println("一共有" + numberOfArithmeticSlices(nums) + "个等差数列");
    }

    //想好用动态规划就想想动态规划五步曲!
    //1.确定dp[i]的下标以及dp值的含义：   以num[i]结尾的所有子数组中，有 dp[i] 个等差数列
    //2.确定状态转移方程：               ① 因为以num[i]结尾的子数组中有 dp[i] 个等差数列，如果num[i+1]也能和num[i]及其之前的子数组组成等差，
    //                                  那么所有以num[i]结尾的等差数列，一定也可以组成以num[i+1]结尾的等差数列，即也有 dp[i] 个！
    //                                  同时num[i+1]结尾的等差数列还会多一个：num[i-1] num[i] num[i+1]，即有1个！
    //                                  故总共有 dp[i] + 1 个以num[i]结尾的等差数列
    //                                ② 如果num[i+1]不能和num[i]及其之前的子数组组成等差，那 dp[i+1] 则重置为0！
    //3.dp数组的初始化状态：             i必须大于等于3，故dp[0]=0;dp[1]=0;
    //4.确定遍历顺序：                  分析状态转移方程可知当前值依赖前1个值来确定，所以递推顺序应该是从前往后
    //5.返回值:                        因为一共计算所有等差数列数量，故要把dp[i]进行求和
    public static int numberOfArithmeticSlices(int[] nums) {
        if(nums.length < 3) return 0;

        int[] dp = new int[nums.length];
        int total = 0;

        //如果满足等差：dp[i] = dp[i-1] + 1， 如果不满足等差：dp[i] = 0;
        for(int i=2; i<nums.length; i++){
            if(nums[i] - nums[i-1] == nums[i-1] - nums[i-2]){
                dp[i] = dp[i-1] + 1;
            }
        }

        for (int n : dp){
            total += n;
        }
        return total;
    }
}
