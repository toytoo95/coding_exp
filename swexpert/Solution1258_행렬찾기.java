import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
 
public class Solution {
    public static int[][] arr;
    public static boolean[][] visited;
    public static int[] dy = {1, 0, -1, 0};
    public static int[] dx = {0, 1, 0, -1};
    public static int n;
    public static List<Box> vList;
 
    private static int bfs(int y, int x) {
        Queue<Box> q = new LinkedList<>();
        q.offer(new Box(y, x));
        int cnt = 0;
        visited[y][x] = true;
 
        while(!q.isEmpty()) {
            Box b = q.poll();
 
            for (int k = 0; k < 4; k++) {
                int ny = b.y+dy[k];
                int nx = b.x+dx[k];
 
                if(ny>=n || nx>=n || ny<0 || nx<0) continue;
                if(arr[ny][nx]!=0 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    cnt++;
                    q.offer(new Box(ny, nx));
                }
            }
        }
        return cnt;
    }
 
    private static void check(int y, int x) {
        int yCnt=0, xCnt = 0;
        for (int i = y; i < n; i++) { //행 크기 확인
            if(arr[i][x]!=0) yCnt++;
            if(arr[i][x]==0) break;
        }
        for (int i = x; i < n; i++) { //열 크기 확인
            if(arr[y][i]!=0) xCnt++;
            if(arr[y][i]==0) break;
        }
        vList.add(new Box(yCnt, xCnt, yCnt*xCnt));
    }
 
    private static void sort() {
        int[] s = new int[vList.size()];
        for (int i = 0; i < vList.size(); i++) {
            Box b = vList.get(i);
            s[i] = b.x;
        }
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
 
        for (int test = 1; test <= t; test++) {
            n = sc.nextInt();
            arr = new int[n][n];
            visited = new boolean[n][n];
             vList = new ArrayList<>();
 
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
 
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(arr[i][j]!=0 && !visited[i][j]) {
                        bfs(i, j);
                        check(i, j);
                    }
                }
            }
            
            Collections.sort(vList, new Comparator<Box>() {
                @Override
                public int compare(Box o1, Box o2) {
                    if(o1.val==o2.val) {
                        return o1.y-o2.y;
                    }
                    return o1.val-o2.val;
                }
            });
            System.out.print("#"+test+" "+vList.size()+" ");
            for (int i = 0; i < vList.size(); i++) {
                Box b = vList.get(i);
                System.out.print(b.y+" "+b.x+" ");
            }
            System.out.println();
        }
    }
 
    static class Box{
        int x;
        int y;
        int val;
 
        Box(int y, int x){
            this.x = x; //열
            this.y = y; //행
        }
         
        Box(int y, int x, int val){
            this.x = x; //열
            this.y = y; //행
            this.val = val;
        }
    }
}
