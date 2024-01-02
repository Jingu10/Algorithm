import java.util.Scanner;

public class Main {
	static int mod = 1000000009;

	public static void main(String[] args) {

		int[][] dp = new int[100001][4]; // n은 최대 1~10
		dp[1][1] = 1;
		dp[2][2] = 1;
		dp[3][3] = 1;
		dp[3][1] = 1;
		dp[3][2] = 1;

		for (int i = 4; i <= 100000; i++) {
			dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % mod;
			dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % mod;
			dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % mod;
		}
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test = 0; test < T; test++) {
			int n = sc.nextInt();
			long result = ((long)dp[n][1] + (long)dp[n][2] + (long)dp[n][3]) % mod;
			System.out.println(result);
		}
	}
}