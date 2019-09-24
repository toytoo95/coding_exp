import java.util.Scanner;
 
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N;
        int temp = 0;
 
        N = sc.nextInt();
        int []score = new int[N];
        for (int i = 0; i < N; i++) 
            score[i] = sc.nextInt();
 
        for (int i = 0; i < score.length-1; i++) { 
            for (int j = 0; j < score.length-i-1; j++) {
                if (score[j]> score[j+1]) {
                    temp = score[j];
                    score[j] = score[j+1];
                    score[j+1] = temp;
                }
            }
        }
        System.out.println(score[N/2]);
    }
}
