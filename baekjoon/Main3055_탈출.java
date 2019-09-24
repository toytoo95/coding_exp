import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static char[][] map;
	public static boolean[][] visited;
	public static int[] dy = {1, 0, -1, 0};
	public static int[] dx = {0, 1, 0, -1};
	public static int r, c, ans = Integer.MAX_VALUE;
	public static ArrayList<Fire> list = new ArrayList<>();
	
	public static void fire() {
		ArrayList<Fire> li = new ArrayList<>();
		li.addAll(list);
		list.clear();
		
		for (int i = 0; i < li.size(); i++) {
			Fire f = li.get(i);
			for (int j = 0; j < 4; j++) {
				int ny = f.y+dy[j];
				int nx = f.x+dx[j];
				
				if(ny<0 || nx<0 || ny>= r || nx>=c || map[ny][nx]!='.') continue;
				map[ny][nx] = '*';
				list.add(new Fire(ny, nx));
			}
		}
	}

	public static void findWay(int sy, int sx) {
		Queue<Fire> q = new LinkedList<>();
		q.offer(new Fire(sy, sx, 0));
		int max = -1;
		
		while (!q.isEmpty()) {
			Fire f = q.poll();
			if(f.cnt>max) {
				max = f.cnt;
				fire();
			}
			
			for (int i = 0; i < 4; i++) {
				int ny = f.y+dy[i];
				int nx = f.x+dx[i];
				
				if(ny<0 || nx<0 || ny>= r || nx>=c || map[ny][nx]=='*' || map[ny][nx]=='X' || visited[ny][nx]) continue;
				
				if(map[ny][nx]=='D') {
					ans = f.cnt+1;
					return;
				}
				else if(map[ny][nx]=='.') {
					q.offer(new Fire(ny, nx, f.cnt+1));
					visited[ny][nx] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		r = sc.nextInt();
		c = sc.nextInt();
		map = new char[r][c];
		visited = new boolean[r][c];
		int sy=0, sx=0;
		
		sc.nextLine();
		for (int i = 0; i < r; i++) {
			String s = sc.nextLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j]=='*') list.add(new Fire(i, j)); //불을 미리 리스트에 넣어놓기
				else if(map[i][j]=='S') {
					sy = i; sx = j;
					map[i][j] = '.';
				}
			}
		}
		
		findWay(sy, sx);
		
		if(ans==Integer.MAX_VALUE) System.out.println("KAKTUS");
		else System.out.println(ans);
	}
	
	static class Fire{
		int y;
		int x;
		int cnt;
		
		Fire(int y, int x){
			this.y = y;
			this.x = x;
		}
		
		Fire(int y, int x, int cnt){
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
}
