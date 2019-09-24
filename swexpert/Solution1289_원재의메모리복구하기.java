import java.util.Arrays;
import java.util.Scanner;;
 
public class Solution {
    static int Answer;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int T;
 
        T=sc.nextInt();
        for(int test_case = 1; test_case <= T; test_case++) {
            Answer = 0;
            String original;
            String check;
 
            original = sc.next(); //원래 상태 받기
            if (original.length() > 50 && original.length() < 1) {
                System.out.println("#" + test_case + " Wrong Input");
                break;
            }
             
            int []first = new int[original.length()]; //원래 상태 크기만큼의 초기화 배열 생성
             
            for (int i = 0; i < original.length(); i++) { //메모리 크기만큼 돌리기
                if (original.charAt(i) == '1' && first[i] == 0) {
                    for (int j = i; j < first.length; j++) {
                        first[j] = 1;
                    }
                    Answer++;
 
                    check = Arrays.toString(first);
                    if (check == original) { //지금까지의 결과가 원래상태와 같은지 비교
                        break;
                    }
                }
                else if (original.charAt(i) == '0' && first[i] == 1) {
                    for (int j = i; j < first.length; j++) {
                        first[j] = 0;
                    }
                    Answer++;
 
                    check = Arrays.toString(first);
                    if (check == original) { //지금까지의 결과가 원래상태와 같은지 비교
                        break;
                    }
                }
            }
            System.out.println("#" + test_case + " " + Answer);
        }
    }
}
