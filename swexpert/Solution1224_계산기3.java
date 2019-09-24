import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
    public static char[] stack = new char[100];
    public static int top = -1;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        for (int test = 1; test < 11; test++) {
            int top1 = -1;
            top = -1;
            int[] sum = new int[100];
            StringBuilder sb = new StringBuilder();
            int len = sc.nextInt();
            String s = sc.next();
 
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if ('0'<=c && c<= '9') {
                    sb.append(c);
                }
                else if (c == ')') {
                    char t = ' ';
                    while((t = stack[top--])!='(') {
                        sb.append(t);
                    }
                }
                else {
                    while(getIcp(c)<=getIsp()) {
                        char t = stack[top--]; //pop()
                        sb.append(t);
                    }
                    stack[++top] = c;
                }
            }
            while(top != -1) {
                sb.append(stack[top--]);
            }
 
            for (int i = 0; i < sb.length(); i++) {
                char c = sb.charAt(i);
                //System.out.println(c);
                if ('0'<=c && c<= '9') {
                    sum[++top1] = c-'0';
                }
 
                else {
                    int n2 = sum[top1--]; //pop()
                    int n1 = sum[top1--];
                    int nn = 0;
                    switch(c) {
                    case '+':
                        nn = n1+n2;
                        break;
                    case '-':
                        nn = n1-n2;
                        break;
                    case '*':
                        nn = n1*n2;
                        break;
                    case '/':
                        nn = n1/n2;
                        break;      
                    }
                    sum[++top1] = nn; //push()
                }   
            }
            System.out.println("#"+test+ " "+ sum[top1]);
        }
    }
 
    private static int getIcp(char c) {
        switch(c) {
        case '+':
        case '-':
            return 1;
        case '*':
            return 2;
        case '/':
            return 2;
        case'(':
            return 3;
        }
        return 0;
    }
 
    private static int getIsp() {
        char c = (top == -1)? '(':stack[top];
        switch(c) {
        case '+':
        case '-':
            return 1;
        case '*':
            return 2;
        case '/':
            return 3;
        case'(':
            return 0;
        }
        return 0;
    }
}
