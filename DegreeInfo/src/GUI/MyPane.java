package GUI;

import InterFaces.IController;
import InterFaces.ICourse;
import InterFaces.IMyPanel;
import javafx.scene.layout.VBox;


public class MyPane extends VBox implements IMyPanel {

	
	private TitlePane titlePane;
	
	private CourseDataAndStatisticsPane dataAndStats;
	
	private CourseButtonPane buttonPane;

	
	public MyPane(IController controller) {
		
		this.setPrefSize(1200, 900);
		this.setStyle("-fx-background-color: azure");
		
		titlePane = new TitlePane();
		titlePane.setStyle("-fx-border-color: BLACK");	
		
		buttonPane = new CourseButtonPane(controller);
		
		dataAndStats = new CourseDataAndStatisticsPane(controller , buttonPane);
		
		this.getChildren().addAll(titlePane , dataAndStats , buttonPane);
		
	}

	@Override
	public void notify(ICourse course) {
		dataAndStats.addCourse(course);
	}

	@Override
	public void notify(double[] statistics) {
		if(statistics != null) {
			dataAndStats.updateStatisitcs(statistics);
		}
	}

	@Override
	public void notify(boolean clean) {
		if(clean) {
			dataAndStats.cleanCourses();
		}
		
	}
	


	
	
	
}
