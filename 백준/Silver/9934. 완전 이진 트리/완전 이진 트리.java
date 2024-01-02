import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int K, size;
	static int[] nums; // 중위순회
	static List<Integer>[] levels;

	public static void main(String[] args) throws IOException {
		init();
		dfs(size, 0, 1);
		printAns();
	}

	private static void dfs(int length, int firstIndex, int level) {
		if (length == 1) {
			levels[level].add(nums[firstIndex]);
			return;
		}

		int root = firstIndex + length / 2;
		levels[level].add(nums[root]);

		dfs(length / 2, firstIndex, level + 1);
		dfs(length / 2, root + 1, level + 1);

	}

	private static void printAns() {
		for (int i = 1; i <= K; i++) {
			for (int n : levels[i]) {
				System.out.print(n + " ");
			}
			System.out.println();
		}

	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		size = (int) Math.pow(2, K) - 1;
		nums = new int[size];
		levels = new ArrayList[K + 1];
		for (int i = 1; i <= K; i++) {
			levels[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < size; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
	}
}