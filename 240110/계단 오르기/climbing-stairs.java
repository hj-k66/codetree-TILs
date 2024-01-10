import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];
        if(n < 5){
            System.out.println(1);
            return;
        }
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 1;
        for(int i = 5; i <= n; i++){
            dp[i] = dp[i-2] + dp[i-3];
        }
        System.out.println(dp[n] % 10007);
    }
}