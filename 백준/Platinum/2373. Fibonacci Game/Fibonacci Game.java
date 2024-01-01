import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] fibo = new int[31];
		fibo[1] = 1;
		fibo[2] = 1;
		for(int i = 3; i < 31; i++) {
			fibo[i] = fibo[i-1] + fibo[i-2];
		}
		
		// 규칙찾기
		// N이 피보나치 수라면 선공이 무조건 필패한다.
		// N이 피보나치 수가 아니라면, N보다 작은 피보나치 수 중 가장 큰 피보나치 수를 N에서 뺀 값의 구슬로 게임을 하는 것과 같다.
		// ex) 구슬이 30개인 게임과 구슬이 9개(30-21)인 게임의 답은 같다.
		// 즉, N보다 작은 피보나치 수 중 가장 큰 피보나치 수를 N에서 빼는 작업을 반복한다.
		// 반복과정에서 그 값이 피보나치 수가 되면, 그 수가 답이 된다.
		
		// 피보나치인지 확인하기
		for(int i = 1; i < 31; i++) {
			if(N == fibo[i]) {
				System.out.println(-1);
				return;
			}
		}
		
		
		// 피보나치가 아님이 보장됨
		while(true) {
			for(int i = 30; i > 0; i--) {
				if(fibo[i] < N) {
					N -= fibo[i];
					break;
				}
			}
			
			for(int i = 1; i < 31; i++) {
				if(N == fibo[i]) {
					System.out.println(N);
					return;
				}
			}
			
			
		}
		
	}
}