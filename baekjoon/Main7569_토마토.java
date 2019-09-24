import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int[] dy = {0, 1, 0, -1, 0, 0}; //오, 위, 왼, 아래, 앞, 뒤
	public static int[] dx = {1, 0, -1, 0, 0, 0};
	public static int[] dh = {0, 0, 0, 0, 1, -1};
	public static int[][][] box;
	public static int m, n, h, max;
	public static Queue<Direction> q = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		h = sc.nextInt();
		box = new int[n][m][h];
		int ans = -1;

		for (int k = 0; k < h; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					box[i][j][k] = sc.nextInt();
					if(box[i][j][k]==1) q.offer(new Direction(i, j, k, 0));
				}
			}
		}


		if(q.size()==n*m) ans = 0;
		else if(q.size()==0) ans = -1;
		else {
			tomato();
			ans = max;

			for (int k = 0; k < h; k++) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m; j++) {
						if(box[i][j][k]==0) ans = -1;
					}
				}
			}
		}
		System.out.println(ans);
	}

	public static void tomato() {

		while(!q.isEmpty()) {
			Direction dir = q.poll();

			for (int i = 0; i < 6; i++) {
				int ny = dir.y+dy[i];
				int nx = dir.x+dx[i];
				int nh = dir.h+dh[i];
				int nday = dir.day+1;

				if(ny<0 || nx<0 || ny>=n || nx>=m || nh<0 || nh>=h) continue;

				if(box[ny][nx][nh]==0) {
					box[ny][nx][nh] = nday;
					q.offer(new Direction(ny, nx, nh, nday));
				}
				max = Math.max(dir.day, max);
			}
		}
	}

	static class Direction{
		int x;
		int y;
		int h;
		int day;

		Direction(int y, int x, int h, int day){
			this.y = y;
			this.x = x;
			this.h = h;
			this.day = day;
		}
	}
}
