package LeetCode_101书.动态规划.分割类型;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
139. 单词拆分
给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。

注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。

示例 1：
输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。

示例 2：
输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
     注意，你可以重复使用字典中的单词。

示例 3：
输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false

提示：
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s 和 wordDict[i] 仅有小写英文字母组成
wordDict 中的所有字符串 互不相同
 */
public class 单词拆分_139 {
    public static void main(String[] args){
        String s = "applepenapplepen";
        List<String> list = new LinkedList<>();
        list.add("apple");
        list.add("pen");

        System.out.println(wordBreak(s,list));
    }

    /*想好用动态规划就想想动态规划五步曲!
    1.确定dp[i]的下标以及dp值的含义：   以i这个数结尾，是否能被单词拆分？

    2.确定状态转移方程：               对于每个dp[i],如果字符串的 0~i 位置能够被分为很多个可拆分的单词段，那它就应该为true
                                    所以当发现一个0~j位置可以被一个单词拆分后，就只需要再检查j~i位置能否被分为很多个可拆分的单词段，问题依然相同！
                                    于是我们需要遍历每一个j(j<i)，看j~i的最后一个单词段是否等于一个单词，如果是就 证明位置0~i是可被拆分的

    3.dp数组的初始化状态：             默认以0这个数结尾，被单词拆分，因为0~i如果是一个单词，那它应该默认是可拆分的

    4.确定遍历顺序：                  由于dp[i]的值需要前面dp[j](j<i)来确定，所以递推顺序应该是从前往后

    5.返回值:                        因为计算实现n这个数需要的最少的完全平方数，所以返回dp[n]
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        Set<String> set = new HashSet<>(wordDict);
        dp[0] = true;

        for(int i=1; i<=s.length(); i++){
            for(int j=0; j<i; j++){
                if(dp[j] && set.contains(s.substring(j,i)) ){
                    dp[i] = true;
                }
            }
        }

        return dp[s.length()];
    }
}
