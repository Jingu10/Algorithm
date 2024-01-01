import java.util.Scanner;

public class Main {
	static int N;
	static int[] nums;
	static int[]dp;

	public static void main(String[] args) {
		init();
		solve();
		System.out.println(dp[N-1]);
	}

	

	private static void solve() {
		for(int i = 3; i < N; i++) {
			dp[i] = Math.max(dp[i-2] + nums[i], dp[i-3] + nums[i-1] + nums[i]);
		}
	}



	private static void init() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		nums = new int[N];
		dp = new int[N]; //
		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextInt();
		}
		
		if(N == 1) {
			System.out.println(nums[0]);
			System.exit(0);
		}
		
		if(N == 2) {
			System.out.println(nums[0] + nums[1]);
			System.exit(0);
		}
		
		dp[0] = nums[0];
		dp[1] = nums[0] + nums[1];
		dp[2] = Math.max(nums[0] + nums[2], nums[1] + nums[2]);
	}
}