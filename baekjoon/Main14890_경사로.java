import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = 0;
		int n = sc.nextInt();
		int l = sc.nextInt();
		int[][] map = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < n; i++) { 
			int len = 1;
			int height = 0;
			for (int j = 1; j < n; j++) { //가로 한 줄 탐색
				if(map[i][j] == map[i][j-1]) {
					len++;
				}else if(map[i][j] > map[i][j-1] || map[i][j] < map[i][j-1]) {
					height++;
					if(map[i][j] - map[i][j-1] > 1 || map[i][j-1] - map[i][j] > 1 || height > 1) //높이 차이가 1이상 => 지나갈 수 없는 길
						break;
					else if(map[i][j-1] < map[i][j]) {
						if(len < l) {
							break;
						}
						len = 1;
						height = 0;
					}else{
						len = 1;
					}
				}
				if(height == 1 && len == l){
					len = 0;
					height = 0;
				}
				
				if(j == n-1) {
					if(height == 1 && len < l)
						break;
					else
						count++;
				}
			}
			len = 1;
			height = 0;
      
			for (int j = 1; j < n; j++) { //세로 한 줄 탐색
				if(map[j-1][i] == map[j][i]) {
					len++;
				}else if(map[j][i] > map[j-1][i] || map[j][i] < map[j-1][i]) {
					height++;
					if(map[j][i] - map[j-1][i] > 1 || map[j-1][i] - map[j][i] > 1 || height > 1) //높이 차이가 1이상 => 지나갈 수 없는 길
						break;
					else if(map[j][i] > map[j-1][i]) {
						if(len < l) {
							break;
						}
						len = 1;
						height = 0;
					}else {
						len = 1;
					}
				}
				if(height == 1 && len == l) {
					len = 0;
					height = 0;
				}
				
				if(j == n-1) {
					if(height == 1 && len < l)
						break;
					else
						count++;
				}
			}		
		}
		System.out.println(count);
	}
}
