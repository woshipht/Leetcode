package 自己平时随便写的训练;

public class 实现strStr_28 {
    public static void main(String[] args){
        String haystack  = "hello";
        String needle   = "ll";
        System.out.println(strStr(haystack,needle));
    }

    //暴力破解：直接从haystack[0]开始，判断needle是否等于haystack后面的内容，如果不等就再从haystack[1]开始，直到遍历
    static int strStr(String haystack, String needle) {
        int len_haystack = haystack.length();
        int len_needle = needle.length();

        if(len_needle == 0){
            return 0;
        }

        for(int i=0; i+len_needle <= len_haystack; i++){
            boolean flag = true;
            for(int j=0; j<len_needle; j++){
                if(haystack.charAt(i+j) != needle.charAt(j)){
                    flag = false;
                }
            }
            if(flag == true){
                return i;
            }
        }
        return -1;
    }
}
