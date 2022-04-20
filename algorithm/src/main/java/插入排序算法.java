import java.util.Arrays;

/**
 * 插入排序算法：选取未排序的元素，插入到已排序区间的合适位置，直到未排序区间为空
 *
 * @author djq
 * @date 2022/4/20 15:46
 */
public class 插入排序算法 {
    public static void main(String[] args) {
        int[] arr = {1,4,2,6,5,7,9,3,8,-1};
        System.out.println("原始数据："+ Arrays.toString(arr));
        for (int i = 1; i < arr.length;i++){
            int temp = arr[i];
            int j = i - 1;
            for (;j>=0;j--){
                if (arr[j] > temp){
                    arr[j+1] = arr[j];
                }else {
                    break;
                }
            }
            arr[j+1] = temp;
        }
        System.out.println("插入排序: " + Arrays.toString(arr));
    }
}
