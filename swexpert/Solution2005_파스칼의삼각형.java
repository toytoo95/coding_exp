import java.util.Scanner;
 
 /*dp*/

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int test_case = 1; test_case <= t; test_case++) {
            int n = sc.nextInt();
            int[][] pascal = new int[n+1][n+1];
             
            System.out.println("#"+test_case);
             
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    if(j==0 || j==i) {
                        pascal[i][j] = 1;
                        System.out.print(1+" ");
                    }
                    else {
                        pascal[i][j] = pascal[i-1][j-1]+pascal[i-1][j];
                        System.out.print(pascal[i][j]+" ");
                    }
                }
                System.out.println();
            }
        }
    }
}
