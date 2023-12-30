import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Quest{
		public int STR;
		public int INT;
		public int PNT;
		
		public Quest(int STR, int INT, int PNT) {
			this.STR = STR;
			this.INT = INT;
			this.PNT = PNT;
		}
		
	}
	
	
	static int N; // 퀘스트 갯수
	static int maxClearedQuest;
	static boolean[][] DP; // STR, INT 스탯에 도달 가능한지
	static int[][] LP; // leftStat // (STR, INT 상태에서 퀘스트를 깨고 얻은 포인트) - (현재 스탯 합 - 2)
	static Quest[] questArr;
	
	public static void main(String[] args) throws IOException {
		init();
		solve();
		System.out.println(maxClearedQuest);
	}
	
	
	private static void solve() {
		for(int STR = 1; STR <= 1000; STR++) {
			for(int INT = 1; INT <= 1000; INT++) {
				// 현재 스탯에 도달할 수 있는지 체크. 도달 못하면 다음 스탯 체크
				if(canReach(STR, INT)) {
					DP[STR][INT] = true;
					int cnt = 0; // 깰 수 있는 퀘스트 수
					int PNT = 0; // 깰 수 있는 퀘스트 다 깨고 얻은 포인트
					for(int q = 0; q < N; q++) {
						// 퀘스트를 깰 수 있으면
						if(canClear(STR, INT, questArr[q])) {
							cnt++;
							PNT += questArr[q].PNT;
						}
					}
					maxClearedQuest = Math.max(maxClearedQuest, cnt);
					LP[STR][INT] = PNT - (STR + INT - 2);
				}
			}
		}
	}


	private static boolean canClear(int STR, int INT, Quest quest) {
		if(STR >= quest.STR || INT >= quest.INT) return true;
		
		return false;
	}


	private static boolean canReach(int STR, int INT) {
		if(STR == 1 && INT == 1) return true;
		if(DP[STR-1][INT] && LP[STR-1][INT] > 0) return true;
		if(DP[STR][INT-1] && LP[STR][INT-1] > 0) return true;
		
		return false;
	}


	static void init() throws IOException {
		maxClearedQuest = 0;
		DP = new boolean[1001][1001];
		DP[1][1] = true;
		LP = new int[1001][1001];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		questArr = new Quest[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int STR = Integer.parseInt(st.nextToken());
			int INT = Integer.parseInt(st.nextToken());
			int PNT = Integer.parseInt(st.nextToken());
			questArr[i] = new Quest(STR, INT, PNT);
		}
	}
}