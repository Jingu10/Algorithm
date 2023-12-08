import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int[] counting = new int[26];
		
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			int n = (int)ch - 97;
			counting[n]++;
		}
		
		for(int i = 0; i < 26; i++) {
			System.out.print(counting[i] + " ");
		}
		
	}
}