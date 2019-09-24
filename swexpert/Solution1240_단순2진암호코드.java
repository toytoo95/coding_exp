import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
 
        for (int test_case = 1; test_case <= 10; test_case++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            String[] arr = {"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};
            String[] code = new String[8];
            String line = null;
            int[] codeNum = new int[8];
 
            sc.nextLine();
            for (int i = 0; i < n; i++) {
                String s = sc.nextLine();
                 
                for (int j = 0; j < m; j++) {
                    if(s.split("")[j].equals("1")) {
                        line = s;
                        break;
                    }
                }
            }
            if(line != null) {
                for (int i = m-1; i >= 0; i--) {
                    if(line.split("")[i].equals("1")) {
                        for (int j = 7; j >= 0; j--) {
                            code[j] = line.substring(i-6, i+1);
                            i -= 7;
                        }
                    }
                }
            }
            int sum = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j <= 9; j++) {
                    if(code[i].equals(arr[j])) {
                        codeNum[i] = j;
                    }
                }
            }
            for (int i = 0; i < codeNum.length; i++) {
                if(i%2 == 0)
                    sum += codeNum[i]*3;
                else
                    sum += codeNum[i];
            }
            if(sum%10 == 0) {
                sum = 0;
                for (int i = 0; i < codeNum.length; i++) {
                    sum += codeNum[i];
                }
            } else sum = 0;
            System.out.println("#"+test_case+" "+sum);
        }
    }
}
