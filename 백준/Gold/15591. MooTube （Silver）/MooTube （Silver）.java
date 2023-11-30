import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, Q;
	static int v, k;
	static boolean[] visited;
	static List<List<int[]>> graph;
	static BufferedReader br;
	static StringTokenizer st;

	
	public static void main(String[] args) throws IOException {
		init();
		for(int i = 0; i < Q; i++) {
			input();
			print();
		}
	}
	
	
	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < N - 1; i ++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			graph.get(from).add(new int[] {to, r});
			graph.get(to).add(new int[] {from, r});
		}
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
	}
	
	static void print() {
		visited = new boolean[N+1];
		visited[v] = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(v);
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			for(int[] adj : graph.get(curr)) {
				
				if(!visited[adj[0]] && adj[1] >= k) {
					cnt++;
					visited[adj[0]] = true;
					queue.add(adj[0]);
				}
			}
		}
		
		System.out.println(cnt);
		
	}
}

/*
 * 
 * "N-1개의 동영상 쌍을 골라서 어떤 동영상에서 다른 동영상으로 가는 경로가 반드시 하나 존재하도록 했다."
 * -> N-1개의 간선을 통해 N개의 정점이 모두 연결 된다 -> 스패닝 트리 구조구나~~ 를 파악해야 한다!!
 * 임의의 두 정점 간의 USADO를 결정하기 위해 두 가지 상황을 생각할 수 있다.
 * 1) 한 번에 갈 수 있는 경우 -> 간선 가중치가 K이상 이라면 갈 수 있으므로 카운트를 하나 늘리고, bfs 큐에 넣으면 된다
 * 2) 어떤 노드를 거쳐야만 갈 수 있는 경우 -> 간선 중 최소의 가중치가 모든 경로의 가중치가 된다고 생각하면 된다. but 우리는 인접한 것만 고려하면 된다.
 * -> 일단 내가 내 인접한 노드에 갈 수 있다면 (가중치가 K이상) 카운트를 하나 늘리고 bfs 큐에 넣으면 된다. 그러고, 그 다음 인접한 노드로 가는 가중치가 K 미만이라면 
 * 그 다음으로의 가중치도 K 미만으로 정해지기 때문에 bfs큐에 넣지 않는다. (더 고려할 필요가 없다.)
 * 
 * 주의) 스패닝트리이기 때문에 1, 2가 같이 있는 구성은 나올 수가 없음!! 계속 두가지가 있는 상황을 고려해서 시간을 썼다.
 * 
 */
