import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class Solution {
    public static int count, min, n, click;
    public static int [][] map, visit;
    public static int[] dx = {1, 1, 1, 0, -1, -1, -1, 0}; //북동, 동, 동남, 남, 남서, 서, 북서, 북
    public static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
 
    private static void getCount(int y, int x) {
        Queue<Direction> q = new LinkedList<>();
        q.offer(new Direction(y, x));
        if(map[y][x]==-2) {
            click++;
            visit[y][x] = 1;
        }
 
        while(!q.isEmpty()) {
            Direction dir = q.poll();
            if(map[dir.y][dir.x]!=-2) continue;
            int c = 0;
            for (int i = 0; i <= 8; i++) {
 
                if(i == 8) {
                    map[dir.y][dir.x] = c;
                    count--;
                    if(c==0) { //주변에 지뢰가 없을 때 주변 좌표 넣기 => 그 곳들도 쭉 찾아봐야해서
                        for (int j = 0; j < 8; j++) { 
                            int ny = dir.y+dy[j];
                            int nx = dir.x+dx[j];
 
                            if(ny<0 || nx<0 || ny>=n || nx>=n) continue;
                            if(visit[ny][nx]==1 && map[ny][nx]!=0) {
                                click--;
                                visit[ny][nx] = 0;
                            }
                            q.offer(new Direction(ny, nx));
                        }
                    }
                    break;
                }
 
                int ny = dir.y+dy[i];
                int nx = dir.x+dx[i];
 
                if(ny<0 || nx<0 || ny>=n || nx>=n) continue;
                if(map[ny][nx]==-1) c++;
            }
        }
    }
 
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        for (int test = 1; test <= t; test++) {
            n = Integer.parseInt(sc.nextLine());
            map = new int[n][n];
            visit = new int[n][n];
            count = 0;
            click = 0;
 
            for (int i = 0; i < n; i++) {
                String s = sc.nextLine();
                for (int j = 0; j < n; j++) {
                    if(s.charAt(j)=='*') map[i][j] = -1;
                    else {
                        map[i][j] = -2;
                        count++;
                    }
                }
            }
 
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    getCount(i, j);
                }
            }
            System.out.println("#"+test+" "+click);
        }
    }
}
class Direction{
    int x;
    int y;
 
    Direction(int y, int x){
        this.y = y;
        this.x = x;
    }
}
