import java.io.*;
import java.util.*;

class Pair{
    int x;
    int y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int n;
    static int[][] grid;
    static int[][] step;
    static boolean[][] visited;
    static List<Pair> bad = new ArrayList<>();
    static Queue<Pair> q = new LinkedList<>();

    static boolean inRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static void bfs(){
        int[] dx = new int[]{-1,1,0,0};
        int[] dy = new int[]{0,0,-1,1};

        while(!q.isEmpty()){
            Pair curr = q.poll();
            int x = curr.x;
            int y = curr.y;
            
            for(int i = 0; i < 4; i++){
                int newX = x + dx[i];
                int newY = y + dy[i];
                if(inRange(newX, newY) && !visited[newX][newY] && grid[newX][newY] == 1){
                    visited[newX][newY] = true;
                    q.add(new Pair(newX, newY));
                    step[newX][newY] = step[x][y] + 1;
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
        step = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int temp = Integer.parseInt(st.nextToken());
                grid[i][j] = temp;
                if(temp == 2) bad.add(new Pair(i,j));
            }
        }

        for(int i = 0; i < k; i++){
            Pair curr = bad.get(i);
            q.add(curr);
            visited[curr.x][curr.y] = true;
        }

        bfs();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(step[i][j] != 0){
                    System.out.print(step[i][j] + " ");
                    continue;
                }
                if(grid[i][j]==0) {
                    System.out.print(-1 + " ");
                    continue;
                }
                else if(grid[i][j] == 2) {
                    System.out.print(0 + " ");
                    continue;
                }
                System.out.print(-2 + " ");
            }
            System.out.println();
        }
        

    }
}