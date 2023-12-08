import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[9];
		int[] real = new int[7];
		
		for(int i = 0; i < 9; i++) {
			
			arr[i] = sc.nextInt();
		}

		int sum = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sum = 0;
				if (i == j)
					continue;
				for (int k = 0; k < 9; k++) {
					if (k != i && k != j)
						sum += arr[k];
				}
				if (sum == 100) {
					int index = 0;
					for (int k = 0; k < 9; k++) {
						if (k != i && k != j)
							real[index++] = arr[k];
					}
					break;
				}
			}
		}

		Arrays.sort(real);
		for (int num : real) {
			System.out.println(num);
		}
	}

}
