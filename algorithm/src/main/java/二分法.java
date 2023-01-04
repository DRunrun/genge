/**
 * 分治法案例  查找在有序数组中是否出现过某个值
 * 时间复制度是O(logn)
 * @author djq
 * @date 2022/4/20 15:43
 */
public class 二分法 {
    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        int target = 9;
        int index = search(nums,target);
        System.out.println(index);
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while(left<=right){
            int mid = (right - left) / 2 + left;
            int value = nums[mid];
            if(value == target){
                return mid;
            }else if(value<target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return -1;
    }
}
