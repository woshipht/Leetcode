package LeetCode_101书.贪心算法.分配问题;

import java.util.Arrays;

/*
455. 分发饼干

假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。

对每个孩子 i，都有一个胃口值g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j]。
如果 s[j]>= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。

示例1:
输入: g = [1,2,3], s = [1,1]
输出: 1
解释:
你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
所以你应该输出1

示例2:
输入: g = [1,2], s = [1,2,3]
输出: 2
解释:
你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
你拥有的饼干数量和尺寸都足以让所有孩子满足。
所以你应该输出2

 */
public class 分配饼干_455 {
    public static void main(String[] args){
        int[] 饥饿值 = new int[]{1,2,3,4,3,2,2};
        int[] 饼干值 = new int[]{2,1,1,3,4,2,2};

        System.out.println( "共有" + findContentChildren(饥饿值,饼干值) + "个孩子吃饱");
    }

    //用贪心算法，即排序后，保证每一次给孩子分饼干都是分的最优解，故最终的结果就一直是最优解
    public static int findContentChildren(int[] j, int[] b) {
        //对两数组排序，保证顺序
        Arrays.sort(j);
        Arrays.sort(b);

        int children = 0;
        int cookie = 0;

        //从每一个孩子开始匹配，找到能满足饥饿值的第一个饼干，然后让孩子数加1，因为两数组都是有序的，于是找不到饼干，孩子数就不加！
        while (children < j.length && cookie < b.length){
            if(j[children] <= b[cookie]){
                children++;
            }
            cookie++;
        }

        return children;
    }
}
