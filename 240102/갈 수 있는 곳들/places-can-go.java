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
    static int count;

    static boolean inRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static boolean canGo(int x, int y){
        if(!inRange(x,y)) return false;
        if(visited[x][y] || grid[x][y] == 1) return false;
        return true;
    }
    
    
    static void push(int x, int y){
        visited[x][y] = true;
        q.add(new Pair(x,y));
    }

    static void bfs(){
        int[] dx = new int[]{-1,1,0,0};
        int[] dy = new int[]{0,0,-1,1};

        while(!q.isEmpty()){
            Pair curr = q.poll();
            int x = curr.x;
            int y = curr.y;
            count++;

            for(int i = 0; i < 4; i++){
                int newX = x + dx[i];
                int newY = y + dy[i];
                if(canGo(newX, newY)){
                    push(newX, newY);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        grid = new int[n][n];
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < k;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            if(canGo(x,y)){
                push(x,y);
                bfs();
            }
        }

        System.out.println(count);
    }
}