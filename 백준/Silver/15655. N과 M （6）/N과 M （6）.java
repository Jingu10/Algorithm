import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] nums;
	static int[] res;
	static boolean[] visited;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		nums = new int[N];
		res = new int[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		sb = new StringBuilder();
		visited = new boolean[N];
		dfs(0, 0);
		System.out.println(sb);
		
	}
	public static void dfs(int at, int depth) {
		if(depth == M) {
			for(int i = 0; i < M; i++) {
				sb.append(res[i] + " ");
			}
			sb.append('\n');
			return;
		}
		
		for(int i = at; i < N; i++) {
			if(visited[i]) continue;
			
			res[depth] = nums[i];
			visited[i] = true;
			dfs(i + 1, depth+1);
			visited[i] = false;
		}
	}
}