import java.io.*;
import java.util.*;

public class Main {

    static class Pair implements Comparable<Pair>{
        int x;
        int y;
        int value;


        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Pair(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public int compareTo(Pair o){
            if(this.value != o.value) return this.value - o.value;
            if(this.x != o.x) return o.x - this.x;
            return o.y - this.y;  
        }
    }

    static int[][] grid;
    static boolean[][] visited;
    static Queue<Pair> q = new LinkedList<>();
    static List<Pair> moved;
    static int n;

    static void push(int x, int y){
        visited[x][y] = true;
        q.add(new Pair(x,y));
    }

    static boolean inRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static boolean canGo(int x, int y){
        if(!inRange(x, y) || visited[x][y]) return false;
        return true;
    }

    static void bfs(int x, int y){
        int[] dx = new int[]{-1,1,0,0};
        int[] dy = new int[]{0,0,-1,1};
        
        while(!q.isEmpty()){
            Pair curr = q.poll();
            int currX = curr.x;
            int currY = curr.y;

            for(int i = 0; i < n; i++){
                int newX = currX + dx[i];
                int newY = currY + dy[i];
                if(canGo(newX, newY) && grid[x][y] > grid[newX][newY]){
                    visited[newX][newY] = true;
                    push(newX, newY);
                    moved.add(new Pair(newX, newY, grid[newX][newY]));
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
                grid[i][j] =  Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        push(x,y);
        while(k-- > 0){
            moved = new ArrayList<>();
            visited = new boolean[n][n];
            push(x,y);
            bfs(x, y);
            Collections.sort(moved);
            if(moved.size() == 0){
                break;
            }
            x = moved.get(moved.size() - 1).x;
            y = moved.get(moved.size() - 1).y;         
        }
        System.out.println((x+1) + " " + (y+1));
    }
}