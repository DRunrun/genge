/**
 * 分治法案例  查找在有序数组中是否出现过某个值
 * 时间复制度是O(logn)
 * @author djq
 * @date 2022/4/20 15:43
 */
public class 二分法 {
    public static void main(String[] args) {
        //需要查找的数字
        int targetNumb = 5;
        //目标有序数组
        int[] arr ={1,2,3,4,5,6,7,8,9};
        //中位值
        int middle = 0;
        //两个索引
        int low = 0;
        int high = arr.length - 1;
        int isfind = 0;
        while (low <= high){
            middle = (high + low) / 2;
            if (arr[middle] == targetNumb){
                System.out.println(targetNumb + "在数组中，下标值为："+middle);
                isfind = 1;
                break;
            }else if (arr[middle] > targetNumb) {
                //说明该数在low~middle之间
                high = middle - 1;
            }else {
                //说明该值在middle~high之间
                low = middle + 1;
            }
        }
        if (isfind == 0){
            System.out.println("数组不含：" + targetNumb);
        }
    }
}
