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