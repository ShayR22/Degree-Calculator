package GUI;

import java.text.DecimalFormat;

import Course.CourseData;
import InterFaces.IController;
import InterFaces.ICourse;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CourseDataAndStatisticsPane extends HBox {

	private final int WIDTH = 1200;
	private final int HEIGHT = 500;
	private final int COURSE_PANE_HEIGHT = 50;
	
	private final int DISPLAY_COURSES_WIDTH = (int) (0.7 * WIDTH);
	
	private final int DISPLAY_COURSES_HEADLINES_HEIGHT = 50;
	private final double NAME_HEADLINE_WIDTH_COEFFIENCT = 0.3;
	private final double SCORE_HEADLINE_WIDTH_COEFFIENCT = 0.11;
	private final double CREDIT_HEADLINE_WIDTH_COEFFIENCT = 0.3;
	private final double YEAR_HEADLINE_WIDTH_COEFFIENCT = 0.11;
	private final double SEMESTER_HEADLINE_WIDTH_COEFFIENCT = 0.18;
	
	private final Font LABEL_FONT = Font.font("Serif", 20); 

	private Label semesterAvarage;
	private Label semesterAvarageValue;
	private Label semesterCreditPoints;
	private Label semesterCreditPointsValue;
	
	private Label yearAvarage;
	private Label yearAvarageValue;
	private Label yearCreditPoints;
	private Label yearCreditPointsValue;
	
	private Label totalAvarage;
	private Label totalAvarageValue;
	private Label totalCreditPoints;
	private Label totalCreditPointsValue;
	
	private Label courseName;
	private Label courseScore;
	private Label courseCreditPoints;
	private Label courseYear;
	private Label courseSemester;
	
	private Button calculate;
	
	private VBox coursesDataDisplay;
	private int numOfCourses;
	
	private VBox courseStatisticsDisplay;
	
	private CourseButtonPane buttonPane;
	
	private IController controller;
	
	private ScrollPane wrapperPane;
	
	public CourseDataAndStatisticsPane(IController controller , CourseButtonPane cbp) {
		this.controller = controller;
		this.buttonPane = cbp;
		
		this.setPrefSize(WIDTH, HEIGHT);
	
		initProperties();
		setCourseStatisticsDisplay();
		setCalculate();
		addHeadLines();
		this.getChildren().addAll(wrapperPane , courseStatisticsDisplay);
		
	}
	
	
	private void initProperties() {
		// Semester statistics labels
		semesterAvarage = new Label("Semester Avarage:");
		semesterAvarage.setFont(LABEL_FONT);
		
		semesterAvarageValue = new Label();
		semesterAvarageValue.setFont(LABEL_FONT);
		
		semesterCreditPoints = new Label("Semester Credit Points:");
		semesterCreditPoints.setFont(LABEL_FONT);
		
		semesterCreditPointsValue = new Label();
		semesterCreditPointsValue.setFont(LABEL_FONT);
		
		// year statistics labels
		yearAvarage = new Label("Year Avarage:");
		yearAvarage.setFont(LABEL_FONT);
		
		yearAvarageValue = new Label("");
		yearAvarageValue.setFont(LABEL_FONT);
		
		yearCreditPoints = new Label("Year Credit Points:");
		yearCreditPoints.setFont(LABEL_FONT);
		
		yearCreditPointsValue = new Label();
		yearCreditPointsValue.setFont(LABEL_FONT);
		
		// total statistics labels
		totalAvarage = new Label("Total Avarage:");
		totalAvarage.setFont(LABEL_FONT);
		
		totalAvarageValue = new Label();
		totalAvarageValue.setFont(LABEL_FONT);
		
		totalCreditPoints = new Label("Total Credit Points:");
		totalCreditPoints.setFont(LABEL_FONT);
		
		totalCreditPointsValue = new Label();
		totalCreditPointsValue.setFont(LABEL_FONT);
		
		
		
		calculate = new Button("Calculate Statistics");
		calculate.setFont(LABEL_FONT);
		calculate.setAlignment(Pos.CENTER);
		calculate.setPrefSize(WIDTH - DISPLAY_COURSES_WIDTH, HEIGHT/8);
		calculate.setStyle("-fx-background-color: white;\n -fx-border-color: black");
	
		// courseesDataDisplay headLines
		
		  courseName = new Label("Name");
		  courseName.setFont(LABEL_FONT);
		  courseName.setPrefSize(DISPLAY_COURSES_WIDTH*NAME_HEADLINE_WIDTH_COEFFIENCT , DISPLAY_COURSES_HEADLINES_HEIGHT);
		  courseName.setAlignment(Pos.CENTER);
		  
		    
		  courseScore = new Label("Grade");
		  courseScore.setFont(LABEL_FONT);
		  courseScore.setPrefSize(DISPLAY_COURSES_WIDTH * SCORE_HEADLINE_WIDTH_COEFFIENCT , DISPLAY_COURSES_HEADLINES_HEIGHT);
		  courseScore.setAlignment(Pos.CENTER);
		  
		  courseCreditPoints = new Label("CreditPoints");
		  courseCreditPoints.setFont(LABEL_FONT);
		  courseCreditPoints.setPrefSize(DISPLAY_COURSES_WIDTH * CREDIT_HEADLINE_WIDTH_COEFFIENCT , DISPLAY_COURSES_HEADLINES_HEIGHT);
		  courseCreditPoints.setAlignment(Pos.CENTER);
		  
		  courseYear = new Label("Year");
		  courseYear.setFont(LABEL_FONT);
		  courseYear.setPrefSize(DISPLAY_COURSES_WIDTH * YEAR_HEADLINE_WIDTH_COEFFIENCT , DISPLAY_COURSES_HEADLINES_HEIGHT);
		  courseYear.setAlignment(Pos.CENTER); 
		  
		  courseSemester = new Label("Semester");
		  courseSemester.setFont(LABEL_FONT);
		  courseSemester.setPrefSize(DISPLAY_COURSES_WIDTH * SEMESTER_HEADLINE_WIDTH_COEFFIENCT , DISPLAY_COURSES_HEADLINES_HEIGHT);
		  courseSemester.setAlignment(Pos.CENTER);
		
	
		// panes
		coursesDataDisplay = new VBox();
		coursesDataDisplay.setPrefSize(DISPLAY_COURSES_WIDTH, HEIGHT);
		coursesDataDisplay.setStyle(" -fx-border-color: BLACK");
		
		courseStatisticsDisplay = new VBox();		
		courseStatisticsDisplay.setPrefSize(WIDTH - DISPLAY_COURSES_WIDTH, HEIGHT);
		
		wrapperPane = new ScrollPane();
		wrapperPane.setPrefSize(DISPLAY_COURSES_WIDTH, HEIGHT);
		wrapperPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		wrapperPane.setContent(coursesDataDisplay);
		wrapperPane.setFitToHeight(false);
		wrapperPane.setStyle("-fx-background: azure;\n -fx-border-color: black");
		
		
		numOfCourses = 0;
		
	}
	
	private void setCourseStatisticsDisplay() {
		
		//semester
		HBox semAvarage = new HBox();
		semAvarage.setSpacing(10);
		semAvarage.setPrefSize(WIDTH, HEIGHT/7);
		semAvarage.getChildren().addAll(this.semesterAvarage ,this.semesterAvarageValue);
		
		HBox semCreditAvarage = new HBox();
		semCreditAvarage.setSpacing(10);
		semCreditAvarage.setPrefSize(WIDTH, HEIGHT/7);
		semCreditAvarage.getChildren().addAll(this.semesterCreditPoints ,this.semesterCreditPointsValue);
		
		//year
		HBox yearAvarage = new HBox();
		yearAvarage.setSpacing(10);
		yearAvarage.setPrefSize(WIDTH, HEIGHT/7);
		yearAvarage.getChildren().addAll(this.yearAvarage ,this.yearAvarageValue);

		HBox yearCreditAvarage = new HBox();
		yearCreditAvarage.setSpacing(10);
		yearCreditAvarage.setPrefSize(WIDTH, HEIGHT/7);
		yearCreditAvarage.getChildren().addAll(this.yearCreditPoints ,this.yearCreditPointsValue);
		
		
		//total
		HBox totalAvarage = new HBox();
		totalAvarage.setSpacing(10);
		totalAvarage.setPrefSize(WIDTH, HEIGHT/7);
		totalAvarage.getChildren().addAll(this.totalAvarage , this.totalAvarageValue);
		
		HBox totalCreditAvarage = new HBox();
		totalCreditAvarage.setSpacing(10);
		totalCreditAvarage.setPrefSize(WIDTH, HEIGHT/7);
		totalCreditAvarage.getChildren().addAll(this.totalCreditPoints ,this.totalCreditPointsValue);
		
	
		
		// grouping HBox from above
		VBox semesterInfo = new VBox();
		semesterInfo.setPrefSize(WIDTH, HEIGHT/3 - HEIGHT/24);
		semesterInfo.getChildren().addAll(semAvarage , semCreditAvarage);
		semesterInfo.setStyle("-fx-border-color: BLACK");
		
		
		VBox yearInfo = new VBox();
		yearInfo.setPrefSize(WIDTH, HEIGHT/3 - HEIGHT/24);
		yearInfo.getChildren().addAll(yearAvarage , yearCreditAvarage);
		yearInfo.setStyle("-fx-border-color: BLACK");
		
		VBox totalInfo = new VBox();
		totalInfo.setPrefSize(WIDTH, HEIGHT/3 - HEIGHT/24);
		totalInfo.getChildren().addAll(totalAvarage , totalCreditAvarage);
		totalInfo.setStyle("-fx-border-color: BLACK");
		
		
		
		courseStatisticsDisplay.setAlignment(Pos.BOTTOM_CENTER);
	//	courseStatisticsDisplay.setSpacing(40);
		courseStatisticsDisplay.getChildren().addAll(semesterInfo , yearInfo , totalInfo , calculate);
		courseStatisticsDisplay.setStyle(" -fx-border-color: BLACK");
		
	}

	private void setCalculate() {
		this.calculate.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				CourseData cd = new CourseData(null, false, false, true);
				cd.setStatistics(buttonPane.getYear(), buttonPane.getSemester());
				controller.notify(cd);
			}
		});
	}
	
	private void addHeadLines() {
		HBox headlines = new HBox();
		headlines.setPrefSize(DISPLAY_COURSES_WIDTH, DISPLAY_COURSES_HEADLINES_HEIGHT);
		headlines.getChildren().addAll(courseName , courseScore , courseCreditPoints , courseYear , courseSemester);
		headlines.setStyle(" -fx-border-color: BLACK");
		
		coursesDataDisplay.getChildren().add(headlines);
	}
	
	
	public void addCourse(ICourse course) {
		CoursePane cp = new CoursePane(course.getName(), course.getScore(), course.getCreditPoints(), course.getYear(), course.getSemester());
		numOfCourses++;
		if(numOfCourses * COURSE_PANE_HEIGHT > HEIGHT){
			coursesDataDisplay.setPrefHeight(numOfCourses * COURSE_PANE_HEIGHT);
		}
		
		coursesDataDisplay.getChildren().add(cp);
	}
	
	public void updateStatisitcs(double[] statistics) {

		semesterAvarageValue.setText(String.format(new DecimalFormat("##.##").format(statistics[0])));
		semesterCreditPointsValue.setText(String.format(new DecimalFormat("##.##").format(statistics[1])));
		
		yearAvarageValue.setText(String.format(new DecimalFormat("##.##").format(statistics[2])));
		yearCreditPointsValue.setText(String.format(new DecimalFormat("##.##").format(statistics[3])));
		
		totalAvarageValue.setText(String.format(new DecimalFormat("##.##").format(statistics[4])));
		totalCreditPointsValue.setText(String.format(new DecimalFormat("##.##").format(statistics[5])));
		
	}
	
	public void cleanCourses() {
		coursesDataDisplay.getChildren().clear();
		numOfCourses = 0;
		addHeadLines();
	}
	
}

	