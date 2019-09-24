import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static ArrayList<Location> list;
	public static boolean[][] ladder;
	public static int n, m, h, min;
	
	public static boolean move() { //사다리 탐색하는 함수
		
		for (int i = 0; i < n; i++) {
			int x = i;
			int y = 0;
			while(true) {
				if(ladder[y][x]) x += 1;
				else if(x>0 && ladder[y][x-1]) x -= 1;
				
				y += 1;
				
				if(y==h && x!=i) return false; //도착지가 출발점과 다른 열일 때
				else if(y==h && x==i) break; //마지막에 도착했는데 출발지와 열이 같을 때
			}
		}
		return true;
	}
	
	public static void dfs(int num, int cnt, int i) {
		if(cnt>3) return;
		
		for (int j = i; j < list.size(); j++) {
			Location l = list.get(j);
			
			if(ladder[l.y][l.x] || (l.x>0 && ladder[l.y][l.x-1])) continue; //해당 자리나 오른쪽열에 다리가 연결되어 있을 때
			
			ladder[l.y][l.x] = true;
			if(cnt<num) {
				dfs(num, cnt+1, i+1);
			}
			
			if(cnt==num && move()) {
				min = Math.min(min, cnt);
				return;
			}
			ladder[l.y][l.x] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		ladder = new boolean[h+1][n];
		list = new ArrayList<>();
		min = 5;
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			ladder[a][b] = true;
		}
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				if(j<n-1 && !ladder[i][j]) list.add(new Location(i, j));
			}
		}
		
		if(m==0 || move()) min = 0;
		else {
			for (int i = 1; i <= 3; i++) {
				dfs(i, 1, 0);
				if(min<4) break;
				else if(h<2 && i==1) break;
				else if(h==2 && i==2) break;
				else if(h==3 && i==3) break;
			}
		}
		
		if(min==5) min = -1;
		System.out.println(min);
	}
	
	static class Location{
		int y;
		int x;
		
		Location(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
}
