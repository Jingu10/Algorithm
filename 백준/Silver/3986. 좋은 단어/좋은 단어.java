import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static int N;
	static String[] words;
	public static void main(String[] args) throws IOException {
		init();
		solve();
	}
	
	
	private static void solve() {
		int good = 0;
		for(int i = 0; i < N; i++) {
			String word = words[i];
			if(word.length() % 2 == 0) {
				Stack<Character> st = new Stack<>();
				for(int j = 0; j < word.length(); j++) {
					char ch = word.charAt(j);
					if(st.isEmpty()) {
						st.push(ch);
					} else {
						if(st.peek() == ch) {
							st.pop();
						} else {
							st.push(ch);
						}
					}
				}
				if(st.isEmpty()) {
					good++;
				}
				
			}
		}
		
		System.out.println(good);
		
	}


	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		words = new String[N];
		for(int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}
	}
}

/*
 * N(단어의 수) 1~100
 * 단어길이 2~10만
 * 모든 단어 길이 합은 100만을 넘지 않는다.
 * 
 * 1) 단어의 길이가 홀수면, 좋은 단어가 아니다.
 * 2) 단어의 길이가 짝수더라도, A와 B가 홀수개라면 좋은 단어가 아니다. 
 *    A, B 갯수를 세는 것보다 그냥 스택에 넣어서 판별하는게 낫다고 판단. 효율성 차이가 크지 않지만, 가독성 면에서 좋아서.
 * 3) 단어가 교차된다는 것을 어떻게 알까? 
 * -> A가 나왔어. 그러면, 다음 A가 나오기 전의 B는 무조건 짝수개 여야만 함. 
 * -> B가 나와도 마찬가지.
 * -> 스택을 활용해보자!! 스택에 순서대로 넣어서, 둘이 같은 단어일 때 없어지게 만들자. 
 * -> 만약, 스택이 비어있지 않게 되면 그건 좋은 단어가 아니다!!
 * -> 스택이 비면 좋은 단어.
 * -> 모든 단어 길이 합이 백만을 넘지 않는 것도 스택의 공간 할당에 대한 조건이다.
 * 
 */