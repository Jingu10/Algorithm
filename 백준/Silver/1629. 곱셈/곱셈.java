import java.util.Scanner;

public class Main {
	static int C;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		C = sc.nextInt();
		
		System.out.println(pow(A,B));
	}
	public static long pow(int a, int b) {
		if(b == 0) return 1;
		
		long tmp = pow(a, b/2);
		
		if(b % 2 == 0) return tmp * tmp % C;
		
		return (tmp * tmp % C) * a % C;
		
	}
}