package test;

/**
 * Created by sherlock on 2016/12/6.
 */
public class NumberTests {

    static int fibonacci(int n){
        if(n==1||n==0)
            return n;
        else
            return fibonacci(n-1) + fibonacci(n-2);
    }

    public static void main(String[] args){
        for (int i = 0;i < 10;i++){
            System.out.println(fibonacci(i));
        }
    }
}
