package backtrack.sudoku.GUI.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class SudokuPane extends GridPane {
	private List<int[][]> result; // 存放结果
	private int[][] sudoku;
	private List<Map<Integer, Integer>> row; // 行
	private List<Map<Integer, Integer>> col; // 列
	private List<Map<Integer, Integer>> small; // 小九宫格
	private GridPane[] smallPane;
	private List<SudokuLabel> labels;

	public SudokuPane() {
		super();
		initialization();
		draw(true, null);
	}

	public SudokuPane(int[][] sudoku, int[][] oldSudoku) {
		super();
		initialization();
		this.sudoku = sudoku;
		this.draw(false, oldSudoku);
	}

	public void reset() {
		this.getChildren().clear();
		this.initialization();
		this.draw(true, null);
	}

	public List<int[][]> process() {
		this.result.clear();
		process(0, 0);

		return this.result;
	}

	public void draw(boolean clickable, int[][] oldSudoku) {
		for (int i = 0; i < 81; i++) {
			int x = i / 9;
			int y = i % 9;

			SudokuLabel label = new SudokuLabel(sudoku[x][y]);
			if (oldSudoku != null && oldSudoku[x][y] == 0)
				label.setTextFill(Color.RED);
			labels.add(label);

			int lowPos = x / 3 * 3 + y / 3;

			smallPane[lowPos].add(label, y % 3, x % 3);

			if (clickable) {
				label.setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						Integer num = Integer.parseInt(label.getText());
						Integer temp = num.intValue();

						num += 1;
						while (row.get(x).get(num) == null || col.get(y).get(num) == null
								|| small.get(lowPos).get(num) == null) {
							if (num == 10) {
								num = 0;

								break;
							}
							num += 1;
						}

						if (temp != 0) {
							row.get(x).put(temp, temp);
							col.get(y).put(temp, temp);
							small.get(lowPos).put(temp, temp);
						}

						if (num != 0) {
							row.get(x).remove(num);
							col.get(y).remove(num);
							small.get(lowPos).remove(num);
						}
						label.setText(num + "");
						sudoku[x][y] = num;
					}
				});
			}
		}

		this.setGridLinesVisible(true);
	}

	private void process(int r, int c) {
		if (r == 8 && c == 9) {
			result.add(clone(sudoku));

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
				Map<Integer, Integer> lowMap = small.get((r / 3 * 3 + c / 3));

				if (rowMap.get(i) != null && colMap.get(i) != null && lowMap.get(i) != null) {
					sudoku[r][c] = i;
					rowMap.remove(i);
					colMap.remove(i);
					lowMap.remove(i);

					process(r, c + 1);

					rowMap.put(i, i);
					colMap.put(i, i);
					lowMap.put(i, i);
					sudoku[r][c] = 0;
				}
			}
		} else {
			process(r, c + 1);
		}
	}

	private void initialization() {
		this.result = new ArrayList<>();
		this.sudoku = new int[9][9];
		this.smallPane = new GridPane[9];
		this.row = new ArrayList<>();
		this.col = new ArrayList<>();
		this.small = new ArrayList<>();
		this.labels = new ArrayList<>();

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 1; i < 10; i++) {
			map.put(i, i);
		}

		for (int i = 0; i < 9; i++) {
			this.row.add(clone(map));
			this.col.add(clone(map));
			this.small.add(clone(map));

			smallPane[i] = new GridPane();
			smallPane[i].setGridLinesVisible(true);
			this.add(smallPane[i], i % 3, i / 3);
		}
	}

	private int[][] clone(int[][] array) {
		int[][] newArray = new int[array.length][array[0].length];

		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i].clone();
		}

		return newArray;
	}

	private Map<Integer, Integer> clone(Map<Integer, Integer> map) {
		Map<Integer, Integer> cloned = new HashMap<>();

		for (Integer i : map.keySet()) {
			cloned.put(i, map.get(i));
		}

		return cloned;
	}

	public int[][] getSudoku() {
		return sudoku;
	}
}
