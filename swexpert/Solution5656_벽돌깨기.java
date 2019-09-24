import java.util.Scanner;
 
public class Solution {
    public static int[][] game, origin;
    public static int[] dy = {1, 0, -1, 0};
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dir;
    public static int w, h, n, cnt, ans, c;
     
    public static void crush(int num, int y, int x) { //벽돌 부시기
        c++;
        game[y][x] = 0;
        for (int i = 0; i < 4; i++) {
            int ny = y+dy[i];
            int nx = x+dx[i];
             
            for (int j = 0; j < num-1; j++) {
                if(ny<0 || nx<0 || ny>= h || nx>=w) break;
                if(game[ny][nx]!=0) {
                    crush(game[ny][nx], ny, nx);
                }
                ny+=dy[i];
                nx+=dx[i];
            }
        }
    }
     
    public static void move(int y, int x) { //벽돌 내리기
        if(y>=h) return;
        if(game[y][x]==0) {
            game[y][x]=game[y-1][x];
            game[y-1][x] = 0;
            move(y+1, x);
        }else return;
    }
     
    public static void biz(int y, int x) {
        if(y>=h) return;
        if(game[y][x]==0) {
            biz(y+1, x);
        }else {
            crush(game[y][x], y, x);
        }
    }
     
    public static void select(int s) {
        if(s==n) {
            c=0;
            for (int i = 0; i < n; i++) {
                biz(0, dir[i]);
                for (int k = h-2; k >= 0; k--) {
                    for (int j = 0; j < w; j++) {
                        if(game[k][j]!=0) move(k+1, j);
                    }
                }
            }
            ans = Math.min(ans, cnt-c);
 
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    game[i][j] = origin[i][j];
                }
            }
            return;
        }
        for (int i = 0; i < w; i++) {
            dir[s] = i;
            select(s+1);
        }
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
         
        for (int test = 1; test <= t; test++) {
            n = sc.nextInt();
            w = sc.nextInt();
            h = sc.nextInt();
            cnt = 0; ans = 100000;
            game = new int[h][w];
            origin = new int[h][w];
            dir = new int[n];
             
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    origin[i][j] = sc.nextInt();
                    game[i][j] = origin[i][j];
                    if(game[i][j]!=0) cnt++;
                }
            }
            select(0);
            System.out.println("#"+test+" "+ans);
        }
    }
}
