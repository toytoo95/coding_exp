import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
 
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int[][] arr = new int[300][300];
         
        for (int j = 1; j < 300; j++) { //1행에 값 넣기
            if(j==1) arr[1][j] = 1;
            else arr[1][j] = arr[1][j-1]+j;
        }
        
        for (int i = 1; i < 300; i++) { //열
            int k = i;
            for (int j = 2; j < 300; j++) { //행
                if(j==2) arr[j][i] = arr[j-1][i]+k;
                else arr[j][i] = arr[j-1][i]+k;
                k++;
            }
        }
         
        int t = sc.nextInt();
        for (int test = 1; test <= t; test++) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            int[] x = new int[2];
            int[] y = new int[2];
            int k = 0;
 
            for (int i = 1; i < 300; i++) {
                boolean flag = true;
                for (int j = 1; j < 300; j++) {
                    if(arr[i][j]==p) {
                        x[k] = j;
                        y[k] = i;
                        k++;
                    }
                    if(arr[i][j]==q) {
                        x[k] = j;
                        y[k] = i;
                        k++;
                    }
                    if(k==2) {
                        flag = false;
                        break;
                    }
                }
                if(flag == false) break;
            }
            System.out.println("#"+test+" "+arr[y[0]+y[1]][x[0]+x[1]]);
        }
    }
}
