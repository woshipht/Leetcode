package LeetCode_101书.搜索问题.回朔法;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
46. 全排列
给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

示例 1：
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

示例 2：
输入：nums = [0,1]
输出：[[0,1],[1,0]]
示例 3：

输入：nums = [1]
输出：[[1]]

提示：
1 <= nums.length <= 6
-10 <= nums[i] <= 10
nums 中的所有整数 互不相同
 */
public class 全排列_46 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};

        System.out.println(permute(nums));
    }

    /*
        回朔法：    对于每一个当前位置 i，我们可以将其于与之后的任意位置交换，然后继续处理位置 i+1，直到处理到最后一位

                   我们以nums = {1, 2, 3}为例，有一个指针指向当前确定位置：{ | 1 2 3}
                   在这个情况下，我们可以交换|右边任何数的位置，所以过程是
                                   （交换2和2）         （交换3和3）         （无法交换）
                   { | 1 2 3}  ->  {1 | 2 3}  ->      {1 2 | 3}  ->      {1 2 3 | } -> 到最后一位！加入List
                                   {1 | 2 3}  <- 退回  {1 2 | 3}  <- 退回
                                   （交换2和3）          （交换2和2）        （无法交换）
                                   {1 | 3 2}  ->      {1 3 | 2}  ->      {1 3 2 | } -> 到最后一位！加入List
                                   {1 | 3 2}  <- 退回  {1 3 | 2}  <- 退回

                   可以理解为：每次做出交换后，就确定一位数的位置，然后再继续向下，直到所有数的位置都确定，就可以得到一个结果。
                   这时候就需要回到交换前的上一个状态，看看上一个状态还有没有其他可以交换的数，
                   如果有就再次交换，如果没有就再回到上上个交换前的状态，在此重复这个过程

                   必须要把深入一位数的位置时，做出的交换再换回来，不然交换前的上一个状态就不一样了，最后结果会错
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> finalList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            list.add(num);
        }

        //直接初始化进入回溯算法，因为只有一个初始对象 nums[]
        backTrack(list,finalList,0);

        return finalList;
    }

    public static void backTrack(List<Integer> list, List<List<Integer>> finalList, int position){
        if(position == list.size()){
            //必须是一个新的对象
            //如果是finalList.add(list)，那么添加的都是一个对象，都是地址指向这个对象，所以我们最后回溯的时候，全都会回溯
            finalList.add(new ArrayList<>(list));
        }

        for(int i=position; i<list.size(); i++){
            //修改当前节点状态
            Collections.swap(list,position,i);
            //递归子节点
            backTrack(list,finalList,position+1);
            //回改当前节点状态
            Collections.swap(list,position,i);
        }
    }
}
