import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static ArrayList<String> list = new ArrayList<>();
	public static String[] arr;
	public static int n, m;
	
	public static void find() {
		for (int i = 1; i < n+m; i++) {
			if(arr[i].equals(arr[i-1])) {
				list.add(arr[i]);
				i += 1;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new String[n+m];
		
		for (int i = 0; i < n+m; i++) {
			arr[i] = br.readLine();
		}
		
		Arrays.sort(arr);

		find();
		
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
