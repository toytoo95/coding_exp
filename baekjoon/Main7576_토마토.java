import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int[] dy = {0, 1, 0, -1}; //오, 위, 왼 , 아래
	public static int[] dx = {1, 0, -1, 0};
	public static int[][] box;
	public static int m, n, max;
	public static Queue<Direction> q = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		box = new int[n][m];
		int ans = -1;
		int cnt = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				box[i][j] = sc.nextInt();
				if(box[i][j]==1) q.offer(new Direction(i, j, 0));
			}
		}

		if(q.size()==n*m) ans = 0;
		else if(q.size()==0) ans = -1;
		else {
			tomato();
			ans = max;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(box[i][j]==0) ans = -1;
				}
			}
		}
		System.out.println(ans);
	}

	public static void tomato() {
		int day = 1;

		while(!q.isEmpty()) {
			Direction dir = q.poll();

			for (int i = 0; i < 4; i++) {
				int ny = dir.y+dy[i];
				int nx = dir.x+dx[i];
				int nday = dir.day+1;

				if(ny<0 || nx<0 || ny>=n || nx>=m) continue;

				if(box[ny][nx]==0) {
					box[ny][nx] = nday;
					q.offer(new Direction(ny, nx, nday));
				}
				max = Math.max(dir.day, max);
			}
		}
	}

	static class Direction{
		int x;
		int y;
		int day;

		Direction(int y, int x, int day){
			this.y = y;
			this.x = x;
			this.day = day;
		}
	}
}
