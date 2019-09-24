import java.util.Arrays;
import java.util.Scanner;
 
public class Solution {
    public static char[][] map;
    public static int[] dx = {-1, 0, 1, 0}; //L U R D
    public static int[] dy = {0, -1, 0, 1};
    public static int h, w, test;
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (test = 1; test <= t; test++) {
            h = sc.nextInt();
            w = sc.nextInt();
            sc.nextLine();
            map = new char[h][w];
            int y=0, x=0;
 
            for (int i = 0; i < h; i++) {
                String s = sc.nextLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = s.charAt(j);
                    if(map[i][j]=='^' || map[i][j]=='v' || map[i][j]=='<' ||map[i][j]=='>') {
                        y = i;
                        x = j;
                    }
                }
            }
            int n = Integer.parseInt(sc.nextLine());
            String input = sc.nextLine();
            move(y, x, input);
 
            System.out.print("#"+test+" ");
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
    }
 
    public static void move(int y, int x, String input) {
        char direction = map[y][x];
        for (int i = 0; i < input.length(); i++) {
            //System.out.println(i+", "+input.length());
            char order = input.charAt(i);
 
            if(order == 'S') {
                if(direction == '<') {
                    for (int j = x; j >= 0; j--) {
                        if(map[y][j]=='#') break;
                        else if(map[y][j]=='*') {
                            map[y][j] = '.';
                            break;
                        }
                    }
                } else if (direction == '>') {
                    for (int j = x; j < w; j++) {
                        if(map[y][j]=='#') break;
                        else if(map[y][j]=='*') {
                            map[y][j] = '.';
                            break;
                        }
                    }
                } else if(direction == '^') {
                    for (int j = y; j >= 0; j--) {
                        if(map[j][x]=='#') break;
                        else if(map[j][x]=='*') {
                            map[j][x] = '.';
                            break;
                        }
                    }
                }else {
                    for (int j = y; j < h; j++) {
                        if(map[j][x]=='#') break;
                        else if(map[j][x]=='*') {
                            map[j][x] = '.';
                            break;
                        }
                    }
                }
            }else {
                int ny = 0;
                int nx = 0;
                switch (order) {
                case 'L':
                    ny = y+dy[0];
                    nx = x+dx[0];
                    order = '<';
                    break;
                case 'R':
                    ny = y+dy[2];
                    nx = x+dx[2];
                    order = '>';
                    break;
                case 'U':
                    ny = y+dy[1];
                    nx = x+dx[1];
                    order = '^';
                    break;
                case 'D':
                    ny = y+dy[3];
                    nx = x+dx[3];
                    order = 'v';
                    break;
                }
 
                if(ny<0 || nx<0 || ny>=h || nx>=w || map[ny][nx]=='-' || map[ny][nx]=='#' || map[ny][nx]=='*') {
                    map[y][x] = order;
                    direction = order;
                    continue;
                }
                else {
                    map[y][x] = '.';
                    y = ny;
                    x = nx;
                    map[y][x] = order;
                    direction = order;
                }
            }
        }
    }
}
