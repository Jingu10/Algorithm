import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] nums;
	static Integer[] res;
	static boolean[] visited;
	static Set<String> set;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N];
		res = new Integer[M];
		visited = new boolean[N];
		sb = new StringBuilder();
		set = new HashSet<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);

		dfs(0);
		System.out.println(sb);
	}

	public static void dfs(int depth) {
		if (depth == M) {
			StringBuilder sb2 = new StringBuilder();
			for (int i = 0; i < M; i++) {
				sb2.append(res[i]).append(" ");
			}
			sb2.append('\n');
			
			if(!set.contains(sb2.toString())) {
				set.add(sb2.toString());
				sb.append(sb2);
			}
			
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;

			res[depth] = nums[i];
			visited[i] = true;
			dfs(depth + 1);
			visited[i] = false;
		}
	}
}