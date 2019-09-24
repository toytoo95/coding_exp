import java.util.Scanner;

/**
  백트랙킹
*/

public class Solution {
    public static int[] pNum = {2, 3, 5, 7, 11, 13, 17};
    public static int cnt = 0;
 
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int t = sc.nextInt();
 
        for (int test_case = 1; test_case <= t; test_case++) {
            double answer = 0;
            double skillA = sc.nextDouble()/100;
            double skillB = sc.nextDouble()/100;
 
            double sA = 0;
            double sB = 0;
             
            if(skillA != 0 || skillB != 0) {
                for (int i = 0; i < 7; i++) {
                    cnt = 0;
                    comb(18, pNum[i]);
                    sA += cnt*Math.pow(skillA, pNum[i])*Math.pow(1-skillA, Math.abs(18-pNum[i]));
                    sB += cnt*Math.pow(skillB, pNum[i])*Math.pow(1-skillB, Math.abs(18-pNum[i]));
                }
            }
            answer = 1-(1-sA)*(1-sB);
            System.out.print("#"+test_case+" ");
            System.out.printf("%f \n", answer);
        }
    }
 
    private static void comb(int n, int r) {
        if(r == 0) {
            cnt++;
            return;
        }
 
        if(n < r)
            return;
 
        comb(n-1, r-1);
        comb(n-1, r);
    }
}
