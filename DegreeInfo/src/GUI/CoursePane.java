package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class CoursePane extends HBox {
	
	private final String RED_FILL_BLACK_BORDER = " -fx-border-color: BLACK;\n -fx-background-color: RED";
	private final String YELLEOW_FILL_BLACK_BORDER = "-fx-border-color: BLACK;\n -fx-background-color: Yellow";
	
	
	private final double NAME_WIDTH_COEFFIENCT = 0.3;
	private final double SCORE_WIDTH_COEFFIENCT = 0.11;
	private final double CREDIT_WIDTH_COEFFIENCT = 0.3;
	private final double YEAR_WIDTH_COEFFIENCT = 0.11;
	private final double SEMESTER_WIDTH_COEFFIENCT = 0.18;
	
	private final int WIDTH = 840;
	private final int HEIGHT = 50;
	private final int LABEL_HEIGHT = 35;
	
	private final Font LABEL_FONT = Font.font("Serif", 15);

	
	private Label courseNameValue;
	private Label courseScoreValue;
	private Label courseCreditPointsValue;
	private Label courseYearValue;
	private Label courseSemesterValue;

	public CoursePane(String courseName , double courseScore , double courseCreditPoints, int year , char semester ) {
		this.setPrefSize(WIDTH ,HEIGHT);
		
		
		courseNameValue = new Label(courseName);
		courseNameValue.setPrefSize(NAME_WIDTH_COEFFIENCT * WIDTH , LABEL_HEIGHT);
		courseNameValue.setAlignment(Pos.BASELINE_LEFT);
		courseNameValue.setFont(LABEL_FONT);
		courseNameValue.setStyle("-fx-border-color: BLACK");
		
		
		int courseScoreInt = (int) courseScore;
		courseScoreValue = new Label(String.valueOf(courseScoreInt));
		courseScoreValue.setPrefSize(SCORE_WIDTH_COEFFIENCT * WIDTH , LABEL_HEIGHT);
		courseScoreValue.setAlignment(Pos.CENTER);
		courseScoreValue.setFont(LABEL_FONT);
		courseScoreValue.setStyle("-fx-border-color: BLACK");
		
		

		courseCreditPointsValue = new Label(String.valueOf(courseCreditPoints));
		courseCreditPointsValue.setPrefSize(CREDIT_WIDTH_COEFFIENCT * WIDTH , LABEL_HEIGHT);
		courseCreditPointsValue.setAlignment(Pos.CENTER);
		courseCreditPointsValue.setFont(LABEL_FONT);
		courseCreditPointsValue.setStyle("-fx-border-color: BLACK");
		
		courseYearValue = new Label(String.valueOf(year));
		courseYearValue.setPrefSize(YEAR_WIDTH_COEFFIENCT * WIDTH , LABEL_HEIGHT);
		courseYearValue.setAlignment(Pos.CENTER);
		courseYearValue.setFont(LABEL_FONT);
		courseYearValue.setStyle("-fx-border-color: BLACK");
		
		courseSemesterValue = new Label("" + semester);
		courseSemesterValue.setPrefSize(SEMESTER_WIDTH_COEFFIENCT * WIDTH , LABEL_HEIGHT);
		courseSemesterValue.setAlignment(Pos.CENTER);
		courseSemesterValue.setFont(LABEL_FONT);
		courseSemesterValue.setStyle("-fx-border-color: BLACK");

		if(courseScore <= 70 ){
			courseNameValue.setStyle(RED_FILL_BLACK_BORDER);
			courseScoreValue.setStyle(RED_FILL_BLACK_BORDER);
			courseCreditPointsValue.setStyle(RED_FILL_BLACK_BORDER);
			courseYearValue.setStyle(RED_FILL_BLACK_BORDER);
			courseSemesterValue.setStyle(RED_FILL_BLACK_BORDER);
		}
		else if(courseScore <= 80){
			courseNameValue.setStyle(YELLEOW_FILL_BLACK_BORDER);
			courseScoreValue.setStyle(YELLEOW_FILL_BLACK_BORDER);
			courseCreditPointsValue.setStyle(YELLEOW_FILL_BLACK_BORDER);
			courseYearValue.setStyle(YELLEOW_FILL_BLACK_BORDER);
			courseSemesterValue.setStyle(YELLEOW_FILL_BLACK_BORDER);
		}
		
		
		
		this.getChildren().addAll(courseNameValue ,  courseScoreValue , courseCreditPointsValue , courseYearValue , courseSemesterValue);
			
	}
	

	
}
