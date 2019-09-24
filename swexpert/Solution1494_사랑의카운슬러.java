import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
    public static long min, x, y, xMinus, yMinus;
    public static int sumX, sumY;
    public static int[][] arr;
    public static int[] xDemo;
    public static int[] yDemo;
 
    public static void comb(int n, int r) {
        if(r==0) {
            x = 0;
            y = 0;
            xMinus = 0;
            yMinus = 0;
            getVector();
            return;
        }
        if(n < r) return;
 
        xDemo[r-1] = arr[n-1][0];
        yDemo[r-1] = arr[n-1][1];
        comb(n-1, r-1);
        comb(n-1, r);
    }
 
    public static void getVector() {
        for (int i = 0; i < xDemo.length; i++) {
            x += xDemo[i];
            y += yDemo[i];
        }
        x -= sumX-x;
        y -= sumY-y;
        long vector = x*x+y*y;
        min = Math.min(min, vector);
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
 
        for (int test = 1; test <= t; test++) {
            int n = sc.nextInt();
            arr = new int[n][2];
            xDemo = new int[n/2];
            yDemo = new int[n/2];
            x = 0;
            y = 0;
            min = Long.MAX_VALUE;
            sumX = 0;
            sumY = 0;
 
            for (int i = 0; i < n; i++) {
                arr[i][0] = sc.nextInt();
                arr[i][1] = sc.nextInt();
                sumX += arr[i][0];
                sumY += arr[i][1];
            }
            comb(n, n/2);
 
            System.out.println("#"+test+" "+min);
        }
    }
}
