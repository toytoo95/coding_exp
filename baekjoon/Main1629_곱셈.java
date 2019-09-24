import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();

		long ans = 1;
		long multi = a%c;
		
		while(b > 0) {
			if(b%2 == 1) {
				ans = ans*multi;
				ans  = ans%c;
			}
			multi = ((multi%c)*(multi%c))%c;
			b = b/2;
		}
		System.out.println(ans);
	}
}
