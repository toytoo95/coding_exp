import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main15683_감시_승주 {
	public static int[] dy = {1, 0, -1, 0}; //북 동 남 서
	public static int[] dx = {0, 1, 0, -1};
	public static int[][] office;
	public static int n, m, min;
	public static ArrayList<CCTV> cc;

	public static void check(int dir, int i, int[][] arr) {
		int ny = cc.get(i).y;
		int nx = cc.get(i).x;
		
		while(true) {
			ny += dy[dir];
			nx += dx[dir];
			
			if(ny<0 || nx<0 || ny>n-1 || nx>m-1 || arr[ny][nx]==6) break; // 범위를 벗어나거나 벽인경우
			if(arr[ny][nx]>0 && arr[ny][nx]<6) continue; //해당 자리가 cctv인 경우
			
			if(arr[ny][nx]>7) arr[ny][nx] += 1;
			else if(arr[ny][nx]==0) arr[ny][nx] = 7;
		}
	}

	public static void dfs(int i, int[][] arr) { //사각지대 개수
		
		if(i==cc.size()) {
			int val = 0;
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					if(arr[j][k]==0) val++;
				}
			}
			min = Math.min(min, val);
			return;
		}
		
		int[][] tmp = new int[n][m];
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				tmp[j][k] = arr[j][k];
			}
		}
		
		CCTV c = cc.get(i);
		int num = c.num;
		if(num==1 || num==3) num = 4;
		else if(num==5) num = 1;
		for (int j = 0; j < num; j++) { //시도해봐야하는 방향의 개수만큼 돌기
			switch (c.num) {
			case 1:
				check(j, i, arr);
				break;
			case 2:
				check(j, i, arr);
				check((j+2)%4, i, arr);
				break;
			case 3:
				check(j, i, arr);
				check((j+1)%4, i, arr);
				break;
			case 4:
				check(j, i, arr);
				check((j+1)%4, i, arr);
				check((j+2)%4, i, arr);
				break;
			case 5:
				check(j, i, arr);
				check((j+1)%4, i, arr);
				check((j+2)%4, i, arr);
				check((j+3)%4, i, arr);
				break;
			default:
				break;
			}
			dfs(i+1, arr);
			for (int l = 0; l < n; l++) {
				for (int k = 0; k < m; k++) {
					arr[l][k] = tmp[l][k];
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		office = new int[n][m];
		min = n*m;
		cc = new ArrayList<>();
		int[][] arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
				arr[i][j] = office[i][j];
				if(office[i][j]!=0 & office[i][j]!=6) cc.add(new CCTV(i, j, office[i][j]));
			}
		}
		
		dfs(0, arr);

		System.out.println(min);
	}

	static class CCTV{
		int y;
		int x;
		int num;

		public CCTV(int y, int x, int num) {
			this.y = y;
			this.x = x;
			this.num = num;
		}
	}
}
