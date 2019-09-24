import java.util.Scanner;
 
public class Solution {
    public static int[] plan, money, check;
    public static int min;
     
    public static void dfs(int month, int sum) {
        if(month>=13) {
            min = Math.min(min, sum);
            return;
        }
         
        dfs(month+1, sum+plan[month]*money[0]);
        dfs(month+1, sum+money[1]);
        dfs(month+3, sum+money[2]);
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
 
        for (int test = 1; test <= t; test++) {
            money = new int[4];
            check = new int[13];
            for (int i = 0; i < 4; i++) {
                money[i] = sc.nextInt();
            }
            plan = new int[13];
            min = money[3];
 
            for (int i = 1; i < 13; i++) {
                plan[i] = sc.nextInt();
            }
            dfs(1, 0);
            System.out.println("#"+test+" "+min);
        }
    }
}
