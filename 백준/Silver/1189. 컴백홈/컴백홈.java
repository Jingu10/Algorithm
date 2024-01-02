import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, K;
	static char[][] map;
	static int cnt = 0;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		init();
		solve();
		System.out.println(cnt);
	}
	
	
	private static void solve() {
		dfs(R-1, 0, 1);
	}


	private static void dfs(int row, int col, int dis) {
		if(dis > K) return;
		
		if(row == 0 && col == C - 1) {
			if(dis == K)
				cnt++;
			return;
		}
		
		
		for(int d = 0; d < 4; d++) {
			int nrow = row + dr[d];
			int ncol = col + dc[d];
			if(nrow >= 0 && ncol >= 0 && nrow < R && ncol < C && !visited[nrow][ncol] && map[nrow][ncol] == '.') {
				visited[nrow][ncol] = true;
				dfs(nrow, ncol, dis+1);
				visited[nrow][ncol] = false;
			}
		}
		
		
		
	}


	static void init() throws IOException {
		cnt = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		visited[R-1][0] = true;
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
	}
}