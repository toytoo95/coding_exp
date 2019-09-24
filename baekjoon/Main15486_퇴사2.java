import java.util.Scanner;

//퇴사2
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N;

		N = sc.nextInt(); //며칠 남았는지 받기
		int []T = new int[N+2];
		int []P = new int[N+2];
		int[] dp = new int[N+2];

		int max = 0;

		for (int i = 0; i < N; i++) {
			T[i+1] = sc.nextInt(); //상담 걸리는 시간
			P[i+1] = sc.nextInt(); //받는 돈
		}

		for (int i = 1; i <=N+1; i++) {
			dp[i] = Math.max(dp[i], max);
			if (T[i]+i < N+2)
				dp[T[i]+i] = Math.max(dp[T[i]+i],P[i]+dp[i]);
			//출력될 최대 수입
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}
