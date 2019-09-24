import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

			int n = sc.nextInt(); //검색대 개수
			int m = sc.nextInt(); //통과해야 하는 사람
			long[] time = new long[n];

			for (int i = 0; i < n; i++) {
				time[i] = sc.nextLong();
			}
      
			Arrays.sort(time);
			long ans = time[n-1]*m;
			long left = 0;
			long right = time[n-1]*m;

			while(left <= right) {
				long mid = (left+right)/2;
				long total = 0;

				for (int i = 0; i < n; i++) 
					total += mid/time[i];

				if(total<m) left = mid+1;
				else {
					right = mid-1;
					if(ans>mid) ans = mid;
				}
			}
			System.out.println(ans);
	}
}
