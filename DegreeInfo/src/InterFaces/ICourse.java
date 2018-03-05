package InterFaces;

import java.io.RandomAccessFile;

public interface ICourse {

	public void setScore(double score);
	public double getScore();
	
	public void setName(String name);
	public String getName();
	
	public void setCreditPoints(double creditPointsNumber);
	public double getCreditPoints();
	
	public void setYear(int year);
	public int getYear();
	
	public void setSemester(char semester);
	public char getSemester();
	
	public void writeCourse(RandomAccessFile raf);
	
	public String toString();
	
	
}
