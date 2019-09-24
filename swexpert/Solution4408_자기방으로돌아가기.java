import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int test = 1; test <= t; test++) {
            int n = sc.nextInt();
            int[] first = new int[n];
            int[] end = new int[n];
            int[] room = new int[201];
             
            for (int i = 0; i < n; i++) {
                first[i] = sc.nextInt();
                end[i] = sc.nextInt();
            }
             
            for (int i = 0; i < n; i++) {
                if(first[i] > end[i]) {
                    int fin = first[i]/2+first[i]%2;
                    int now = end[i]/2+end[i]%2;
                    for (int j = now; j <= fin; j++) {
                        room[j] += 1;
                    }
                }
                else {
                    int now = first[i]/2+first[i]%2;
                    int fin = end[i]/2+end[i]%2;
                    //System.out.println(now);
                    for (int j = now; j <= fin; j++) {
                        room[j] += 1;
                    }
                }
            }
            Arrays.sort(room);
            System.out.println("#"+test+" "+room[199]);
        }
    }
}
