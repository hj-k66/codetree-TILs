import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;

    static boolean canGo(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < m;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] grid = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int max = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(canGo(i+1, j) && canGo(i+1, j+1)){
                    max = Math.max(max, grid[i][j] + grid[i+1][j] + grid[i+1][j+1]);
                }
                if(canGo(i,j+1) && canGo(i+1, j)){
                    max = Math.max(max, grid[i][j+1] + grid[i][j] + grid[i+1][j]);
                }
                if(canGo(i,j+1) && canGo(i+1, j+1)){
                    max = Math.max(max, grid[i][j+1] + grid[i][j] + grid[i+1][j+1]);
                }
                if(canGo(i,j+1) && canGo(i+1,j) && canGo(i+1, j+1)){
                    max = Math.max(max, grid[i][j+1] + grid[i+1][j] + grid[i+1][j+1]);
                }
                if(canGo(i,j+1) && canGo(i,j+2)){
                    max = Math.max(max, grid[i][j] + grid[i][j+1] + grid[i][j+2]);
                }
                if(canGo(i+1, j) && canGo(i+2, j)){
                    max = Math.max(max, grid[i][j] + grid[i+1][j] + grid[i+2][j]);

                }
            }
        }

        System.out.println(max);
    }
}