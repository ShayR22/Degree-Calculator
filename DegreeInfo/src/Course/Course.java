package Course;
import java.io.IOException;
import java.io.RandomAccessFile;
import DataManagement.FixedLengthStringIO;

import InterFaces.ICourse;

public class Course implements ICourse {
	
	public static final int NAME_SIZE = 25;
	public static final int SCORE_SIZE = 3;
	public static final int CREDIT_SIZE = 3;
	public static final int YEAR_SIZE = 1;
	public static final int SEMESTER_SIZE = 1;
	
	
	
	private String name;
	private double score;
	private double creditPoints;
	private int year;
	private char semester;
	
	public Course(String name , double score , double creditPoints , int year , char semester) {

		this.name = name;
		this.score = score;
		this.creditPoints = creditPoints;
		this.year = year;
		this.semester = semester;
		
	}

	@Override
	public int getYear() {
		return year;
	}
	
	@Override
	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public char getSemester() {
		return semester;
	}

	@Override
	public void setSemester(char semester) {
		this.semester = semester;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public double getScore() {
		return score;
	}

	@Override
	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public double getCreditPoints() {
		return creditPoints;
	}

	@Override
	public void setCreditPoints(double creditPoints) {
		this.creditPoints = creditPoints;
	}

	@Override
	public void writeCourse(RandomAccessFile raf) {
		try {
			FixedLengthStringIO.writeFixedLengthString(this.name, NAME_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString("" + this.score, SCORE_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString("" + this.creditPoints, CREDIT_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString("" + this.year, YEAR_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString("" + this.semester, SEMESTER_SIZE , raf);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		String str = "name is: " + this.name.trim() + " score is: " + this.score + " creditpoints are: " + this.creditPoints + "\n" + "year is: " + this.year + " semester is: " + this.semester;
		return str;
	}


}
