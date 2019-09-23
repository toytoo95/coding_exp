import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2115_벌꿀채취_승주 {
	public static int[][] honey, maxVal;
	public static int n, m, c, max, mval, mbowl;
	
	public static void getBowl(int y, int x, int i, int sum, int doub, int mdouble) {
//		System.out.println("좌표="+y+" "+(x+i));
		for (int j = i; j < m; j++) {
			if(honey[y][x+j]+sum>c) continue; //꿀통의 합이  c를 넘을 때
			doub += honey[y][x+j]*honey[y][x+j];
			sum += honey[y][x+j];
			if(doub > mdouble) mdouble = doub;
			if(sum == c) { //꿀통의 합이 c와 같을 때애도 max값 확인하고 return
				mbowl = Math.max(mbowl, mdouble);
				return;
			}
			getBowl(y, x, j+1, sum, doub, mdouble);
			doub -= honey[y][x+j]*honey[y][x+j];
			sum -= honey[y][x+j];
		}
		
		if(i==m) { //꿀통을 모두 수집했을 때
			mbowl = Math.max(mbowl, mdouble);
			return;
		}
	}
	
	public static void findMax(int y, int x, int k) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n-m+1; j++) {
				if(i==y && ((j>=x && j<=x+m-1) || (j>=x-m+1 && j<=x))) continue;
				max = Math.max(max, k+maxVal[i][j]);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int test = 1; test <= t; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			honey = new int[n][n];
			maxVal = new int[n][n];
			max = 0;
			mval = 0;
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
//					System.out.print(honey[i][j]+" ");
				}
//				System.out.println();
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n-m+1; j++) {
					mbowl = 0;
					getBowl(i, j, 0, 0, 0, 0); //
					maxVal[i][j] = mbowl;
//					System.out.print(mbowl+" ");
				}
//				System.out.println();
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n-m+1; j++) {
//					System.out.println("찾으러 간당 "+i+" "+j);
					findMax(i, j, maxVal[i][j]);
				}
			}
			
			System.out.println("#"+test+" "+max);
		}
	}
}
