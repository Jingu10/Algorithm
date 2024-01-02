import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] linkInfo;
	static int N, M, H;
	static int ans = 4;
	
	public static void main(String[] args) throws IOException {
		init();
		dfs(1, 0); // 세로, 사다리 추가횟수
		if(ans == 4) ans = -1;
		System.out.println(ans);
	}
	
	
	private static void dfs(int level, int cnt) {
		if(cnt > 3 || cnt > ans) return;
		
		// 사다리 3개를 놓아서 통과되는 케이스가 사다리 1개를 놓아서 통과되는 케이스보다 먼저 나올 수 있음!!
		// 즉, 모든 경우의 수를 봐야만 함
		if(check()) {
			if(cnt < ans) ans = cnt;
		}
		
		// 현재의 레벨보다 위에 있는 애들은 볼 필요가 없지 (상상해보기)
		for(int i = level; i <= H; i++) {
			for(int j = 1; j < N; j++) { // N의 오른쪽으로는 어차피 사다리 연결X
				// 나한테 들어오는 놈이 있으면 안되고
				// 나로부터 뻗어나가는 게 있으면 안되고
				// 내가 갈 위치에 이미 있는 것도 안돼
				if(linkInfo[i][j] || linkInfo[i][j-1] || linkInfo[i][j+1]){
					continue;
				}
				linkInfo[i][j] = true;
				dfs(i, cnt+1);
				linkInfo[i][j] = false;
				
			}
		}
		
	}


	private static boolean check() {
		
		//세로선
		for(int 세로선 = 1; 세로선 <= N; 세로선++) {
			int start = 세로선;
			//가로선 
			for(int 가로선 = 1; 가로선 <= H; 가로선++) {
				if(linkInfo[가로선][start]) {
					start++;
				} else if(linkInfo[가로선][start-1]) {
					start--;
				}
			}
			if(start != 세로선) {
				// 처음 시작위치와 달라졌다면
				return false;
			}
		}
		
		
		return true;
	}


	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		linkInfo = new boolean[H+1][N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			linkInfo[from][to] = true; 
		}
	}
}