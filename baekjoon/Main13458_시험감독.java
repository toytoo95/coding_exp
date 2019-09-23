import java.util.ArrayList;
import java.util.Scanner;

public class Main13458_시험감독 {
	public static ArrayList<Integer> pp;
	public static int n, b, c;
	public static long min;
	
	public static void count() {
		min = 0;
		for (int i = 0; i < n; i++) { //int i = n-1; i >= 0; i--
			if(pp.get(i)<=b) min++;
			else {
				int num = pp.get(i) - b;
				min += num/c+1;
				if(num%c != 0) min++; //나머지가 있으면 감독관이 한명 더 필요
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		min = Long.MAX_VALUE;
		pp = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			pp.add(sc.nextInt()); //각 시험장에 있는 응시자 수
		}
		b = sc.nextInt();
		c = sc.nextInt();
		
		count();
		
		System.out.println(min);
	}
}
