import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution2382_미생물격리_승주 {
	public static ArrayList<Location> list;
	public static Location[][] arr;
	public static int[] dy = {-1, 1, 0, 0}; //상 하 좌 우
	public static int[] dx = {0, 0, -1, 1};
	public static int n, m, k;
	
	public static void check(Location lo) { //해당 자리에 여러 그룹이 겹칠때 
		if(arr[lo.y][lo.x] == null) { //빈 자리일 때
			arr[lo.y][lo.x] = lo;
			return;
		}else if(arr[lo.y][lo.x].val < lo.val) { //새로 온 그룹의 미생물수가 더 많을 때
			lo.sum += arr[lo.y][lo.x].sum + arr[lo.y][lo.x].val;
			arr[lo.y][lo.x] = lo;
		}else { //원래 있던 애가 클 때
			arr[lo.y][lo.x].sum += lo.val;
		}
	}
	
	public static void move() {
		arr = new Location[n][n];
		int num = list.size();
		
		for (int i = num-1; i >= 0; i--) {
			Location lo = list.get(i);
			list.remove(i);
			int ny = lo.y+dy[lo.dir];
			int nx = lo.x+dx[lo.dir];
			
			if(ny<0 || nx<0 || ny>=n || nx>=n) continue; //범위 벗어나는 경우
			lo.y = ny;
			lo.x = nx;
			if(ny==0 || nx==0 || ny==n-1 || nx==n-1) { //약품이 칠해진 셀이 도착했을 때
				int dir = 0;
				lo.val /= 2;
				if(lo.val == 0) continue; //미생물 수가 0이되면 멈추고 다음 그룹 확인
				switch (lo.dir) { //방향 반대로 바꾸기
				case 0: case 2:
					dir = lo.dir+1;
					break;
				case 1: case 3:
					dir = lo.dir-1;
					break;
				default:
					break;
				}
				lo.dir = dir;
			}
			check(lo);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int test = 1; test <= t; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			int ans = 0;
			
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken()); //미생물 수
				int dir = Integer.parseInt(st.nextToken())-1;
				
				list.add(new Location(y, x, num, dir, 0));
			}
			
			for (int i = 0; i < m; i++) { //시간만큼 돌리기
				move();
				for (int j = 0; j < n; j++) { //한 번 돌리고 리스트에 담기
					for (int k = 0; k < n; k++) {
						if(arr[j][k] != null) {
							if(arr[j][k].sum!=0) {
								arr[j][k].val += arr[j][k].sum;
								arr[j][k].sum = 0;
							}
							list.add(arr[j][k]);
						}
					}
				}
			}
			
			for (int i = 0; i < list.size(); i++) {
				ans += list.get(i).val;
			}
			
			System.out.println("#"+ test+" "+ans);
		}
	}
	
	static class Location{
		int y;
		int x;
		int val;
		int dir;
		int sum;
		
		Location(int y, int x, int val, int dir, int sum){
			this.y = y;
			this.x = x;
			this.val = val;
			this.dir = dir;
			this.sum = sum;
		}
	}
}
