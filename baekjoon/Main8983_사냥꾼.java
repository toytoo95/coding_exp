import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main8983_사냥꾼 {
	public static List<Animal> ani = new ArrayList<>();
	public static int[] pp;
	public static int m, n, l, ans;

	public static void find() {
		int num = 0;
		for (int i = 0; i < ani.size(); i++) { //동물 기준
			while(num<pp.length-1 && ani.get(i).x>pp[num+1]) num++;
			if(Math.abs(ani.get(i).x-pp[num])+ani.get(i).y<=l) ans++;
			else if(num!=pp.length-1 && Math.abs(ani.get(i).x-pp[num+1])+ani.get(i).y<=l) ans++;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		pp = new int[m];
		ans = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			pp[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(y > l) continue; //높이가 사정거리를 벗어나면 필요x
			ani.add(new Animal(x, y));
		}
		Arrays.sort(pp);
		Collections.sort(ani, new Comparator<Animal>() {

			@Override
			public int compare(Animal o1, Animal o2) {
				// TODO Auto-generated method stub
				return o1.x-o2.x;
			}
		});
		find();
		System.out.println(ans);
	}
	
	static class Animal{
		int y;
		int x;
		Animal(int x, int y){
			this.y = y;
			this.x = x;
		}
	}
}
