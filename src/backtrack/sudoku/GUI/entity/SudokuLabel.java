package backtrack.sudoku.GUI.entity;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class SudokuLabel extends Label {
	public SudokuLabel(Integer num) {
		super(num + "");
		
		this.setPrefHeight(100);
		this.setPrefWidth(100);
		this.setTextAlignment(TextAlignment.CENTER);
		this.setAlignment(Pos.CENTER);
		
		this.setFont(Font.font("STKaiTi", FontWeight.BOLD, FontPosture.ITALIC, 25));
	}
}
