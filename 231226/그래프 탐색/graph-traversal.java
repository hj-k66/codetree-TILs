import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<Integer>[] graph;
    public static boolean[] visited;
    public static int count = 0;

    public static void DFS(int v){
        for(int i = 0; i < graph[v].size();i++){
            int curr = graph[v].get(i);
            if(!visited[curr]){
                count++;
                visited[curr] = true;
                DFS(curr);   
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        visited = new boolean[n+1];
        for(int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }

        visited[1] = true;
        DFS(1);
        System.out.println(count);
        
    }
}