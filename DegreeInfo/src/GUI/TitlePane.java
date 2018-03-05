package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TitlePane extends HBox {
	
	private Label courses;
	private Label statistics;
	
	public TitlePane() {	
		this.setPrefSize(1200, 40);
		this.setPadding(new Insets(0, 0, 0, 300));
		
		this.courses = new Label("Courses");
		this.courses.setPrefSize(600, 50);
		this.courses.setFont(Font.font("Serif", FontWeight.BOLD, 20));
		
		this.statistics = new Label("Statistics");
		this.statistics.setPrefSize(300, 50);
		this.statistics.setFont(Font.font("Serif", FontWeight.BOLD, 20));
		
		this.getChildren().addAll(this.courses , this.statistics);
		
		
		
	}
	
	
}
