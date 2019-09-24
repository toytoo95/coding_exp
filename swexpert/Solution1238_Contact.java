import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class Solution {
    public static int[][] call;
    public static boolean[] did;
    public static ArrayList<Integer> list;
     
    public static void bfs(int s) {
        int max = 0;
        list = new ArrayList<>();
        Queue<Phone> q = new LinkedList<>();
        for (int i = 1; i < 101; i++) {
            if(call[s][i]==1) q.offer(new Phone(s, i, 0));
        }
        did[s] = true;
        while(!q.isEmpty()) {
            Phone p = q.poll();
            if(did[p.to]==true) continue;
             
            did[p.to] = true;
            p.cnt += 1;
            if(max<p.cnt) {
                max = p.cnt;
                list.clear();
                list.add(p.to);
            }else if(max==p.cnt) list.add(p.to);
             
            for (int i = 1; i < 101; i++) {
                if(call[p.to][i]==1) q.offer(new Phone(p.to, i, p.cnt+1));
            }
        }
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        for (int test = 1; test <= 10; test++) {
            call = new int[101][101];
            did = new boolean[101];
            int len = sc.nextInt();
            int start = sc.nextInt(); //시작
             
            for (int i = 0; i < len/2; i++) {
                int y = sc.nextInt(); //from
                int x = sc.nextInt(); //to
                call[y][x] = 1;
            }
             
            bfs(start);
            int max = 0;
            for (int i = 0; i < list.size(); i++) {
                //System.out.println(list.get(i));
                if(max<list.get(i)) max = list.get(i);
            }
            System.out.println("#"+test+" "+max);
        }
    }
     
    static class Phone{
        int from;
        int to;
        int cnt;
         
        Phone(int from, int to, int cnt){
            this.from = from;
            this.to= to;
            this.cnt = cnt;
        }
    }
}
