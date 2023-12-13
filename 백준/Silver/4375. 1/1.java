import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			int num = sc.nextInt();
			int mod = 0;
			int digit = 1; // 자릿수
			
			while(true) {
				// 1 11 111 1111... 이 순서대로 계속 늘려가면서, 이게 n으로 떨어지는지 계산하면됨
				
				//만약, mod = mod * 10 + 1 이면, 
				// 1, 11, 111, 1111, 이런 순으로 증가 하게 된다.
				mod = ((mod * 10) % num + 1) % num;
				
				if(mod == 0) {
					System.out.println(digit);
					break;
				}
				
				// 이게 시간초과가 걸리는 이유는, mod가 엄청나게 커질 수 있기 때문이다.! 
				// 표현 범위를 벗어나서 무한 루프에 빠지게 됨.
				// mod % num == 0 을 검사할거면,
				// ((mod*10)%num + 1%num ) % num == 0인지를 검사해도 똑같다 라는 것..!
				
				
				digit++;
				
			}
		}
	}
}

/*
 * 모듈러 연산 이제는 정복하자
 * (a + b) % n = ( a%n + b%n ) % n
 * (a * b) % n = ( a%n *  b%n ) % n
 * 
 * 
*/