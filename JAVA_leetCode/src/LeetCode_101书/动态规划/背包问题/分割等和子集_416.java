package LeetCode_101书.动态规划.背包问题;

/*416. 分割等和子集
给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等

示例 1：
输入：nums = [1,5,11,5]
输出：true
解释：数组可以分割成 [1, 5, 5] 和 [11]

示例 2：
输入：nums = [1,2,3,5]
输出：false
解释：数组不能分割成两个元素和相等的子集

提示：
1 <= nums.length <= 200
1 <= nums[i] <= 100
 */
public class 分割等和子集_416 {
    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,6};

        System.out.println(canPartition2(nums));
    }

    /*想好用动态规划就想想动态规划五步曲!
    本题为背包问题！
    1.确定dp[i][j]的下标以及dp值的含义：   当一共有num[0] ~ num[i-1]个数时，它们能否满足 任意组合 求和得到j

    2.确定状态转移方程：                 情况①：如果j比新进来的这个数num[i-1]都要小，那这个数一定不能放进来，否则直接超了！于是这个数不放，等效于就是和这个数进入判断前的状态一样
                                            则 dp[i][j] = dp[i-1][j] (num[i]因为不能放进来，所以和 i-1 时的状态是保持一致的)

                                      情况②：如果j不比新进来的这个数小，那这个数有可能放进来，要判断！
                                             情况③： 如果这个数放进来：
                                                        这个数的值为num[i],如果原本是 dp[i][j], 放进来，那就等效于在i+1(进来一个)，j+num[i](增加的这个值)的位置
                                                    和 dp[i][j]位置的状态是一样的

                                                        例： int[] = {1,2,3,6}    如果dp[2][3] = true(只进来1,2,是否存在他们组合成为3,是存在的)
                                                    现在放进来一个数 3, 那么dp[2+1][3+3] = dp[3][6] (只进来1,2,3,是否存在他们组合成为6)，
                                                    等效于在 dp[2][3] 的基础上加新来的这个数，那么 dp[2][3]如果是true， dp[3][6]也应该是true
                                                        所以等效于dp[i][j] = dp[i-1][j-num[i]]

                                            情况④：  如果这个数不放进来：
                                                    则 dp[i][j] = dp[i-1][j] (num[i]因为不能放进来，所以和 i-1 时的状态是保持一致的)

                                            故综合考虑两种情况，看放与不放哪个能满足，选哪个

    3.dp数组的初始化状态：             dp[0][0]意味着一个数都不放，能否加起来得到0，应该是true

    4.确定遍历顺序：                  由于dp[i][j]的值需要前面 dp[i-1][j] 和 dp[i-1][j-num[i]]确定
                                    所以两个循环应该是从左上到右下，

    5.返回值:                        因为是需要放入所有数时，能否相加起来等于target，所以返回 dp[num.length-1][target]
     */
    public static boolean canPartition1(int[] nums) {
        //当数组长度少于2，不可能平分成2个相同的数
        if(nums.length < 2) return false;

        int total = 0;
        int max = 0;
        for (int num : nums){
            total += num;
            max = Math.max(max,num);
        }
        //当所有数的和为奇数，不可能平分成2个相同的数
        if(total % 2 == 1) return false;

        //想要的目标数！
        int target = total / 2;
        //当数组中最大的数已经比 想要的目标数 还大，不可能平分成2个相同的数
        if(max > target) return false;

        boolean[][] dp = new boolean[nums.length+1][target+1];
        dp[0][0] = true;

        for(int i=1; i<=nums.length; i++){
            for (int j=0; j<=target; j++){
                //即此时想要的和为j，如果j比新进来的这个数都要小，那这个数一定不能放进来，否则直接超了！于是这个数不放，等效于就是和这个数进入判断前的状态一样
                //则 dp[i][j] = dp[i-1][j] (num[i]因为不能放进来，所以和 i-1 时的状态是保持一致的)
                if(j < nums[i-1]){
                    dp[i][j] = dp[i-1][j];
                /*即此时想要的和为j，如果j不比新进来的这个数小，那这个数有可能放进来，要判断！

                情况①：如果这个数放进来：
                      这个数的值为num[i],如果原本是 dp[i][j], 放进来，那就等效于在i+1(进来一个)，j+num[i](增加的这个值)的位置 和 dp[i][j]位置的状态是一样的
                      例： int[] = {1,2,3,6}    如果dp[2][3] = true(只进来1,2,是否存在他们组合成为3,是存在的)
                                               现在放进来一个数 3, 那么dp[2+1][3+3] = dp[3][6] (只进来1,2,3,是否存在他们组合成为6)，
                                               等效于在 dp[2][3] 的基础上加新来的这个数，那么 dp[2][3]如果是true， dp[3][6]也应该是true
                      所以等效于dp[i][j] = dp[i-1][j-num[i]]

                情况②：如果这个数不放进来：
                      则 dp[i][j] = dp[i-1][j] (num[i]因为不能放进来，所以和 i-1 时的状态是保持一致的)

                故综合考虑两种情况，看放与不放哪个能满足，选哪个
                 */
                }else {
                    dp[i][j] = dp[i-1][j-nums[i-1]] | dp[i-1][j];
                }
            }
        }
        return dp[nums.length-1][target];
    }

    public static boolean canPartition2(int[] nums) {
        //当数组长度少于2，不可能平分成2个相同的数
        if(nums.length < 2) return false;

        int total = 0;
        int max = 0;
        for (int num : nums){
            total += num;
            max = Math.max(max,num);
        }
        //当所有数的和为奇数，不可能平分成2个相同的数
        if(total % 2 == 1) return false;

        //想要的目标数！
        int target = total / 2;
        //当数组中最大的数已经比 想要的目标数 还大，不可能平分成2个相同的数
        if(max > target) return false;

        boolean[] dp = new boolean[target+1];
        dp[0] = true;
        for(int i=1; i<=nums.length; i++){
            for(int j=target; j>=nums[i-1] ; j--){
                dp[j] = dp[j] || dp[j-nums[i-1]];
            }
        }

        return dp[target];
    }
}
