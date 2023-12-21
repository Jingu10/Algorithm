import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int J = sc.nextInt();
		
		int left = 1;
		int right = M;
		int dis = 0;
		
		for(int apple = 1; apple <= J; apple++) {
			int pos = sc.nextInt();
			
			if(pos < left) {
				int diff = left - pos;
				dis += diff;
				left -= diff;
				right -= diff;
			} else if(pos > right) {
				int diff = pos - right;
				dis += diff;
				right += diff;
				left += diff;
			} else {
				// 아무것도 하지 않는다.
			}
		}
		
		System.out.println(dis);
	}
}