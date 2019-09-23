import java.util.ArrayList;
import java.util.Scanner;

/* 3:15시작
 *[명령어]
 * 	NUM X: X를 스택의 가장 위에 저장한다. (0 ≤ X ≤ 10^9)
	POP: 스택 가장 위의 숫자를 제거한다.
	INV: 첫 번째 수의 부호를 바꾼다. (42 -> -42)
	DUP: 첫 번째 숫자를 하나 더 스택의 가장 위에 저장한다.
	SWP: 첫 번째 숫자와 두 번째 숫자의 위치를 서로 바꾼다.
	ADD: 첫 번째 숫자와 두 번째 숫자를 더한다.
	SUB: 첫 번째 숫자와 두 번째 숫자를 뺀다. (두 번째 - 첫 번째)
	MUL: 첫 번째 숫자와 두 번째 숫자를 곱한다.
	DIV: 첫 번째 숫자로 두 번째 숫자를 나눈 몫을 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다. (두/첫)
	MOD: 첫 번째 숫자로 두 번째 숫자를 나눈 나머지를 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다. (두%첫)
	
	[에러]
	-숫자가 부족해서 연산을 수행할 수 없을 때
	-0으로 나눴을 때(DIV, MOD)
	-연산 결과의 절댓값이 10^9를 넘어갈 때
	-모든 수행이 종료됐을 때 스택에 저장되어 있는 숫자가 1개가 아니라면, "ERROR"를 출력
	 => 에러가 발생한 경우 현재 프로그램 수행을 멈추고 다른 명령은 수행x
	
	[음수 처리]
	MOD의 부호는 피제수(나누어지는 수)의 부호와 같다
 * */

public class Baekjoon3425_고스택 {
	public static ArrayList<Integer> gostack;
	public static ArrayList<String> order;
	
	public static void doOrder() {
		for (int i = 0; i < order.size(); i++) {
			String s = order.get(i);
			int n = 0;
			switch (s) {
			case "POP":
				if(gostack.size()<=0){
					System.out.println("ERROR");
					return;
				}
				gostack.remove(gostack.size()-1);
				break;
			case "INV":
				if(gostack.size()<=0){
					System.out.println("ERROR");
					return;
				}
				n = -1 * gostack.get(gostack.size()-1);
				gostack.remove(gostack.size()-1);
				gostack.add(n);
				break;
			case "DUP":
				if(gostack.size()<=0){
					System.out.println("ERROR");
					return;
				}
				n = gostack.get(gostack.size()-1);
				gostack.add(n);
				break;
			case "SWP":
				if(gostack.size()<2) {
					System.out.println("ERROR");
					return;
				}
				n = gostack.get(gostack.size()-2);
				gostack.remove(gostack.size()-2);
				gostack.add(n);
				break;
			case "ADD":
				if(gostack.size()<2 || Math.abs((long)(gostack.get(gostack.size()-1) + gostack.get(gostack.size()-2)))>1000000000) {
					System.out.println("ERROR");
					return;
				}
				n = gostack.get(gostack.size()-1) + gostack.get(gostack.size()-2);
				gostack.remove(gostack.size()-2);
				gostack.remove(gostack.size()-1);
				gostack.add(n);
				break;
			case "SUB":
				if(gostack.size()<2 || (long)(gostack.get(gostack.size()-2)-gostack.get(gostack.size()-1))>1000000000) {
					System.out.println("ERROR");
					return;
				}
				n = gostack.get(gostack.size()-2) - gostack.get(gostack.size()-1);
				gostack.remove(gostack.size()-2);
				gostack.remove(gostack.size()-1);
				gostack.add(n);
				break;
			case "MUL":
				if(gostack.size()<2 || Math.abs((long)gostack.get(gostack.size()-1)*gostack.get(gostack.size()-2))>1000000000) {
					System.out.println("ERROR");
					return;
				}
				n = gostack.get(gostack.size()-2) * gostack.get(gostack.size()-1);
				gostack.remove(gostack.size()-2);
				gostack.remove(gostack.size()-1);
				gostack.add(n);
				break;
			case "DIV":
				if(gostack.size()<2 || gostack.get(gostack.size()-1)==0) {
					System.out.println("ERROR");
					return;
				}
				n = gostack.get(gostack.size()-2) / gostack.get(gostack.size()-1);
				gostack.remove(gostack.size()-2);
				gostack.remove(gostack.size()-1);
				gostack.add(n);
				break;
			case "MOD":
				if(gostack.size()<2 || gostack.get(gostack.size()-1)==0) {
					System.out.println("ERROR");
					return;
				}
				n = gostack.get(gostack.size()-2) % gostack.get(gostack.size()-1);
				gostack.remove(gostack.size()-2);
				gostack.remove(gostack.size()-1);
				gostack.add(n);
				break;
			default: //NUM일 때 -> 숫자 들어가있음
				gostack.add(Integer.parseInt(s));
				break;
			}
			if(gostack.size()>0 && Math.abs(gostack.get(gostack.size()-1))>1000000000) {
				System.out.println("ERROR");
				return;
			}
		}
		if(gostack.size()!=1) {
			System.out.println("ERROR");
			return;
		}
		System.out.println(gostack.get(0));
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		gostack = new ArrayList<>();
		order = new ArrayList<>();
		boolean flag = true;
		int cnt = 0;
		
		while(flag) {
			String s = sc.next();
			switch (s) {
			case "NUM":
				String i = sc.next();
				order.add(i);
				break;
			case "END":
				if(cnt!=0) System.out.println();
				int n = sc.nextInt();
				for (int j = 0; j < n; j++) {
					gostack.add(sc.nextInt());
					doOrder();
					gostack.clear();
				}
				order = new ArrayList<>();
				cnt++;
				break;
			case "QUIT":
				flag = false;
				break;
			default:
				order.add(s);
				break;
			}
		}
	}
}
