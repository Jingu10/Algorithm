import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] health;
	static int ans;
	static int[][] dh = { { 9, 3, 1 }, { 9, 1, 3 }, { 3, 9, 1 }, { 3, 1, 9 }, { 1, 9, 3 }, { 1, 3, 9 } };

	public static void main(String[] args) throws IOException {
		init();
		solve();
		System.out.println(ans);
	}

	private static void solve() {
		if (N == 1) {
			solve1(health[0], 0);
			return;
		}

		if (N == 2) {
			solve2(health[0], health[1], 0);
			return;
		}

		if (N == 3) {
			solve3();
		}
	}

	private static void solve3() {
		int[][][] visited = new int[health[0]][health[1]][health[2]];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { health[0], health[1], health[2], 0 }); // 마지막은 이동거리(=공격횟수)

		while (!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int h1 = tmp[0];
			int h2 = tmp[1];
			int h3 = tmp[2];
			int dis = tmp[3];

			for (int d = 0; d < 6; d++) {
				int nh1 = Math.max(0, h1 - dh[d][0]);
				int nh2 = Math.max(0, h2 - dh[d][1]);
				int nh3 = Math.max(0, h3 - dh[d][2]);
				
				if(nh1 == 0 && nh2 == 0 && nh3 == 0) {
					System.out.println(dis + 1);
					System.exit(0);
				}
				
				if(visited[nh1][nh2][nh3] == 0) {
					visited[nh1][nh2][nh3] = dis + 1;
					queue.add(new int[] {nh1, nh2, nh3, dis + 1});
					
				}
				
			}

		}

	}

	private static void solve1(int i, int T) {
		T += i / 9;
		if (i % 9 > 0) {
			T++;
		}
		if (T < ans)
			ans = T;

	}

	private static void solve2(int i, int j, int T) {
		if (i <= 0 && j <= 0) {
			if (T < ans)
				ans = T;
			return;
		} else if (i <= 0) {
			solve1(j, T);
			return;
		} else if (j <= 0) {
			solve1(i, T);
			return;
		}

		solve2(i - 9, j - 3, T + 1);
		solve2(i - 3, j - 9, T + 1);
	}

	static void init() throws IOException {
		ans = Integer.MAX_VALUE;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		health = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			health[i] = Integer.parseInt(st.nextToken());
		}
	}
}