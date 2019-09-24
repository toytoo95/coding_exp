import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
    public static int n, m, count;
    public static int[][] student;
    public static int[] visited;
 
    public static void dfs(int y, int flag) {
        visited[y]=1;
        for (int i = 1; i <= n; i++) {
            if(flag==student[y][i] && visited[i]==0) {
                count++;
                dfs(i, student[y][i]);
            }
        }
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
 
        for (int test = 1; test <= t; test++) {
            n = sc.nextInt();
            m = sc.nextInt();
            student = new int[n+1][n+1];
            visited = new int[n+1];
            int ans = 0;
 
            for (int i = 0; i < m; i++) {
                int l = sc.nextInt(); //작은 애
                int r = sc.nextInt(); //큰 애
                student[l][r] = -1;
                student[r][l] = 1;
            }
 
            for (int i = 1; i <= n; i++) {
                count = 0;
                dfs(i, 1);
                dfs(i, -1);
                //System.out.println("i="+i+" visited="+Arrays.toString(visited));
                if(count==n-1) ans++;
                visited = new int[n+1];
            }
            System.out.println("#"+test+" "+ans);
        }
        sc.close();
    }
}
