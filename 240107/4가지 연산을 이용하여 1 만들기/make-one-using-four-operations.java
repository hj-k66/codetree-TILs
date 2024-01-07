import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();
        int[] step = new int[n + 1000000];
        q.add(n);
        while(!q.isEmpty()){
            int curr = q.poll();
            if(curr <= 1) break;
            if(curr % 3 == 0){
                if(step[curr / 3] == 0){
                    q.add(curr / 3);    
                    step[curr/3] = step[curr] + 1;
                }
                
            }
            if(curr % 2 == 0){
                 if(step[curr / 2] == 0){
                    q.add(curr / 2);    
                    step[curr/2] = step[curr] + 1;
                }
            }
            if(step[curr+1] == 0){
                q.add(curr + 1);        
                step[curr + 1] = step[curr] + 1;
            }
            
            if(step[curr-1] == 0){
                q.add(curr - 1);
                step[curr - 1] = step[curr] + 1;
            }
            
        }
        System.out.println(step[1]);
    }
}