import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static char[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		init();
		solve(0, 0, N);
		System.out.println(sb);
		
	}

	private static void solve(int row, int col, int size) {
		if(size == 1) {
			sb.append(arr[row][col]);
			return;
		}
		
		char first = arr[row][col];
		
		for(int i = row; i < row + size; i++) {
			for(int j = col; j < col + size; j++) {
				if(arr[i][j] != first) {
					sb.append('(');
					solve(row, col, size / 2);
					solve(row, col + size / 2, size / 2);
					solve(row + size / 2, col, size / 2);
					solve(row + size / 2, col + size / 2, size / 2);
					sb.append(')');
					return;
				}
			}
		}
		
		sb.append(first);
		return;
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < N; j++) {
				arr[i][j] = line.charAt(j);
			}
		}
	}
}