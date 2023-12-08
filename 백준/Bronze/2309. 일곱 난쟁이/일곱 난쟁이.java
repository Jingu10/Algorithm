import java.util.Arrays;
import java.util.Scanner;

/* 9C7이 가능하면 9C2도 가능하다. 두 명을 뽑아서, 전체합 - 두명 합 == 100인지를 검사 */


public class Main {
	static int[] nums = new int[9];
	static int sumAll = 0;
	static int[] selected = new int[2]; 
	static boolean[] visited = new boolean[9]; // 뽑 or 안뽑
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		nums = new int[9];
		for(int i = 0; i < 9; i++) {
			nums[i] = sc.nextInt();
			sumAll += nums[i];
		}
		Arrays.sort(nums);
		comb(0);
	}

	private static void comb(int depth) {
		if(depth == 2) { // 2명 뽑은 케이스 
			if(sumAll - selected[0] - selected[1] == 100) {
				for(int i = 0; i < 9; i++) {
					if(nums[i] == selected[0] || nums[i] == selected[1]) continue;
					System.out.println(nums[i]);
				}
				System.exit(0);
			}
			return;
		}
		for(int i = 0; i < 9; i++) {
			if(!visited[i]) {
				visited[i] = true;
				selected[depth] = nums[i];
				comb(depth + 1);
				visited[i] = false;
			}
		}
		
	}
}