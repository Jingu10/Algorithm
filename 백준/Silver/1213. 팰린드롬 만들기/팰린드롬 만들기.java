import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int[] counting = new int[26];

		for (int i = 0; i < input.length(); i++) {
			int index = (int) input.charAt(i) - 65;
			counting[index]++;
		}

		// 불가능한지 체크
		int oddCnt = 0;
		for (int i = 0; i < 26; i++) {
			if (counting[i] % 2 == 1) {
				oddCnt++;
			}
		}

		// 홀수가 2개 이상이면 무조건 불가능. 문자열 길이가 짝수라면, 홀수가 1개여도 불가능임. (문자열 길이가 홀수일 때 홀수가 1개인건 가능)
		if (oddCnt >= 2 || (input.length() % 2 == 0 && oddCnt == 1)) {
			System.out.println("I'm Sorry Hansoo");
			return;
		}

		// 길이가 바뀌진 않으니까, 같은 길이로 char 배열을 만들어 놓는다
		char[] palindrome = new char[input.length()];
		int index = 0;
		// 팰린드롬을 만들어보자
		for (int i = 0; i < 26; i++) {
			if (counting[i] % 2 == 1) {
				palindrome[input.length() / 2] = (char) (i + 65);
				counting[i]--;
			}
			if (counting[i] > 0) {
				while (counting[i] != 0) {
					palindrome[index] = (char) (i + 65);
					palindrome[input.length() - 1 - index] = (char) (i + 65);
					counting[i] -= 2;
					index++;
				}
			}
		}

		for (char ch : palindrome) {
			System.out.print(ch);
		}

	}
}