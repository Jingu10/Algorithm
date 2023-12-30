import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int priceB = Integer.parseInt(st.nextToken());
		int priceC = Integer.parseInt(st.nextToken());

		int[] A = new int[N + 1]; // 번호에 해당하는 공장에서 라면을 몇 개 사야 하는지
		st = new StringTokenizer(br.readLine());
		
		long cnt = 0;
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			cnt += (long)A[i];
		}
		
		if(priceB <= priceC) { // 무조건 한 set씩만 사는 게 이득. B원으로만 구입
			System.out.println((long)priceB * cnt);
			return;
		}

		int[] B = new int[N + 1]; // 번호에 해당하는 공장에서 B원으로 라면을 몇 개 샀는지
		int[] C = new int[N + 1]; // 번호에 해당하는 공장에서 C원으로 라면을 몇 개 샀는지

		// 핵심 아이디어! 내가 B원으로 라면을 한 개 샀다면,
		// 다음 공장에서 C원으로 살 수 있는 쿠폰을 한 장
		// 다다음 공장에서 C원으로 살 수 있는 쿠폰을 한 장 얻은 것이다

		int cn = 0; // coupon next
		int cnn = 0; // coupon next next

		// 모든 공장을 하나씩 순회해보자
		for (int i = 1; i <= N; i++) {
			int ramen = A[i]; // 현재 내가 사야 할 라면의 갯수
			int coupon = cn; // 내가 지금 쓸 수 있는 쿠폰의 갯수

			if (coupon >= ramen) { // 쿠폰이 라면 보다 많으면 쿠폰으로만 구입하면 됨
				C[i] = ramen;
			} else { // 쿠폰이 라면 갯수보다 적으면.. 일단 쿠폰을 다 쓰고 남은건 B원으로 구입하자
				C[i] = coupon;
				B[i] = ramen - coupon;
			}
			// cn과 cnn을 결정해보자
			// 일단 cnn에 영향을 줄 수 있는 건 내가 지금 B를 몇 개 썻느냐 밖에 없다 (잘 생각해보면 자명하다)
			// cnn을 확정 짓는건 쉽지만, cnn은 cn에 영향을 주는 변수이므로 cn을 먼저 갱신해야 된다

			// cn을 결정하는 것만 해결하면 이 문제를 풀 수 있다
			// 여기서 cn은 다음 칸에서 쓸 수 있는 쿠폰의 갯수가 된다 (헷갈리지 말 것!)

			if (A[i] < cnn) {
				cn = A[i]; // 만약 cnn이 4였는데 현재 칸이 2라면.. cnn이 4라는 전제 자체가 틀린 거임 (3셋으로 묶을 수 있는 경우가 2개 밖에 없었던 것!)
			} else {
				cn = cnn + B[i]; // 이건 그렇지 않을까? 하고 생각한 것
			}

			cnn = B[i]; 

		}

		long ans = 0;
		long cntB = 0;
		long cntC = 0;
		for (int i = 1; i <= N; i++) {
			cntB += (long)B[i];
			cntC += (long)C[i];
		}
		
		ans = (cntB * priceB) + (cntC * priceC);
		
		System.out.println(ans);
	}
}