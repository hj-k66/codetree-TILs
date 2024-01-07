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

public class OtherSolution{
    //각 사람마다 bfs를 돌려 가장 가까운 쉘터를 구하는게 아니라,
    //m개의 쉘터를 시작으로 하는 bfs를 한 번 돌린다. (거꾸로 생각하기)
    
    public static int n, h, m;
    public static ArrayList<Pair> sPos = new ArrayList<>(); //비를 피할 수 있는 공간 저장
    public static int[][] a = new int[MAX_N][MAX_N];
    public static Queue<Pair> q = new LinkedList<>();
    public static boolean[][] visited;
    public static int[][] step;

    public static boolean canGo(int x, int y) {
        return inRange(x, y) && a[x][y] != 1 && !visited[x][y];
    }

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

     public static void push(int nx, int ny, int newStep) {
        q.add(new Pair(nx, ny));
        visited[nx][ny] = true;
        step[nx][ny] = newStep;
    }

    public static void BFS() {
        // queue에 남은 것이 없을때까지 반복합니다.
        while(!q.isEmpty()) {
            // queue에서 가장 먼저 들어온 원소를 뺍니다.
            Pair currPos = q.poll();
            int x = currPos.x, y = currPos.y;
    
            int[] dx = new int[]{-1, 1, 0, 0};
            int[] dy = new int[]{0, 0, -1, 1};
    
            // queue에서 뺀 원소의 위치를 기준으로 4방향을 확인해봅니다.
            for(int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir], ny = y + dy[dir];
    
                // 아직 방문한 적이 없으면서 갈 수 있는 곳이라면
                // 새로 queue에 넣어줍니다.
                if(canGo(nx, ny))
                    // 최단 거리는 이전 최단거리에 1이 증가하게 됩니다. 
                    push(nx, ny, step[x][y] + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        h = sc.nextInt();
        m = sc.nextInt();
        a = new int[n][n];
        visited = new boolean[n][n];
        step = new int[n][n];
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
                if(a[i][j] == 3)
                    sPos.add(new Pair(i, j)); //비를 피할 수 있는 공간 저장
            }

        for(int i = 0; i < (int) sPos.size(); i++)
            push(sPos.get(i).x, sPos.get(i).y, 0); //비를 피할 수 있는 공간을 시작점으로 하는 bfs 진행
        bfs();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(a[i][j] != 2)
                    System.out.print(0 + " ");
                else {
                    if(!visited[i][j])
                        System.out.print(-1 + " ");
                    else
                        System.out.print(step[i][j] + " ");
                }
            }
            System.out.println();
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
