package Course;

import java.util.TreeSet;
import InterFaces.ICourse;
import InterFaces.IStatistics;

public class AllCourseStats implements IStatistics {

	private final int DEFUALT_COMPERATOR_BY_NAME = 4;
	private final boolean DEFUALT_COMPERATOR_ASCENDING = true;
	
	
	private TreeSet<ICourse> allCourses;
	
	
	
	public AllCourseStats() {
		allCourses = new TreeSet<ICourse>(new CourseComperator(DEFUALT_COMPERATOR_BY_NAME ,DEFUALT_COMPERATOR_ASCENDING));
	}
	
	@Override
	public void addCourse(ICourse newCurse) {
		allCourses.add(newCurse);
	}

	@Override
	public double getTotalAvarageScore() {
		double totalScore = 0;
		double totalCreditPoints = 0;
		for (ICourse iCourse : allCourses) {
			totalScore += (iCourse.getCreditPoints() * iCourse.getScore());
			totalCreditPoints += iCourse.getCreditPoints();
		}
		double avarage = totalScore / totalCreditPoints;
		return avarage;
		
	}
	
	@Override
	public double getTotalCreditPoints() {
		double creditPoints = 0;
		for (ICourse iCourse : allCourses) {
			creditPoints += iCourse.getCreditPoints();
		}
		
		return creditPoints;
	}
	

	
	@Override
	public double getYearAvarageScore(int year) {
		double totalYearScore = 0;
		double totalYearCreditPoints = 0;
		for (ICourse iCourse : allCourses) {
			if(iCourse.getYear() == year) {
				totalYearScore += (iCourse.getCreditPoints() * iCourse.getScore());
				totalYearCreditPoints += iCourse.getCreditPoints();
			}
		}
		double avarage = totalYearScore / totalYearCreditPoints;
		return avarage;
		
	}
	
	
	@Override
	public double getYearCreditPoints(int year) {
		double creditPoints = 0;
		for (ICourse iCourse : allCourses) {
			if(iCourse.getYear() == year) {
				creditPoints += iCourse.getCreditPoints();
			}
		}
		
		return creditPoints;
	}
	
	
	

	@Override
	public double getSemesterYearAvarageScore(int year, char semester) {
		// TODO implement method
		double totalYearSemScore = 0;
		double totalYearSemCreditPoints = 0;
		for (ICourse iCourse : allCourses) {
			if(iCourse.getYear() == year && iCourse.getSemester() == semester) {
				totalYearSemScore += (iCourse.getCreditPoints() * iCourse.getScore());
				totalYearSemCreditPoints += iCourse.getCreditPoints();
			}
		}
		double avarage = totalYearSemScore / totalYearSemCreditPoints;
		return avarage;

	}
	
	@Override
	public double getSemesterYearCreditPoints(int year, char semester) {
		double creditPoints = 0;
		for (ICourse iCourse : allCourses) {
			if(iCourse.getYear() == year && iCourse.getSemester() == semester) {
				creditPoints += iCourse.getCreditPoints();
			}
		}
		
		return creditPoints;
	}
	
	
	
	
	@Override
	public void sortInfo(int sortBy, boolean ascending) {
		 TreeSet<ICourse> temp  = new TreeSet<ICourse>(new CourseComperator(sortBy, ascending));
		 
		 for (ICourse iCourse : allCourses) {
			temp.add(iCourse);
		 } 
		 
		 this.allCourses = temp;
	}


	@Override
	public int getNumberOfCourses() {
		return this.allCourses.size();
	}

	@Override
	public TreeSet<ICourse> getCourses() {
		return this.allCourses;
	}

	@Override
	public void setTreeSet(TreeSet<ICourse> treeSet) {
		if(treeSet != null) {
			this.allCourses = treeSet;
		}
		
	}
	
}
