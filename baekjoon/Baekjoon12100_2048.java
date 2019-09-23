import java.util.Scanner;

public class Baekjoon12100_2048 {
	public static boolean[][] flag;
	public static int max, n;
	public static int[] dy = {1, 0, -1, 0}; //남 동 북 서
	public static int[] dx = {0, 1, 0, -1};
	
	private static int[][] move(int[][] arr, int dir, int i, int j) {
		int ny = i;
		int nx = j;
		
		while(true) {
			ny += dy[dir];
			nx += dx[dir];
			
			if(ny<0 || nx<0 || ny>=n || nx>=n) { //범위를 벗어날 때
				if((ny-dy[dir])!=i || (nx-dx[dir])!=j) {
					arr[ny-dy[dir]][nx-dx[dir]] = arr[i][j];
					arr[i][j] = 0;
				}
				break;
			}
			else if(arr[ny][nx]!=0) {
				//다음 자리가 0이 아니고 그자리의 숫자가 움직이는 숫자랑 같은 값일 때
				if(arr[ny][nx]==arr[i][j] && !flag[ny][nx]) { //두 값이 같고 이번 턴에서 바뀐 값이 아닐 때
					arr[ny][nx] *= 2;
					flag[ny][nx] = true;
					max = Math.max(max, arr[ny][nx]);
				}
				else arr[ny-dy[dir]][nx-dx[dir]] = arr[i][j];
				arr[i][j] = 0;
				break;
			}
		}
		
		return arr;
	}
	
	public static void shake(int[][] arr, int cnt) {
		for (int i = 0; i < 4; i++) {
			if(i==0) { //남
				for (int y = n-2; y >= 0; y--) {
					for (int x = 0; x < n; x++) {
						if(arr[y][x]!=0)
							arr = move(arr, i, y, x);
					}
				}
			}else if(i==1) { //동
				if(cnt==1) { //체크용
					System.out.println("before");
					for (int j = 0; j < n; j++) {
						for (int j2 = 0; j2 < n; j2++) {
							System.out.print(arr[j][j2]+" ");
						}
						System.out.println();
					}
				}
				for (int y = 0; y < n; y++) {
					for (int x = n-2; x >= 0; x--) {
						if(arr[y][x]!=0)
							arr = move(arr, i, y, x);
					}
				}
			}else if(i==2){ //북
				for (int y = 1; y < n-1; y++) {
					for (int x = 0; x < n; x++) {
						if(arr[y][x]!=0)
							arr = move(arr, i, y, x);
					}
				}
			}else { //서
				for (int y = 0; y < n; y++) {
					for (int x = 1; x < n; x++) {
						if(arr[y][x]!=0)
							arr = move(arr, i, y, x);
					}
				}
			}
			if(cnt==1) {
				System.out.println("dir="+i+" check!!!! count="+cnt);
				for (int j = 0; j < n; j++) {
					for (int j2 = 0; j2 < n; j2++) {
						System.out.print(arr[j][j2]+" ");
					}
					System.out.println();
				}
			}
			
			if(cnt==5) return;
			flag = new boolean[n][n];
			shake(arr, cnt+1);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int[][] arr = new int[n][n];
		flag = new boolean[n][n];
		max = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
				max = Math.max(max, arr[i][j]);
			}
		}
		
		shake(arr, 1);
		System.out.println(max);
	}
}
