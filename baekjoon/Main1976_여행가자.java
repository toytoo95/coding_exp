import java.util.Scanner;

public class Main {
	public static int[][] city;
	public static int[] trip;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		city = new int[n][n];
		trip = new int[m];
		boolean flag = true;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				city[i][j] = sc.nextInt();
                if(i==j) city[i][j]=1;
			}
		}
		for (int i = 0; i < m; i++) {
			trip[i] = sc.nextInt()-1;
		}
    
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if(city[j][i]==1 && city[i][k]==1) city[j][k]=1;
				}
			}
		}
		
		for (int i = 0; i < m-1; i++) {
			if(city[trip[i]][trip[i+1]]==0) {
				flag = false;
				break;
			}
		}
		
		if(flag)System.out.println("YES");
		else System.out.println("NO");
	}
}
