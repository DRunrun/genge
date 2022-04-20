import java.util.Arrays;

/**
 * TODO:
 *
 * @author djq
 * @date 2022/4/20 15:47
 */
public class 归并排序算法 {
    public static void main(String[] args) {
        int[] arr = { 49, 38, 65, 97, 76, 13, 27, 50 };
        int[] tmp = new int[arr.length];
        System.out.println("原始数据："+ Arrays.toString(arr));
        //二分法拆解
        customMergeSort(arr,tmp,0,arr.length - 1);
    }
    /**
     * 递归排序
     */
    private static void customMergeSort(int[] arr, int[] tmp, int start, int end) {
        if(start<end){
            int mid = (start + end)/2;
            //对左侧子序列进行递归排序
            customMergeSort(arr,tmp,start,mid);
            //对右侧子序列进行递归排序
            customMergeSort(arr,tmp,mid+1,end);
            //合并
            customDoubleMerge(arr,tmp,start,mid,end);
        }
    }
    /**
     * 合并
     */
    private static void customDoubleMerge(int[] arr, int[] tmp, int left, int mid, int right) {

    }
}
