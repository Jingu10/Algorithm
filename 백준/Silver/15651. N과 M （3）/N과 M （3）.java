import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M; // 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 출력
	static int[] res;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		init();
		perm(0);
		System.out.println(sb);
	}
	
	
	private static void perm(int depth) {
		// M개를 모두 뽑은 경우 출력
		if(depth == M) {
			for(int num : res) {
				sb.append(num + " ");
			}
			sb.append('\n');
			return;
		}
		
		for(int i = 1; i <= N; i++) {
				res[depth] = i;
				perm(depth + 1);
			
		}
	}


	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		res = new int[M]; // M개 뽑은 순열
	}
}