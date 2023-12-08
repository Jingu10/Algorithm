import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M; // 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 출력
	static int[] res;
	static boolean[] visited; // 뽑았던 수를 또 뽑으면 안되기 때문에 visited 배열 설정
	
	public static void main(String[] args) throws IOException {
		init();
		perm(0);
	}
	
	
	private static void perm(int depth) {
		// M개를 모두 뽑은 경우 출력
		if(depth == M) {
			for(int num : res) {
				System.out.print(num + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) { // 아직안뽑았어
				visited[i] = true;
				res[depth] = i;
				perm(depth + 1);
				visited[i] = false;
			}
		}
	}


	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 1]; 
		res = new int[M]; // M개 뽑은 순열
	}
}