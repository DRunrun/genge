package com.base;

/**
 * TODO
 *
 * @author djq
 * @date 2022-08-05 17:55
 **/
public class 异常捕获 {
    public static void main(String[] args) {
        try {
            try {
                String a = null;
                a.substring(1);
            }catch (Exception e){
                System.out.println("内部异常");
            }
        }catch (Exception e){
            System.out.println("外部异常");
        }
    }
}
