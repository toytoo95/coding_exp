import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int test = 1; test <= t; test++) {
            int n = sc.nextInt();
            int[] price = new int[n];
            int ans = 0;
             
            for (int i = 0; i < n; i++) {
                price[i] = sc.nextInt();
            }
            Arrays.sort(price);
            for (int i = n-1; i >= 0; i-=3) {
                if(i==0) {
                    ans+=price[0];
                    break;
                }
                ans+=price[i]+price[i-1];
            }
            System.out.println("#"+test+" "+ans);
        }
    }
}
