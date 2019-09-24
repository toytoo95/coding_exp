import java.util.Arrays;
import java.util.Scanner;

/*dp*/
 
public class Solution {
    public static int n, k;
    public static int[][] bag;
    public static int[][] thing;
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
         
        for (int test_case = 1; test_case < t+1; test_case++) {
            n = sc.nextInt();
            k = sc.nextInt();
            thing = new int[n+1][2];
            bag = new int[n+1][k+1];
             
            for (int i = 1; i < n+1; i++) {
                thing[i][0] = sc.nextInt(); //부피
                thing[i][1] = sc.nextInt(); //가치
            }
             
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= k; j++) {
                    if(thing[i][0] > j) bag[i][j] = bag[i-1][j];
                    else {
                        bag[i][j] = Math.max(bag[i-1][j-thing[i][0]]+thing[i][1], bag[i-1][j]);
                    }
                }
            }
            System.out.println("#"+test_case+" "+bag[n][k]);
        }
    }
}
About
