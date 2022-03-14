package 自己平时随便写的训练;

import java.util.HashMap;

public class 两数之和_1 {
    public static void main(String[] args){
        int[] nums = {5,9,11,15};
        int target = 16;
        int[] result = twoSum(nums,target);

        for(int i=0; i<result.length; i++){
            System.out.print(result[i] + " ");
        }
    }

//    static int[] twoSum(int[] nums, int target) {
//        for(int i=0; i<nums.length; i++){
//            for(int j=i+1; j<nums.length; j++){
//                if(nums[i] + nums[j] == target){
//                    return new int[] {i,j};
//                }
//            }
//        }
//        return new int[0];
//    }

    static int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();

        for(int i=0; i<nums.length; i++){
            if(hashMap.containsKey(target - nums[i])){
                return new int[] {hashMap.get(target - nums[i]),i};
            }else {
                hashMap.put(nums[i],i);
            }
        }
        return new int[0];
    }
}
