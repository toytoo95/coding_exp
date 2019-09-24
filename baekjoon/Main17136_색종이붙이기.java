import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static ArrayList<Location> list;
	public static int[][] paper, arr;
	public static int min;
	
	public static boolean check(int y, int x, int size) {
		for (int i = y; i < y+size; i++) {
			for (int j = x; j < x+size; j++) {
				if(i<0 || j<0 || i>9 || j>9 || arr[i][j]!=1) {
					return false;
				}
				arr[i][j] = 2;
			}
		}
		return true;
	}

	public static void dfs(int i, int num, int[] papers) {
		if(i >= list.size() || min <= num) {
			min = Math.min(min, num);
			return;
		}
		
		Location lo = list.get(i);
		
		if(arr[lo.y][lo.x] > 1) {
			dfs(i+1, num, papers);
		}else {
			int[][] temp = new int[10][10];
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					temp[j][k] = arr[j][k];
				}
			}
			
			for (int j = 5; j > 0; j--) {
				if(lo.x+j-1>9 || lo.y+j-1>9 || arr[lo.y+j-1][lo.x+j-1]!=1 || papers[j]==0) continue; //최소한의 범위 설정
				
				if(check(lo.y, lo.x, j)) {
					papers[j] -= 1;
					dfs(i+1, num+1, papers);
					papers[j] += 1;
				}
				for (int k = 0; k < 10; k++) {
					for (int l = 0; l < 10; l++) {
						arr[k][l] = temp[k][l];
					}
				}
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		paper = new int[10][10];
		arr = new int[10][10];
		list = new ArrayList<>();
		int[] papers = {0, 5, 5, 5, 5, 5};
		min = 26;


		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
				arr[i][j] = paper[i][j];
				if(paper[i][j]==1) list.add(new Location(i, j));
			}
		}
		
		if(list.isEmpty()) min = 0;
		else dfs(0, 0, papers);
		
		if(min==26) min = -1;
		System.out.println(min);
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
