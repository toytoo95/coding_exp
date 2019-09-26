import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * [조건]
 * 다리의 길이는 2이상
 * 다리는 직선
 * 다리는 겹칠 수 있으나 분리된 각각의 다리
 * 모든 섬이 연결되어있어야 함
 * 
 * [입력]
 * 지도의 세로 크기 N, 가로 크기 M
 * 다음 줄부터 N개의 줄에 지도의 정보 주어짐
 * 0은 바다, 1은 땅
 * 
 * [출력]
 * 모든 섬을 연결하는 다리 길이의 최솟값
 * 
 * [풀이]
 * 0. bfs로 섬의 번호를 메기며 표시
 * 1. 만들 수 있는 다리의 경우를 모두 구해서 저장(단, 길이와 시작 섬, 끝 섬이 같으면 저장X)
 * 2. 섬 수-1개의 다리 조합 구하기
 * 3. 그 다리로 갈 수 있는 섬들 표시
 * 4. 모든 섬을 가는지 확인
 * 5. 모두 가면 min값 확인하고 안가면 2번으로
 * 
 * */

public class Main17472_다리만들기2 {
	public static ArrayList<Location> list;
	public static int[][] map;
	public static int n, m, min, inum;
	public static int[] dy = {1, 0, -1, 0};
	public static int[] dx = {0, 1, 0, -1};
	
	public static boolean check(boolean[][] island) {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		int cnt = 0;
		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(1);
		
		while(cnt<=island.length && !q.isEmpty()) {
			int num = q.poll();
			
			for (int i = 1; i <= inum; i++) {
				if(island[num][i]) {
					q.add(i);
					nums.add(i);
				}
			}
			cnt++;
		}
		for (int i = 1; i <= inum; i++) { //모든 섬을 가는지 체크
			if(!nums.contains(i)) { //i번을 가지고 있지 않으면 그 섬은 못가는거
				return false;
			}
		}
		
		return true;
	}
	
	public static void dfs(ArrayList<Integer> island, int sum, int cnt, int num) {
		if(island.size()>=num) {
			boolean[][] check = new boolean[inum+1][inum+1];
			for (int i = 0; i < num; i++) { //섬에서 섬으로 갈 수 있는 길 체크
				int s = list.get(island.get(i)).si-1;
				int e = list.get(island.get(i)).ei-1;
				check[s][e] = true;
				check[e][s] = true;
			}
			
			if(check(check)) min = Math.min(min, sum);
			return;
		}
		
		for (int i = cnt; i < list.size(); i++) {
			island.add(i);
			dfs(island, sum+list.get(i).len, i+1, num);
			if(!island.isEmpty()) island.remove(island.size()-1);
		}
	}
	
	public static void findBridge(int y, int x) {
		Queue<Location> q = new LinkedList<>();
		q.add(new Location(y, x, 0, 0)); //사방으로 탐색
		q.add(new Location(y, x, 0, 1));
		q.add(new Location(y, x, 0, 2));
		q.add(new Location(y, x, 0, 3));
		int num = map[y][x];
		
		while(!q.isEmpty()) {
			Location lo = q.poll();
			int ny = lo.y+dy[lo.dir];
			int nx = lo.x+dx[lo.dir];
			
			//범위 벗어남
			if(ny<0 || nx<0 || ny>n-1 || nx>m-1) continue;
			//다리생성시
			else if(map[ny][nx]>1 && map[ny][nx]!=num && lo.len>1) {
				Location ll = new Location(map[ny][nx], num, lo.len);
				Location original = new Location(num, map[ny][nx], lo.len);
				if(list.contains(ll) || list.contains(original)) {
//					System.out.println("겹쳐서 빠짐");
					continue; //이미 들어가있는 경로일 때
				}
				list.add(original);
//				System.out.println(num+" "+map[ny][nx]+"안겹침");
			}
			//바다일 때
			else if(map[ny][nx]==0) q.add(new Location(ny, nx, lo.len+1, lo.dir));
		}
	}

	public static void findIsland(int y, int x, int cnt) {
		Queue<Location> q = new LinkedList<>();
		q.add(new Location(y, x));
		map[y][x] = cnt;
//		System.out.println("y="+y+", x="+x);
		
		while(!q.isEmpty()) {
			Location lo = q.poll();
			y = lo.y;
			x = lo.x;
			
			for (int i = 0; i < 4; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				if(ny<0 || nx<0 || ny>=n || nx>=m || map[ny][nx]!=1) continue;
				map[ny][nx] = cnt;
				q.add(new Location(ny, nx));
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		min = n*m+1; //최대가 지도의 크기를 넘어갈 수는 없기 때문에
		list = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 2;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j]==1) {
					findIsland(i, j, cnt);
					cnt++;
				}
			}
		}
		inum = cnt-2; //섬의 개수
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j]>1) findBridge(i, j);
			}
		}
		
		ArrayList<Integer> island = new ArrayList<>();
		dfs(island, 0, 0, inum-1);
		dfs(island, 0, 0, inum);
		
		if(min==n*m+1) min = -1;
		System.out.println(min);
	}
	
	static class Location{
		int y;
		int x;
		int si;
		int ei;
		int len;
		int dir;
		
		public Location(int y, int x) {
			this.y = y;
			this.x = x;
		}

		public Location(int si, int ei, int len) {
			this.si = si;
			this.ei = ei;
			this.len = len;
		}
		
		public Location(int y, int x, int len, int dir) {
			this.y = y;
			this.x = x;
			this.len = len;
			this.dir = dir;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Location other = (Location) obj;
			if (ei != other.ei)
				return false;
			if (len != other.len)
				return false;
			if (si != other.si)
				return false;
			return true;
		}
	}
}
