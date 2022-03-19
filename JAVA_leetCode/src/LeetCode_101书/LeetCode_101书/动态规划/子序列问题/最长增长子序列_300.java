package LeetCode_101书.动态规划.子序列问题;

import java.util.Arrays;

/*
300. 最长递增子序列
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。


示例 1：
输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4

示例 2：
输入：nums = [0,1,0,3,2,3]
输出：4

示例 3：
输入：nums = [7,7,7,7,7,7,7]
输出：1

提示：
1 <= nums.length <= 2500
-104 <= nums[i] <= 104
 */
public class 最长增长子序列_300 {
    public static void main(String[] args){
        int[] nums = new int[]{4,10,4,3,8,9};

        System.out.println(lengthOfLIS2(nums));
    }

    /*第一种做法，简单思想！
    想好用动态规划就想想动态规划五步曲!
    1.确定dp[i]的下标以及dp值的含义：   以i这个数结尾，可以构成的最长增长子序列的长度

    2.确定状态转移方程：               当j<i，对于每个dp[i]，其前面所有的dp[j]如果满足有 nums[j] < nums[i]，证明dp[i] = dp[j] + 1
                                    即找到一个比nums[i]小的数，那一定能在这个数的最长增长子序列的长度基础上再多一个nums[i]
                                    只要比较所有小于nums[i]的nums[j]的 dp[j]+1， 选择其中最大的一个就是此时的dp[i]

    3.dp数组的初始化状态：             所有数字都至少有基于自己的长度1，所以初始值为1

    4.确定遍历顺序：                  由于dp[i]的值需要前面dp[j](j<i)来确定，所以递推顺序应该是从前往后

    5.返回值:                        因为计算实现n这个数需要的最大次数，所以返回 max = Max(max, dp[i])
     */
    public static int lengthOfLIS1(int[] nums) {
        if(nums.length <= 1) return nums.length;

        int[] dp = new int[nums.length];
        int max = 1;
        Arrays.fill(dp,1);

        for(int i=0; i<nums.length; i++){
            for(int j=0; j<i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                }
            }
            max = Math.max(dp[i],max);
        }
        return max;
    }

    /*二分查找思想
        我们定义一个 dp 数组，其中 dp[k] 存储长度为 k+1 的最长递增子序列的最后一个数字。
        ①我们遍历每一个位置 i，如果其对应的数字大于 dp 数组中所有数字的值，那么我们把它放在 dp 数组尾部，表示最长递增子序列长度加 1；
        ②如果我们发现这个数字在 dp 数组中比数字 a 大、比数字 b 小，则我们将 b 更新为此数字，使得之后构成递增序列的可能性增大

        例：数组 [10,9,2,5,3,7,101,4]
        num     dp
        10      [10]
        9       [9]
        2       [2]
        5       [2,5]
        3       [2,3]
        7       [2,3,7]
        101     [2,3,7,101]
        18      [2,3,4,101]
     */
    public static int lengthOfLIS2(int[] nums) {
        if(nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        int len = 1;
        dp[0] = nums[0];

        for(int i=1; i<nums.length; i++){
            if(nums[i] > dp[len-1]){
                dp[len] = nums[i];
                len++;
            }else {
                //pos用于确认应该替换的数据的位置
                int low = 0; int high = len;
                while(low < high){
                    int mid = low + (high - low)/2;
                    if(nums[i] > dp[mid]){
                        low = mid+1;
                    }else {
                        high = mid;
                    }
                }
                dp[low] = nums[i];
            }
        }

        return len;
    }
}
