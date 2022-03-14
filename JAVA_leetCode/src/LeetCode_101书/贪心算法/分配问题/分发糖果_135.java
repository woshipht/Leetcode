package LeetCode_101书.贪心算法.分配问题;

import java.util.Arrays;

/*
135. 分发糖果

n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
你需要按照以下要求，给这些孩子分发糖果：

1.每个孩子至少分配到 1 个糖果。
2.相邻两个孩子评分更高的孩子会获得更多的糖果。

请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目

示例1：
输入：ratings = [1,0,2]
输出：5
解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果

示例2：
输入：ratings = [1,2,2]
输出：4
解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果
     第三个孩子只得到 1 颗糖果，这满足题面中的两个条件

 */
public class 分发糖果_135 {
    public static void main(String[] args){
        int[] ratings = new int[]{1,2,87,87,87,2,1};

        System.out.println( "共分发" + candy(ratings) + "个糖果");
    }

    //错误思想：如果不相等就count++，如果出现类似：1,3,1这样的情况，3的位置会+2，但是题目要求只要3的位置多就行，所以应该是3的位置+1
//    public static int candy(int[] ratings) {
//        int count = ratings.length;
//
//        for(int i=0; i<ratings.length-1; i++){
//            //若不相等，则直接count++，因为必然出现有一个孩子要多拿糖果
//            if(ratings[i] != ratings[i+1]){
//                count++;
//            }
//        }
//        return count;
//    }

    //用贪心算法，保证从左到右一定能保证糖果数正确，而后从右到左也保证糖果数正确，则最终的结果就一定是最优解
    public static int candy(int[] ratings) {
        //默认分配每个人初始都是1个糖果
        int count[] = new int[ratings.length];
        Arrays.fill(count,1);
        int num = 0;

        //首先进行从左到右判断，只要右边大于左边，则令右边的糖果数等于左边的糖果数+1
        for(int i=0; i<ratings.length-1; i++){
            if(ratings[i] < ratings[i+1]) count[i+1] = count[i]+1;
        }

        //再进行从右到左判断，只要左边大于右边 && 左边的糖果数不大于右边，则令左边的糖果数等于右边的糖果数+1
        for(int i=ratings.length-1; i>0; i--){
            if(ratings[i-1] > ratings[i] && count[i-1] <= count[i]) count[i-1] = count[i]+1;
        }

        for(int i=0; i<ratings.length; i++){
            num += count[i];
        }
        return num;
    }
}
