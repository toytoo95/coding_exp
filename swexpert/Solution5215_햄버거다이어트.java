import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
    public static int[][] ingr;
    public static int n, kal, max;
 
    public static void getTaste(int i, int score, int kals) {
        if(i == n) {
            max = Math.max(max,  score);
            return;
        }
        if(kal>=kals+ingr[i][1]) getTaste(i+1, score+ingr[i][0], kals+ingr[i][1]);
         
        getTaste(i+1, score, kals);
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
 
        for (int test = 1; test <= t; test++) {
            n = sc.nextInt();
            kal = sc.nextInt();
            ingr = new int[n][2];
            max = 0;
 
            for (int i = 0; i < n; i++) {
                ingr[i][0] = sc.nextInt(); //점수
                ingr[i][1] = sc.nextInt(); //칼로리
            }
 
            getTaste(0, 0, 0);
 
            System.out.println("#"+test+" "+max);
        }
    }
}
