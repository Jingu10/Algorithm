import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, C;
	static Map<Integer, int[]> map;
	static PriorityQueue<Info> pq;
	
	static class Info {
		public int num;
		public int cnt; // 등장횟수
		public int first; // 첫 등장위치
		
		public Info(int num, int cnt, int first) {
			this.num = num;
			this.cnt = cnt;
			this.first = first;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		solve();
		printAns();
	}
	
	
	private static void solve() {
		for(Map.Entry<Integer, int[]> entry : map.entrySet()) {
			int key = entry.getKey();
			int[] value = entry.getValue();
			Info info = new Info(key, value[0], value[1]);
			pq.add(info);
		}
	}


	private static void printAns() {
		StringBuilder sb = new StringBuilder();
		
		while(!pq.isEmpty()) {
			Info info = pq.poll();
			for(int i = 0; i < info.cnt; i++) {
				sb.append(info.num + " ");
			}
		}
		
		System.out.println(sb);
	}


	static void init() throws IOException {
		map = new HashMap<>();
		pq = new PriorityQueue<>(new Comparator<Info>() {

			@Override
			public int compare(Info o1, Info o2) {
				if(o2.cnt == o1.cnt) {
					return o1.first - o2.first;
				}
				
				return o2.cnt - o1.cnt;
			}
			
		});
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(map.isEmpty() || !map.containsKey(num)) {
				map.put(num, new int[] {1, i});
			} else {
				int cnt = map.get(num)[0];
				int first = map.get(num)[1];
				map.put(num, new int[] {cnt + 1, first});
			}
		}
	}
}

/*
 * 빈도를 해시맵에 담으면 어떨까?
 * 
 * 키는 숫자이고, value는 등장횟수
 * 
 * 근데 이걸 빈도 내림차순으로 정렬을 해야되는데 해쉬맵을 정렬하는게 가능한가? 안될 거 같다..
 * 
 * 
 * 우선순위큐를 생각해보자 객체를 정렬한다고 생각해봐
 * 
 * Info 객체에는 int 숫자 int 등장횟수 int 맨처음 나온 위치
 * 
 * 이렇게 3개가 저장된다..
 * 
 * 문제는 같은 숫자일 때는 등장횟수를 하나씩 늘려가야 되는데 그러면.. 매 번 객체 리스트를 뒤지면서 이전에 같은 숫자가 나왔는지를
 * 생각해야만 한다;; 이걸 좀 더 효율적으로 할 순 없을까?
 * 
 * 해쉬맵에 객체를 저장하는 건 어떨까? 그렇게 한 다음에 전체 해쉬맵을 순회하면서 우선순위 안에 넣으면서 정렬하는 방식
 * 
 * 넣으면서 정렬하기 vs 일단 꺼내고 정렬하기 우선순위큐에서 삽입 1번은 O(log(n)) 이기 때문에, n개의 원소를 모두 삽입하면
 * O(nlog(n))
 * 
 * 그리고, 일단 다 꺼낸다음에 그 컬렉션을 정렬하면 그것도 O(nlog(n)) 둘 다 똑같다!
 * 
 * 
 * 정리) 해쉬맵을 이용하자 해쉬맵의 키는 숫자이고, 해쉬맵의 value는 int[]를 쓰자. int[]는 0번째 인덱스는 등장횟수를 1번째
 * 인덱스는 처음나온 위치를 쓰자.
 * 
 * 그렇게 모든 해쉬맵이 저장이 됐으면 해쉬맵을 순회하면서, key와 value안 두 요소를 합친 총 3개의 요소를 가진 객체를 만들어
 * 우선순위큐에 집어넣는다.
 * 
 * 우선순위큐를 순회하면서 결과를 출력한다.
 */