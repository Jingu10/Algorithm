import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = new String(sc.next());
		
		for(int i = 0; i < str.length() / 2; i++) {
			char front = str.charAt(i);
			char rear = str.charAt(str.length() - 1 - i);
			if(front != rear) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(1);
	}
}