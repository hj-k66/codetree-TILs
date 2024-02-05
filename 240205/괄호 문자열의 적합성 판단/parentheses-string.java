import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < input.length(); i++){
            char curr = input.charAt(i);
            if(curr == '('){
                stack.push(curr);
            }else{
                if(stack.isEmpty()) {
                    System.out.println("No");
                    return;
                }
                stack.pop();
            }
        }
        if(!stack.isEmpty()) System.out.println("No");
        else System.out.println("Yes");
    }
}