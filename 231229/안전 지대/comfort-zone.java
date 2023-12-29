import java.io.*;
import java.util.*;

public class Main {

    static int[][] grid;
    static int[][] visited;
    static int n;
    static int m;

    static boolean inRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    static boolean canGo(int x, int y, int k){
        if(!inRange(x,y)) return false;
        if(visited[x][y] == 1 || grid[x][y] <= k) return false;
        return true;
    }

    static void dfs(int x, int y, int k){
        int[] dx = new int[]{1,0,-1,0};
        int[] dy = new int[]{0,1,0,-1};

        for(int i = 0; i < 4; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];

            if(canGo(newX, newY, k)){
                visited[newX][newY] = 1;
                dfs(newX, newY, k);
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

        //로직
        int maxK = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(maxK < grid[i][j]) maxK = grid[i][j];
            }
        }
        
        int maxArea = 0;
        int answerK = 1;
        for(int k = maxK; k >= 1; k--){
            int area = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(canGo(i,j,k)){
                        visited[i][j] = 1;
                        dfs(i,j,k);
                        area++;
                    }
                }
            }
            if(maxArea <= area){
                maxArea = area;
                answerK = k;
            }
        }

        System.out.println(answerK + " " + maxArea);
        
    }
}