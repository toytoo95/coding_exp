import java.io.FileInputStream;
import java.util.Scanner;
 
public class Solution {
    static int Answer;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int T, seq;
 
        T=sc.nextInt();
        for(int test_case = 1; test_case <= T; test_case++) {
            seq = sc.nextInt(); //수열의 길이
            int []arr = new int[seq];
            int []dp = new int[seq];
            Answer = 0;
 
            for (int i = 0; i < seq; i++) { //수열의 크기만큼 배열에 받아오기
                arr[i] = sc.nextInt(); //수열의 원소 받기
            }
            dp[0] = 1;
            for (int i = 1; i < seq; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (arr[j] < arr[i] && dp[i] < dp[j]+1) {
                        dp[i] = dp[i]+1;
                    }
                }
                if(Answer < dp[i])
                    Answer = dp[i];
            }
            System.out.println("#" + test_case + " " + Answer);
        }
    }
}
