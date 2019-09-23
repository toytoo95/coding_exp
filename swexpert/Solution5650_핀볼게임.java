import java.util.ArrayList;
import java.util.Scanner;

/**
 * 출발지점에 도착하거나 블랙홀에 빠지면 끝
 * 벽이나 블록에 부딪히면 +1점
 * */

public class Solution5650_핀볼게임 {
	public static ArrayList<Location> list, warm;
	public static int[][] arr;
	public static int max, sy, sx;
	public static int[] dy = {1, 0, -1, 0}; //남 동 북 서
	public static int[] dx = {0, 1, 0, -1};
	
	private static void move(int y, int x, int dir, int cnt) { //움직일 애
		if(y<0 || y>=arr.length || x<0 || x>=arr.length) {
			cnt++;
			switch (dir) {
			case 0:
				dir = 2;
				break;
			case 1:
				dir = 3;
				break;
			case 2:
				dir = 0;
				break;
			case 3:
				dir = 1;
				break;
			default:
				break;
			}
		}
		else if(arr[y][x]==-1 || (y==sy && x==sx)) { //블랙홀이나 시작점에 도착했을 때
			max = Math.max(max, cnt);
			return;
		}else if(5 < arr[y][x] && arr[y][x] <= 10) { //웜홀에 빠졌을 때
			for (int i = 0; i < warm.size(); i++) {
				Location d = warm.get(i);
				if(d.val==arr[y][x] && d.y!=y && d.x!=x) {
					y = d.y;
					x = d.x;
					break;
				}
			}
		}else if(arr[y][x]!=0) { //벽일 때
			cnt++;
			switch (arr[y][x]) {
			case 1:
				if(dir==0) dir=1;
				else if(dir==3) dir=2;
				else if(dir==1) dir=3;
				else dir=0;
				break;
			case 2:
				if(dir==0) dir=2;
				else if(dir==1) dir=3;
				else if(dir==2) dir=1;
				else dir=0;
				break;
			case 3:
				if(dir==0) dir=2;
				else if(dir==1) dir=0;
				else if(dir==2) dir=3;
				else dir=1;
				break;
			case 4:
				if(dir==0) dir=3;
				else if(dir==1) dir=2;
				else if(dir==2) dir=0;
				else dir=1;
				break;
			case 5:
				if(dir==0) dir=2;
				else if(dir==1) dir=3;
				else if(dir==2) dir=0;
				else dir=1;
				break;
			default:
				break;
			}
		}
		
		move(y+dy[dir], x+dx[dir], dir, cnt);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int test = 1; test <= t; test++) {
			int n = sc.nextInt();
			arr = new int[n][n];
			max = 0;
			list = new ArrayList<>();
			warm = new ArrayList<>();
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					arr[i][j] = sc.nextInt();
					if(5 < arr[i][j] && arr[i][j] <= 10) //웜홀 넣기
						warm.add(new Location(i, j, arr[i][j]));
					else if(arr[i][j]==0) //빈공간 넣기
						list.add(new Location(i, j));
				}
			}
			
			for (int i = 0; i < list.size(); i++) {
				Location lo = list.get(i);
				sy = lo.y;
				sx = lo.x;
				for (int j = 0; j < 4; j++) {
					System.out.println(lo.y+" "+ lo.x+" "+j);
					if(lo.y+dy[j]<0 || lo.y+dy[j]>=n || lo.x+dx[j]<0 || lo.x+dx[j]>=n) continue;
					move(lo.y+dy[j], lo.x+dx[j], j, 0);
				}
			}
			
			System.out.println("#"+test+" "+max);
		}
	}

	static class Location{
		int y;
		int x;
		int val;

		Location(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		Location(int y, int x, int val){
			this.y = y;
			this.x = x;
			this.val = val;
		}
	}
}
