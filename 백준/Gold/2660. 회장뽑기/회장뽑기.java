import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N; // 사람 수
	static List<List<Integer>> graph;
	static int[] scoreArr;

	public static void main(String[] args) throws IOException {
		init();
		solve();
		printAns();
	}

	private static void printAns() {
		int score = Integer.MAX_VALUE;
		int cnt = 0;
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (scoreArr[i] < score) {
				score = scoreArr[i];
			}
		}
		for (int i = 1; i <= N; i++) {
			if (scoreArr[i] == score) {
				cnt++;
				list.add(i);
			}
		}

		System.out.println(score + " " + cnt);
		for (int p : list) {
			System.out.print(p + " ");
		}
	}

	private static void solve() {
		// 모든 사람을 시작점으로 bfs를 총 N번 돈다.
		for (int i = 1; i <= N; i++) {
			int maxDis = 0;

			boolean[] visited = new boolean[N + 1];
			Queue<int[]> queue = new LinkedList<>(); // 0번째인덱스는 사람번호, 1번째인덱스는 거리
			visited[i] = true;
			queue.add(new int[] { i, 0 });

			while (!queue.isEmpty()) {
				int[] tmp = queue.poll();
				int num = tmp[0];
				int dis = tmp[1];

				// 그 번호 사람과 인접한 모든 사람 조회
				for (int adj : graph.get(num)) {
					if (!visited[adj]) {
						visited[adj] = true;
						queue.add(new int[] { adj, dis + 1 });

						if (dis + 1 > maxDis) {
							maxDis = dis + 1;
						}

					}
				}
			}//while(BFS)
			
			scoreArr[i] = maxDis;
		}//for
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		scoreArr = new int[N + 1];
		graph = new ArrayList<>(); // 해당 리스트는 일단 1번부터 N번까지의 요소를 가져야 한다
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>()); // 0번부터 50번까지 요소 추가
		}

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if (from == -1) {
				break;
			}

			// 무방향 그래프이므로 양쪽 다 추가해 줄 것
			graph.get(from).add(to);
			graph.get(to).add(from);
		}

	}// init
}// 메인클래스

/*
 * 백준 2660 회장뽑기
 * 
 * [문제분석] 각 회원은 다른 회원들과 가까운 정도에 따라 점수를 받는다.
 * 
 * 어떤 회원의 점수가 1점: 다른 모든 사람이 이 사람과 직접 연결 돼 있음
 * 
 * 
 * 2점: 모든 사람이 이 사람과 두 번 안에 만남
 * 
 * 3점: 모든 사람이 이 사람이 세 번 안에 만남
 * 
 * 가장 점수가 낮은 사람이 회장이 된다
 * 
 * 
 * 
 * [입력] (1) 회원의 수 (50이하) (2) 두 개의 숫자 // 두 회원이 친구라는 뜻 (3) -1 -1 // 간선 입력이 끝났다는 뜻
 * 
 * [출력] (1) 회장후보점수 후보수 출력 (2) 회장 후보를 오름차순으로 출력
 * 
 * [전략] 모든 사람이 일단 연결이 돼 있긴 함 bfs를 돌리는데, dis를 갱신 모든 순회가 끝났을 때 dis값이 이 사람의 점수 그리고
 * 이 dis값을 크기가 회원수인 배열에 저장을 해 놓고 min값을 구하고, 그 min값과 같은 애들을 출력 하면 된다
 */