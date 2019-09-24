import java.util.Scanner;

public class Main {
	public static int[][] wheel, turn;
	
	//돌리는 함수
	public static void change(int num, boolean way) {
		if(way) {
			int tmp = wheel[num][7];
			for (int i = 7; i > 0; i--) {
				wheel[num][i] = wheel[num][i-1];
			}
			wheel[num][0] = tmp;
		}else {
			int tmp = wheel[num][0];
			for (int i = 0; i < 7; i++) {
				wheel[num][i] = wheel[num][i+1];
			}
			wheel[num][7] = tmp;
		}
	}
	
	//어느 쪽으로 가는지&움직이는지 확인
	public static void move(int num, boolean way)  {
		int right = wheel[num][2];
		int left = wheel[num][6];
		boolean w = way;
		
		for (int i = num+1; i < 4; i++) { //오른
			if(right!=wheel[i][6]) {
				w = !w;
				right = wheel[i][2];
				change(i, w);
			}else break;
		}
		w = way;
		for (int i = num-1; i >= 0; i--) { //왼
			if(left!=wheel[i][2]) {
				w = !w;
				left = wheel[i][6];
				change(i, w);
			}else break;
		}
		change(num, way);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		wheel = new int[4][8];
		int ans = 0;
		
		for (int i = 0; i < 4; i++) {
			String s = sc.nextLine();
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = s.charAt(j)-'0'; //톱니바퀴 입력
			}
		}
		
		int k = sc.nextInt(); //회전수
		for (int i = 0; i < k; i++) {
			int num = sc.nextInt();
			int way = sc.nextInt();
			boolean flag = true;
			if(way==-1) flag = false;
			move(num-1, flag);
		}
		
		for (int i = 0; i < 4; i++) {
			if(wheel[i][0]==1) ans+=Math.pow(2, i);
		}
		System.out.println(ans);
	}
}
