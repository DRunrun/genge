package com.base;




/**
 * DES加密
 *
 * @author djq
 * @date 2021/7/1 19:33
 */
public class DES加密 {
    public static void main(String[] args) {
        addDes();
    }

    private static void addDes() {
        String url = "https://mobile.abchina.com/mpay/mobileBank/zh_CN/EBusinessModule/BarcodeH5Act.aspx?token=16251335707054211820";
        String turnTo = url.split("=")[1];
        System.out.println(turnTo);
    }
}
