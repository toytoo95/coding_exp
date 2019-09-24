import java.util.Scanner;

/**그래프*/
 
public class Solution {
    public static int[]data;
    public static int[]data2;
    public static boolean[] visit;
    public static int ans = 0;
    public static String num;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int te = 0; te < 10; te++) {
            int t = sc.nextInt();
            int road = sc.nextInt();
            visit = new boolean[101];
            data = new int[101];
            data2 = new int[101];
            ans = 0;
             
            for (int i = 0; i < road; i++) {
                int num = sc.nextInt();
                if(data[num] == 0) {
                    data[num] = sc.nextInt();
                } else {
                    data2[num] = sc.nextInt();
                }
            }
            dfs(0);
            System.out.println("#"+t+" "+ans);
        }
    }
 
    private static void dfs(int node) {
        visit[node] = true;
        if(data[node] == 99 || data2[node] == 99 || node == 99) {
            ans = 1;
        }
        
        for (int next = 0; next < 2; next++) {
            if(visit[data[node]] == false && data[node] != 0) dfs(data[node]);
            else if(visit[data2[node]] == false && data2[node] != 0) {
                dfs(data2[node]);
            }
        }
    }
}
