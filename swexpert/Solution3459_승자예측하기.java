import java.util.Scanner;
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
         
        for (int test = 1; test <= t; test++) {
            long n = sc.nextLong();
            int cnt = 0, x = 0;
            boolean turn = true; //true일 때는 alice, false일 때는 bob
            long sub = 1;
             
            while(n>0) {
                n -= sub;
                if(turn) sub *= 4;
                turn = !turn;
            }
            if(turn)
                System.out.println("#"+test+" Alice");
            else
                System.out.println("#"+test+" Bob");
        }
    }
}
