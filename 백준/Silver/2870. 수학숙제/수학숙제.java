import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<BigInteger> list = new ArrayList<>();
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			String numString = "";
			for(int j = 0; j < input.length(); j++) {
				char ch = input.charAt(j);
				if(ch >= 'a' && ch <= 'z') {
					if(numString.equals("")) {
						continue;
					} else {
						list.add(new BigInteger(numString));
						numString = "";
					}
				} else {
					numString += ch; // 숫자 붙이기
					if(j == input.length() - 1) {
						list.add(new BigInteger(numString));
					}
				}
			}
			
		}
		
		Collections.sort(list, Collections.reverseOrder());
		for(int i = list.size() - 1; i >= 0; i--) {
			System.out.println(list.get(i));
		}
	}
	
}

// 엄청 큰 숫자 문자열 -> BigInteger를 써보는 방법도 생각은 해 봐야 한다
// new BigInteger(문자열)