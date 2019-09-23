import java.util.Scanner;

public class Main17281_야구공_승주 {
	public static int[] turn = new int[9];
	public static boolean[] check;
	public static int[][] inning;
	public static int n, max;
	
	public static void play() { //경기 시작
		int num = 0; //몇번째 타자인지
		int out = 0; //아웃된 사람 수
		int score = 0; //한 이닝에서 얻은 점수
		boolean[] player = new boolean[3];
		
		for (int i = 0; i < n; i++) { //이닝 수만큼 돌기
			while(true) {
				int p = turn[num];
				if(inning[i][p]==1) { //안타
					if(player[2]) {
						score+=1;
						player[2] = false;
					}
					if(player[1]) {
						player[2] = true;
						player[1] = false;
					}
					if(player[0]) {
						player[1] = true;
					}
					player[0] = true; //1루는 무조건 한 명 나감
				}else if(inning[i][p]==2) {  //2루타
					if(player[2]) {
						score+=1;
						player[2] = false;
					}
					if(player[1]) {
						score += 1;
					}
					if(player[0]) {
						player[2] = true;
						player[0] = false;
					}
					player[1] = true; //2루는 무조건 한 명 나감
				}else if(inning[i][p]==3) { //3루타
					for (int j = 0; j < 3; j++) {
						if(player[j]) {
							player[j] = false;
							score+=1;
						}
					}
					player[2] = true; //3루는 무조건
				}else if(inning[i][p]==4) { //홈런
					for (int j = 0; j < 3; j++) {
						if(player[j]) score+=1;
					}
					score+=1; //타자 점수
					player = new boolean[3];
				}else { //아웃인 경우
					out += 1;
				}
				
				num += 1; //타자 순서 증가
				if(num==9) num = 0;
				if(out==3) break; //이닝 끝
			}
			out = 0;
			player = new boolean[3];
		}
		max = Math.max(max, score);
	}
	
	public static void makeTurn(int cnt, int num) { //타자 순서 정하기
		turn[cnt] = num;
		if(cnt==8) {
			play();
			return;
		}
		
		if(cnt==2) makeTurn(cnt+1, 0); //4번째 타자는 정해짐
		else {
			for (int i = 1; i < 9; i++) {
				if(!check[i]) { //사용하지 않은 수일 때
					check[i] = true;
					makeTurn(cnt+1, i);
					check[i] = false;
				}
			}
		}		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		inning = new int[n][9];
		max = 0;
		turn[3] = 0 ;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 9; j++) {
				inning[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 1; i < 9; i++) { //타자 번호
			check = new boolean[9];
			check[0] = true;
			check[i] = true;
			makeTurn(0, i);
			check[i] = false;
		}
		
		System.out.println(max);
	}
}
