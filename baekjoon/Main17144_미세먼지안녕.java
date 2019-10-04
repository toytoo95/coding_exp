import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main17144_미세먼지안녕 {
	public static ArrayList<Location> list;
	public static int[] dy = {0, 1, 0, -1}; //동 남 서 북
	public static int[] dx = {1, 0, -1, 0};
	public static int[][] arr;
	public static int r, c, t, uy, doy;
	
	public static int move(int y, int x, int d, int num) { //공기 청정기 작동
		while(true) {
			y += dy[d];
			x += dx[d];
			
			if(y<0 || x<0 || y>=r || x>=c || arr[y][x]==-1) break;
			int tmp = arr[y][x];
			arr[y][x] = num;
			num = tmp;
		}
		return num;
	}
	
	public static void dust(int y, int x, int val) { //미세먼지 확산
		int cnt = 0;
		int num = val/5;
		
		for (int i = 0; i < 4; i++) {
			int ny = y+dy[i];
			int nx = x+dx[i];
			if(ny<0 || nx<0 || ny>=r || nx>=c || arr[ny][nx]==-1) continue;
			arr[ny][nx] += num; //확산 되는 자리가 0이 아닐 수도 있기 때문에
			cnt++;
		}
		arr[y][x] -= num*cnt;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		arr = new int[r][c];
		list = new ArrayList<>();
		int ans = 0;
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==-1 && uy==0) uy = i;
				else if(arr[i][j]==-1 && uy!=0) doy = i;
				else if(arr[i][j]>4) list.add(new Location(i, j, arr[i][j]));
			}
		}
		
		for (int i = 0; i < t; i++) {
			for (int j = 0; j < list.size(); j++) {
				Location lo = list.get(j);
				dust(lo.y, lo.x, lo.val);
			}
			int num = move(uy, 0, 0, 0);
			num = move(uy, c-1, 3, num);
			num = move(0, c-1, 2, num);
			move(0, 0, 1, num);
			num = move(doy, 0, 0, 0);
			num = move(doy, c-1, 1, num);
			num = move(r-1, c-1, 2, num);
			move(r-1, 0, 3, num);
			
			if(i<t-1) {
				list.clear();
				for (int j = 0; j < r; j++) {
					for (int k = 0; k < c; k++) {
						if(arr[j][k]>4) list.add(new Location(j, k, arr[j][k]));
					}
				}
			}
		}
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(arr[i][j]==0 || arr[i][j]==-1) continue;
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
