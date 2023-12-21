import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] paper;
	static int blue = 0;
	static int white = 0;
	public static void main(String[] args) throws IOException {
		init();
		solve(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}
	
	
	private static void solve(int row, int col, int size) {
		if(size == 1) {
			if(paper[row][col] == 1) {
				 blue++;
			} else {
				white++;
			}
			return;
		}
		
		int first = paper[row][col];
		
		for(int i = row; i < row + size; i++) {
			for(int j = col; j < col + size; j++) {
				if(paper[i][j] != first) {
					solve(row, col, size / 2);
					solve(row, col + size / 2, size / 2);
					solve(row + size / 2, col, size / 2);
					solve(row + size / 2, col + size / 2, size / 2);
					return;
				}
			}
		}
		
		if(first == 1) {
			blue++;
		} else {
			white++;
		}
		return;
	}


	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}