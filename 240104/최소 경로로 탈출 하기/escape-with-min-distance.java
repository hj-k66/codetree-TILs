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
    static int m;
    static int[][] grid;
    static boolean[][] visited;
    static int[][] step;
    static Queue<Pair> q = new LinkedList<>();

    static void push(int x, int y, int s){
        step[x][y] = s;
        visited[x][y] = true;
        q.add(new Pair(x,y));
    }

    static boolean inRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < m;
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
                    push(newX, newY, step[x][y] + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new int[n][m];
        visited = new boolean[n][m];
        step = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        push(0,0,0);
        bfs();
        int answer = step[n-1][m-1];
        if(step[n-1][m-1] == 0){
            answer = -1;
        }
        System.out.println(answer);
    }
}