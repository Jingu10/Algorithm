import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int ansCost;
	static List<String> ansList; // 정답 배열
	static int[][] food;
	static int[] condition;

	public static void main(String[] args) throws IOException {
		init();
		solve();
		printAns();
	}

	private static void printAns() {
		if (ansCost == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}

		System.out.println(ansCost);
		
		Collections.sort(ansList);
		
		String str = ansList.get(0);
		for(int i = 0; i < str.length(); i++) {
			System.out.print(str.charAt(i));
		}
		
		
	}

	private static void solve() {
		
		for (int select = 1; select <= N; select++) {
			
			int[] combArr = new int[select];
			boolean[] visited = new boolean[N];
			comb(visited, combArr, select, 0, 0);
		}

	}

	private static void comb(boolean[] visited, int[] combArr, int select, int idx, int start) {
		if (idx == select) {
			cal(combArr);
			return;
		}

		for (int i = start; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combArr[idx] = i;
				comb(visited, combArr, select, idx+1, i+1);
				combArr[idx] = 0;
				visited[i] = false;
			}
		}

	}

	private static void cal(int[] combArr) {
		int[] sumArr = new int[5];
		
		for(int i = 0; i < combArr.length; i++) {
			for(int j = 0; j < 5; j++) {
				sumArr[j] += food[combArr[i]][j];
			}
		}
		
		for(int j = 0; j < 4; j++) {
			if(sumArr[j] < condition[j]) return;
		}
		
		// 조건을 모두 만족함
		if(sumArr[4] < ansCost) {
			ansCost = sumArr[4];
			ansList.clear();
			String input = "";
			for(int n : combArr) {
				input += (n+1+" ");
			}
			ansList.add(input);
		} else if(sumArr[4] == ansCost) {
			String input = "";
			for(int n : combArr) {
				input += (n+1+" ");
			}
			ansList.add(input);
		}
		
	}

	private static void init() throws IOException {
		ansCost = Integer.MAX_VALUE;
		ansList = new ArrayList<>();
		condition = new int[4];

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		food = new int[N][5];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			condition[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				food[i][j] = Integer.parseInt(st.nextToken());
			}
		}

	}
}