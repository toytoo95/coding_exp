import java.util.Arrays;
import java.util.Scanner;

/*Dfs ver.*/

public class Main {
	public static int[] num;
	public static int[][] arr;
	public static int[] dy = {1, 0, -1, 0}; //하 우 상 좌
	public static int[] dx = {0, 1, 0, -1};
	public static int n;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = Integer.parseInt(sc.nextLine());
		arr = new int[n][n];
		num = new int[n*n];
		
		for (int i = 0; i < n; i++) {
			String s = sc.nextLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = s.charAt(j)-'0';
			}
		}
		//시작점 찾기
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j]==1) {
					cnt++;
					dfs(i, j, cnt+1);
				}
			}
		}
		//단지크기 넣기
		int size[] = new int[cnt];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j]>1) size[arr[i][j]-2]++;
			}
		}
		
		System.out.println(cnt);
		Arrays.sort(size);
		for (int i = 0; i < size.length; i++) {
			System.out.println(size[i]);
		}
	}

	public static void dfs(int x, int y, int no) {
		int nx = 0, ny = 0;
		arr[x][y] = no;
		
		for (int d = 0; d < 4; d++) {
			nx = x+dx[d];
			ny = y+dy[d];
			
			if(ny<0 || nx<0 || ny>=n || nx>=n) continue;
			if(arr[nx][ny]==1) dfs(nx, ny, no);
		}
		return;
	}
}
