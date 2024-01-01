import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static Queue<int[]> queue1;
	static Queue<int[]> queue2;
	static int jump;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		init();
		solve();
		System.out.println(jump);
	}

	private static void solve() {
		while (true) {
			jump++;
			if (jump % 2 == 1) {
				if (!bfs(queue1, queue2)) {
					break;
				}
			} else {
				if( !bfs(queue2, queue1)) {
					break;
				}
			}
		}

	}

	private static boolean bfs(Queue<int[]> currentQueue, Queue<int[]> nextQueue) {
		while (!currentQueue.isEmpty()) {
			int[] pos = currentQueue.poll();
			int row = pos[0];
			int col = pos[1];

			for (int d = 0; d < 4; d++) {
				int nrow = row + dr[d];
				int ncol = col + dc[d];

				if (nrow > 0 && ncol > 0 && nrow <= N && ncol <= M && !visited[nrow][ncol]) {
					visited[nrow][ncol] = true;
					if (map[nrow][ncol] == '0') {
						currentQueue.add(new int[] { nrow, ncol });
					} else if (map[nrow][ncol] == '1') {
						nextQueue.add(new int[] { nrow, ncol });
						map[nrow][ncol] = '0';
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}

	static void init() throws IOException {
		jump = 0;
		queue1 = new LinkedList<>();
		queue2 = new LinkedList<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		st = new StringTokenizer(br.readLine());
		int startRow = Integer.parseInt(st.nextToken());
		int startCol = Integer.parseInt(st.nextToken());
		st.nextToken();
		st.nextToken(); // 필요없는 토큰 소모

		visited[startRow][startCol] = true;
		queue1.add(new int[] { startRow, startCol });

		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = line.charAt(j - 1);
			}
		}

	}
}