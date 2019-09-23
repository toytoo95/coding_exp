import java.util.Scanner;
 
public class Solution4672_수진이의팰린드롬 {
    public static int[] word;
    public static int max;
    public static void check(int n) {
        int count = 0;
         
        for (int i = 1; i <= n; i++) {
            count+=i;
        }
        max += count;
    }
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
         
        for (int test = 1; test <= t; test++) {
            String s = sc.next();
            word = new int[26];
            max = 0;
             
            for (int i = 0; i < s.length(); i++) {
                word[s.charAt(i)-'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if(word[i]!=0) {
                    check(word[i]);
                }
            }
             
            System.out.println("#"+test+" "+max);
        }
    }
}
