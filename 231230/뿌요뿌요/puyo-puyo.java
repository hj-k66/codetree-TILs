import java.io.*;
import java.util.*;

public class Main {

    static int[][] grid;
    static int[][] visited;
    static int n;
    static int count;

    static boolean inRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static boolean canGo(int x, int y){
        if(!inRange(x,y)) return false;
        if(visited[x][y] == 1) return false;
        return true;
    }

    static void dfs(int x, int y){
        int[] dx = new int[]{-1,1,0,0};
        int[] dy = new int[]{0,0,-1,1};

        for(int i = 0; i < 4; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];

            if(canGo(newX, newY) && grid[newX][newY] == grid[x][y]){
                visited[newX][newY] = 1;
                count++;
                dfs(newX, newY);
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        visited = new int[n][n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //로직
        int blockCnt = 0;
        int maxBlockSize = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(canGo(i,j)){
                    visited[i][j] = 1;
                    count = 1;
                    dfs(i,j);
                    if(count >= 4) blockCnt++;
                    maxBlockSize = Math.max(count, maxBlockSize);
                }
            }
        }
        System.out.println(blockCnt + " " +maxBlockSize);
    }
}