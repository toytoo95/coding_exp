import java.util.Scanner;

public class Main14500_테트로미노 {
	public static int[] dy = {1, 0, -1, 0};
	public static int[] dx = {0, 1, 0, -1};
	public static int[][] arr;
	public static boolean[][] check;
	public static int n, m, max;
	
	public static void dfs(int y, int x, int cnt, int val) {
		if(cnt==4) {
			max = Math.max(max, val);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			
			if(ny<0 || nx<0 || ny>=n || nx>=m || check[ny][nx]) continue;
			check[ny][nx] = true;
			dfs(ny, nx, cnt+1, val+arr[ny][nx]);
			check[ny][nx] = false;
		}
	}
	
	public static void fuck(int y, int x) {
		if(y+1<n && x+2<m) { //위아래
			int val = 0;
			for (int i = y; i < y+2; i++) {
				for (int j = x; j < x+3; j++) {
					val += arr[i][j];
				}
			}
			max = Math.max(max, val-arr[y+1][x]-arr[y+1][x+2]); // ㅜ
			max = Math.max(max, val-arr[y][x]-arr[y][x+2]); // ㅗ
		}
		if(y+2<n && x+1<m) { //양옆
			int val = 0;
			for (int i = y; i < y+3; i++) {
				for (int j = x; j < x+2; j++) {
					val += arr[i][j];
				}
			}
			max = Math.max(max, val-arr[y][x+1]-arr[y+2][x+1]); // ㅏ
			max = Math.max(max, val-arr[y][x]-arr[y+2][x]); // ㅓ
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[n][m];
		check = new boolean[n][m];
		max = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				check[i][j] = true;
				dfs(i, j, 0, 0);
				check[i][j] = false;
				fuck(i, j);
			}
		}
		System.out.println(max);
	}
}
