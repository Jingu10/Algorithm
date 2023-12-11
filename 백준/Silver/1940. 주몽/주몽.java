import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] nums;
	public static void main(String[] args) throws IOException {
		init();
		if(M > 200000) {
			System.out.println(0);
			return;
		}
		solve();
	}
	
	
	private static void solve() {
		int left = 0; 
		int right = N - 1; 
		int cnt = 0;
		
		while(left < right) {
			int sum = nums[left] + nums[right];
			if(sum == M) {
				cnt++;
				left++;
				right--;
			} else if(sum > M) {
				right--;
			} else {
				left++;
			}
		}
		
		System.out.println(cnt);
	}


	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
	}
}

// 재료의 개수는 최대 15000
// M은 1~천만 but, 고유번호가 10만 이하이므로 M이 20만이 넘으면 고려할 필요 조차 없음.
// 투 포인터로 이 문제를 해결한다고 생각해보자.
// 1 2 3 4 5 7 을 조합해서 9를 만들어야 해.
// 1 7 을 시작점으로 잡으면, 두 개를 더하면 9를 만들기에 부족한 상황임.
// 부족하니까 그 합을 늘려야 돼. 그러면, 1은 버리고 2를 선택해. 그러면, 9가 된다.
// 7은 이미 썼으니 5를 택해야 한다. (오른쪽이 작아짐.) 이 상황에서 1은 절대로 선택될 수가 없다.
// 3 5 가 선택된 상황에서, 9를 만들기에 부족하므로 4 5 를 선택. 투포인터가 갈리기 때문에 종료.