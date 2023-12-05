import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, D;
	static int killCnt, enemy, initialEnemy, ans, a1, a2, a3;
	static int[][] board, boardCopy;
	static boolean[][] checkBoard;
	static int[] dr = { 0, -1, 0 }; // 좌 상 우
	static int[] dc = { -1, 0, 1 };
	static int[] pos; // 궁수 3명의 위치를 담은 배열

	public static void main(String[] args) throws IOException {
		init();

		for (a1 = 1; a1 <= M - 2; a1++) {
			for (a2 = a1 + 1; a2 <= M - 1; a2++) {
				for (a3 = a2 + 1; a3 <= M; a3++) {
					createBoardCopy();
					
					initialEnemy = enemy;
					killCnt = 0;
					
					pos[0] = a1;
					pos[1] = a2;
					pos[2] = a3;
					
					while (initialEnemy > 0) {
						checkAttack();
						killEnemy();
						if (initialEnemy == 0)
							break;
						moveEnemy();
					}
					renewAns();

				}
			}
		}

		System.out.println(ans);

	}

	private static void createBoardCopy() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				boardCopy[i][j] = board[i][j];
			}
		}
		
	}

	private static void renewAns() {
		if(killCnt > ans) ans = killCnt;
	}

	private static void moveEnemy() {
		// 사라지는 적부터 카운트
		for(int col = 1; col <= M; col++) {
			if(boardCopy[N][col] == 1) {
				initialEnemy--;
			}
		}
		for(int row = N - 1; row >= 1; row--) {
			for(int col = 1; col <= M; col++) {
				boardCopy[row + 1][col] = boardCopy[row][col];
			}
		}
		
		for(int col = 1; col <= M; col++) {
			boardCopy[1][col] = 0;
		}

	}

	private static void killEnemy() {
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				if(checkBoard[i][j]) {
					checkBoard[i][j] = false;
					boardCopy[i][j] = 0;
					killCnt++;
					initialEnemy--;
				}
			}
		}

	}

	private static void checkAttack() {
		// 3명의 궁수에 대한 체크 (시작점은 (a1, M))
		a: for (int archer = 0; archer < 3; archer++) {
			int row = N;
			int col = pos[archer];
			
			if (D == 1) {
				if(boardCopy[row][col] == 1) checkBoard[row][col] = true;
			} else {

				if(boardCopy[row][col] == 1) {
					checkBoard[row][col] = true;
					continue;
				}
				
				boolean[][] visited = new boolean[N + 1][M + 1]; // bfs 방문 체크 배열
				Queue<int[]> queue = new LinkedList<>(); // bfs용 큐
				visited[row][col] = true;
				queue.add(new int[] {row, col, 1}); // 세번째 인자는 공격 거리
				while(!queue.isEmpty()) {
					int[] tmp = queue.poll();
					row = tmp[0];
					col = tmp[1];
					int dis = tmp[2];
					for(int d = 0; d < 3; d++) {
						int nrow = row + dr[d];
						int ncol = col + dc[d];
						if(nrow > 0 && ncol > 0 && ncol <= M && !visited[nrow][ncol] && dis != D) {
							if(boardCopy[nrow][ncol] == 1) {
								checkBoard[nrow][ncol] = true;
								continue a;
							} else {
								visited[nrow][ncol] = true;
								queue.add(new int[] {nrow, ncol, dis + 1});
							}
						}
						
					}
				}
				
			}
		}
	}

	static void init() throws IOException {
		ans = 0;
		enemy = 0;
		pos = new int[3];

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		board = new int[N + 1][M + 1]; // 1번부터 사용
		boardCopy = new int[N + 1][M + 1];
		checkBoard = new boolean[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1)
					enemy++;
			}
		}
	}
}