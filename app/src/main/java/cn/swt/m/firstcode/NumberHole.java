package cn.swt.m.firstcode;

import java.util.Scanner;

/**
 * 介绍：这里写介绍
 * 作者：sweet
 * 邮箱：sunwentao@priemdu.cn
 * 时间: 2017/10/16
 */
public class NumberHole {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String numStr = scan.next();
        scan.close();

        solve(numStr);
    }

    static void solve(String numStr) {
        //输入数字不足4位则用前缀0补足
        while(numStr.length() < 4)
            numStr = "0" + numStr;

        do{
            String bigger = sortDec(numStr);
            String lesser = sortAse(bigger);

            //如果当前的减数与被减数相同，则单独打印并结束计算
            if(bigger.equals(lesser)){
                System.out.println(bigger + " - " + lesser + " = 0000");
                return;
            }

            //计算差值并作为下一轮计算的新numStr
            numStr = diff(bigger,lesser);
            System.out.println(bigger + " - " + lesser + " = " + numStr);

        }while(!"6174".equals(numStr));
    }

    //冒泡排序，降序
    static String sortDec(String numStr) {
        char[] str = numStr.toCharArray();
        for(int i = str.length-1; i >0; --i)
            for(int j=0; j< i; ++j) {
                if(str[j] < str[j+1]) swap(str, j,j+1);
            }

        return new String(str);
    }

    //反转降序字符串，得到升序字符串
    static String sortAse(String bigger) {
        return new StringBuffer(bigger).reverse().toString();
    }

    //交换数组元素
    static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    //计算差值
    static String diff(String bigger, String lesser) {
        String resStr = String.valueOf(Integer.parseInt(bigger) - Integer.parseInt(lesser));

        while(resStr.length() < 4)
            resStr = "0" + resStr;

        return resStr;
    }
}
