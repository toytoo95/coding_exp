import java.util.Scanner;
 
public class Solution {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
         
        for (int test = 1; test <= t; test++) {
            String a = sc.next();
            String b = sc.next();
            int cnt = 0;
             
                for (int i = 0; i < a.length()-b.length()+1; i++) {
                    if(a.substring(i, i+b.length()).equals(b)) {
                        cnt++;
                        i += b.length()-1;
                    }
                }
                System.out.println("#"+test+" "+(a.length()-cnt*b.length()+cnt));
        }
    }
}
