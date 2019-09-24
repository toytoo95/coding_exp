import java.util.Scanner;

/**
  백트랙킹
*/
 
public class Solution {
    public static int[] comp;
    public static int[] home;
    public static int[][] customNum;
    public static int answer;
 
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int t = sc.nextInt();
 
        for (int test_case = 1; test_case <= t; test_case++) {
            answer = 100000000;
            int custom = sc.nextInt();
            comp = new int[2];
            home = new int[2];
            comp[0] = sc.nextInt();
            comp[1] = sc.nextInt();
            home[0] = sc.nextInt();
            home[1] = sc.nextInt();
            customNum = new int [custom][2];
            int[] a = new int[custom];
 
            for (int i = 0; i < custom; i++) {
                customNum[i][0] = sc.nextInt();
                customNum[i][1] = sc.nextInt();
            }
 
            backtrack(a, 0);
            System.out.println("#"+test_case+" "+answer);
        }
    }
 
    private static void backtrack(int[] a, int k) {
        int dist = 0;
        int[] cust = new int[customNum.length];
        if(k == customNum.length) {
            dist = getDist(a);
            if(answer > dist && dist!=0) answer = dist;
        }else {
            int ncands = candidates(a, k, cust);
            for (int i = 0; i < ncands; i++) {
                a[k] = cust[i];
                backtrack(a, k+1);
            }
        }
    }
 
    private static int candidates(int[] a, int k, int[] cust) {
        boolean[] perm = new boolean[a.length];
        for (int i = 0; i < k; i++) perm[a[i]] = true;
 
        int ncands = 0;
        for (int i = 0; i < perm.length; i++) {
            if(perm[i] == false) cust[ncands++] = i;
        }
        return ncands;
    }
 
    private static int getDist(int[] a) {
        int dist = 0;
        dist += Math.abs(comp[0]-customNum[a[0]][0])+Math.abs(comp[1]-customNum[a[0]][1]);
         
        for (int i = 0; i < a.length-1; i++) {
            dist += Math.abs(customNum[a[i]][0]-customNum[a[i+1]][0])+Math.abs(customNum[a[i]][1]-customNum[a[i+1]][1]);
            if(dist > answer)
                return 0;
        }
        dist += Math.abs(home[0]-customNum[a[a.length-1]][0])+Math.abs(home[1]-customNum[a[a.length-1]][1]);
        return dist;
    }
}
