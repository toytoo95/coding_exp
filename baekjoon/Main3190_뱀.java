import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main3190_뱀 {
	public static int[] dy = {1, 0, -1, 0}; //남 동 북 서
	public static int[] dx = {0, 1, 0, -1};
	public static int[][] board, turn;
	public static int n, k, sec, l;
	public static ArrayList<Location> list;
	public static boolean flag;
	
	public static void move(int dir) {
		Location lo = list.get(0);
		Location last = list.get(list.size()-1);
		dir = (lo.d+dir)%4;
		int ny = lo.y+dy[lo.d];
		int nx = lo.x+dx[lo.d];
    
		if(ny<0 || nx<0 || ny>=n || nx>=n || board[ny][nx]==1) flag = false;
		else if(board[ny][nx]==4) {
			list.add(0, new Location(ny, nx, dir));
			board[ny][nx] = 1;
		}
		else { //움직일 자리에 사과도 없고 벽이나 자신의 몸도 없을 때
			board[last.y][last.x] = 0; //꼬리부분을 다시 빈 곳으로 만들기
			list.remove(list.size()-1); //꼬리 삭제
			list.add(0, new Location(ny, nx, dir)); //머리 집어넣기
			board[ny][nx] = 1;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		board = new int[n][n];
		sec = 0;
		flag = true;
		list = new ArrayList<>();
		list.add(new Location(0, 0, 1));
		board[0][0] = 1;
		
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			board[y][x] = 4; //사과 위치
		}
		
		l = Integer.parseInt(br.readLine());
		turn = new int[l][2];
		for (int i = 0; i < l; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			turn[i][0] = Integer.parseInt(st.nextToken());
			String s = st.nextToken();
			if(s.equals("L")) turn[i][1] = 1;
			else turn[i][1] = 3;
		}
		
		int num = 0;
		while(flag) {
			sec++;
			int dir = 0;
			if(num<l && turn[num][0]==sec) {
				dir = turn[num][1];
				num++;
			}
			move(dir);
		}
		System.out.println(sec);
	}
	
	static class Location{
		int y;
		int x;
		int d;
		Location(int y, int x, int d){
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
}
