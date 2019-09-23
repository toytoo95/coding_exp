import java.util.Scanner;
 
 
public class Solution4366_정식이의은행업무 {
    public static int[] two, three, subTwo, subThree;
    public static boolean flag;
    public static int ans;
 
    public static void change() {
        flag = false;
        for (int i = 0; i < three.length; i++) {
            for (int j = 0; j < 2; j++) {
                subThree[i] += 1;
                if(subThree[i]==3) subThree[i]=0;
                changeTwo();
            }
            if(flag) return;
            else subThree[i] = three[i];
        }
    }
 
    public static void changeTwo() {
        flag = false;
        for (int i = 0; i < two.length; i++) {
            subTwo[i] += 1;
            if(subTwo[i]==2) subTwo[i]=0;
             
            flag = check();
             
            if(flag) return;
            else subTwo[i] = two[i];
        }
    }
 
    public static boolean check() {
        int tw = 0, th = 0;
        for (int i = 1; i <= three.length; i++) {
            th += subThree[i-1]*Math.pow(3, three.length-i);
        }
        
        for (int i = 1; i <= two.length; i++) {
            tw += subTwo[i-1]*Math.pow(2, two.length-i);
            if(tw>th) return false;
        }
        
        if(tw==th) {
            ans = tw;
            return true;
        }
        return false;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
 
        for (int test = 1; test <= t; test++) {
            String tw = sc.next();
            String th = sc.next();
            two = new int[tw.length()];
            subTwo = new int[tw.length()];
            three = new int[th.length()];
            subThree = new int[th.length()];
            ans = 0;
 
            for (int i = 0; i < tw.length(); i++) { //2진수를 배열에
                two[i] = tw.charAt(i)-'0';
                subTwo[i] = two[i];
            }
            
            for (int i = 0; i < th.length(); i++) { //3진수를 배열에
                three[i] = th.charAt(i)-'0';
                subThree[i] = three[i];
            }
 
            change();
 
            System.out.println("#"+test+" "+ans);
        }
    }
}
