import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
 
public class Solution {
    public static ArrayList<Integer> pnum;
    public static int count;
 
    private static void getSum(int num) {
        for (int i = 0; i < pnum.size(); i++) {
            int n1 = pnum.get(i);
            for (int j = i; j < pnum.size(); j++) {
                int n2 = pnum.get(j);
                for (int k = j; k < pnum.size(); k++) {
                    int n3 = pnum.get(k);
                    if(n1+n2+n3==num) count++;
                }
            }
        }
    }
 
    public static boolean prime(int num) {
        for (int i = 2; i < num; i++) {
            if(num%i == 0) return false;
        }
        return true;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
 
        for (int test = 1; test <= t; test++) {
            pnum = new ArrayList<>();
            int n = sc.nextInt();
            count = 0;
 
            for (int i = 2; i <= n; i++) {
                if(prime(i)) pnum.add(i); //입력값 이하의 수들 중에서 소수 찾기
            }
            getSum(n);
            System.out.println("#"+test+" "+count);
        }
    }
}
