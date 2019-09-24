import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		int[] dx = {-1, 0, 1, 0}; //북동남서
		int[] dy = {0, 1, 0, -1};
		int[] backx = {1, 0, -1, 0};
		int[] backy = {0, -1, 0, 1};
		int[][] arr = new int[N][M];
		int count = 1;
		int wall = 0;
		int nextd;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		arr[r][c] = 5;
		while (wall < 5) {
			nextd = d-1 <0? 3:d-1;
			if(arr[r+dx[nextd]][c+dy[nextd]] == 0) {
				r += dx[nextd];
				c += dy[nextd];
				arr[r][c] = 5; //청소
				count++;

				if(d == 0)
					d = 3;
				else
					d--;

				wall = 0;
			}
			else {
				if(wall == 4) { //네 방향 모두 청소가 되어있거나 벽일 때(2-3,2-4)
					if(arr[r+backx[d]][c+backy[d]] == 1) 
						break;
					else {
						r += backx[d];
						c += backy[d];
						wall = 0;
					}
				}
				else {
					if(d == 0)
						d = 3;
					else
						d--;
					wall++;
				}

			}
		}
		System.out.println(count);
	}
}
