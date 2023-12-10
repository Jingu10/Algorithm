import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static Map<String, Integer> map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int n;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int test = 0; test < T; test++) {
			init();
			solve();
		}
		System.out.println(sb);

	}

	private static void solve() {
		if(n == 0) {
			sb.append(0).append('\n');
			return;
		} 
		
		int cnt = 1; 
		
		for(String key : map.keySet()) {
			cnt *= map.get(key) + 1; // 선택하지 않음, 하나만 선택하는 부분집합
		}
		cnt--; // 아무것도 선택하지 않는 경우 하나 제외
		sb.append(cnt).append('\n');
	}

	static void init() throws IOException {
		map = new HashMap<>();
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			st.nextToken(); // 이름은 필요없으니, 버리기
			String kind = st.nextToken(); // 의상 종류는 필요하다
			if(map.isEmpty() || !map.containsKey(kind)) {
				map.put(kind, 1);
			} else {
				map.put(kind, map.get(kind) + 1);
			}
		}
	}
}

// face 3개, body 2개, foot 1개 라고 가정해보자
// face에서 선택할 수 있는 경우는 총 4가지 (0, a, b, c)
// 이런 식으로 각 그룹에서 4 * 3 * 2 총 24가지 경우의 수가 가능
// but 모든 그룹에서 아무것도 선택하지 않는 경우 1개를 제외해야 한다.