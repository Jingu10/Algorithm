import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static class Line {
		public int from;
		public int to;
		
		public Line(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	
	static int N, ans;
	static Line[] lines;
	
	public static void main(String[] args) throws IOException {
		init();
		solve();
		System.out.println(ans);
	}
	
	
	private static void solve() {
		// 첫번째 케이스는 미리 계산
		int from = lines[0].from;
		int to = lines[0].to;
		ans = to - from;
		
		
		for(int i = 1; i < N; i++) {
			if(lines[i].from >= to) {
				ans += lines[i].to - lines[i].from;
				from = lines[i].from;
				to = lines[i].to;
			} else if(lines[i].from < to && lines[i].to > to) {
				ans += lines[i].to - to;
				from = lines[i].from;
				to = lines[i].to;
			}
			
		}
	}


	static void init() throws IOException {
		ans = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		lines = new Line[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			lines[i] = new Line(from, to);
		}
		
		Arrays.sort(lines, new Comparator<Line>() {

			@Override
			public int compare(Line o1, Line o2) {
				if(o1.from == o2.from) {
					return o1.to - o2.to;
				}
				
				return o1.from - o2.from;
			}
			
		});
	}
}

/*
 * [선긋기]
 * 
 * 선이 놓여질 수 있는 경우를 모두 생각해보자
 * 
 * 일단 정렬을 했다. from 오름차순, from이 같은 경우에만 to 오름차순
 * 
 * 
 * 1. (from, to 일치)이전 것과 완전히 겹치는 경우 -> 계산하지 않는다. 2. 이전 것보다 from은 큰데, to는 작은 경우
 * -> 안에 들어가니까 계산 X
 * 
 * 이전의 from to 값을 가지고 있어야 하고,
 * 
 * 현재의 from 이 from 이상이면서 현재의 to가 to 이하라면 1,2 케이스에 해당
 * 
 * 3. 일부만 겹치는 경우 -> 계산하고 to값 갱신
 * 
 * 4. 아예 겹치지 않는 경우 -> 계산하고 from, to 값 갱신
 * 
 */