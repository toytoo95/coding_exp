import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*List*/
 
public class Solution {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
 
        for (int test_case = 1; test_case < 11; test_case++) {
            int n = sc.nextInt();
            List<Integer> code = new ArrayList();
            List<Integer> arr = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                code.add(sc.nextInt());
            }
            
            int a = sc.nextInt();
            for (int i = 0; i < a; i++) {
                char type = sc.next().charAt(0);
                int x = sc.nextInt();
                int y = sc.nextInt();
 
                for (int j = 0; j < y; j++) {
                    arr.add(sc.nextInt());  
                }
 
                while(arr.size() != 0) {
                    code.add(x, arr.remove(arr.size()-1));
                }
            }
 
            System.out.print("#"+test_case+" ");
            for (int i = 0; i < 10; i++) {
                System.out.print(code.remove(0)+" ");
            }
            System.out.println();
        }
    }
}
