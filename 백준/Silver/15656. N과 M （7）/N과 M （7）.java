import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M; // N개의 수 중 M개를 뽑은 수열을 출력
	static int[] res;
	static int[] nums;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		init();
		perm(0);
		System.out.println(sb);
	}

	private static void perm(int depth) {
		// M개를 모두 뽑은 경우 출력
		if (depth == M) {
			for (int num : res) {
				sb.append(num + " ");
			}
			sb.append('\n');
			return;
		}

		for (int i = 0; i < N; i++) {
			res[depth] = nums[i];
			perm(depth + 1);
		}

	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		res = new int[M]; // M개 뽑은 순열
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
	}
}