package 自己平时随便写的训练;

public class 搜索插入位置_35 {
    public static void main(String[] args){
        int[] nums  = {1,3,5,6};
        int target = 8;
        System.out.println(searchInsert(nums,target));
    }

    //题目要求是时间复杂度log2N，所以要用二分查找，暴力遍历需要N.
    static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int mid;

        while (left <= right){
            mid = (left+right)/2;

            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                left = mid+1;
            }else if(nums[mid] > target){
                right = mid-1;
            }
        }
        return left;
    }
}
