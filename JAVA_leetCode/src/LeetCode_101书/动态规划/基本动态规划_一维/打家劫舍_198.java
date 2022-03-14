package LeetCode_101书.动态规划.基本动态规划_一维;

/*
198. 打家劫舍

你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

示例 1：
输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。偷窃到的最高金额 = 1 + 3 = 4 。

示例 2：
输入：[2,7,9,3,1]
输出：12
解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class 打家劫舍_198 {
    public static void main(String[] args){
        int[] nums = new int[]{2,7,9,3,1};
        System.out.println("最高金额为" + rob(nums));
    }

    //想好用动态规划就想想动态规划五步曲!
    //1.确定dp[i]的下标以及dp值的含义：   偷到第i个房子时，最多的金额
    //2.确定状态转移方程：               如果第i个房子不偷，那此时dp[i]=dp[i-1]，即最多的金额等于前一个房子能偷的金额;
    //                                  如果第i个房子偷，那此时dp[i]=dp[i-2]+num[i]，即最多的金额等于前二个房子能偷的金额+当前房子金额;
    //                                  故状态转移方程为：dp[i] = Max(dp[i-1] , dp[i-2]+num[i])
    //3.dp数组的初始化状态：             i=0时，没有房子偷,dp[0]=0; i=1时，只能偷1个房子，dp[1]=num[1]; i=2时，只能偷2个房子,选两者最大;
    //4.确定遍历顺序：                  分析状态转移方程可知当前值依赖前两个值来确定，所以递推顺序应该是从前往后
    //5.返回值:                        因为一共计算偷到第num.length-1个房子，所以返回dp[num.length-1]
    public static int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return nums[0] > nums[1] ? nums[0] : nums[1];

        int prev2 = nums[0];
        int prev1 = nums[0] > nums[1] ? nums[0] : nums[1];
        int now = 0;

        //dp[i] = Max(dp[i-1] , dp[i-2]+num[i])
        for(int i=2; i<nums.length; i++){
            now = (prev2+nums[i]) > prev1 ? (prev2+nums[i]) : prev1;
            prev2 = prev1;
            prev1 = now;
        }

        return now;
    }
}
