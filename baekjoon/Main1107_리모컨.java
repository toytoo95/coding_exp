import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static int[] number;
	public static int n, m, min, len;
	
	public static void dfs(int cnt, int chan) {

		if(cnt>0) {
			if(chan >= n) min = Math.min(min, chan-n+cnt);
			else min = Math.min(min, n-chan+cnt);
			if(cnt>len) return;
		}
		
		for (int i = 0; i < number.length; i++) {
			chan += number[i]*Math.pow(10, cnt);
			dfs(cnt+1, chan);
			chan -= number[i]*Math.pow(10, cnt);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		number = new int[10-m];
		int[] sub = new int[m];
		
		if(n>100) min = n-100; //처음 시작점 100에서의 거리를 최소값으로 두고 시작
		else min = 100-n;
		
		if(m > 0) {
			for (int i = 0; i < m; i++) {
				sub[i] = sc.nextInt();
			}
			Arrays.sort(sub);
		}
		
		if(m < 10) { //안눌리는 번호 제외하고 눌리는 번호 받기
			int num = 0;
			int j = 0;
			for (int i = 0; i < 10; i++) {
				if(j<m && sub[j]==i) {
					j++;
					continue;
				}
				number[num] = i;
				num++;
			}
		}
		
		if(n>=10 && n<100) len = 2;
		else if(n>=100 && n<1000) len = 3;
		else if(n>=1000 && n<10000) len = 4;
		else if(n>=10000 && n<100000) len = 5;
		else if(n>=100000 && n<1000000) len = 6;
		else if(n>=1000000) len = 7;
		else len = 1;
		
		if(m!=100) dfs(0, 0);
		
		System.out.println(min);
	}
}
