import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[] nums;
	static int[] pSum;
	public static void main(String[] args) throws IOException {
		init();
		solve();
	}
	
	
	private static void solve() {
		int max = pSum[K-1];
		
		for(int i = K; i < N; i++) {
			int tmp = pSum[i] - pSum[i-K];
			if(tmp > max) {
				max = tmp;
			}
		}
		
		System.out.println(max);
	}


	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nums = new int[N];
		pSum = new int[N];
		
		
		st = new StringTokenizer(br.readLine());
		
		nums[0] = Integer.parseInt(st.nextToken());
		pSum[0] = nums[0];		
		
		for(int i = 1; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			pSum[i] = pSum[i-1] + nums[i];
		}
		
	}
}

// N은 최대 10만 이고, K도 최대 10만이기 때문에 O(N^2) 시간복잡도가 나와서 무식한 계산은 X
// 1~10일 까지의 온도를 입력 받았어. 연속적인 5일 간의 온도 합 중 최대를 구할 거야.
// 그러면, 일단 누적합을 구한 다음에 6~10일 온도 합은 (10일까지의 온도합) - (5일까지의 온도합)으로 계산하면 된다.