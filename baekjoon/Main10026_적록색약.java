import java.util.Scanner;

public class Main {
	public static int nppl, rgbppl, n;
	public static int[]dx = {1, 0, -1, 0}; //동남서북
	public static int[]dy = {0, 1, 0, -1};
	public static String[][] arr;
	public static boolean[][] visited;

	private static void rgb(int y, int x) {
		String s = arr[y][x]; //무슨 색 공간인지
		visited[y][x] = true;
		for (int i = 0; i < 4; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];

			if(ny<n && ny>=0 && nx<n && nx>=0 && !visited[ny][nx]) {
				if(s.equals(arr[ny][nx])) {
					rgb(ny, nx);
				}else if((s.equals("R") && arr[ny][nx].equals("G")) || (arr[ny][nx].equals("R") && s.equals("G"))) rgb(ny, nx);
			}
		}
	}

	private static void normal(int y, int x) {
		String s = arr[y][x]; //무슨 색 공간인지
		visited[y][x] = true;
		
		for (int i = 0; i < 4; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];

			if(ny<n && ny>=0 && nx<n && nx>=0 && !visited[ny][nx]) {
				if(s.equals(arr[ny][nx])) normal(ny, nx);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = Integer.parseInt(sc.nextLine());
		arr = new String[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			String s = sc.nextLine();
			arr[i] = s.split("");
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(!visited[i][j]) {
					nppl++;
					normal(i, j);
				}
			}
		}
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(!visited[i][j]) {
					rgbppl++;
					rgb(i, j);
				}
			}
		}
		System.out.println(nppl+" "+rgbppl);
	}
}
