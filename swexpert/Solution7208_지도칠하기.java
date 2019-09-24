import java.util.Scanner;
 
public class Solution {
    public static int[][] map;
    public static int[] color;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
         
        for (int test = 1; test <= t; test++) {
            int n = sc.nextInt();
            map = new int[n][n];
            color = new int[n];
            int ans = 0;
            int max = Integer.MAX_VALUE;
             
            for (int i = 0; i < n; i++) {
                color[i] = sc.nextInt();
            }
             
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    map[j][k] = sc.nextInt();
                    if(j==k) continue;
                    else if(map[j][k]==1 && color[j]==color[k]) {
                        ans++;
                        color[k] = max--;
                    }
                }
            }
            System.out.println("#"+test+" "+ans);
        }
    }
}
