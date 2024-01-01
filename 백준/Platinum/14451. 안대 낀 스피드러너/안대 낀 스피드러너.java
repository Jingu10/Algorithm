import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[] dir = { -1, 0, 1 }; // 좌회전 -1, 직진 0, 우회전 1
	static int[][][][][][] dp; // row1, col1, row2, col2, dir1, dir2 (dir은 상우하좌 0123)
	static char[][] map;
	static int N;

	public static void main(String[] args) throws IOException {
		init();
		bfs();
		// 원하는 출력 값 dp[0][N-1][0][N-1][][] 이 처음 만족되는 순간의 값 (최소 길이 이므로!)
	}

	private static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { N - 1, 0, N - 1, 0, 0, 1 });

		while (!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int row1 = tmp[0];
			int col1 = tmp[1];
			int row2 = tmp[2];
			int col2 = tmp[3];
			int dir1 = tmp[4];
			int dir2 = tmp[5];

			// 좌회전, 전진, 우회전
			for (int r = 0; r < 3; r++) {
				int ndir1 = (4 + dir1 + dir[r]) % 4;
				int ndir2 = (4 + dir2 + dir[r]) % 4;

				int nrow1 = row1;
				int ncol1 = col1;
				int nrow2 = row2;
				int ncol2 = col2;

	

				// 아직 도착하지 않았다면,
				if (!(row1 == 0 && col1 == N - 1)) {
					if(r == 1) {
						nrow1 += dr[ndir1];
						ncol1 += dc[ndir1];
					}
					
					
					if(nrow1 < 0 || nrow1 >= N || ncol1 < 0 || ncol1 >= N || map[nrow1][ncol1] == 'H') {
						// 움직이지 못하는 상태
						nrow1 = row1;
						ncol1 = col1; 
					}
				}

				if (!(row2 == 0 && col2 == N - 1)) {
					
					if(r == 1) {
						nrow2 += dr[ndir2];
						ncol2 += dc[ndir2];
					}
					
					if(nrow2 < 0 || nrow2 >= N || ncol2 < 0 || ncol2 >= N || map[nrow2][ncol2] == 'H') {
						// 움직이지 못하는 상태
						nrow2 = row2;
						ncol2 = col2; 
					}
				}
				
				if(dp[nrow1][ncol1][nrow2][ncol2][ndir1][ndir2] > 0) continue;
				queue.add(new int[] {nrow1, ncol1, nrow2, ncol2, ndir1, ndir2});
				dp[nrow1][ncol1][nrow2][ncol2][ndir1][ndir2] = dp[row1][col1][row2][col2][dir1][dir2] + 1;
				if(nrow1 == 0 && ncol1 == N-1 && nrow2 == 0 && ncol2 == N-1) {
					System.out.println(dp[nrow1][ncol1][nrow2][ncol2][ndir1][ndir2] - 1);
					return;
				}
			}

		}
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N][N][N][N][4][4]; // 해당 값은 여기에 오기 까지 몇 번 이동 했는지. 구분을 위해 첫 위치를 1로 초기화
		// 출력할 땐 1을 빼주기. 첫 초기화를 1로 해주지 않으면 시작 상태를 다시 방문하는 문제점 발생.
		dp[N - 1][0][N - 1][0][0][1] = 1;

		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
			}
		}

	}
}