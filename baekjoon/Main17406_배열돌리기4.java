import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main17406_배열돌리기4 {
	public static int[][] original, rcs;
	public static int n, m, k, min;
	public static int[] dy = {0, 1, 0, -1}; // 좌 하 우 상(반시계방향)
	public static int[] dx = {-1, 0, 1, 0};
	public static ArrayList<Integer> list;
	
	public static void change(int[][] move, int num) { //회전
		int r = rcs[num][0];
		int c = rcs[num][1];
		int s = rcs[num][2];
		
		for (int k = 0; k < s; k++) {
			int edge = move[r-s+k][c+s-k];
			int ny = r-s+k;
			int nx = c+s-k;
			
			for (int i = 0; i < 4; i++) { //4방향
				for (int j = 0; j < 2*s-2*k; j++) { //길이
					
					if(i==3 && j==2*s-2*k-1) move[ny][nx] = edge;
					else move[ny][nx] = move[ny+dy[i]][nx+dx[i]];
					
					ny += dy[i];
					nx += dx[i];
				}
			}
		}
	}
	
	public static void getMin(int[][] move) {
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = 0; j < m; j++) {
				sum += move[i][j];
			}
			min = Math.min(min, sum);
		}
	}
	
	public static int[][] set(int[][] move) { //배열 초기화 함수
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				move[i][j] = original[i][j];
			}
		}
		return move;
	}
	
	public static void dfs(int cnt, int[][] move, boolean[] num) {
		if(cnt == k) {
			move = set(move);
			for (int i = 0; i < k; i++) {
				change(move, list.get(i));
			}
			getMin(move);
			return;
		}
		
		for (int i = 0; i < k; i++) {
			if(!num[i]) {
				num[i] = true;
				list.add(i);
				dfs(cnt+1, move, num);
				num[i] = false;
				list.remove(list.size()-1);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		original = new int[n+1][m+1];
		int[][] move = new int[n+1][m+1];
		rcs = new int[k][3];
		min = Integer.MAX_VALUE;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				original[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			rcs[i][0] = Integer.parseInt(st.nextToken())-1;
			rcs[i][1] = Integer.parseInt(st.nextToken())-1;
			rcs[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < k; i++) {
			boolean[] num = new boolean[k];
			list = new ArrayList<>();
			move = set(move);
			list.add(i);
			num[i] = true;
			dfs(1, move, num);
		}
		System.out.println(min);
	}
}
