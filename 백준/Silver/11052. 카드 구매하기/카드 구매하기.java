import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] nums = new int[N+1];
		int[] dp = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			nums[i] = sc.nextInt();
			dp[i] = nums[i];
		}
		
		for(int i = 2; i <= N; i++) {
			for(int j = 1; j < i; j++) {
				dp[i] = Math.max(dp[i], dp[i-j] + nums[j]);
			}
			
		}
		
		
		
		
		System.out.println(dp[N]);
	}
}