import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N + 1];
		Arrays.fill(dp, 12345678);
		dp[N] = 0;

		for (int i = N - 1; i >= 1; i--) {
			if (i * 3 <= N) {
				dp[i] = Math.min(dp[i], dp[i * 3] + 1);
			}

			if (i * 2 <= N) {
				dp[i] = Math.min(dp[i], dp[i * 2] + 1);
			}

			dp[i] = Math.min(dp[i], dp[i + 1] + 1);
		}
		System.out.println(dp[1]);

	}
}