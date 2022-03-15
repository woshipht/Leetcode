package LeetCode_101书.动态规划.子序列问题;

/*
1143. 最长公共子序列
给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

示例 1：
输入：text1 = "abcde", text2 = "ace"
输出：3
解释：最长公共子序列是 "ace" ，它的长度为 3

示例 2：
输入：text1 = "abc", text2 = "abc"
输出：3
解释：最长公共子序列是 "abc" ，它的长度为 3

示例 3：
输入：text1 = "abc", text2 = "def"
输出：0
解释：两个字符串没有公共子序列，返回 0

提示：
1 <= text1.length, text2.length <= 1000
text1 和 text2 仅由小写英文字符组成。
 */
public class 最长公共子序列_1143 {
    public static void main(String[] args){
        String text1 = "abcde";
        String text2 = "ace";

        System.out.println(longestCommonSubsequence(text1,text2));
    }

    /*
    想好用动态规划就想想动态规划五步曲!
    1.确定dp[i][j]的下标以及dp值的含义：   当text1到第i位，text2到第j位时，此时的最大公共子序列长度

    2.确定状态转移方程：               情况①：当text1到第i位，text2到第j位时，如果此时的text[i-1]等于text[j-1]，那么证明出现一个可用公共字符！
                                    那么此时dp[i][j]就等于 当没有执行到i,j时，即i-1,j-1时，此时最大公共子序列长度+1
                                    于是dp[i][j] = dp[i-1][j-1] + 1
                                    情况②：当text1到第i位，text2到第j位时，如果此时的text[i-1]不等于text[j-1]，那么证明没有出现公共字符！
                                    于是此时dp[i][j]就等于 1. 没有执行到i时，即i-1,j时，此时最大公共子序列长度
                                                        2. 没有执行到j时，即i,j-1时，此时最大公共子序列长度
                                                        两者中最大的一个!
                                    于是dp[i][j] = max(dp[i][j-1], dp[i-1][j])

    3.dp数组的初始化状态：             默认当i=0,j=0时，最大公共子序列长度一定是0！

    4.确定遍历顺序：                  由于dp[i][j]的值需要前面dp[i-1][j-1]来确定，所以递推顺序应该是从左上到右下

    5.返回值:                        因为计算实现最大i，j时，最大公共子序列长度，所以返回 dp[text1.length()][text2.length()]
 */
    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];

        for(int i=1; i<=text1.length(); i++){
            for(int j=1; j<=text2.length(); j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }
}
