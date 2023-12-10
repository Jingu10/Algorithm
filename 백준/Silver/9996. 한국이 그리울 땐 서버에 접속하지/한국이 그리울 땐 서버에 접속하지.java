import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 1~100
		String pattern = br.readLine();
		StringTokenizer st = new StringTokenizer(pattern, "*");
		String front = st.nextToken();
		String rear = st.nextToken();
		
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			
			
			if(isCorrect(front, rear, input)) {
				sb.append("DA").append('\n');
			} else {
				sb.append("NE").append('\n');
			}
			
		}
		
		System.out.println(sb);
		
	}

	private static boolean isCorrect(String front, String rear, String input) {
		if(front.length() + rear.length() > input.length()) {
			return false;
		}
		
		try {
			String frontInput = input.substring(0,  front.length());
			String rearInput = input.substring(input.length() - rear.length(), input.length());
			if(frontInput.equals(front) && rearInput.equals(rear)) {
				return true;
			}
			
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}