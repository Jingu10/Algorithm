import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M, N, K;
	static boolean[][] board;
	static List<Integer> areas;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		init();
		solve();
	}

	private static void solve() {
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(!board[i][j]) {
					int area = 1;
					Queue<int[]> queue = new LinkedList<>();
					board[i][j] = true;
					queue.add(new int[] {i, j});
					while(!queue.isEmpty()) {
						int[] pos = queue.poll();
						int row = pos[0];
						int col = pos[1];
						for(int d = 0; d < 4; d++) {
							int nrow = row + dr[d];
							int ncol = col + dc[d];
							if(nrow >= 0 && ncol >= 0 && nrow < M && ncol < N && !board[nrow][ncol]) {
								board[nrow][ncol] = true;
								area++;
								queue.add(new int[] {nrow, ncol});
							}
						}
					}
					areas.add(area);
				}
			}
		}
		
		
		
		Collections.sort(areas);
		System.out.println(areas.size());
		for(int area : areas) {
			System.out.print(area + " ");
		}
	}

	private static void init() throws IOException {
		areas = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new boolean[M][N];
		for(int r = 0; r < K; r++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			for(int i = y1; i < y2; i++) {
				for(int j = x1; j < x2; j++) {
					board[i][j] = true;
				}
			}
		}
	}
}