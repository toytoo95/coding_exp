import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
    public static int[] number;
    public static int change;
    public static int check;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
 
        for (int test_case = 1; test_case <= t; test_case++) {
            String num = sc.next();
            change = sc.nextInt();
            number = new int[num.length()];
            for (int i = 0; i < num.length(); i++) {
                number[i] = Integer.parseInt(num.split("")[i]);
            }
            check = 0;
            perm(0, 0);
 
            System.out.println("#"+test_case+" "+check);
        }
    }
     
    private static void swap(int i, int k) {
        int temp = number[i];
        number[i] = number[k];
        number[k] = temp;   
    }
 
    private static void perm(int count, int k) {
        if(count == change) {
            if(check < Integer.parseInt(Arrays.toString(number).replace("[","").replace(",","").replace(" ","").replace("]","")))
                check = Integer.parseInt(Arrays.toString(number).replace("[","").replace(",","").replace(" ","").replace("]",""));
            return;
        }
        for (int i = k; i < number.length; i++) {
            for (int j = i+1; j < number.length; j++) {
                if(number[j] >= number[i]) {
                    swap(i, j);
                    perm(count+1, i);
                    swap(i, j);
                }
            }
        }   
    }
}
