package LeetCode_101书.贪心算法.区间问题;

import java.util.Arrays;
import java.util.Comparator;

/*
435. 无重叠区间

给定一个区间的集合intervals，其中 intervals[i] = [starti, endi]。返回需要移除区间的最小数量，使剩余区间互不重叠。

示例 1:
输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
输出: 1
解释: 移除 [1,3] 后，剩下的区间没有重叠。

示例 2:
输入: intervals = [ [1,2], [1,2], [1,2] ]
输出: 2
解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠

示例 3:
输入: intervals = [ [1,2], [2,3] ]
输出: 0
解释: 你不需要移除任何区间，因为它们已经是无重叠的了

 */
public class 无重叠区间_435 {
    public static void main(String[] args){
        int[][] intervals = new int[][]{{1,3},{2,3},{4,5},{3,5},{2,6},{1,2}};

        System.out.println( "最少要移除" + eraseOverlapIntervals(intervals) + "个重叠区间");
    }

    //求最少的移除区间个数，等价于尽量多保留不重叠的区间。
    //在选择要保留区间时，区间的结尾十分重要：选择的区间结尾越小，余留给其它区间的空间就越大，就越能保留更多的区间。
    //因此，我们采取的贪心策略为，优先保留结尾小且不相交的区间。

    //按照右边界从小到大排序后，例如：上面被排序为{{1,2},{1,3},{2,3},{4,5},{3,5},{2,6}}
    //我们发现如果右边界都相同时，无论如何都只能有一个区间能被留下来，所以留哪一个无所谓，其他的都要移除，例如{4,5},{3,5}这两个留哪一个都可以！
    public static int eraseOverlapIntervals(int[][] intervals) {
        int num = 0;
        //重写一个comparator接口，定义排序方式为按照右边界从小到大排序，例如：上面被排序为{{1,2},{1,3},{2,3},{4,5},{3,5},{2,6}}
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] intervals1, int[] intervals2) {
                return intervals1[1]-intervals2[1];
            }
        });

        //初始右边界为第一个区间的右边界
        int right = intervals[0][1];
        for(int i=1; i<intervals.length; i++){
            //判断第二个区间的左边界是否小于当前右边界，若满足证明这个区间有重叠，要移除。否则证明没有重叠，把右边界变成新区间的右边界！
            if(intervals[i][0] < right){
                num++;
            }else {
                right = intervals[i][1];
            }
        }

        return num;
    }
}
