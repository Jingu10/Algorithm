import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		input: while (true) {
			String word = br.readLine();
			if (word.equals("end")) {
				break;
			}
			
			boolean check1 = false; // 모음 하나 포함하면 true 로 변경
			int moum = 0; // 모음이 연속된 횟수. 3이 되면, input 반복문을 continue
			int jaum = 0;
			char pre = 'A'; // 이전 글자. A는 절대 나오지 않는 글자이므로 A로 초기화. 연속된 글자가 나오면 input 반복문을 continue
			
			for(int i = 0; i < word.length(); i++) {
				char ch = word.charAt(i);
				
				if(ch == pre) {
					if(ch == 'e' || ch=='o') {
						// 아무 동작도 하지 않아도 된다. 다음 검사로 넘어가라
					} else {
						// 다음 단어로 넘어가라
						sb.append('<').append(word).append("> is not acceptable.").append('\n');
						continue input;
					}
				}
				
				if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
					check1 = true;
					jaum = 0;
					moum++;
				} else {
					moum = 0;
					jaum++;
				}
				
				if(moum == 3 || jaum == 3) {
					sb.append('<').append(word).append("> is not acceptable.").append('\n');
					continue input;
				}
				
				pre = ch;
				
			}
			
			if(check1) {
				sb.append('<').append(word).append("> is acceptable.").append('\n');
				
			} else {
				sb.append('<').append(word).append("> is not acceptable.").append('\n');
			}
			
			
			
		}
		System.out.println(sb);
	}
}