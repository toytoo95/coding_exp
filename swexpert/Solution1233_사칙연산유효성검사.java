import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 
public class Solution {
    public static int n;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        for (int test_case = 1; test_case <= 10; test_case++) {
            int result = 1;
            n = sc.nextInt(); //정점의 개수
 
            sc.nextLine();
            for (int i = 1; i <= n; i++) {
                String line = sc.nextLine();
                String[] arr = line.split(" ");
 
                if(arr[1].equals("-") || arr[1].equals("+") || arr[1].equals("*") || arr[1].equals("/")) {
                    if(arr.length < 4)
                        result = 0;
                }else {
                    if(arr.length > 2)
                        result = 0;
                }
            }
            System.out.println("#"+test_case+" "+result);
        }
    }
}
