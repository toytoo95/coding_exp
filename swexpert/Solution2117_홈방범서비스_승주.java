import java.util.Scanner;

public class Solution2117_홈방범서비스_승주 {
	public static int n, m, max, hnum;
	public static int[][] arr, house;

	public static void check(int y, int x, int size) {
		int cnt = 0;
		for (int i = 0; i < hnum; i++) { //집이 범위 안에 있는지 체크
			int ny = Math.abs(house[i][0]-y);
			int nx = Math.abs(house[i][1]-x);
			if(ny+nx<size) {
				cnt++; //집이 마름모 안에 있으면 카운트 증가
			}
		}
		
		if(size*size+(size-1)*(size-1)<=cnt*m) //손해가 아닌 경우
			max = Math.max(max, cnt);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int test = 1; test <= t; test++) {
			n = sc.nextInt();
			m = sc.nextInt();
			max = 0;
			arr = new int[n][n];
			house = new int[n*n][2];
			
			int num = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					arr[i][j] = sc.nextInt();
					if(arr[i][j]==1) {
						house[num][0] = i;
						house[num][1] = j;
						num++;
					}
				}
			}
			hnum = num;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
//					int size = 1;
					int size = n+1;
					while(true) {
//						if(size>n+1) break;
						if(size<1) break;
						else if(max >= hnum) { //더 이상 큰 수가 나올 수 없으므로 종료
							i = n;
							j = n;
							break;
						}
						check(i, j, size);
//						size++;
						size--;
					}
				}
			}
			System.out.println("#"+test+" "+max);
		}
	}
}
