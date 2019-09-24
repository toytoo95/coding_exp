import java.util.Scanner;

public class Solution {
    public static int[] dy = {1, 0, -1, 0};
    public static int[] dx = {0, 1, 0, -1};
    public static int[][] room;
    public static int ans, rNum, n, max, rX, rY;
     
    public static void findRoom(int cnt, int y, int x) {
         
        for (int k = 0; k < 4; k++) {
            int ny = y+dy[k];
            int nx = x+dx[k];
             
            if(ny<0 || nx<0 || ny>=n || nx>=n || (room[ny][nx]-1) != room[y][x]) continue;
            findRoom(cnt+1, ny, nx);
        }
         
        if(cnt>ans) {
            ans = cnt;
            rNum = room[rY][rX];
        }
        if(cnt==ans) rNum = Math.min(rNum, room[rY][rX]);
        return;
    }
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
         
        for (int test = 1; test <= t; test++) {
            n = sc.nextInt();
            room = new int[n][n];
            int max = 0;
            ans = 0;
            rNum = Integer.MAX_VALUE;
             
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    room[i][j] = sc.nextInt();
                    max = Math.max(max, room[i][j]);
                }
            }
             
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(room[i][j] > n*n-ans) continue;
                    if(room[i][j]==n*n-ans && room[i][j]> rNum) continue;
                    rY = i;
                    rX = j;
                    findRoom(1, i, j);
                }
            }
            System.out.println("#"+test+" "+rNum+" "+ans);
        }
    }
}
