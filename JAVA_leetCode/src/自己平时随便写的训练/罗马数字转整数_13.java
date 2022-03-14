package 自己平时随便写的训练;

import java.util.HashMap;

public class 罗马数字转整数_13 {
    public static void main(String[] args){
        String nums = "MCMXCIV";
        System.out.println(romanToInt(nums));
    }

    //思路为从右往左遍历，遇到比上一个大或者相等的数就加，遇到比上一个小的数就减
    static int romanToInt(String s) {
        HashMap<Character,Integer> hashMap = new HashMap<Character,Integer>();
        hashMap.put('I',1);
        hashMap.put('V',5);
        hashMap.put('X',10);
        hashMap.put('L',50);
        hashMap.put('C',100);
        hashMap.put('D',500);
        hashMap.put('M',1000);
        int totalNum = 0;
        int nextNum = 0;

        for(int i=0; i<s.length(); i++){
            if(nextNum > hashMap.get(s.charAt(s.length()-i-1))){        //新方法：String.charAt()
                totalNum -= hashMap.get(s.charAt(s.length()-i-1));
            }else {
                totalNum += hashMap.get(s.charAt(s.length()-i-1));
            }
            nextNum = hashMap.get(s.charAt(s.length()-i-1));
        }
        return totalNum;
    }
}
