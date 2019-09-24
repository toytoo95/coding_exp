import java.util.Scanner;
 
public class Solution {
    public static int[] dy = {-1, 1, 1, -1};
    public static int[] dx = {1, 1, -1, -1};
    public static int n, ans, sy, sx;
    public static int[][] dessert;
    public static boolean[] list;
     
    public static void dfs(int y, int x, int cnt, int dir) {
        if(y==sy && x==sx && cnt!=0) {
            if(cnt<4) return;
            ans = Math.max(ans, cnt);
            return;
        }
         
        for (int i = 0; i < 4; i++) {
            int ny = y+dy[i];
            int nx = x+dx[i];
             
            if(ny<0 || nx<0 || ny>=n || nx>=n || list[dessert[ny][nx]]) continue;
             
            if(dir==0 && i>1) return;
            else if(dir==1 && (i==3 || i==0)) continue;
            else if(dir==2 && i<2) {
                if(i==3 && sx!=nx && Math.abs((sy-ny)/(sx-nx))!=1) continue;
                else continue;
            }
            else if(dir==3 && i!=3) continue;
             
            list[dessert[ny][nx]] = true;
            dfs(ny, nx, cnt+1, i);
            list[dessert[ny][nx]] = false;
        }
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
         
        for (int test = 1; test <= t; test++) {
            list = new boolean[102];
            n = sc.nextInt();
            dessert = new int[n][n];
            ans = -1;
             
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dessert[i][j] = sc.nextInt();
                }
            }
             
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sy = i; sx = j;
                    dfs(i, j, 0, 0);
                    list = new boolean[102];
                }
            }
            System.out.println("#"+test+" "+ans);
        }
    }
}
