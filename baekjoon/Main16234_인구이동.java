import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main16234_인구이동 {
	public static int[] dy = {1, 0, -1, 0};
	public static int[] dx = {0, 1, 0, -1};
	public static int[][] nation, check;
	public static int n, l, r, ans;
	public static boolean flag;
	
	public static void change(ArrayList<Location> list, int sum) { //인구수 바꾸기
		flag = false;
		for (int i = 0; i < list.size(); i++) {
			Location lo = list.get(i);
			nation[lo.y][lo.x] = sum/list.size();
		}
	}
	
	public static void bfs(int y, int x) {
		Queue<Location> q = new LinkedList<>();
		ArrayList<Location> list = new ArrayList<>();
		q.add(new Location(y, x));
		int sum = nation[y][x];
		check[y][x]++;
		
		while(!q.isEmpty()) {
			Location dir = q.poll();
			int num = nation[dir.y][dir.x];
			
			for (int i = 0; i < 4; i++) {
				int ny = dir.y+dy[i];
				int nx = dir.x+dx[i];
				
				if(ny<0 || nx<0 || ny>=n || nx>=n || check[ny][nx]!=0) continue;
				
				int tnum = nation[ny][nx];
				int val = Math.abs(num-tnum);
				if(val<l || val>r) continue; //크기 차이가 범위안에 들지 않을 때
				
				check[ny][nx]++;
				q.add(new Location(ny, nx));
				list.add(new Location(ny, nx));
				sum += nation[ny][nx];
			}
		}
		
		if(!list.isEmpty()) {
			list.add(new Location(y, x));
			change(list, sum); //연합이 있을 때에만 들어가기
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		l = sc.nextInt();
		r = sc.nextInt();
		ans = 0;
		nation = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				nation[i][j] = sc.nextInt();
			}
		}
		
		while(true) {
			flag = true;
			check = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(check[i][j]==0) bfs(i, j);
				}
			}
			if(flag) break;
			ans++;
		}
		
		System.out.println(ans);
		sc.close();
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
