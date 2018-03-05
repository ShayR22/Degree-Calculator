package DataManagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import Course.AllCourseStats;
import Course.Course;
import InterFaces.IController;
import InterFaces.ICourse;
import InterFaces.IDataManager;



public class DataManager implements IDataManager{

	private final String RW = "rw";
	private final String FILE_NOT_FOUND_EXCEPTION = "File does not exist or wrong input for path";
	private final String IO_EXCEPTION = "A problem accured in I/O data to/from the file";
	
	private RandomAccessFile raf;
	
	private AllCourseStats allCourseStats; 

	private FileIterator<ICourse> myIterator;
	
	private String fileName;
	
	private IController controller;
	
	public DataManager(String fileName , IController icm) {
		this.fileName = fileName;
		this.controller = icm;
		allCourseStats = new AllCourseStats();

		try {
			
			raf = new RandomAccessFile(this.fileName, RW);
			myIterator = new FileIterator<ICourse>(raf);
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println(FILE_NOT_FOUND_EXCEPTION);
		}
	}

	public void loadExistingData() {
		try {
			while(myIterator.hasPrevious()) {
				myIterator.previous();
			}
			
			while(raf.getFilePointer() < raf.length()) {
				ICourse temp = myIterator.next();
				if(temp != null) {
					allCourseStats.addCourse(temp);
					
				}
			}
		} catch (IOException e) {
			System.out.println(IO_EXCEPTION);
		}
		
		for (ICourse ic : allCourseStats.getCourses()) {
			controller.notify(ic , null , false);
		}
	}

	@Override
	public void notify(String[] data, boolean cd) {
		ICourse c = null;
		if(cd) {
			c = new Course(data[0], Double.valueOf(data[1].trim()), Double.valueOf(data[2].trim()), Integer.valueOf(data[3].trim()), data[4].trim().charAt(0));
			myIterator.add(c);
			this.allCourseStats.addCourse(c);
		}
		else {
			deleteCourseFromDataBase(data);
		}
		
		if(c != null) {
			this.controller.notify(c , null, false);
		}
	}

	@Override
	public void notify(int sortType, boolean ascending) {
		this.allCourseStats.sortInfo(sortType, ascending);
		
		cleanAndResetSystem();	
	}

	@Override
	public void notify(int year , char semester) {
		
		
		double[] statistics = new double[6];
		statistics[0] = this.allCourseStats.getSemesterYearAvarageScore(year, semester);
		statistics[1] = this.allCourseStats.getSemesterYearCreditPoints(year, semester);
		
		statistics[2] = this.allCourseStats.getYearAvarageScore(year); 
		statistics[3] = this.allCourseStats.getYearCreditPoints(year);
		
		statistics[4] = this.allCourseStats.getTotalAvarageScore();
		statistics[5] = this.allCourseStats.getTotalCreditPoints();
		
		this.controller.notify(null, statistics , false);
		
	}
	
	
//	private boolean checkIfDataBaseIsEmpty() {
//		if (!myIterator.hasNext() && !myIterator.hasPrevious()) {
//			System.out.println("empty");
//			return true;
//		}
//		return false;
//	}
	
	
	private void deleteCourseFromDataBase(String data[]) {
		for (ICourse iCourse : this.allCourseStats.getCourses()) {
			if(compareCoursesByData(data, iCourse)) {
				this.allCourseStats.getCourses().remove(iCourse);
				break;
			}
			
		}
		cleanAndResetSystem();
		
	}
	
	private boolean compareCoursesByData(String data[] , ICourse c2) {
		if(data[0].trim().compareTo(c2.getName().trim()) != 0) {
			return false;
		}
		else if((Integer.valueOf(data[1])) != (int)(c2.getScore())) {
			return false;
		}
		else if(Double.valueOf(data[2]) != c2.getCreditPoints()){
			return false;
		}
		else if(Integer.valueOf(data[3]) != c2.getYear()) {
			return false;
		}
		else if(data[4].charAt(0) != c2.getSemester()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private void cleanAndResetSystem() {
		myIterator.trancuteData();
		this.controller.notify(null, null, true);
		
		
		for (ICourse iCourse : this.allCourseStats.getCourses()) {
			myIterator.add(iCourse);
			this.controller.notify(iCourse, null, false);
		}	
	}
	
	
	
		
}






