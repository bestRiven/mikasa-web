package test;

import org.junit.Test;

/**
 * Created by root2 on 16/8/22.
 */
public class JavaCoreTest {

    @Test
    public void test(){
        System.out.println(sum(5));
    }

    /**
     * 递归方法sum，求 1+2+...+n 的求和
     * @param num
     * @return
     */
    public static int sum(int num) {
        if (num > 0) {
            return num + sum(num - 1); // 调用递归方法
        } else {
            return 0; // 当num=0时，循环结束
        }
    }

    /**
     * 递归方法hanon，求汉诺塔算法
     * @param n
     * @param a
     * @param b
     * @param c
     */
    public static void hanon(int n, char a, char b, char c) {
        if (n == 1) {
            move(1, a, c);// 最后一种情况是，把A柱子上盘子移到C柱子上。
            return;
        }
        hanon(n - 1, a, c, b); // 递归，把n-1个盘子从A 盘上借助C盘移到B盘上
        move(n, a, c);// 调用move()方法
        hanon(n - 1, b, a, c);// 递归，把把n-1个盘子从B盘上借助A盘移到C盘上
    }
    public static void move(int n, char a, char c) {
        System.out.println(n + ":" + a + "-->" + c);// 打印移动盘子情况
    }

}
