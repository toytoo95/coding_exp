import java.util.Scanner;

/**
 * 1:15~3:03(10개 중에 7개 맞음)
 * 
 * 
 * */
 
public class Solution7830_마름모찾기 {
	public static char[][] arr;
	public static int max, n, m;
	
	public static void find(int y, int x) {
		int num = 1;
		int mnum = 0;
		
		while(true) { //윗부분 찾기
			if(x<num || x+num>=m || y+num>=n || arr[y+num][x-num]=='0' || arr[y+num][x+num]=='0') {
				num -= 1;
				break;
			}
			else num += 1;
		}
		mnum = num;
    
		while(true) { //혹시 밑부분이 완벽히 없으면 mnum을 줄여서 체크
			int cnt = mnum;
			num = mnum;
      
			while(true) {
				if(cnt<0 || x<cnt || x+cnt>=m || y+num>=n || arr[y+num][x-cnt]=='0' || arr[y+num][x+cnt]=='0') {
					if(cnt==0) cnt = 1;
					break;
				}
				num += 1;
				cnt -= 1;
			}
      
			if(cnt<=0 || mnum<=0 || mnum+1<=max) break;
			else mnum -= 1; //마름모가 안된경우
		}
		max = Math.max(max, mnum+1);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int test = 1; test <= t; test++) {
			n = sc.nextInt();
			m = sc.nextInt();
			arr = new char[n][m];
			max = 0;
			
			sc.nextLine();
			for (int i = 0; i < n; i++) {
				String s = sc.nextLine();
				for (int j = 0; j < m; j++) {
					arr[i][j] = s.charAt(j);
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(arr[i][j]=='1') {
						find(i, j);
					}
				}
			}
			System.out.println("#"+test+" "+max);
		}
	}
}
