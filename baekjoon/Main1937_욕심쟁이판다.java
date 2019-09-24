import java.util.Scanner;

public class Main {
	public static int[][] visited;
	public static int[][] arr;
	public static int[] dx = {0, 1, 0, -1}; // 동  서
	public static int[] dy = {-1, 0, 1, 0}; //북  남
	public static int n;
	public static int max = 0;

	private static void getDate(int y, int x) {
		int nextX, nextY;
		int value = 0;
		
		for (int k = 0; k < 4; k++) {
			nextX = x+dx[k];
			nextY = y+dy[k];
			
			if(nextX >= n || nextX < 0 || nextY >= n || nextY < 0)
				continue;
			
			if(arr[nextY][nextX] < arr[y][x]) {
				if(visited[nextY][nextX] == 0)
					getDate(nextY, nextX);
				
				if(value < visited[nextY][nextX])
					value = visited[nextY][nextX];
			}

		}
		visited[y][x] = value+1;
		
		if(max < visited[y][x])
			max = visited[y][x];
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		n = sc.nextInt();
		arr = new int[n][n];
		visited = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				getDate(i, j);
			}
		}
		System.out.println(max);
	}
}
