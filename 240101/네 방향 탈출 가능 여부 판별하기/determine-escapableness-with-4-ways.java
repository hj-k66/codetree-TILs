import java.io.*;
import java.util.*;

public class Main {

    static class Pair{
        int x;
        int y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int[][] grid;
    static boolean[][] visited;
    static Queue<Pair> q = new LinkedList<>();
    static int n;
    static int m;

    static boolean inRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    static boolean canGO(int x, int y){
        if(!inRange(x,y)) return false;
        if(visited[x][y] || grid[x][y] == 0) return false;
        return true;
    }

    static void push(int x, int y){
        visited[x][y] = true;
        q.add(new Pair(x,y));
    }

    static int bfs(){
        int[] dx = new int[]{-1,1,0,0};
        int[] dy = new int[]{0,0,-1,1};

        while(!q.isEmpty()){
            Pair curr = q.poll();
            int x = curr.x;
            int y = curr.y;

            if(x == n - 1 && y == m - 1) return 1;
            for(int i = 0; i < 4; i++){
                int newX = x + dx[i];
                int newY = y + dy[i];
                if(canGO(newX, newY)){
                    push(newX, newY);
                }
            }
        }
        return 0;

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new int[n][m];
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        push(0,0);
        System.out.println(bfs());
    }
}