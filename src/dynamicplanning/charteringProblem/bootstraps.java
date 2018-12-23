package dynamicplanning.charteringProblem;

public class bootstraps {
	private static int[][] table;
	private static int[][] flag;

	public static void main(String[] args) {
		int numberOfStation = 5;
		int[][] rent = getRent(numberOfStation);
		table = new int[numberOfStation - 1][numberOfStation];
		flag = new int[numberOfStation - 1][numberOfStation];

		for (int i = 1; i < table[0].length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				int min = rent[j][i];

				for (int k = j + 1; k < i; k++) {
					if (table[j][k] + table[k][i] < min) {
						min = Math.min(table[j][k] + table[k][i], min);
						flag[j][i] = k;
					}
				}

				table[j][i] = min;
			}
		}

		System.out.println("租金表：");
		for (int[] a : rent) {
			for (int b : a) {
				System.out.print(b + "\t");
			}
			System.out.println();
		}
		System.out.println();

		System.out.println("最少租金表：");
		for (int[] a : table) {
			for (int b : a) {
				System.out.print(b + "\t");
			}
			System.out.println();
		}
		System.out.println();

		System.out.println("站1到站" + numberOfStation + "最少租金为： " + table[0][numberOfStation - 1]);
		System.out.println();
		
		System.out.println("路径为：");
		getPath(0, numberOfStation - 1);
	}

	private static void getPath(int start, int end) {
		int mid = flag[start][end];
		if (mid != 0) {
			getPath(start, mid);
			getPath(mid, end);
		} else {
			System.out.println((start+1) + "--->" + (end+1) + " ");
		}
	}

	private static int[][] getRent(int numberOfStation) {
		int[][] rent = new int[numberOfStation - 1][numberOfStation];

		for (int i = 0; i < rent.length; i++) {
			for (int j = i + 1; j < rent[i].length; j++) {
				rent[i][j] = (int) (Math.random() * 16 + 5);
			}
		}

		return rent;
	}
}
