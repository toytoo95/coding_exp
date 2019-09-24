import java.util.Arrays;
import java.util.Scanner;
 
public class Solution{
    public static long[] arr = new long[1000001];
     
    public static void getFirst() {
        for (int i = 1; i <= 1000000; i+=2) {
            for (int j = 1; i*j <= 1000000; j++) {
                arr[i*j] += i;
            }
        }
         
        for (int i = 1; i <= 1000000; i++) {
            arr[i] += arr[i-1];
        }
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        getFirst();
 
        for (int test = 1; test <= t; test++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
              
            System.out.println("#"+test+" "+(arr[r]-arr[l-1]));
        }
    }
}
