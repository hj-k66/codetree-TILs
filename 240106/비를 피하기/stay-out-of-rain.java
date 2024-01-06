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

    static int[][] grid;
    static int n;
    static boolean[][] visited;
    static int[][] step;
    static Queue<Pair> q = new LinkedList<>();
    static int min;

    static boolean inRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static int bfs(){
        int[] dx = new int[]{-1,1,0,0};
        int[] dy = new int[]{0,0,-1,1};

        while(!q.isEmpty()){
            Pair curr = q.poll();
            int x = curr.x;
            int y = curr.y;
            for(int i = 0; i < 4; i++){
                int newX = x + dx[i];
                int newY = y + dy[i];

                if(inRange(newX, newY) && !visited[newX][newY] && grid[newX][newY] != 1){
                    visited[newX][newY] = true;
                    q.add(new Pair(newX, newY));
                    step[newX][newY] = step[x][y] + 1;
                    if(grid[newX][newY] == 3) {
                        min = Math.min(step[newX][newY], min);
                    }
                }
            }

        }
        if(min == 10000) return -1;
        return min;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        grid = new int[n][n];
        visited = new boolean[n][n];
        step = new int[n][n];
        min = 10000;
        int[][] answer = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //사람 찾기
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 2){
                    visited[i][j] = true;
                    q.add(new Pair(i,j));
                    int time = bfs();
                    answer[i][j] = time;
                    visited = new boolean[n][n];
                    step = new int[n][n];
                    q.clear();
                    min = 10000;
                }
            }
        }

        //출력
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }
}