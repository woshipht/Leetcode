package LeetCode_101书.贪心算法.练习题;

import java.util.Arrays;
import java.util.Comparator;

/*
452. 用最少数量的箭引爆气球
在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道
开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。

一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。
弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。

给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。

示例 1：
输入：points = [[10,16],[2,8],[1,6],[7,12]]
输出：2
解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球

示例 2：
输入：points = [[1,2],[3,4],[5,6],[7,8]]
输出：4

示例 3：
输入：points = [[1,2],[2,3],[3,4],[4,5]]
输出：2

示例 4：
输入：points = [[1,2]]
输出：1

示例 5：
输入：points = [[2,3],[2,3]]
输出：1
 */
public class 最少的箭戳气球_452 {
    public static void main(String[] args){
        int[][] points = new int[][]{{3,9}, {7,12},{3,8}, {6,8},{9,10}, {2,9},{0,9}, {3,9},{0,6}, {2,8}};

        System.out.println( "最少要射" + findMinArrowShots(points) + "只箭");
    }

    //逻辑同435，按照右边界从小到大排序，但是要注意不能直接减，因为取值范围是-2^31到2^31-1，直接减会越界！
    //当按照右边界排序后我们发现，如果之后的一个数组满足：其左边界大于第一数组的右边界，那证明这个数组之前的数组的左边界都小于等于第一数组的右边界
    //那么之前的数组都只需要1箭就可以一起射穿！
    //之后把右边界改成这个没有被射穿的气球的右边界，再次重复这个过程！
    //注意：这样的话，最后剩下的1组气球将不会被射箭，因为不存在一个新数组的左边界大于定义的右边界(即已经到底了，最后剩的气球还没有被射箭)，故默认射箭数为1
    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        //重写一个comparator接口，定义排序方式为按照右边界从小到大排序，注意int类型越界！
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] points1, int[] points2) {
                if(points1[1] > points2[1]){
                    return 1;
                }else if(points1[1] < points2[1]){
                    return -1;
                }else {
                    return 0;
                }
            }
        });

        //默认有一个箭是用来射最后一堆气球的
        int num = 1;
        int right = points[0][1];
        for(int i=1; i<points.length; i++){
            //如果找到一个数组满足：其左边界大于第一数组的右边界，对前面的气球射箭！
            if(points[i][0] > right){
                num++;
                right = points[i][1];
            }
        }

        return num;
    }
}
