import java.util.Scanner;

public class Main1267_핸드폰요금 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int y = 0; //영식 요금제
		int m = 0; //민식 요금제
		
		for (int i = 0; i < n; i++) {
			int call = sc.nextInt();
			y += (call/30+1)*10;
			m += (call/60+1)*15;
		}
		
		if(y<m) System.out.println("Y "+y);
		else if(m<y) System.out.println("M "+m);
		else System.out.println("Y M "+y);
	}
}
