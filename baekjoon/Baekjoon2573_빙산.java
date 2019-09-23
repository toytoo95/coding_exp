import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baekjoon2573_빙산 {
	public static int[][] sea;
	public static boolean[][] ch;
	public static int[] dx = {0, 1, 0, -1};
	public static int[] dy = {1, 0, -1, 0}; //   
	public static int n, m, ans;
	public static Queue<Location> q = new LinkedList<>();
	
	public static void pri() {
		System.out.println(ans+"°");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(sea[i][j]);
			}
			System.out.println();
		}
	}
	
	private static boolean check(int size, int nsize) {
		int cnt = 0;
		
		for (int i = 0; i < size; i++) {
			Location l = q.poll();
			sea[l.y][l.x] = l.h;
			if(l.h != 0) q.offer(new Location(l.y, l.x, l.h));
		}
		pri();
		
		for (int i = 1; i < n-1; i++) {
			for (int j = 1; j < m-1; j++) {
				if(sea[i][j]!=0 && !ch[i][j]) {
					int y = i;
					int x = j;
					boolean f = true;
					while(f) {
						for (int k = 0; k < 4; k++) {
							if(sea[y+dy[k]][x+dx[k]]!=0 && !ch[y+dy[k]][x+dx[k]]) {
								ch[y+dy[k]][x+dx[k]] = true;
								y += dy[k];
								x += dx[k];
								break;
							}else if(k==3) f = false;
						}
					}
					cnt++;
					if(cnt>1) return false;
				}
			}
		}
		
		return true;
	}
	
	private static void melt(int size) {
		int cnt = 0;
		int nsize = 0;
		boolean flag = false;
		
		while(!q.isEmpty()) {
			if(cnt==size) {
				cnt = 0;
				ans++;
				if(!check(size, nsize)) {
					q.clear();
					flag = true;
					break;
				}
				size = nsize;
				nsize = 0;
			}
			cnt++;
			
			Location ice = q.poll();
			int num = 0;
			for (int i = 0; i < 4; i++) {
				int ny = ice.y+dy[i];
				int nx = ice.x+dx[i];
				if(ny<0 || nx<0 || ny>=n || nx>=m || sea[ny][nx]!=0) continue;
				num++;
			}
			int h = ice.h-num;
			if(h<0 || h==0) {
				q.add(new Location(ice.y, ice.x, 0));
				continue;
			}else {
				q.add(new Location(ice.y, ice.x, h));
				nsize++;
			}
		}
		if(!flag) ans = 0;
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		ans = 0;
		
		sea = new int[n][m];
		ch = new boolean[n][m];
		if(ch[0][0]) System.out.println("dksf");
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sea[i][j] = sc.nextInt();
				if(sea[i][j] != 0)
					q.add(new Location(i, j, sea[i][j]));
			}
		}
		
		melt(q.size());
		System.out.println(ans);
	}

	static class Location{
		int y;
		int x;
		int h;
		
		Location(int y, int x, int h){
			this.y = y;
			this.x = x;
			this.h = h;
		}
	}
}
