import java.util.Scanner;
 
public class Solution {
    public static String[] word;
    public static char[][] key = {{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
    public static int ans, n;
    public static String s;
     
    public static void check(int w) {
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) { //눌린 번호만큼
            flag = false;
            int num = s.charAt(i)-'0'; //자판 번호
            for (int j = 0; j < 4; j++) { //자판 알파벳 만큼
                if((j==3 && !(num==7 || num==9))) break;
                 
                if(i>=word[w].length()) break;
                else if(key[num-2][j]==word[w].charAt(i)) {
                    //System.out.println(w+" same: "+key[num-2][j]+", "+word[w].charAt(i));
                    flag = true;
                    break;
                }
            }
            if(!flag) return;
        }
        ans++;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
         
        for (int test = 1; test <= t; test++) {
            s = sc.next();
            n = sc.nextInt();
            word = new String[n]; //사전 단어
            ans = 0;
             
            for (int i = 0; i < n; i++) {
                word[i] = sc.next();
            }
             
            for (int i = 0; i < word.length; i++) {
                check(i);
            }           
             
            System.out.println("#"+test+" "+ans);
        }
    }
}
