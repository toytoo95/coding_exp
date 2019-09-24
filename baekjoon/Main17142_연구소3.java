import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17142_연구소3 {
	public static ArrayList<Location> virus;
	public static int[] dy = {1, 0, -1, 0};
	public static int[] dx = {0, 1, 0, -1};
	public static int[][] lab;
	public static int min, n, m;
	
	public static boolean check(int[][] arr) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j]==0) return false;
			}
		}
		return true;
	}
	
	public static void move(ArrayList<Location> list) {
		int[][] arr = new int[n][n];
		int max = 0;
		
		Queue<Location> q = new LinkedList<>();
		for (int i = 0; i < list.size(); i++) {
			q.add(list.get(i));
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = lab[i][j];
			}
		}
		
		while(!q.isEmpty()){
			Location v = q.poll();
			int y = v.y;
			int x = v.x;
			int cnt = v.cnt;

			for (int i = 0; i < 4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];

				if(ny<0 || nx<0 || ny>n-1 || nx>n-1 || !(arr[ny][nx]==0 || arr[ny][nx]==2)) continue;

				if(arr[ny][nx]!=2) max = Math.max(max, cnt-1);
				arr[ny][nx] = cnt+1;
				q.add(new Location(ny, nx, cnt+1));
			}
			if(max>=min) {
				return;
			}
		}
		if(check(arr)) {
			min = Math.min(min, max);
			return;
		}
	}
	
	public static void dfs(ArrayList<Location> list, int num) {
		if(list.size()==m) {
			move(list);
			return;
		}
		else if(num==virus.size()) return;
		
		for (int i = num; i < virus.size(); i++) {
			Location v = virus.get(i);
			list.add(v);
			dfs(list, i+1);
			if(!list.isEmpty())list.remove(list.size()-1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		virus = new ArrayList<>();
		lab = new int[n][n];
		min = n*n+1;
		ArrayList<Location> list = new ArrayList<>();
		boolean flag = false;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if(lab[i][j]==2) virus.add(new Location(i, j, 2));
				if(lab[i][j]==0) flag = true;
			}
		}
		
		if(flag) dfs(list, 0);
		else min = 0;
		
		if(min==n*n+1) min = -1;
		System.out.println(min);
	}

	static class Location{
		int y;
		int x;
		int cnt;
		
		Location(int y, int x, int cnt){
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
}
