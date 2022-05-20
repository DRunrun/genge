package com.base;

import java.util.Random;

/**
 * TODO:
 *
 * @author djq
 * @date 2022/4/27 11:28
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println(randomString(-229985452)+ " " + randomString(-147909649));
    }

    private static String randomString(int i) {
        Random ran = new Random(i);
        StringBuilder builder = new StringBuilder();
        while (true){
            int k = ran.nextInt(27);
            if (k == 0) {
                break;
            }
            builder.append((char)('`' + k));
        }
        return builder.toString();
    }
}
