import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		Stack <int[]> st = new Stack<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] NGE = new int[N];
		Arrays.fill(NGE, -1);
		StringTokenizer stk = new StringTokenizer(br.readLine());
		st.push(new int[] {Integer.parseInt(stk.nextToken()), 0});
		for(int i = 1; i < N; i++) {
			int num = Integer.parseInt(stk.nextToken());
			while(!st.isEmpty() && st.peek()[0] < num) {
				int idx = st.pop()[1];
				NGE[idx] = num;
			}
			
			st.push(new int[] {num, i});
		}
		
		StringBuilder sb = new StringBuilder();
		for(int n : NGE) {
			sb.append(n + " ");
		}
		System.out.println(sb);
	}
}