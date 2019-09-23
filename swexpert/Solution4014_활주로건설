import java.util.Scanner;

public class Solution4014_활주로건설 {
	public static int[][] arr;
	public static boolean[][] flag;
	public static int ans, n, x;
	
	public static void check(int num) {
		flag = new boolean[n][n];

		for (int j = 0; j < n; j++) {
			if((j>0 && Math.abs(arr[num][j]-arr[num][j-1])>1) || (j<n-1 && Math.abs(arr[num][j]-arr[num][j+1])>1)) break; //높이 차이가 1이상일 때
			else { //높이 차이가 1인 경우
				if(j>0 && arr[num][j]>arr[num][j-1]) { //왼쪽이 작을 때 => 지나온 방향을 탐색해야 할 때
					int nj = j;
					for (int i = nj-1; i >= nj-x; i--) {
						if(i<0 || flag[num][i] || (i<nj-1 && arr[num][i]!=arr[num][i+1])) { //크기가 안됐는데 범위를 벗어나거나 이미 그 자리에 활주로가 있을 때, 높이 차이가 있을 때
							j = n+1;
							break;
						}flag[num][i] = true;
					}
				}
				if(j<n-1 && arr[num][j]>arr[num][j+1]) { //오른쪽이 작을 때
					int nj = j;
					for (int i = nj+1; i <= nj+x; i++) {
						if(i>=n || flag[num][i] || (i>nj+1 && arr[num][i]!=arr[num][i-1])) { //크기가 안됐는데 범위를 벗어나거나 이미 그 자리에 활주로가 있을 때
							j = n+1;
							break;
						}flag[num][i] = true;
						j = i;
						if(j==nj+x) j -= 1;
					}
				}
			}
			if(j==n-1) ans+=1; //끝까지 왔으면 활주로가 될 수 있다는 의미
		}
		flag[num][num] = false; //행,열 확인할 때 겹치는 부분
		
		//System.out.println(num+"열");
		for (int i = 0; i < n; i++) {
			if((i>0 && Math.abs(arr[i][num]-arr[i-1][num])>1) || (i<n-1 && Math.abs(arr[i][num]-arr[i+1][num])>1)) break;
			else {
				if(i>0 && arr[i][num] > arr[i-1][num]) { //위쪽이 작을 때
					int ni = i;
					for (int j = ni-1; j >= ni-x; j--) {
						if(j<0 || flag[j][num] || (j<ni-1 && arr[j][num]!=arr[j+1][num])) {
							i = n+1;
							break;
						}flag[j][num] = true;
					}
				}
				if(i<n-1 && arr[i][num] > arr[i+1][num]) { //아래쪽이 작을 때
					int ni = i;
					for (int j = ni+1; j <= ni+x; j++) {
						if(j>=n || flag[j][num] || (j>ni+1 && arr[j][num]!=arr[j-1][num])) {
							i = n+1;
							break;
						}flag[j][num] = true;
						i = j;
						if(i==ni+x) i -= 1;
					}
				}
			}
			if(i==n-1) ans += 1;
		}
	}

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int test = 1; test <= t; test++) {
			n = sc.nextInt();
			x = sc.nextInt();
			arr = new int[n][n];
			ans = 0;
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			for (int i = 0; i < n; i++) {
				check(i);
			}
			System.out.println("#"+test+" "+ans);
		}
	}
}
