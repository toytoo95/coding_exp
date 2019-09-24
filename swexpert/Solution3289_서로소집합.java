import java.util.Scanner;

/*그래프*/

public class Solution {
    public static int[]parent;
     
    public static void makeSet(int v) { //자기자신과 연결
        parent[v] = v;
    }
     
    public static int findSet(int v) {
        if(parent[v] < 0 || parent[v]==v) return v;
        return parent[v] = findSet(parent[v]);
    }
     
    public static int getSetSize(int v) { //안씀
        return -parent[findSet(v)];
    }
 
    public static void unionSet(int u, int v) { //합집합으로 만드는 함수
        int root1 = findSet(u);
        int root2 = findSet(v);
         
        if(root1 == root2) return;
        parent[root2] = root1;
    }
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int test_case = 1; test_case <= t; test_case++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            parent = new int[n+1];
             
            for (int i = 0; i < n+1; i++) {
                parent[i] = -1;
            }
             
            System.out.print("#"+test_case+" ");
            for (int i = 0; i < m; i++) {
                int first = sc.nextInt();
                int u = sc.nextInt();
                int v = sc.nextInt();
                 
                if(first == 0) {
                    unionSet(u, v);
                }else {
                    if(findSet(u) == findSet(v)) System.out.print(1);
                    else System.out.print(0);
                }
            }
            System.out.println();
        }
    }
}
