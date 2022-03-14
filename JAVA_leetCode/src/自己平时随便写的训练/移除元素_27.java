package 自己平时随便写的训练;

public class 移除元素_27 {
    public static void main(String[] args){
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        int len = removeElement(nums,val);
        System.out.println(len);
        print(nums,len);
    }

    //思路与26题几乎完全一样
    static int removeElement(int[] nums, int val) {
        int p=0;

        for(int q=0; q<nums.length; q++){
            if(nums[q] != val){
                nums[p] = nums[q];
                p++;
            }
        }

        return p;
    }

    static void print(int[] num, int len){
        for (int i=0; i < len; i++){
            System.out.println(num[i]);
        }
    }
}
