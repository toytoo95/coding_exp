import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static char[][] board;
	public static int[] dx = {1, 0, -1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static int n, ans;

	private static void swap(int i, int j, int ny, int nx) {
		char temp = board[i][j];
		board[i][j] = board[ny][nx];
		board[ny][nx] = temp;
	}

	public static void findDist(int y, int x) {
		for (int i = 0; i < 4; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			int count = 0;
			if(ny>=0 && nx>=0 && ny<n && nx<n) {
				if(board[ny][nx] != board[y][x]) continue;
				int nny = ny;
				int nnx = nx;
				while(board[nny][nnx] == board[y][x]) {
					nny += dy[i];
					nnx += dx[i];
					count++;
					if(nny<0 || nnx<0 || nny>=n || nnx>=n) break;
				}
				if(i < 3) {
					if(i == 0) i = 2;
					else i = 3;
					nny = ny+dy[i];
					nnx = nx+dx[i];
					if(nny>=0 && nnx>=0 && nny<n && nnx<n) {
						while(board[nny][nnx] == board[y][x]) {
							nny += dy[i];
							nnx += dx[i];
							count++;
							if(nny<0 || nnx<0 || nny>=n || nnx>=n) break;
						}
					}
				}
			}
			ans = Math.max(ans, count);
		}
	}

	public static void getCandy() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < 4; k++) {
					int ny = i+dy[k];
					int nx = j+dx[k];
					if(ny<0 || nx<0 || ny>=n || nx>=n) continue;
					swap(i, j, ny, nx);
					findDist(ny, nx);
					swap(i, j, ny, nx);
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = Integer.parseInt(sc.nextLine());
		board = new char[n][n];
		ans = 0;
		
		for (int i = 0; i < n; i++) {
			String s = sc.nextLine();
			for (int j = 0; j < n; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		getCandy();
		
		System.out.println(ans);
	}
}
