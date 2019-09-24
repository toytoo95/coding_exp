import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
         
        for (int test = 1; test <= t; test++) {
            int n = Integer.parseInt(br.readLine().trim());
            boolean[] arr = new boolean[n+1];
            int ans = 0;
            int num = 0;
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
             
            for (int i = 0; i < n; i++) {
                num = Integer.parseInt(st.nextToken());
                if(!arr[num-1]) ans += 1;
                arr[num] = true;
            }
            System.out.println("#"+test+" "+ans);
        }
    }
}
