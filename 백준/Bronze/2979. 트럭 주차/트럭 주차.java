import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] price = new int[4];
		for(int i =1; i <= 3; i++) {
			price[i] = sc.nextInt();
		}
		
		int[] time = new int[101]; /// 1부터 100까지 (도착 시간과 떠난 시간, 오자마자 떠나는 경우는 없음)
		
		for(int i = 0; i < 3; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			for(int j = start; j <= end; j++) {
				time[j]++;
			}
		}
		
		int sum = 0;
		
		for(int i = 1; i <= 100; i++) {
			if(time[i] >= time[i-1]) {
				sum += time[i-1] * price[time[i-1]];
			} else {
				sum += time[i] * price[time[i]];
			}
		}
		
		System.out.println(sum);
		
		
		
	}
}