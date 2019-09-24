import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
 
public class Solution {
    public static int[] aMove, bMove;
    public static Location[] bc;
    public static int[] dy = {0, -1, 0, 1, 0}; //상 우 하 좌
    public static int[] dx = {0, 0, 1, 0, -1};
    public static int m, c;
     
    public static int dist(int y, int x) {
        for (int i = 0; i < c; i++) {
            int dis = (int)Math.abs(x-bc[i].x)+(int)Math.abs(y-bc[i].y);
             
            if(dis <= bc[i].c && bc[i].connect==0) {
                bc[i].connect = 1;
                return bc[i].p;
            }
        }
        return 0;
    }
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
         
        for (int test = 1; test <= t; test++) {
            m = sc.nextInt();
            c = sc.nextInt();
            aMove = new int[m];
            bMove = new int[m];
            bc = new Location[c];
            int ans = 0;
             
            for (int i = 0; i < m; i++) {
                aMove[i] = sc.nextInt();
            }
             
            for (int i = 0; i < m; i++) {
                bMove[i] = sc.nextInt();
            }
             
            for (int i = 0; i < c; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int cc = sc.nextInt(); //충전 범위로 충전소 체크
                int p = sc.nextInt(); //처리량
                bc[i] = new Location(y, x, cc, p);
            }
             
            Arrays.sort(bc, new Comparator<Location>() {
                public int compare(Location o1, Location o2) {
                    return o2.p-o1.p;
                }
            });
             
            int x = 1, y = 1, x2 = 10, y2 = 10;
            for (int i = 0; i <= m; i++) {
                int tmp = dist(y, x);
                tmp += dist(y2, x2);
                 
                for (int j = 0; j < c; j++) {
                    bc[j].connect = 0;
                }
                 
                int tmp2 = dist(y2, x2);
                tmp2 += dist(y, x);
                 
                for (int j = 0; j < c; j++) {
                    bc[j].connect = 0;
                }
                 
                ans += Math.max(tmp, tmp2);
                 
                if(i==m) break;
                y += dy[aMove[i]]; x += dx[aMove[i]];
                y2 += dy[bMove[i]]; x2 += dx[bMove[i]];
            }
             
            System.out.println("#"+test+" "+ans);
        }
        sc.close();
    }
     
    static class Location {
        int y;
        int x;
        int c;
        int p;
        int connect;
         
        Location(int y, int x, int c, int p){
            this.y = y;
            this.x = x;
            this.c = c;
            this.p = p;
        }
    }
}
