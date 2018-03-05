package Controller;

import java.text.DecimalFormat;
import java.util.ArrayList;

import Course.CourseData;
import InterFaces.IController;
import InterFaces.ICourse;
import InterFaces.IDataManager;
import InterFaces.IMyPanel;

public class Controller implements IController {

	private ArrayList<IMyPanel> views;
	private ArrayList<IDataManager> moduls;
	
	public Controller() {
		views = new ArrayList<IMyPanel>();
		moduls = new ArrayList<IDataManager>();
	}
	
	@Override
	public void registerView(IMyPanel imp) {
		if(!views.contains(imp)) {
			views.add(imp);
		}
	}

	@Override
	public void registerModul(IDataManager idm) {
		if(!moduls.contains(idm)) {
			moduls.add(idm);
		}
	}


	@Override
	public void notify(CourseData courseData) {
		if(courseData.isSort()) {
			modulSortRequest(courseData);
		}
		else if(courseData.isStatistics()) {
			modulStatisticsRequest(courseData);
		}
		else {
			modulCourseRequest(courseData);
		}
	}
	
	
	private void modulCourseRequest(CourseData courseData) {
		
		String data[] = new String[5];
		data[0] = courseData.getName();
		data[1] = String.format(new DecimalFormat("##.##").format(courseData.getScore()));
		data[2] = "" + courseData.getCreditPoints();
		data[3] = "" + courseData.getYear();
		data[4] = "" + courseData.getSemester();
		
		for (IDataManager idm : moduls) {
			idm.notify(data , courseData.isAddOrDelete());
		}
	}
	
	private void modulSortRequest(CourseData courseData) {
		int sortType = courseData.getSortyType();
		boolean ascending = courseData.getAscending();
		
		for (IDataManager idm : moduls) {
			idm.notify(sortType, ascending);
		}
	}
	
	private void modulStatisticsRequest(CourseData courseData) {
		int year = courseData.getYear();
		char semester = courseData.getSemester();
		
		for (IDataManager idm : moduls) {
			idm.notify(year , semester);
		}
	}

	@Override
	public void notify(ICourse course , double[] statistics , boolean clean) {
		if(clean) {
			viewCleanRequest(clean);
		}
		else if(statistics != null) {
			viewStatisticsRequrst(statistics);
		}
		else {
			viewCourseRequest(course);
		}
	}
	
	private void viewCleanRequest(boolean clean) {
		for (IMyPanel imp : views) {
			imp.notify(clean);
		}
	}

	private void viewStatisticsRequrst(double[] statistics) {
		for (IMyPanel imp : views) {
			imp.notify(statistics);
		}
	}

	private void viewCourseRequest(ICourse course) {
		for (IMyPanel imp : views) {
			imp.notify(course);
		}
	}

}
