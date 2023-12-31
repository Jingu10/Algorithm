import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		init();
		int ans = 0; 
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 'L') {
					int maxDis = 0;
					boolean[][] visited = new boolean[N][M];
					visited[i][j] = true;
					Queue <int[]> queue = new LinkedList<>();
					queue.add(new int[] {i, j, 0});
					
					while(!queue.isEmpty()) {
						int[] tmp = queue.poll();
						int row = tmp[0];
						int col = tmp[1];
						int dis = tmp[2];
						for(int d = 0; d < 4; d++) {
							int nrow = row + dr[d];
							int ncol = col + dc[d];
							if(nrow >= 0 && ncol >= 0 && nrow < N && ncol < M && map[nrow][ncol] == 'L' && !visited[nrow][ncol]) {
								visited[nrow][ncol] = true;
								queue.add(new int[] {nrow, ncol, dis + 1});
								if(dis+1 > maxDis) maxDis = dis + 1;
							}
						}
					}
					
					if(maxDis > ans) ans = maxDis;
					
				}
			}
		}
		System.out.println(ans);
	}
	
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				map [i][j] = line.charAt(j);
			}
		}
	}
}