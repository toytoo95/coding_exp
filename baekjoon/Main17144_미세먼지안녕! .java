import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static Queue<Location> q = new LinkedList<>();
	public static int r, c, t, machine;
	public static int[][] arr;
	public static int dy[] = {0, 1, 0, -1}; //오 아래 왼 위
	public static int dx[] = {1, 0, -1, 0};
	
	public static void bfs() {
		int size = q.size();
		while(size>0) {
			Location dir = q.poll();
			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				int ny = dir.y+dy[i];
				int nx = dir.x+dx[i];
				if(ny<0 || nx<0 || ny>=r || nx>=c || arr[ny][nx]==-1) continue;
				arr[ny][nx] += dir.val/5;
				cnt++;
			}
			q.offer(new Location(dir.y, dir.x, dir.val/5*cnt));
			size--;
		}
		while(!q.isEmpty()) {
			Location dir = q.poll();
			if(arr[dir.y][dir.x]!=0 && arr[dir.y][dir.x]!=-1)
				arr[dir.y][dir.x] -= dir.val;
		}
	}
	
	public static void clean() { //공기청정기 작동
		//윗부분
		for(int i=machine-1; i>0; i--) {
			arr[i][0]=arr[i-1][0];
		}
		for(int i=0; i<c-1; i++) {
			arr[0][i]=arr[0][i+1];
		}
		for(int i=0; i<machine; i++) {
			arr[i][c-1]=arr[i+1][c-1];
		}
		for(int i=c-1; i>1; i--) {
			arr[machine][i]=arr[machine][i-1];
		}
		arr[machine][1] = 0;
		//아래부분
		for(int i=machine+2; i<r-1; i++) {
			arr[i][0]=arr[i+1][0];
		}
		for(int i=0; i<c-1; i++) {
			arr[r-1][i]=arr[r-1][i+1];
		}
		for(int i=r-1; i>machine+1; i--) {
			arr[i][c-1]=arr[i-1][c-1];
		}
		for(int i=c-1; i>0; i--) {
			arr[machine+1][i]=arr[machine+1][i-1];
		}
		arr[machine+1][1] = 0;
	}
	
	public static void count() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(arr[i][j]!=-1 && arr[i][j]!=0)
					q.offer(new Location(i, j, arr[i][j]));
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		r = sc.nextInt();
		c = sc.nextInt();
		t = sc.nextInt();
		int ans = 0;
		arr = new int[r][c];
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				arr[i][j] = sc.nextInt();
				if(arr[i][j]==-1 && machine==0) machine=i;
				else if(arr[i][j]!=-1 && arr[i][j]!=0) q.offer(new Location(i, j, arr[i][j]));
			}
		}
		
		for (int i = 0; i < t; i++) {
			bfs();
			clean();
			if(i!=t-1)
				count();
		}
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(arr[i][j]!=-1)
					ans += arr[i][j];
			}
		}
		
		System.out.println(ans);
	}

	static class Location{
		int y;
		int x;
		int val;
		
		Location(int y, int x, int val){
			this.y = y;
			this.x = x;
			this.val = val;
		}
	}
}
