import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String[] arr = new String[121];
		arr = s.split(" ");
		int num = 0;

		for (int i = 1; i < arr.length; i++) {
			String value = arr[0];

			if(arr[i] == null)
				break;

			String text = arr[i];
			if(text.length() > 2) {
				for (int j = text.length()-2; j >= 0; j--) {
					if(text.charAt(j) == ']'||text.charAt(j) == '['||text.charAt(j) == '*'||text.charAt(j) == '&') {
						if(text.charAt(j) == ']') {
							value += "[]";
							j --;
						}else
							value += text.charAt(j);
					}
					else {
						if(j == 0) {
							value = value+" "+text.charAt(0);
						}else {
							value = value + " " + text.substring(0, j+1);
							j = 0;
						}
					}
				}
			}else {
				value = value+" "+text.charAt(0);
			}
			System.out.println(value+";");
		}
	}
}
