package traversal.sudoku.entity;

import java.util.HashMap;
import java.util.Map;

public class Sudoku {
	private int[][] sudoku;
	Map<Integer, Integer> map = new HashMap<>();

	public Sudoku() {
		sudoku = new int[3][3];
		for (Integer i = 1; i < 10; i++) {
			map.put(i, i);
		}

		sudoku[1][1] = map.remove(5);
	}

	public void findSudokuSolve() {
		for (Integer i = 1; i < 10; i++) {
			if (i == 5)
				continue;

			Integer pos_0_0 = map.remove(i);
			sudoku[0][0] = pos_0_0;

			Integer pos_2_2 = map.remove(15 - sudoku[0][0] - sudoku[1][1]);
			sudoku[2][2] = pos_2_2;

			for (int j = 1; j < 10; j++) {
				Integer pos_0_1 = map.get(j);
				if (pos_0_1 == null) {
					continue;
				}
				sudoku[0][1] = map.remove(pos_0_1);
				

				Integer pos_0_2 = map.get(15 - sudoku[0][0] - sudoku[0][1]);
				if (pos_0_2 == null) {
					map.put(pos_0_1, pos_0_1);
					continue;
				}
				sudoku[0][2] =map.remove(pos_0_2);

				Integer pos_2_0 = map.get(15 - sudoku[1][1] - sudoku[0][2]);
				if (pos_2_0 == null) {
					map.put(pos_0_2, pos_0_2);
					map.put(pos_0_1, pos_0_1);
					continue;
				}
				
				sudoku[2][0] = map.remove(pos_2_0);

				Integer pos_1_0 = map.get(15 - sudoku[0][0] - sudoku[2][0]);
				Integer pos_2_1 = map.get(15 - sudoku[2][0] - sudoku[2][2]);
				Integer pos_1_2 = map.get(15 - sudoku[0][2] - sudoku[2][2]);

				if (pos_1_0 != null && pos_2_1 != null && pos_1_2 != null) {
					sudoku[1][0] = pos_1_0;
					sudoku[2][1] = pos_2_1;
					sudoku[1][2] = pos_1_2;
					System.out.println(this);
				}
				
				map.put(pos_0_2, pos_0_2);
				map.put(pos_0_1, pos_0_1);
				map.put(pos_2_0, pos_2_0);
			}

			map.put(pos_0_0, pos_0_0);
			map.put(pos_2_2, pos_2_2);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int[] array : sudoku) {
			for (int num : array) {
				sb.append((num));
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
