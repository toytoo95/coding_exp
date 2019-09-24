import java.util.Scanner;
 
public class Solution {
    public static String stack[];
    public static String[] arr;
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        for (int test = 1; test <= 10; test++) {
            String beta;
            int T = sc.nextInt(); //테스트 케이스 길이
            arr = new String [T];
            int b = 0;
 
            beta = sc.next();
 
            for (int i = 0; i < T-1; i++) {
                arr[i] = beta.substring(i, i+1);
            }
            arr[T-1] = beta.substring(T-1);
 
            System.out.print("#"+test+" ");
            getans();
        }
    }
     
    private static void getans() {
        int top = arr.length-1; //맨 오른쪽 끝
        int a = 0, b = 0, c = 0, d = 0;
 
        while(top >= 0 && a>=0 && b>=0 && c>=0 && d>=0) {
            if(arr[top].equals("}") || arr[top].equals("{")) {
                if(arr[top].equals("}"))
                    a++;
                else
                    a--;
            }
            else if (arr[top].equals(")") || arr[top].equals("(")) {
                if(arr[top].equals(")"))
                    b++;
                else
                    b--;
            }
            else if (arr[top].equals(">") || arr[top].equals("<")) {
                if(arr[top].equals(">"))
                    c++;
                else
                    c--;
            }
            else if (arr[top].equals("]") || arr[top].equals("[")) {
                if(arr[top].equals("]"))
                    d++;
                else
                    d--;
            }
            top--;
        }
        if(top == -1 && a==0 && b==0 && c==0 && d==0)
            System.out.println(1);
        else
            System.out.println(0);
    }
}
