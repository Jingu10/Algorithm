import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<String> list = new ArrayList<>();
		
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
						numString = removeZero(numString);
						list.add(numString);
						numString = "";
					}
				} else {
					numString += ch; // 숫자 붙이기
					if(j == input.length() - 1) {
						numString = removeZero(numString);
						list.add(numString);
					}
				}
			}
			
		}
		
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if(o1.length() == o2.length()) {
					// 사전 순 비교할 때는 compareTo를 활용한다.
					return o1.compareTo(o2);
				}
				
				return o1.length() - o2.length();
			}
			
		});
		
		for(String n : list) {
			System.out.println(n);
		}
	}

	private static String removeZero(String numString) {
		int notZeroIndex = -1;
		for(int i = 0; i < numString.length(); i++) {
			if(numString.charAt(i) != '0') {
				notZeroIndex = i;
				break;
			}
		}
		
		if(notZeroIndex == -1) {
			// 모든 숫자가 0인 경우
			return "0";
		} else {
			String res = "";
			for(int i = notZeroIndex; i < numString.length(); i++) {
				res += numString.charAt(i);
			}
			return res;
		}
		
	}

	
}