import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static Hero hero;
	static char[][] grid;
	static Item[][] itemMap;
	static Monster[][] monsterMap;
	static String command;
	static int T; // 턴
	static int currRow;
	static int currCol;

	static class Hero {
		int level = 1;
		int exp = 0;
		int maxHP = 20;
		int curHP = 20;
		int SATT = 2; // 스탯 공격력
		int SDEF = 2; // 스탯 방어력

		int WATT = 0; // 무기 공격력
		int ADEF = 0; // 방어구 방어력

		int startRow; // 시작위치
		int startCol;
		int currRow;
		int currCol;

		boolean HR, RE, CO, EX, DX, HU, CU;
		int acc = 0; // 장신구 갯수

		Hero(int R, int C) {
			this.startRow = R;
			this.startCol = C;
			this.currRow = R;
			this.currCol = C;
		}
	}

	static class Item {
		char type;
		int value;
		String effect;

		Item(char type, int value) {
			this.type = type;
			this.value = value;
		}

		Item(char type, String effect) {
			this.type = type;
			this.effect = effect;
		}
	}

	static class Monster {
		String name;
		int W, A, H, E; // 공 방 체 경
		int maxHP;

		Monster(String name, int W, int A, int H, int E) {
			this.name = name;
			this.W = W;
			this.A = A;
			this.maxHP = H;
			this.H = H;
			this.E = E;
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		play();
	}

	private static void printRes(String result) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sb.append(grid[i][j]);
			}
			sb.append('\n');
		}

		sb.append("Passed Turns : " + T).append('\n');
		sb.append("LV : " + hero.level).append('\n');
		sb.append("HP : " + hero.curHP + "/" + hero.maxHP).append('\n');
		sb.append("ATT : " + hero.SATT + "+" + hero.WATT).append('\n');
		sb.append("DEF : " + hero.SDEF + "+" + hero.ADEF).append('\n');
		sb.append("EXP : " + hero.exp + "/" + hero.level * 5).append('\n');
		sb.append(result);

		System.out.println(sb);
		System.exit(0);
	}

	private static void play() {

		for (int turn = 1; turn <= command.length(); turn++) {
			char op = command.charAt(turn - 1);
			char moveResult = move(op); // 캐릭터를 이동 시키고 이동한 위치의 값을 반환
			T++;
			switch (moveResult) {

			case '^':
				onSpike();
				break;

			case 'B':
				getItem();
				break;
			case '&':
				fightMonster();
				break;
			case 'M':
				fightBoss();
				break;

			case '.':
				// 아무일도 일어나지 않는다. 이동은 move에서 처리
				break;
			}
		} // 모든 커맨드 종료
		grid[hero.currRow][hero.currCol] = '@';
		printRes("Press any key to continue.");

	}

	private static void fightBoss() {
		if (hero.HU) {
			hero.curHP = hero.maxHP;
		}

		Monster monster = monsterMap[hero.currRow][hero.currCol];
		int damage = hero.SATT + hero.WATT;

		if(hero.CO) {
			if(hero.DX) {
				damage *= 3;
			} else {
				damage *= 2;
			}
		}
		
		monster.H -= Math.max(1, (damage - monster.A));
		if (monster.H <= 0) {
			killBoss(monster);
			return;
		}
		

		// 첫 방어 (HU가 없으면 맞음)
		if (!hero.HU) {
			
			hero.curHP -= Math.max(1, (monster.W - (hero.SDEF + hero.ADEF)));
			if (hero.curHP <= 0) {
				death(monster.name);
				return;
			}
		}
		// 늘어난 데미지 다시 원상태로!!
		damage = hero.SATT + hero.WATT;
		while (true) {
			monster.H -= Math.max(1, (damage - monster.A));
			if (monster.H <= 0) {
				killBoss(monster);
				return;
			}

			hero.curHP -= Math.max(1, (monster.W - (hero.SDEF + hero.ADEF)));
			if (hero.curHP <= 0) {
				death(monster.name);
				return;
			}

		}
		
		// 이제 번갈아 가면서 공격

	}

	private static void fightMonster() {
		Monster monster = monsterMap[hero.currRow][hero.currCol];
		int damage = hero.SATT + hero.WATT;
		// CO 있을 시 첫번째 턴은 따로 처리!
		if (hero.CO) {
			int firstDamage = damage;
			if (hero.DX)
				firstDamage *= 3;
			else
				firstDamage *= 2;

			monster.H -= Math.max(1, (firstDamage - monster.A));
			if (monster.H <= 0) {
				killMonster(monster);
				return;
			}

			// 내가 맞을 차례
			hero.curHP -= Math.max(1, (monster.W - (hero.SDEF + hero.ADEF)));
			if (hero.curHP <= 0) {
				death(monster.name);
				return;
			}
		}

		while (true) {
			monster.H -= Math.max(1, (damage - monster.A));
			if (monster.H <= 0) {
				killMonster(monster);
				return;
			}

			hero.curHP -= Math.max(1, (monster.W - (hero.SDEF + hero.ADEF)));
			if (hero.curHP <= 0) {
				death(monster.name);
				return;
			}

		}

	}

	private static void killBoss(Monster boss) {
		killMonster(boss);
		grid[hero.currRow][hero.currCol] = '@';
		printRes("YOU WIN!");

	}

	private static void killMonster(Monster monster) {
		grid[hero.currRow][hero.currCol] = '.';
		if (hero.HR)
			hero.curHP = Math.min(hero.curHP + 3, hero.maxHP);
		int exp = monster.E;
		if (hero.EX)
			exp = (int) (exp * 1.2);
		hero.exp += exp;
		if (hero.exp >= hero.level * 5)
			levelUp();
		
	}

	private static void levelUp() {
		hero.level++;
		hero.exp = 0;
		hero.maxHP += 5;
		hero.curHP = hero.maxHP;
		hero.SATT += 2;
		hero.SDEF += 2;

	}

	private static void getItem() {
		grid[hero.currRow][hero.currCol] = '.'; // 빈칸으로 만들기
		Item item = itemMap[hero.currRow][hero.currCol]; // 아이템 정보 불러오기
		if (item.type == 'W') {
			hero.WATT = item.value; // 무조건 교체됨
		} else if (item.type == 'A') {
			hero.ADEF = item.value;
		} else if (item.type == 'O') {
			if (hero.acc < 4) {
				switch (item.effect) {
				case "HR":
					if (!hero.HR) {
						hero.HR = true;
						hero.acc++;
					}
					break;
				case "RE":
					if (!hero.RE) {
						hero.RE = true;
						hero.acc++;
					}
					break;
				case "CO":
					if (!hero.CO) {
						hero.CO = true;
						hero.acc++;
					}
					break;
				case "EX":
					if (!hero.EX) {
						hero.EX = true;
						hero.acc++;
					}
					break;
				case "DX":
					if (!hero.DX) {
						hero.DX = true;
						hero.acc++;
					}
					break;
				case "HU":
					if (!hero.HU) {
						hero.HU = true;
						hero.acc++;
					}
					break;
				case "CU":
					if (!hero.CU) {
						hero.CU = true;
						hero.acc++;
					}
					break;

				}// 스위치케이스

			} // 장신구 4개 조건
		} // 장신구

	}

	private static void onSpike() {
		// DX 장신구 보유중인지 체크
		int damage = 5;
		if (hero.DX)
			damage = 1;

		hero.curHP -= damage;

		if (hero.curHP <= 0) {
			death("SPIKE TRAP");
		}

		// 피가 남았으면 이 함수는 거기서 종료 grid에서 스파이크는 바뀌지 않음!

	}

	private static void death(String string) {
		hero.curHP = 0;
		// 부활 장신구 있는지 체크
		if (hero.RE) {
			if(grid[hero.currRow][hero.currCol] != '^') {
				// 몹한테 죽은거라 몹도 풀피로 회복
				monsterMap[hero.currRow][hero.currCol].H = monsterMap[hero.currRow][hero.currCol].maxHP;
			}
			hero.RE = false; // 소멸 시키기
			hero.acc--; // 장신구 갯수 하나 줄이는 거 잊지 말기
			hero.curHP = hero.maxHP;
			hero.currRow = hero.startRow;
			hero.currCol = hero.startCol;
			return;
		} else {
			// 부활 장신구가 없으면..
			String res = "YOU HAVE BEEN KILLED BY " + string + "..";
			printRes(res);
		}

	}

	private static char move(char op) {
		int nrow = hero.currRow;
		int ncol = hero.currCol;

		switch (op) {
		case 'U':
			nrow--;
			break;
		case 'R':
			ncol++;
			break;
		case 'D':
			nrow++;
			break;
		case 'L':
			ncol--;
			break;
		}

		// 이동할 수 없다면, 캐릭터의 위치를 바꾸지 않고 현재 위치에서의 값만 반환
		if (nrow == 0 || ncol == 0 || nrow == N + 1 || ncol == M + 1 || grid[nrow][ncol] == '#') {
			return grid[hero.currRow][hero.currCol];
		}

		// 경계밖 또는 벽이 아닌 경우는 일단 이동 할 수 있다!!

		// 이동 가능한 경우. 위치를 바꾸고 다음 위치의 값을 반환
		hero.currRow = nrow;
		hero.currCol = ncol;
		return grid[hero.currRow][hero.currCol];
	}

	private static void init() throws IOException {
		T = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new char[N + 1][M + 1];
		itemMap = new Item[N + 1][M + 1];
		monsterMap = new Monster[N + 1][M + 1];

		int K = 0; // 몬스터 수
		int L = 0; // 상자 수

		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				grid[i][j] = line.charAt(j - 1);
				if (grid[i][j] == '@') {
					grid[i][j] = '.';
					hero = new Hero(i, j);
				}
				if (grid[i][j] == 'B')
					L++;
				if (grid[i][j] == '&' || grid[i][j] == 'M')
					K++;
			}
		} // grid 입력 끝

		command = br.readLine();

		// K개의 몬스터 정보
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			int W = Integer.parseInt(st.nextToken()); // 공
			int A = Integer.parseInt(st.nextToken()); // 방
			int H = Integer.parseInt(st.nextToken()); // 체
			int E = Integer.parseInt(st.nextToken()); // 경험치

			monsterMap[R][C] = new Monster(name, W, A, H, E);
		}

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			char type = st.nextToken().charAt(0);
			if (type == 'O') {
				String effect = st.nextToken();
				itemMap[R][C] = new Item(type, effect);
			} else {
				int value = Integer.parseInt(st.nextToken());
				itemMap[R][C] = new Item(type, value);
			}
		}

	}

}