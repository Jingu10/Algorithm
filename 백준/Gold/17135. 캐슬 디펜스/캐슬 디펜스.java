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


/*
캐슬디펜스

N * M 격자

각 칸에 포함된 적의 수는 최대 하나 (있거나 없거나)

격자판 N번행 바로 아래의 모든 칸에는 성이 있음
(이 말은 즉, 1번 행부터 시작한다는 뜻!)

성이 있는 칸에 궁수를 3명 배치할 거고,
하나의 칸에 최대 1명의 궁수만 있을 수 있음

각각의 턴마다 궁수는 적 하나를 공격, 모든 궁수는 동시에 공격

궁수는 누구를 때리냐? 거리가 D이하인 적 중 가장 가까운 적
그런 적이 여러 명이라면 가장 왼쪽 적을 공격
(거리의 정의는 bfs의 최단거리의 그 거리이다..!)

하나의 적이 여러 궁수에게 공격당할 수도 있다.
공격받은 적은 게임에서 제외한다.

궁수의 공격이 끝나면, 적은 아래로 한 칸 이동한다.
성이 있는 칸으로 이동한 경우, 게임에서 제외된다.

모든 적이 격자판에서 제외되면 게임 끝!

내가 구해야 하는 것은 궁수의 공격으로 제거할 수 있는 적의 최대 수
궁수를 배치하고 나면, 게임은 정해져있으므로
적을 최대로 제거할 수 있게끔 궁수를 배치하는 것이 중요!!
(지금 생각나는건 모든 경우의 수 완전탐색.. N의 size를 봐야함 15아래)

[제한]
N, M은 3~15 (역시 이럴 수 밖에 없음 ㅋ)
D는 1~10 (궁수의 공격 거리 제한)

[입력]
(1) N M D // 행수, 열수, 궁수공격거리제한
(2) N * M 격자판 상태가 주어짐 // 0은 빈 칸, 1은 적이 있는 칸

[출력]
궁수 공격으로 제거할 수 있는 적의 최대 수 출력 
(주의) 맨 아래에 도달해서 제외된 적은 제거된 적이 아니다


[전략]

0. 격자판 입력 받을 때 적이 총 몇 명인지 세기! (종료 조건 판단)

1. 일단 NC3 으로 궁수를 배치한다 (어떤 배치가 최적인지 알 수 없으니
모든 경우의 수를 판단)

15C3 을 뽑는 경우의 수 = 455


2. bfs를 돌릴 건데 시작점에선 위로만 가야 되고, 
나머지에선 왼쪽 위 오른쪽 세 방향으로만 가게 하면 됨 
(bfs를 도는데 아래로 갈 필요가 전혀 없음)

가장 가까운 적이면서, 가장 왼쪽에 있는 적을 공격해야 하므로
bfs를 돌 때는 무조건 왼쪽부터 들어가게끔 설정해야 함

bfs를 도는데 그 칸에 적이 있다면, 공격대상인지 판단하는 변수를
true로 바꾸고 bfs를 바로 종료 (여러 대상이 선택되지 않게 하기 위해)

이런 식으로 세 명의 궁수에 대한 bfs를 돌린다.


3. 배열을 순회하며 true인 적을 모두 제거 (15 * 15 = 225)

4. 남은 적의 수 갱신하고, 게임이 종료 됐는지 체크 
(남은 적의 수가 0인지)

5. 궁수를 아래로 한 칸씩 전진 (15 * 15 = 225)

6. 성에 도달한 적을 제외하고, 다시 게임 종료 체크

7. 게임이 종료될 때 까지(최대 15턴) 2~6을 반복하고, 출력 최대값 갱신

8. 모든 경우의 수에 대해 2~7을 반복

시간복잡도 
455 * (225 + 225) * 15 = 3백만
*/
