import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class Solution {
    public static int[][] map;
    public static boolean[][] visited;
    public static int[] dy = {1, 0, -1, 0}; //아래 오른쪽 위 왼쪽
    public static int[] dx = {0, 1, 0, -1};
    public static int n, m, l, count;
    public static Queue<Location> q;
 
    public static void bfs(int y, int x) {
        q = new LinkedList<>();
        visited[y][x] = true;
        if(map[y][x]==1) { //나머지 3방향 모두 가능
            for (int j = 0; j < 4; j++) {
                q.offer(new Location(y, x, j, 1));
            }
        }else if(map[y][x]==2) {
            q.offer(new Location(y, x, 0, 1));
            q.offer(new Location(y, x, 2, 1));
        }else if(map[y][x]==3) {
            q.offer(new Location(y, x, 1, 1));
            q.offer(new Location(y, x, 3, 1));
        }else if(map[y][x]==4) {
            q.offer(new Location(y, x, 1, 1));
            q.offer(new Location(y, x, 2, 1));
        }else if(map[y][x]==5) {
            q.offer(new Location(y, x, 1, 1));
            q.offer(new Location(y, x, 0, 1));
        }else if(map[y][x]==6) {
            q.offer(new Location(y, x, 0, 1));
            q.offer(new Location(y, x, 3, 1));
        }else if(map[y][x]==7) {
            q.offer(new Location(y, x, 3, 1));
            q.offer(new Location(y, x, 2, 1));
        }
 
        while(!q.isEmpty()) {
            Location loc = q.poll();
            if(loc.cnt>l) return;
            visited[loc.y][loc.x] = true;
            int ny = loc.y+dy[loc.dir];
            int nx = loc.x+dx[loc.dir];
            int d = loc.dir;
 
            if(ny<0 || nx<0 || ny>=n || nx>=m || map[ny][nx]==0 || visited[ny][nx]) continue;
 
            if(map[ny][nx]==1) { //3방향 가능
                int dd = loc.dir;
                if(loc.dir!=0); q.offer(new Location(ny, nx, 2, loc.cnt+1));
                if(loc.dir!=1); q.offer(new Location(ny, nx, 3, loc.cnt+1));
                if(loc.dir!=2); q.offer(new Location(ny, nx, 0, loc.cnt+1));
                if(loc.dir!=3); q.offer(new Location(ny, nx, 1, loc.cnt+1));
                 
            }else if(map[ny][nx]==2 && (loc.dir==0 || loc.dir==2)) {
                q.offer(new Location(ny, nx, d, loc.cnt+1));
            }else if(map[ny][nx]==3 &&  (loc.dir==1 || loc.dir==3)) {
                q.offer(new Location(ny, nx, d, loc.cnt+1));
            }else if(map[ny][nx]==4 && (loc.dir==0 || loc.dir==3)) {
                if(loc.dir==0) d = 1;
                else if(loc.dir==3) d = 2;
                q.offer(new Location(ny, nx, d, loc.cnt+1));
            }else if(map[ny][nx]==5 && (loc.dir==2 || loc.dir==3)) {
                if(loc.dir==3) d = 0;
                else if(loc.dir==2) d = 1;
                q.offer(new Location(ny, nx, d, loc.cnt+1));
            }else if(map[ny][nx]==6 && (loc.dir==1 || loc.dir==2)) {
                if(loc.dir==1) d = 0;
                else if(loc.dir==2) d = 3;
                q.offer(new Location(ny, nx, d, loc.cnt+1));
            }else if(map[ny][nx]==7 && (loc.dir==0 || loc.dir==1)) {
                if(loc.dir==1) d = 2;
                else if(loc.dir==0) d = 3;
                q.offer(new Location(ny, nx, d, loc.cnt+1));
            }
        }
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
 
        for (int test = 1; test <= t; test++) {
            n = sc.nextInt(); //세로
            m = sc.nextInt(); //가로
            int r = sc.nextInt(); //시작 행
            int c = sc.nextInt(); //시작 열
            l = sc.nextInt(); //소요된 시간
            map = new int[n][m];
            visited = new boolean[n][m];
            count = 0;
 
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
 
            bfs(r, c);
 
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(visited[i][j]) {
                        count++;
                    }
                }
            }
 
            System.out.println("#"+test+" "+count);
        }
    }
 
    static class Location {
        int y;
        int x;
        int dir;
        int cnt;
        
        Location(int y, int x, int dir, int cnt){
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
}
