package 自己平时随便写的训练;

public class 最长公共前缀_14 {
    public static void main(String[] args){
        String[] nums = {"flower","flow","fly"};
        System.out.println(longestCommonPrefix(nums));
    }

    //双循环判断，当有一个位不完全相等时，输出当前相等的公共前缀部分
    static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String longestPrefix;
        for(int i=0; i<strs[0].length(); i++){
            for(int j=0; j<strs.length; j++){
                if(i > strs[j].length() - 1 || strs[j].charAt(i) != strs[0].charAt(i)){
                    longestPrefix = strs[0].substring(0,i);     //新方法: String.substring()
                    return longestPrefix;
                }
            }
        }
        return strs[0];
    }
}
