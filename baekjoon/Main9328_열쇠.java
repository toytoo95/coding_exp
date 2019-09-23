import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * <빌딩 조건>
	'.'는 빈 공간을 나타낸다.
	'*'는 벽을 나타내며, 상근이는 벽을 통과할 수 없다.
	'$'는 상근이가 훔쳐야하는 문서이다.
	알파벳 대문자는 문을 나타낸다.
	알파벳 소문자는 열쇠를 나타내며, 그 문자의 대문자인 모든 문을 열 수 있다.
	
	*<열쇠 조건>
	* 문을 열 수 있는 열쇠의 개수는 0개, 1개, 또는 그 이상
	* 열쇠로 열 수 있는 문의 개수는 0개, 1개, 또는 그 이상
	* 열쇠는 여러번 사용가능
	*
	*<해결방법>
	* 1 바깥쪽 위주로 for문 돌리면서 길 찾기(이미 가지고있는 열쇠는 사용해서 문 열기)
	* 2 갈 수 있는 길 찾으면 쭉 들어가기
	* 2.1 열쇠찾기(bfs)
	* 3 문열고 들어가서 문서 찾기(bfs)
	*/

public class Baekjoon9328_열쇠 {
	public static char[][] arr;
	public static boolean[][] visit;
	public static ArrayList<Character> key;
	public static Queue<Location> way;
	public static int[] dy = {1, 0, -1, 0};
	public static int[] dx = {0, 1, 0, -1};
	public static int max, h, w;
	
	public static void bfs() {
		visit = new boolean[h][w];
		Queue<Location> q = new LinkedList<>();
		q.addAll(way);
		
		while(!q.isEmpty()) {
			Location d = q.poll();
			for (int i = 0; i < 4; i++) {
				int ny = d.y + dy[i];
				int nx = d.x + dx[i];
				
				if(ny<0 || nx<0 || ny>=h || nx>=w || arr[ny][nx]=='*' || visit[ny][nx]) continue;
				if(arr[ny][nx]=='.' || arr[ny][nx]=='$') {
					if(arr[ny][nx]=='$') {
						max += 1;
						arr[ny][nx] = '.';
					}
					visit[ny][nx] = true;
					q.add(new Location(ny, nx));
				}else { //열쇠나 문일 때
					String c = Character.toString(arr[ny][nx]);
					if(c.equals(c.toLowerCase())) { //찾은게 열쇠일 때
						boolean flag = true;
						for (int j = 0; j < key.size(); j++) {
							if(arr[ny][nx]==key.get(j)) {
								flag = false;
								break;
							}
						}
						if(flag) { //새로운 열쇠가 들어왔을 때
							key.add(arr[ny][nx]);
							arr[ny][nx] = '.';
							q.clear();
							find();
						}else arr[ny][nx] = '.';
					}else { //문일 때
						char k = c.toLowerCase().charAt(0);
						for (int j = 0; j < key.size(); j++) {
							if(k==key.get(j)) { //맞는 열쇠가 있을 때
								arr[ny][nx] = '.';
								visit[ny][nx] = true;
								q.add(new Location(ny, nx));
							}
						}
					}
				}
			}
		}
	}

	public static void find() { //겉을 훑어서 갈 수 있는 길 찾는 함수
		way = new LinkedList<>();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if(arr[i][j]=='.') {
					way.add(new Location(i, j));
				}else if(arr[i][j]=='$') {
					max+=1;
					arr[i][j] = '.';
					way.add(new Location(i, j));
				}else if(arr[i][j]!='*') { //문이나 열쇠일 때
					String c = Character.toString(arr[i][j]);
					if(c.equals(c.toLowerCase())) { //찾은게 열쇠일 때
						key.add(arr[i][j]);
						arr[i][j] = '.';
						way.add(new Location(i, j));
					}else { //문일 때
						char k = c.toLowerCase().charAt(0);
						for (int l = 0; l < key.size(); l++) {
							if(k==key.get(l)) { //문에 맞는 열쇠가 있을 때
								arr[i][j] = '.';
								way.add(new Location(i, j));
							}
						}
					}
				}
				if(i!=0 && i!=h-1 && j!=w-1) j = w-2;
			}
		}
		bfs();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int test = 0; test < t; test++) {
			h = sc.nextInt();
			w = sc.nextInt();
			arr = new char[h][w];
			visit = new boolean[h][w];
			max = 0;
			way = new LinkedList<>();
			
			sc.nextLine();
			for (int i = 0; i < h; i++) {
				String s = sc.nextLine(); //빌딩 한줄 받기
				for (int j = 0; j < w; j++) {
					arr[i][j] = s.charAt(j); //잘라서 배열에 입력
				}
			}
			String k = sc.nextLine(); //열쇠 문자 받기, 없으면 0들어옴
			key = new ArrayList<>();
			if(!k.equals("0")) { //열쇠가 없으면 입력할 필요X
				for (int i = 0; i < k.length(); i++) {
					key.add(k.charAt(i)); //열쇠 입력
				}
			}
			find();
			System.out.println(max);
		}
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
