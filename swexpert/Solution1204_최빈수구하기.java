import java.util.Scanner;
 
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
 
        T=sc.nextInt();
        for(int test_case = 1; test_case <= T; test_case++) {
            test_case = sc.nextInt();
            int []arr = new int[1000];
            int []check = new int[101];
 
            for (int i = 0; i < 1000; i++) {
                arr[i] = sc.nextInt();
                check[arr[i]] += 1; 
            }
 
            int max = check[0];
            int score = 0;
            for (int i = 0; i < 101; i++) {
                if(check[i] >= max) {
                    max = check[i];
                    score = i;
                }
            }
            System.out.println("#" + test_case + " " + score);
        }
    }
}
