import java.util.Scanner;
 
public class Solution4796_의석이의우뚝선산 {
    public static int[] height;
    public static int n, ans;
     
    public static int check(int idx) {
        int cntL = 0;
        int cntR = 0;
         
        for (int i = idx; i < n; i++) {
            if(height[i]<height[i-1]) { //산 꼭대기일 때
                for (int j = i; j < n; j++) {
                    if(height[j]>height[j-1]) {
                        break;
                    }
                    else cntR++;
                }
                break;
            }else if(height[i]>height[i-1]) cntL++;
        }
         
        ans+=cntL*cntR;
        return cntL+cntR-1;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
         
        for (int test = 1; test <= t; test++) {
            n = sc.nextInt();
            height = new int[n];
            ans = 0;
             
            for (int i = 0; i < n; i++) {
                height[i] = sc.nextInt();
            }
             
            for (int i = 0; i < n-1; i++) {
                if(height[i]<height[i+1]) {
                    i+=check(i+1);
                }
            }
             
            System.out.println("#"+test+" "+ans);
        }
    }
}
