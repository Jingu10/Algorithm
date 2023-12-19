import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int n, k;
	static int[] coins;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		init();
		solve();
		if(dp[k] == Integer.MAX_VALUE) dp[k] = -1;
		System.out.println(dp[k]);
	}
	
	
	private static void solve() {
		for(int p = 1; p <= k; p++) {
			for(int i = 0; i < coins.length; i++) {
				if(coins[i] > p) break;
				int diff =  p - coins[i];
				if(dp[diff] != Integer.MAX_VALUE && dp[diff] + 1 < dp[p]) {
					dp[p] = dp[diff] + 1;
				}
			}
		}
		
	}


	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		Set<Integer> set = new HashSet<>();
		dp = new int[k + 1];
		for(int i = 0; i < n; i++) {
			set.add(Integer.parseInt(br.readLine()));
		}
		
		int size = set.size();
		coins = new int[size];
		int idx = 0;
		for(int n : set) {
			coins[idx++] = n;
		}
		Arrays.sort(coins);
		
		
		
		for(int i = 1; i <= k; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		
	}
}