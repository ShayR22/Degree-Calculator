package GUI;

import Course.Course;
import Course.CourseData;
import InterFaces.IController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CourseButtonPane extends HBox {
	
	private final int WIDTH = 1200;
	private final int HEIGHT = 360;
	
	private final String NAME_LABEL  = "Course Name:";
	private final String SCORE_LABEL  = "Course Score:";
	private final String CREDIT_POINTS_LABEL  = "Course Credit Points:";
	
	private final Font PANE_FONT = Font.font("Serif", 15);

	private final String CREATE_BUTTON = "Create";
	private final String DELETE_BUTTON = "Delete";
	
	private Button createB;
	private Button deleteB;
	
	
	private Label yearL;
	private Label semesterL;
	
	private ObservableList<Integer> yearNumberList = FXCollections.observableArrayList(1 , 2 , 3 ,4);
	private ObservableList<String> semesterNumberList = FXCollections.observableArrayList("A" , "B" ,"C");
	private ObservableList<String> sortByList = FXCollections.observableArrayList("Name" , "Grade" ,"Credit Points" , "Year && Semester");
	private ObservableList<String> sortAsencdingList = FXCollections.observableArrayList("Ascending" , "Descending");
	
	
	private Label sortByL;
	private Label sortByAscendingL;
	private ComboBox<String> sortByComboBox;
	
	private ComboBox<String> sortByAscending;
	
	private ComboBox<Integer> yearComboBox;
	private ComboBox<String> semesterComboBox;
	
	private Button sortB;

	
	private Label nameL;
	private Label scoreL;
	private Label creditPointsL;
	
	private TextField nameT;
	private TextField scoreT;
	private TextField creditPointsT;
	
	private HBox createAndDeleteAndTextPane;
	
	private VBox sortPanel;
	
	private IController controller;
	
	public CourseButtonPane(IController controller) {
		this.controller = controller;
		this.setPrefSize(WIDTH, HEIGHT);
		
		initProperties();
		setSizesToComponents();
		setcreateDeleteTextBox();
		setSortPanel();
		addListenerToButtons();
		
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(50, 150, 50, 20));
		this.getChildren().addAll(createAndDeleteAndTextPane , sortPanel);
		
	}
	
	private void initProperties() {
		nameL = new Label(NAME_LABEL);
		nameL.setFont(PANE_FONT);
	
		scoreL = new Label(SCORE_LABEL);
		scoreL.setFont(PANE_FONT);
		
		
		creditPointsL = new Label(CREDIT_POINTS_LABEL);
		creditPointsL.setFont(PANE_FONT);
		
		nameT = new TextField();
		nameT.setFont(PANE_FONT);
		
		scoreT =  new TextField();
		scoreT.setFont(PANE_FONT);
		
		creditPointsT = new TextField();
		creditPointsT.setFont(PANE_FONT);
		
		
		createB = new Button(CREATE_BUTTON);
		createB.setFont(PANE_FONT);
		createB.setPrefSize(WIDTH/5, HEIGHT/3);
		createB.setAlignment(Pos.CENTER);
		
		deleteB = new Button(DELETE_BUTTON);
		deleteB.setFont(PANE_FONT);
		
		sortByL = new Label("Sort By:");
		sortByL.setFont(PANE_FONT);
		
		sortByAscendingL = new Label("Sort By:");
		sortByAscendingL.setFont(PANE_FONT);
		
		sortByComboBox = new ComboBox<String>(sortByList);
		sortByComboBox.getSelectionModel().select(3);
		
		sortByAscending = new ComboBox<String>(sortAsencdingList);
		sortByAscending.getSelectionModel().select(0);

		
		yearL = new Label("Year:");
		yearL.setFont(PANE_FONT);
		
		semesterL = new Label("Semester:");
		semesterL.setFont(PANE_FONT);
		
		
		yearComboBox = new ComboBox<Integer>(yearNumberList);
		yearComboBox.getSelectionModel().select(0);
		
		semesterComboBox = new ComboBox<String>(semesterNumberList);
		semesterComboBox.getSelectionModel().select(0);
	
		
		sortB = new Button("Sort");
		sortB.setFont(PANE_FONT);
		
		
		createAndDeleteAndTextPane = new HBox();
		createAndDeleteAndTextPane.setPrefSize(WIDTH*2/3, HEIGHT);
		
		sortPanel = new VBox();
		sortPanel.setPrefSize(WIDTH/3, HEIGHT);

		
	}
	
	private void setSizesToComponents() {
		createB.setPrefSize(WIDTH/5, HEIGHT/10);
		createB.setStyle("-fx-background-color: white;\n -fx-border-color: black");
		
		nameL.setPrefSize(WIDTH/8, HEIGHT/10);
		scoreL.setPrefSize(WIDTH/8, HEIGHT/10);
		creditPointsL.setPrefSize(WIDTH/8, HEIGHT/10);
		yearL.setPrefSize(WIDTH/10, HEIGHT/10);
		semesterL.setPrefSize(WIDTH/10, HEIGHT/10);

		
		nameT.setPrefSize(WIDTH/6, HEIGHT/10);
		scoreT.setPrefSize(WIDTH/6, HEIGHT/10);
		creditPointsT.setPrefSize(WIDTH/6, HEIGHT/10);
		
		
		deleteB.setPrefSize(WIDTH/5, HEIGHT/10);
		deleteB.setStyle("-fx-background-color: white;\n -fx-border-color: black");
		
		yearComboBox.setPrefSize(WIDTH/6, HEIGHT/10);
		yearComboBox.setStyle("-fx-background-color: white;\n -fx-border-color: black");
		
		semesterComboBox.setPrefSize(WIDTH/6, HEIGHT/10);
		semesterComboBox.setStyle("-fx-background-color: white;\n -fx-border-color: black");
		
		
		sortByComboBox.setPrefSize(WIDTH/6, HEIGHT/10);
		sortByComboBox.setStyle("-fx-background-color: white;\n -fx-border-color: black");
		
		sortByAscending.setPrefSize(WIDTH/6, HEIGHT/10);
		sortByAscending.setStyle("-fx-background-color: white;\n -fx-border-color: black");
		
		sortB.setPrefSize(WIDTH/5, HEIGHT/10);
		sortB.setStyle("-fx-background-color: white;\n -fx-border-color: black");
		
	}
	
	private void setcreateDeleteTextBox() {
		
		VBox createAndDeletePane = new VBox();
		createAndDeletePane.setPrefSize(WIDTH/3, HEIGHT);
		createAndDeletePane.setSpacing(50);
		
		HBox sortByAndBox = new HBox();
		sortByAndBox.setPrefSize(WIDTH/3, HEIGHT/10);
		sortByAndBox.setSpacing(10);
		sortByAndBox.getChildren().addAll(sortByL ,sortByComboBox);
		
		HBox sortAscendingAndBox = new HBox();
		sortAscendingAndBox.setPrefSize(WIDTH/3, HEIGHT/10);
		sortAscendingAndBox.setSpacing(10);
		sortAscendingAndBox.getChildren().addAll(sortByAscendingL ,sortByAscending);

		createAndDeletePane.getChildren().addAll(createB , deleteB , sortByAndBox ,sortAscendingAndBox);
			
		VBox TextsPane = new VBox();
		
		TextsPane.setPrefSize(WIDTH/3, HEIGHT);
		
		HBox name = new HBox();
		name.setPrefSize(WIDTH/3, HEIGHT/3);
		name.getChildren().addAll(nameL , nameT);
		
		HBox score = new HBox();
		score.setPrefSize(WIDTH/3, HEIGHT/3);
		score.getChildren().addAll(scoreL , scoreT);
		
		HBox creditPoints = new HBox();
		creditPoints.setPrefSize(WIDTH/3, HEIGHT/3);
		creditPoints.getChildren().addAll(creditPointsL , creditPointsT);
		
		TextsPane.getChildren().addAll(name , score , creditPoints);
		
		
		createAndDeleteAndTextPane.getChildren().addAll(createAndDeletePane , TextsPane);
	}
	
	private void setSortPanel() {
		this.sortPanel.setSpacing(100);
		
		HBox year = new HBox();
		year.setPrefSize(WIDTH/3, HEIGHT/10);
		year.getChildren().addAll(yearL , yearComboBox);
		
		HBox semester = new HBox();
		semester.setPrefSize(WIDTH/3, HEIGHT/10);
		semester.getChildren().addAll(semesterL , semesterComboBox);
		
		HBox sortForPadding = new HBox();
		sortForPadding.setPrefSize(WIDTH/3, HEIGHT/10);
		
		Label emptyLabel = new Label();
		emptyLabel.setPrefSize(WIDTH/8, HEIGHT/10);
		
		sortForPadding.getChildren().addAll(emptyLabel , sortB);
		
		this.sortPanel.getChildren().addAll(year , semester , sortForPadding);
		
	}
	

	private void addListenerToButtons() {
		this.createB.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				CourseData cd = checkData();
				if(cd != null) {
					controller.notify(cd);
				}
			}
		});
		
		this.deleteB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				CourseData cd = checkData();
				if(cd != null) {
					cd.setAddOrDelete(false);
					controller.notify(cd);
				}
				
			}
		});
		
		this.sortB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				//handle null as data for course as this is sorting method , false for adding or deleting
				// true for sort and false for statistics request
				CourseData cd = new CourseData(null, false, true, false);
				int sortType = sortByComboBox.getSelectionModel().getSelectedIndex() + 1; // + 1 array starts from zero
				boolean asecnding;
				
				if(sortByAscending.getSelectionModel().getSelectedIndex() == 0) {
					asecnding = true;
				}
				else {
					asecnding = false;
				}

				cd.setSortType( sortType , asecnding);
				
				controller.notify(cd);
				
				
			}
		});
	
	}
	
	private CourseData checkData() {
		int score = 0;
		double creditPoints = 0;
		boolean validInput = true;
		if(nameT.getText().isEmpty()) {
			nameT.setText("fill me");
			validInput = false; 
		}
		
		if(scoreT.getText().isEmpty()) {
			scoreT.setText("fill me");
			validInput = false;
		}
		else {
			try {
				score = Integer.valueOf(scoreT.getText());
				if(score < 1 || score > 100) {
					validInput = false;	
				}
			}
			catch(NumberFormatException e) {
				validInput = false;
			}
		}
		
		if(creditPointsT.getText().isEmpty()) {
			creditPointsT.setText("fill me");
			validInput = false;
		}
		else {
			try {
				creditPoints = Double.valueOf(creditPointsT.getText());
				if(creditPoints < 0.5 || creditPoints > 10) {
					validInput = false;	
				}
			}
			catch(NumberFormatException e) {
				validInput = false;
			}
		}
			
		if(validInput) {
			return new CourseData(new Course(nameT.getText(), score, creditPoints, yearComboBox.getSelectionModel().getSelectedItem(), semesterComboBox.getSelectionModel().getSelectedItem().charAt(0)), true, false , false); 	
		}	
		else {
			return null;
		}
		
	}	
	
	public int getYear() {
		return this.yearComboBox.getSelectionModel().getSelectedItem();
	}
	
	public char getSemester() {
		return this.semesterComboBox.getSelectionModel().getSelectedItem().charAt(0);
	}
	
	
}


