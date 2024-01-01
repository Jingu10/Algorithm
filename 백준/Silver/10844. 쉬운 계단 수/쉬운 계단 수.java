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
            for(int k = 0; k <= 1023; k++){
			ans = (ans + dp[N][j][k]) % mod;
            }
		}

		System.out.println(ans);

	}

}

/*
 * 1. 이 문제가 dp인지 인지하기 (나머지 연산이 있다는 것을 보면 알 수 있음)
 * 2. 점화식을 세워보자 
 * 3. dp 배열을 정의해보자 
 * 
 * 사실 2~3은 동시에 가야 돼
 * dp 배열은 dp[i][j][k] i는 자릿수(1~N). j는 맨 마지막 숫자(0~9).
 * dp 배열의 값은 해당 조건을 만족하는 계단수의 수.
 * k는 해당 수가 어떤 수를 포함하고 있는지(0~1023 비트마스킹)
 * 우리가 원하는 것은 dp[N][j][1023]의 합
 * 
 * 
 * i-1번째 자릿수의 숫자 중에서 j-1 또는 j+1 로 끝나면서 k패턴의 수를 포함하고 있는 모든 수 중에서
 * j라는 숫자를 끝에 붙이면 그게 계단수가 되겠지? 이 경우 k패턴에서 j를 포함한 패턴으로 바꿔주면 됨(k | (1 << j))
 * 
 * 
 * 4. 예외 케이스를 처리해보자 (0으로 끝나는 수는 1로 끝나는 수에서 밖에 올 수 없다. 9로 끝나는 수도 마찬가지)
 * 
 * 이 문제는 비트마스킹에 대한 이해도가 높아야 한다! 많이 연습하기
 */