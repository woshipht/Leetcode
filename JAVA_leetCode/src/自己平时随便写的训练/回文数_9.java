package 自己平时随便写的训练;

public class 回文数_9 {
    public static void main(String[] args){
        int nums = 123454321;
        System.out.println(isPalindrome(nums));
    }

//    没有考虑超界的情况，即反转后-2^32<x<2^32-1不满足条件
//    static boolean isPalindrome(int x) {
//        if(x<0){
//            return false;
//        }
//
//        int other = x;
//        int rev = 0;
//        int least;
//        while(other!=0){
//            least = other%10;
//            other = other/10;
//            rev = rev*10 + least;
//        }
//
//        if(rev != x){
//            return false;
//        }
//        return true;
//    }

    static boolean isPalindrome(int x) {
        //当x<0,不可能回文；同时若以0作为个位的数，也不可能回文（除了0本身）
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        //把x逐渐变小，revertedNumber逐渐变大，找到两者几乎相等的中间点
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        //如果有偶数个数字组成，x与revertedNumber正好相等；如果有奇数个数字组成，revertedNumber会比x大10倍+中间的那个数
        //例如：原=123454321，x=1234, revertedNumber=12345
        //例如：原=12344321，x=1234, revertedNumber=1234
        return x == revertedNumber || x == revertedNumber / 10;
    }
}
