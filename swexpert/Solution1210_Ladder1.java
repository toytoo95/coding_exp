import java.util.Scanner;
 
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int [][] arr= new int[100][100];
        int T;
 
        for (int test_case = 0; test_case < 10; test_case++) {
            T = sc.nextInt();
            int y = 0;
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    arr[i][j] = sc.nextInt();
                    if(arr[i][j] == 2) //도착지 좌표 찾기
                        y = j;
                }
            }
            
            int i = 99;
            while(i > 0) {
                arr[i][y] = 3; //무한루프 방지용
                if(y-1 >= 0 && arr[i][y-1] == 1)//왼쪽으로 길이 있을 때
                    y--;
                else if(y+1 < 100 && arr[i][y+1] == 1 ) //오른쪽으로 길이 있을 떼
                    y++;
                else if(i-1 >= 0 && arr[i-1][y] == 1 )//위로 길이 있을 떼
                    i--;
                else
                    break;
            }
            System.out.println("#"+T+" "+y);
        }
    }
}
