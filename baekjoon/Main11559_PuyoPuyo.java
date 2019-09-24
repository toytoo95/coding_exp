import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static int[] dy = {1, 0, -1, 0};
	public static int[] dx = {0, 1, 0, -1};
	public static char[][] puyo = new char[12][6];
	public static int ans;
	
	public static void check(int y, int x) {
		int ny = y+1;
		if(ny<0 || ny>=12) return;
		if(puyo[ny][x]=='.') {
			puyo[ny][x] = puyo[y][x];
			puyo[y][x] = '.';
			check(ny, x);
		}
	}
	
	public static void bfs(int y, int x, char c) {
		ArrayList<Location> list = new ArrayList<>();
		Queue<Location> q = new LinkedList<>();
		q.offer(new Location(y, x));
		list.add(new Location(y, x));
		int cnt = 1;
		puyo[y][x] = '.';
		
		while(!q.isEmpty()) {
			Location dir = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = dir.y+dy[i];
				int nx = dir.x+dx[i];
				
				if(ny<0 || nx<0 || ny>=12 || nx>= 6) continue;
				if(puyo[ny][nx]==c) {
					q.offer(new Location(ny, nx));
					puyo[ny][nx]='.';
					list.add(new Location(ny, nx));
					cnt++;
				}
			}
		}
		if(cnt<4) {
			for (int i = 0; i < list.size(); i++) {
				Location dir = list.get(i);
				puyo[dir.y][dir.x] = c;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ans = 0;
		int num = 0;
		
		for (int i = 0; i < 12; i++) {
			String s = sc.nextLine();
			for (int j = 0; j < 6; j++) {
				puyo[i][j] = s.charAt(j);
				if(puyo[i][j]!='.') num++;
			}
		}
		
		while(true) {
			int cnt = 0;
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if(puyo[i][j]!='.') {
						//System.out.println(i+" "+j+" "+puyo[i][j]);
						bfs(i, j, puyo[i][j]);
						if(puyo[i][j]=='.') cnt++; //뿌요가 터졌다는 의미
					}
				}
			}
			if(cnt>0) ans++;
			
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if(puyo[i][j]!='.') {
						check(i, j);
					}
				}
			}
			if(cnt==0) break;
		}
		System.out.println(ans);
	}
	
	static class Location{
		int y;
		int x;
		
		Location(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
