import java.util.Scanner;

public class Main {
	static long C;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long A = sc.nextLong();
		long B = sc.nextLong();
		C = sc.nextLong();
		
		// (A * B) % C = ((A % C) * (B % C)) % C
		
		System.out.println(pow(A, B));
		
	}
	private static long pow(long a, long b) {
		
		if(b == 0) return 1; 
		
		long tmp = pow(a, b / 2);
		
		long res = ((tmp % C) * (tmp % C)) % C;
		
		if(b % 2 == 0) {
			return res;
		} else {
			return ((res % C) * (a % C)) % C;
		}
		
		
		
		
		
	}
}