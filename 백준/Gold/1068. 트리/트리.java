import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N; // 정점 갯수
	static int[] parents; // 정점들의 부모 노드
	static int cutNode; // 자를 노드의 번호
	static int root; // 루트 노드
	static int ans; // 노드를 자른 후 리프노드의 갯수
	
	static List<List<Integer>> tree;
	
	public static void main(String[] args) throws IOException {
		init();
		solve(); // 순회해보자
		System.out.println(ans);
	}
	
	
	private static void solve() {
		if(cutNode == root) return;
		
		dfs(root);
		
	}


	private static void dfs(int parent) {
		
		// 노드가 없어 -> 해당 노드가 리프노드임 
		if(tree.get(parent).isEmpty()) {
			ans++;
			return;
		}
		
		if(tree.get(parent).size() == 1) {
			if(tree.get(parent).get(0) == cutNode) {
				ans++;
				return;
			}
		}
		
		for(int child : tree.get(parent)) {
			if(child != cutNode) {
				dfs(child);
			}
		}
		
		
	}


	static void init() throws IOException {
		ans = 0;
		tree = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parents = new int[N];
		for(int i = 0; i < N; i++) {
			tree.add(new ArrayList<>());
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			parents[i] = Integer.parseInt(st.nextToken());
			if(parents[i] == -1) {
				root = i;
				continue;
			}
			
			int from = parents[i];
			int to = i; 
			
			tree.get(from).add(to);
		}
		
		
		cutNode = Integer.parseInt(br.readLine());
	}
}