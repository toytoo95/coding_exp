import java.util.Scanner;

public class Main {
	public static int c, p, count;
	public static int[] field;

	private static void sixth() {
		for (int i = 0; i < c-1; i++) {
			if(i < c-2) {
				if(field[i]==field[i+1] && field[i]==field[i+2]) count++;
				else if(p==6 && field[i]+1==field[i+1] && field[i]+1==field[i+2]) count++;
				else if(p==7 && field[i]==field[i+1] && field[i]-1==field[i+2]) count++;
			}
			if(field[i]==field[i+1]) count++;
			else if(p==6 && field[i]-2==field[i+1]) count++;
			else if(p==7 && field[i]+2==field[i+1]) count++;
		}
	}

	private static void fifth() {
		for (int i = 0; i < c-1; i++) {
			if(i < c-2) {
				if(field[i]==field[i+1] && field[i]==field[i+2]) count++;
				else if(field[i]-1==field[i+1] && field[i]==field[i+2]) count++;
			}
			if(field[i]-1==field[i+1]) count++;
			else if(field[i]+1==field[i+1]) count++;
		}
	}

	private static void third() {
		for (int i = 0; i < c-1; i++) {
			if(i < c-2) {
				if(p==3 && field[i]==field[i+1] && field[i]+1==field[i+2]) count++;
				else if(p==4 && field[i]-1==field[i+1] && field[i]-1==field[i+2]) count++;
			}
			if(p==3 && field[i]-1==field[i+1]) count++;
			else if(p==4 && field[i]+1==field[i+1]) count++;
		}
	}

	private static void second() {
		for (int i = 0; i < c-1; i++) {
			if(field[i]==field[i+1]) {
				count++;
			}
		}
	}

	private static void first() {
		for (int i = 0; i <= c-4; i++) {
			if(field[i]==field[i+1] && field[i]==field[i+2] && field[i]==field[i+3]) {
				count++;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		c = sc.nextInt(); //열
		p = sc.nextInt(); //블럭 번호
		field = new int[c];
		count = 0;

		for (int i = 0; i < c; i++) {
			field[i] = sc.nextInt();
		}
		switch (p) {
		case 1:
			count += c;
			first();
			break;
		case 2:
			second();
			break;
		case 3: case 4:
			third();
			break;
		case 5:
			fifth();
			break;
		case 6: case 7:
			sixth();
			break;
		default:
			break;
		}
		System.out.println(count);
	}
}
