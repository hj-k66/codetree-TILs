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
    static Queue<Pair> q = new LinkedList<>();
    static boolean[][] visited;
    static int[][] knight;

    static boolean inRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static int bfs(int r1, int c1, int r2, int c2){
        int[] dx = new int[]{-1, -2, -2, -1, 1, 2, 2, 1};
        int[] dy = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
        q.add(new Pair(r1, c1));

        while(!q.isEmpty()){
            Pair curr = q.poll();
            int x = curr.x;
            int y = curr.y;

            for(int i = 0; i < 8; i++){
                int newX = x + dx[i];
                int newY = y + dy[i];
                if(inRange(newX, newY) && !visited[newX][newY]){
                    knight[newX][newY] = knight[x][y] + 1;
                    visited[newX][newY] = true;
                    q.add(new Pair(newX, newY));
                    if(newX == r2 && newY == c2){
                        return knight[newX][newY];
                    }
                }
            }
        }
        return -1;
        

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken()) - 1;
        int c1 = Integer.parseInt(st.nextToken()) - 1;
        int r2 = Integer.parseInt(st.nextToken()) - 1;
        int c2 = Integer.parseInt(st.nextToken()) - 1;
        if(n == 1) {
            System.out.println(0);
            return;
        }
        visited = new boolean[n][n];
        knight = new int[n][n];
        int answer = bfs(r1, c1, r2, c2);
        System.out.println(answer);
    }
}