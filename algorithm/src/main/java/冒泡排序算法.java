import java.util.Arrays;

/**
 * 冒泡排序算法:重第一个数据开始，依次比较相邻元素的大小，如果前者大于后者，则进行交换，
 * 把大的元素往后交换，通过多轮迭代，直到没有交换操作为止
 *
 * 最好的时间复杂度是O(n)  最坏的时复杂度是O(n*n)
 * 不需要开辟额外的空间  空间复杂度是O(1)
 *
 * @author djq
 * @date 2022/4/20 15:45
 */
public class 冒泡排序算法 {
    public static void main(String[] args) {
        int[] arr = {1,4,2,6,5,7,9,3,8,-1};
        System.out.println("原始数据："+ Arrays.toString(arr));
        for (int i = 1; i < arr.length;i++){
            for (int j = 0; j < arr.length -1; j++){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }

        }
        System.out.println("冒泡排序：" + Arrays.toString(arr));
    }
}
