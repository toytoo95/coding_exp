import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14889_스타트와링크 {
	public static int[] list;
	public static int[][] arr;
	public static int n, min;
	
	public static void getNum(int[] other) { //나머지 수 채우기
		int num = 0;
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if(num<n/2 && list[num]==i) {
				num++;
				continue;
			}
			other[cnt] = i;
			cnt++;
		}
	}
	
	public static void check() { //능력치 비교 함수
		int[] other = new int[n/2];
		int start = 0;
		int link = 0;
		
		getNum(other);
		
		for (int i = 0; i < n/2; i++) {
			int num = list[i];
			for (int j = 0; j < n/2; j++) { //dfs에서 담은 수
				if(num==list[j]) continue;
				start += arr[num][list[j]];
			}
			
			num = other[i];
			for (int j = 0; j < n/2; j++) { //dfs에서 담고 남은 나머지
				if(num==list[j]) continue;
				link += arr[num][other[j]];
			}
		}
		min = Math.min(min, Math.abs(start-link));
	}
	
	public static void dfs(int cnt, int num) {
		if(cnt==n/2) {
			check();
			return;
		}
		
		for (int i = num; i < n; i++) {
			list[cnt] = i;
			dfs(cnt+1, i+1);
			list[cnt] = 0;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		list = new int[n/2];
		min = Integer.MAX_VALUE;
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0);
		
		System.out.println(min);
	}
}
