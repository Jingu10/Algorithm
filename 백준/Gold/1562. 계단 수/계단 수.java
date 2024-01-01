import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int mod = 1000000000;
		int ans = 0;

		int[][][] dp = new int[N + 1][10][1024];

		for (int j = 1; j <= 9; j++) {
			dp[1][j][1 << j] = 1;
		}

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= 9; j++) {
				for (int k = 0; k <= 1023; k++) {
					if (j == 0) {
						dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i - 1][1][k]) % mod;
					} else if (j == 9) {
						dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i - 1][8][k]) % mod;
					} else {
						dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i - 1][j - 1][k]) % mod;
						dp[i][j][k | (1 << j)] = (dp[i][j][k | (1 << j)] + dp[i - 1][j + 1][k]) % mod;
					}
				}
			}
		}

		for (int j = 0; j <= 9; j++) {
			ans = (ans + dp[N][j][1023]) % mod;
		}

		System.out.println(ans);

	}

}