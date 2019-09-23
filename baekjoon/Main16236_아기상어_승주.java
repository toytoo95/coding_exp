import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16236_아기상어_승주 {
	public static int[] dy = {-1, 0, 1, 0};
	public static int[] dx = {0, -1, 0, 1};
	public static int[][] arr;
	public static int n, y, x, size, num, ans;
	public static ArrayList<Fish> list;
	
	public static void eat(ArrayList<Fish> list) {
		Fish fish = list.get(0);
		for (int j = 1; j < list.size(); j++) {
			Fish f = list.get(j);
//			System.out.println(fish.x+", "+fish.y+"/"+f.y+", "+f.x);
			if(f.cnt > fish.cnt) continue;
			else if(f.cnt < fish.cnt) {
				fish = f;
				continue;
			}
			if(f.y < fish.y) fish = f; //위에 있는 물고기
			else if(f.y == fish.y) { //높이가 같을 때 가장 왼쪽에 있는 물고기
				if(f.x < fish.x) fish = f;
			}
		}
		num += 1;
		y = fish.y; //아기상어 위치 수정
		x = fish.x;
		ans += fish.cnt;
//		System.out.println(y+", "+x+" 물고기 먹힘");
		arr[y][x] = 0;
		if(num==size) { //만약 잡아먹은 물고기 수가 아기상어의 크기와 같으면 아기상어 크기 +1
			num = 0;
			size++;
//			System.out.println("아기상어 성장!! -> "+size);
		}
	}
	
	public static void find() { //근처에 물고기 있는 지 탐색
		list = new ArrayList<>();
		Queue<Fish> q = new LinkedList<>();
		boolean[][] visit = new boolean[n][n];
		q.add(new Fish(y, x, 0)); // 아기상어 자리 입력
		visit[y][x] = true;
		
		while(!q.isEmpty()) {
			int len = q.size();
			for (int j = 0; j < len; j++) {
				Fish f = q.poll();
				for (int i = 0; i < 4; i++) {
					int ny = f.y+dy[i];
					int nx = f.x+dx[i];
					
					if(ny<0 || nx<0 || ny>=n || nx>=n || arr[ny][nx]>size || visit[ny][nx]) continue;
					if(arr[ny][nx]!=0 && arr[ny][nx] < size) list.add(new Fish(ny, nx, f.cnt+1));
					else q.add(new Fish(ny, nx, f.cnt+1));
					visit[ny][nx] = true;
				}
			}
			if(!list.isEmpty()) { //근처에 크기가 작은 물고기가 있을 때
//				System.out.println("작은 물고기가 있오요");
				eat(list);
				return;
			}
		}
	}
	
	public static boolean check() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j]!=0 && arr[i][j]<size) return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		ans = 0;
		size = 2;
		boolean flag = false;
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 9) {
					y = i;
					x = j;
					arr[i][j] = 0;
				}else if(!flag && arr[i][j] != 0 && arr[i][j]<size) flag = true;
			}
		}
		
		if(flag) { //먹을 수 있는 물고기가 한마리라도 있을 때
			while(check()) {
//				System.out.println("먹을 물고기가 있어서 찾으러 간답");
				find();
				if(list.isEmpty()) break; //길이 막혀서 아무곳으로도 갈 수 없을 때
//				System.out.println("시간= "+ans);
			}
		}
		System.out.println(ans);
	}

	static class Fish{
		int y;
		int x;
		int cnt;
		
		public Fish(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
}
