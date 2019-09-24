import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int test = 1; test <= t; test++) {
            int n = sc.nextInt();
            int[] A = new int[n];
            int[] B = new int[n];
            int[] busStop = new int[5001];
             
            for (int i = 0; i < n; i++) {
                A[i] = sc.nextInt();
                B[i] = sc.nextInt();
                for (int j = A[i]; j <= B[i]; j++) {
                    busStop[j] += 1;
                }
            }
             
            int p = sc.nextInt();
            System.out.print("#"+test+" ");
            for (int i = 0; i < p; i++) {
                int c = sc.nextInt();
                System.out.print(busStop[c]+" ");
            }
            System.out.println();
        }
    }
}
