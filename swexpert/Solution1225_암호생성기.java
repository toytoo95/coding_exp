import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*Queue*/
 
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        for (int i = 0; i < 10; i++) {
            Queue<Integer> q = new LinkedList();
            int t = sc.nextInt();
            int count = 1;
 
            for (int j = 0; j < 8; j++) {
                q.offer(sc.nextInt());
            }
            while(true) { //조건 바꾸기
                int val = q.poll();
                 
                if (val-count <= 0) {
                    q.offer(0);
                    break;
                }
                q.offer(val-count);
                count++;
                if (count == 6)
                    count = 1;
            }
            System.out.print("#"+t+" ");
            for (int j = 0; j < 8; j++) {
                System.out.print(q.poll()+" ");
            }
            System.out.println();
        }
    }
}
