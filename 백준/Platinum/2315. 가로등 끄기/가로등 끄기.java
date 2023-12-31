import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// dp[left][right][where]: left와 right 사이의 가로등이 모두 꺼져있는 상태 + 내가 서있는 위치(왼쪽 끝0 or
	// 오른쪽 끝1) 에서 남은 가로등을 모두 다 끌 때 까지 낭비되는 전력량
	static int[][][] dp = new int[1001][1001][2];
	static int[][] info;
	static int N, M;

	public static void main(String[] args) throws IOException {
		init();
		System.out.println(solve(M, M, 0));
	}

	// left 와 right 는 절대적 거리가 아니라, 가로등 번호이다
	private static int solve(int left, int right, int where) { 
		if(left == 1 && right == N) {
			return 0; // 모든 가로등을 다 끈 상황
		}
		
		if(dp[left][right][where] != -1) {
			return dp[left][right][where]; // 이미 구한 적 있는 값은 중복 계산 하지 않기
		}
		
		// 현재 서있는 가로등 번호
		int now = left;
		if(where == 1) now = right;
		
		int ans = Integer.MAX_VALUE; // 결국 두 케이스 중 뭐가 더 작음? 이걸 고르기 위한 것!!
		
		// 지금 내가 가로등을 끈 범위의 바로 왼쪽 한 칸을 끄러 감
		if(left > 1) {
			//(이동하는 시간) * (이동하는 동안 아직 끄지 않은 가로등들의 전력 소비량 누적합)
			int tmp = solve(left -1, right, 0) + (info[now][0]-info[left-1][0])*(info[N][1]-info[right][1]+info[left-1][1]);
			if(tmp < ans) ans = tmp;
		}
		
		// 지금 내가 가로등을 끈 범위의 바로 오른쪽 한 칸을 끄러 감
		if(right < N) {
			int tmp = solve(left, right + 1, 1) + (info[right+1][0]-info[now][0])*(info[N][1]-info[right][1]+info[left-1][1]);
			if(tmp < ans) ans = tmp;
		}
		
		dp[left][right][where] = ans;
		
		return dp[left][right][where];
	}

	static void init() throws IOException {
		for(int i = 1; i <= 1000; i++) {
			for(int j = 1; j <= 1000; j++) {
				dp[i][j][0] = -1;
				dp[i][j][1] = -1;
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 가로등의 수
		M = Integer.parseInt(st.nextToken()); // 처음 서있는 가로등 번호

		info = new int[N + 1][2]; // 위치와 초당 전력소비량 저장

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = info[i - 1][1] + Integer.parseInt(st.nextToken());
		}
		
	}
}
