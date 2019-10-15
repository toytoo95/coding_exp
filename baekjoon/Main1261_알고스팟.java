import java.util.PriorityQueue;
import java.util.Scanner;

public class Main1261_알고스팟 {
	public static int[] dy = {1, 0, -1, 0};
	public static int[] dx = {0, 1, 0, -1};
	public static int[][] map;
	public static int n, m, min;
	
	public static void bfs() {
		PriorityQueue<Location> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[n][m];
		visited[0][0] = true;
		pq.add(new Location(0, 0, 0));
		
		while(!pq.isEmpty()) {
			Location lo = pq.poll();
			
			for (int i = 0; i < 4; i++) {
				int wall = 0;
				int ny = lo.y+dy[i];
				int nx = lo.x+dx[i];
				
				if(ny<0 || nx<0 || ny>=n || nx>=m || visited[ny][nx]) continue;
				if(map[ny][nx]==1) wall = 1;
				visited[ny][nx] = true;
				
				if(ny==n-1 && nx==m-1) {
					min = lo.cnt;
					return;
				}
				pq.add(new Location(ny, nx, lo.cnt+wall));
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		sc.nextLine();
		map = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			String s = sc.nextLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		if(n==1 && m==1) min = 0;
		else {
			min = n+m-1; //테두리로 가는 방법(일단 최소한)
			bfs();
		}
		System.out.println(min);
	}
	
	static class Location implements Comparable<Location>{
		int y;
		int x;
		int cnt;

		public Location(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Location o) {
			// TODO Auto-generated method stub
			return this.cnt-o.cnt;
		}
	}
}
