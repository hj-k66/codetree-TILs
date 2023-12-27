import java.io.*;
import java.util.*;

public class Main {

    static int[][] visited;
    static int[][] grid;
    static int n;
    static int m;
    static int answer = 0;

    public static boolean inRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static boolean canGo(int x, int y){
        if(!inRange(x,y)) return false;
        if(visited[x][y] == 1 || grid[x][y] == 0) return false;
        return true;
    }

    public static void dfs(int x, int y){
        int[] dx = new int[]{1,0};
        int[] dy = new int[]{0,1};

        for(int i = 0; i < 2; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            if(canGo(newX, newY)){
                if(newX == n-1 && newY == m-1){
                    answer =  1;
                }
                visited[newX][newY] = 1;
                dfs(newX, newY);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new int[n][m];
        visited = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[0][0] = 1;
        dfs(0,0);
        System.out.println(answer);
    }
}