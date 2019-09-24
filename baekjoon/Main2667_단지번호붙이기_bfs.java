import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*Bfs ver.*/

public class Main {
	public static Queue<Location> q = new LinkedList<>();
	public static int[] num;
	public static int[][] arr;
	public static int[] dy = {1, 0, -1, 0};
	public static int[] dx = {0, 1, 0, -1};
	public static int n, cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = Integer.parseInt(sc.nextLine());
		arr = new int[n][n];
		num = new int[n*n];
		cnt = 1;
		
		for (int i = 0; i < n; i++) {
			String s = sc.nextLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = s.charAt(j)-'0';
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j]==1) {
					bfs(i, j);
					getNum();
				}
			}
		}
		System.out.println(cnt-1); //총 단지 수
		Arrays.sort(num);
		for (int i = 0; i < n*n; i++) {
			if(num[i]!=0) System.out.println(num[i]);
		}
	}

	public static void getNum() {
		int c = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j]==cnt) c++;
			}
		}
		num[cnt-1] = c;
	}

	private static void bfs(int y, int x) {
		q.offer(new Location(y, x));
		cnt++;
		arr[y][x] = cnt;
		while(!q.isEmpty()) {
			Location lo = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int ny = lo.y+dy[i];
				int nx = lo.x+dx[i];
				
				if(ny<0 || nx<0 || ny>=n || nx >=n) continue;
				if(arr[ny][nx]==1) {
					arr[ny][nx] = cnt;
					q.offer(new Location(ny, nx));
				}
			}
		}
	}
	
	static class Location{
		int x;
		int y;
		
		Location(int y, int x){
			this.x = x;
			this.y = y;
		}
	}
}
