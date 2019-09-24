import java.util.Scanner;

/*String*/
 
public class Solution {
    public static long[][] island;
    public static long[] weight;
    public static int[] arrX;
    public static int[] arrY;
    public static long ans;
    public static int isNum;
 
    public static void prim() {
        for (int i = 0; i < isNum; i++) weight[i] = -1;
        weight[0] = 0;
        
        for (int i = 1; i < isNum; i++) {
            long minWeight = Long.MAX_VALUE;
            int minVertax = isNum+1;
            for (int j = 0; j < isNum; j++) {
                if(weight[j] < 0) continue;
                for (int k = 0; k < isNum; k++) {
                    if(weight[k] >= 0) continue;
                    //System.out.println(island[j][k]);
                    if(island[j][k] < minWeight) {
                        minWeight = island[j][k];
                        minVertax = k;
                    }
                }
            }
            weight[minVertax] = minWeight;
        }
        for(int i = 0; i<weight.length; i++) ans += weight[i];
    }
 
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        int t = sc.nextInt();
 
        for (int testcase = 1; testcase <= t; testcase++) {
            ans = 0;
            isNum = sc.nextInt();
            island = new long [isNum][isNum];
            weight = new long[isNum];
            arrX = new int[isNum];
            arrY = new int[isNum];
 
            for (int i = 0; i < isNum; i++) arrX[i] = sc.nextInt();
 
            for (int i = 0; i < isNum; i++) arrY[i] = sc.nextInt();
 
            double e = sc.nextDouble(); //환경부담 세율 실수
 
            for (int i = 0; i < isNum; i++) {
                for (int j = 0; j < isNum; j++) {
                    if(i != j) {
                        long x = arrX[i]-arrX[j];
                        long y = arrY[i]-arrY[j];
                        long val = x*x + y*y;
                        island[i][j] = island[j][i] = val;
                    }
                }
            }
            prim();
            System.out.printf("#%d %.0f\n",testcase, e*ans);
        }
        sc.close();
    }
}
