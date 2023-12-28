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
        if(!inRange(x, y)) return false;
        if(visited[x][y] == 1 || grid[x][y] == 0) return false;
        return true;
    }
    
    static void dfs(int x, int y){
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        for(int i = 0; i < 4; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            if(canGo(newX, newY)){
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
        List<Integer> town = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                count = 0;
                dfs(i,j);
                if(count != 0)
                    town.add(count);
            }
        }
        //출력
        Collections.sort(town);
        System.out.println(town.size());
        for(int i = 0; i < town.size(); i++){
            System.out.println(town.get(i));
        }
    }
}