package backtrack.sudoku.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sudoku {
	private int[][] sudoku;
	private int count;
	private List<Map<Integer, Integer>> row; // 行
	private List<Map<Integer, Integer>> col; // 列
	private List<Map<Integer, Integer>> low; // 小九宫格

	public Sudoku() {
		initialization();
		createSudoku();
		System.out.println("随机生成的数独如下：");
		printSudoku();
	}
	
	public void findSudokuSolve() {
		findSudokuSolve(0, 0);
		if(count == 0) {
			System.out.println("该数独无解！");
		}
	}

	private void findSudokuSolve(int r, int c) {
		if (r == 8 && c == 9) {
			count++;
			System.out.println("解" + count + "：");
			printSudoku();
			
			return;
		}

		if (c == 9) {
			r++;
			c = 0;
		}

		if (sudoku[r][c] == 0) {
			for (int i = 1; i <= 9; i++) {
				Map<Integer, Integer> rowMap = row.get(r);
				Map<Integer, Integer> colMap = col.get(c);
				Map<Integer, Integer> lowMap = low.get((r / 3 * 3 + c / 3));

				if (rowMap.get(i) != null && colMap.get(i) != null && lowMap.get(i) != null) {
					sudoku[r][c] = i;
					rowMap.remove(i);
					colMap.remove(i);
					lowMap.remove(i);

					findSudokuSolve(r, c + 1);

					rowMap.put(i, i);
					colMap.put(i, i);
					lowMap.put(i, i);
					sudoku[r][c] = 0;
				}
			}
		} else {
			findSudokuSolve(r, c + 1);
		}
	}

	private void initialization() {
		this.count = 0;
		this.sudoku = new int[9][9];
		this.row = new ArrayList<>();
		this.col = new ArrayList<>();
		this.low = new ArrayList<>();

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 1; i < 10; i++) {
			map.put(i, i);
		}
		for (int i = 0; i < 9; i++) {
			this.row.add(clone(map));
			this.col.add(clone(map));
			this.low.add(clone(map));
		}
	}

	private void createSudoku() {
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku[i].length; j++) {
				if (Math.random() < 0.5) {
					int value = (int) (Math.random() * 9 + 1);
					int lowPos = i / 3 * 3 + j / 3;
					if (row.get(i).get(value) != null && col.get(j).get(value) != null
							&& low.get(lowPos).get(value) != null) {
						row.get(i).remove(value);
						col.get(j).remove(value);
						low.get(lowPos).remove(value);

						sudoku[i][j] = value;
					}
				}
			}
		}
	}

	private void printSudoku() {
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku[i].length; j++) {
				System.out.print(sudoku[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private Map<Integer, Integer> clone(Map<Integer, Integer> map) {
		Map<Integer, Integer> cloned = new HashMap<>();

		for (Integer i : map.keySet()) {
			cloned.put(i, map.get(i));
		}

		return cloned;
	}

}
