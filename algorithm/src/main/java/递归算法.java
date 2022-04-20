/**
 * 递归算法
 *
 * @author djq
 * @date 2022/4/20 15:51
 */
public class 递归算法 {
    public static void main(String[] args) {
        //String x = "x";
        //String y = "y";
        //String z = "z";
        //hanio(3, x, y, z);
        int index = 5;
        System.out.println("value的值是:" + fibon(index));
    }
    /**
     * 输出斐波那契数列中第 x 位的元素
     */
    private static int fibon(int x) {
        if (x == 1){
            System.out.println("第"+x+"斐波那契数为:"+0);
            return 0;
        }else if (x == 2){
            System.out.println("第"+x+"斐波那契数为:"+1);
            return 1;
        }
        int a = fibon(x - 1);
        System.out.println("x->" + x + "|a->" + a);
        int b = fibon(x - 2);
        System.out.println("x->" + x + "|b->" + b);
        return a + b;
    }

    /**
     * 使用递归解决汉诺塔问题
     */
    private static void hanio(int n, String x, String y, String z) {
        if (n < 1) {
            System.out.println("汉诺塔的层数不能小于1");
        } else if (n == 1) {
            System.out.println("移动:" + x + "->" + z);
            return;
        } else {
            hanio(n - 1, x, z, y);
            System.out.println("移动:" + x + "->" + z);
            hanio(n - 1, y, x, z);
        }
    }
}
