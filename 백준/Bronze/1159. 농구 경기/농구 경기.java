import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] counting = new int[26];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			char ch = str.charAt(0);
			int n = ch - 97;
			counting[n]++;
		}
		
		boolean flag = false; // 선발 가능하면 true
		for(int i = 0; i < 26; i++) {
			if(counting[i] >= 5) {
				System.out.print((char)(i+97));
				flag = true;
			}
		}
		if(!flag) {
			System.out.println("PREDAJA");
		}
	}
}