import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static Queue<Location> q = new LinkedList<>();
	public static int n, m, day;
	public static int[][] board, cntBoard;
	public static boolean[][] visit;
	public static int[] dy = {1, 0, -1, 0};
	public static int[] dx = {0, 1, 0, -1};

	public static boolean check() {
		for (int i = 1; i < n-1; i++) {
			for (int j = 1; j < m-1; j++) {
				if(board[i][j] != 5) return false;
			}
		}
		return true;
	}

	public static void bfs(int y, int x) {
		q.offer(new Location(y, x));

		while(!q.isEmpty()) {
			Location lo = q.poll();

			for (int i = 0; i < 4; i++) {
				int ny = lo.y+dy[i];
				int nx = lo.x+dx[i];

				if(ny<0 || nx<0 || ny>=n || nx>=m) continue;
				if(board[ny][nx]==0) {
					board[ny][nx] = 5;
					q.offer(new Location(ny, nx));
				}
			}
		}
	}

	public static void melt() {
		if(check()) { //치즈가 모두 녹았는지 확인하고 값이 true이면 칸 개수 출력
			int cnt = 0;
			for (int i = 1; i < n-1; i++) {
				for (int j = 1; j < m-1; j++) {
					if(cntBoard[i][j] == 1) cnt++;
				}
			}
			System.out.println(day+"\n"+cnt); //모두 녹기 한 시간 전에 치즈조각이 놓인 칸의 개수
			return;
		}else {
			day++;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					cntBoard[i][j] = board[i][j];
				}
			}
			visit = new boolean[n][m];
			for (int i = 1; i < n-1; i++) {
				for (int j = 1; j < m-1; j++) {
					if(board[i][j]==1 && !visit[i][j]) {
						for (int k = 0; k < 4; k++) {
							int ny = i+dy[k];
							int nx = j+dx[k];

							if(ny>=0 && nx>=0 && ny<n && nx<m) {
								if(board[ny][nx]==5 && !visit[ny][nx]) {
									board[i][j] = 5;
									visit[i][j] = true;
									q.offer(new Location(i, j));
								}
							}
						}
					}
				}
			}
			bfs(1, 1);
			melt();
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); //행
		m = sc.nextInt(); //열
		board = new int[n][m];
		cntBoard = new int[n][m];
		visit = new boolean[n][m];
		day = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				board[i][j] = sc.nextInt();
				if(i==0 || j==0 || i==n-1 || j==m-1) {
					board[i][j] = 5;
					q.offer(new Location(i, j));
				}
			}
		}

		bfs(1, 1);
		melt();
	}

	static class Location{
		int x;
		int y;

		Location(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
}
