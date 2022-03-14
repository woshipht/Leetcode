package 自己平时随便写的训练;

public class 删除有序数组中的重复项_26 {
    public static void main(String[] args){
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int len = removeDuplicates(nums);
        System.out.println(len);
        print(nums,len);
    }

    //运用双指针思想，指针p和q，q遍历数组往后移。当nums[p]与nums[q]不相等时，把q指针的数复制到p指针的数上，p++,q++。当相等时，只用q++。
    static int removeDuplicates(int[] nums) {
        int p = 0;

        for(int q = 1; q < nums.length; q++){
            if(nums[p] != nums[q]){
                nums[p+1] = nums [q];
                p++;
            }
        }

        return p+1;
    }

    static void print(int[] num, int len){
        for (int i=0; i < len; i++){
            System.out.println(num[i]);
        }
    }
}
