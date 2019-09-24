import java.util.Scanner;
 
public class Solution {
    public static int n;
    public static String[] a;
     
    public static void inorder(int i) {
        if(i <= n && a[i] != null) {
            inorder(i*2); //왼쪽
            System.out.print(a[i]);
            inorder(i*2+1); //오른쪽
        }
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        for (int test_case = 1; test_case <= 10; test_case++) {
            n = sc.nextInt(); //정점 개수
            sc.nextLine();
            a = new String[n+1];
            for (int i = 1; i <= n; i++) {
                String line = sc.nextLine();
                a[i] = line.split(" ")[1];
            }
            
            System.out.print("#"+test_case+" ");
            inorder(1);
            System.out.println();
        }
    }
}
