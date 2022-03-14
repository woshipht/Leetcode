package LeetCode_101书.贪心算法.练习题;

/*
605. 种花问题

假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。

给你一个整数数组flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，能否在不打破种植规则的情况下
种入 n 朵花？能则返回 true ，不能则返回 false。


示例 1：
输入：flowerbed = [1,0,0,0,1], n = 1
输出：true

示例 2：
输入：flowerbed = [1,0,0,0,1], n = 2
输出：false

 */
public class 种花问题_605 {
    public static void main(String[] args){
        int[] flowerbed = new int[]{1};

        System.out.println(canPlaceFlowers1(flowerbed,0));
        System.out.println(canPlaceFlowers2(flowerbed,0));
    }

    //第一种想法：计算每两个1之间0的数量，如果数量是3、4则能种1个花，5、6能种2个花，以此类推
    //由于数组最前和数组最后计算方式会有变化，我们可以默认数组前多一个0，数组最后也多一个0
    //即{0,0,1,0,0,0,1}等价于{0,0,0,1,0,0,0,1,0}
    public static boolean canPlaceFlowers1(int[] flowerbed, int n) {
        //首先默认数组开头存在一个0，故numOfZero = 1
        int numOfZero = 1;
        int count = 0;

        for(int i=0; i<flowerbed.length; i++){
            //如果遇到1，计算距离上一个1中间会有几个0(或者是距离数组开头会有几个0)，按上述算法进行计算
            if(flowerbed[i] == 0){
                numOfZero++;
            }else {
                count += (numOfZero-1)/2;
                numOfZero = 0;
            }
        }
        //默认数组最后还存在一个0，再计算一下算法
        numOfZero++;
        count += (numOfZero-1)/2;

        return count>=n;
    }

    //第二种想法：强遍历所有可能情况!
    //1.当前位置 flowerbed[i]=0 + 当前这个位置是数组末尾 i==flowerbed.length-1 + 前一个位置上没有种花 flowerbed[i−1]=0，此时可以在该位置种花。
    //2.当前位置 flowerbed[i]=0 + 当前这个位置是数组开头 i==0 + 后一个位置没有种花 flowerbed[i+1]=0，此时可以在该位置种花。
    //3.当前位置 flowerbed[i]=0 + 当前这个位置后一个位置没有种花 flowerbed[i+1]=0 + 前一个位置上没有种花 flowerbed[i−1]=0，此时可以在该位置种花。
    //4.当前位置 flowerbed[i]=0 + 并且只有这一个位置，即 i==flowerbed.length−1 并且 i==0，此时可以在该位置种花。
    //令A：i == flowerbed.length-1， B：flowerbed[i-1] == 0， C：i == 0， D：flowerbed[i+1] == 0， E：flowerbed[i]=0
    //总条件关系为(AB+CD+BD+AC)E = (A+C)(B+D)E -> 等价于 (A||C)&&(B||D)&&E
    public static boolean canPlaceFlowers2(int[] flowerbed, int n) {
//        //情况4
//        if(flowerbed.length == 1){
//            if(flowerbed[0] == 0) return n<=1;
//            if(flowerbed[0] == 1) return n==0;
//        }

        int count = 0;
        for(int i=0; i<flowerbed.length; i++){
//            //情况1
//            if(flowerbed[i] == 0 && i == flowerbed.length-1 && flowerbed[i-1] == 0){
//                flowerbed[i]=1;
//                count++;
//            }
//            //情况2
//            if(flowerbed[i] == 0 && i == 0 && flowerbed[i+1] == 0){
//                flowerbed[i]=1;
//                count++;
//            }
//            //情况3
//            if(flowerbed[i] == 0 && i != flowerbed.length-1 && i!=0 && flowerbed[i-1] == 0 && flowerbed[i+1] == 0){
//                flowerbed[i]=1;
//                count++;
//            }
            //上述3种情况可简化为
            if(flowerbed[i] == 0 && (i == flowerbed.length-1 || flowerbed[i+1] == 0) && (i == 0 || flowerbed[i-1] == 0)){
                flowerbed[i]=1;
                count++;
            }
        }
        return count>=n;
    }
}
