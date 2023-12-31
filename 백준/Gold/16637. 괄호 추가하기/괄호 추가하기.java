import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 누적합 관점에서 생각하기 분할정복!!
// 차근차근 고민하기. 
// 브루트포스 + 재귀

public class Main {
	static int N;
	static int ans;
	static List<Integer> nums;
	static List<Character> opList;
	
	
	public static void main(String[] args) throws IOException {
		init();
		solve();
		System.out.println(ans);
	}
	
	
	
	
	private static void solve() {
		
		
		dfs(0, nums.get(0));
		
		
	}



	private static void dfs(int idx, Integer res) {
		if(idx == opList.size()) {
			ans = Math.max(ans, res);
			return;
		}
		
		dfs(idx + 1, cal(opList.get(idx), res, nums.get(idx + 1)));
		
		if(idx + 2 <= opList.size()) {
			int tmp = cal(opList.get(idx+1), nums.get(idx + 1), nums.get(idx + 2));
			dfs(idx + 2, cal(opList.get(idx), res, tmp));
		}
		
	}




	private static int cal(char op, int n1, int n2) {
	int result = 0;
	switch(op) {
	
	case '+':
		result = n1 + n2;
		break;
	case '-':
		result = n1 - n2;
		break;
	case '*':
		result = n1 * n2;
		break;
	}
	return result;
	}

	




	static void init() throws IOException {
		ans = Integer.MIN_VALUE;
		nums = new ArrayList<>();
		opList = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		
		for(int i = 0; i < N; i++) {
			char ch = input.charAt(i);
			if(i % 2 == 0) {
				nums.add(ch - '0');
			} else {
				opList.add(ch);
			}
		}
		
	}
}