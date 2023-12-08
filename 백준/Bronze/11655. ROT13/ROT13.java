import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		
		String ans = "";
		for(int i = 0; i < line.length(); i++) {
			char ch = line.charAt(i);
			if(ch >= 'A' && ch <= 'Z') {
				int tmp = ch + 13;
				if(tmp > 90) tmp -= 26;
				ans += (char)tmp;
				
			} else if (ch >= 'a' && ch <= 'z') {
				int tmp = ch + 13;
				if(tmp > 122) tmp -= 26;
				ans += (char)tmp;
				
			} else {
				ans += ch;
			}
		}
		System.out.println(ans);
		
	}
}