package 自己平时随便写的训练;

public class 整数反转_7 {
    public static void main(String[] args){
        long nums = 7463847412L;
        System.out.println(reverse(nums));
    }

    static long reverse(long x) {
        long least;
        long reverse = 0;
        long other = x;

        while (other != 0){
            least = other % 10;
            other /= 10;

            if(reverse>214748364 || reverse<-214748364 || (reverse==214748364 && least>7) || (reverse==-214748364 && least<-8)){
                return 0;
            }
            reverse = reverse * 10 + least;
        }
        return reverse;
    }
}
