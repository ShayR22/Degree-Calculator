package InterFaces;

import java.util.TreeSet;

public interface IStatistics {

	public void addCourse(ICourse newCourse);
	
	public double getTotalAvarageScore();
	public double getTotalCreditPoints();
	
	public double getYearAvarageScore(int year);
	public double getYearCreditPoints(int year);
	
	public double getSemesterYearAvarageScore(int year, char semester);
	public double getSemesterYearCreditPoints(int year, char semester);
	
	public void sortInfo(int sortBy , boolean ascending);
	
	public int getNumberOfCourses();
	
	public void setTreeSet(TreeSet<ICourse> treeSet);
	public TreeSet<ICourse> getCourses();
	
	
}
