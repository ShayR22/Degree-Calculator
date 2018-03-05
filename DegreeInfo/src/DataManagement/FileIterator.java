package DataManagement;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.ListIterator;

import Course.Course;
import InterFaces.ICourse;


public class FileIterator <T extends ICourse> implements ListIterator<T>{

	//private final String RW = "rw";
	//private final String FILE_NOT_FOUND_EXCEPTION = "File does not exist or wrong input for path";
	private final String IO_EXCEPTION = "A problem accured in I/O data to/from the file";
	//private final String CLASS_NOT_FOUND_EXCEPTION = "There is a problem in classPath"; //TODO check should be thrown somewhere
	
	
	
	
	private final int ICOURSE_TOTAL_SIZE = (Course.NAME_SIZE + Course.SCORE_SIZE + Course.CREDIT_SIZE + Course.YEAR_SIZE + Course.SEMESTER_SIZE)*2; // *2 cuz of unicode


	private int index;
	private RandomAccessFile raf;
	private int size;
	
	public FileIterator(RandomAccessFile raf) {
		this.size =0;
		this.index =0;
		
		this.raf = raf;

		// if got a file that is not empty get the number of Semesters in it
		try {
			while (raf.getFilePointer() + (size * ICOURSE_TOTAL_SIZE) < raf.length()) {
				size++;
			}
		} catch (IOException e) {
			System.out.println(IO_EXCEPTION);
		}
	}
	
	
	public void trancuteData() {
		this.size = 0;
		this.index = 0;
		try {
			raf.setLength(0);
		} catch (IOException e) {
			System.out.println(IO_EXCEPTION);
		}
	}
	
	@Override
	public void add(T e) {
		try {
			raf.seek(raf.length());
			e.writeCourse(raf);
			size++;
			index = size;
		} catch (IOException e1) {
			System.out.println(IO_EXCEPTION);
		}
		
	}

	@Override
	public boolean hasNext() {
		if (index < size) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasPrevious() {
		if (index > 0) {
			return true;
		}
		return false;
	}

	@Override
	public T next() {
		T temp = null;
		if (hasNext()) {
			temp = getNextICourse();
			index++;
			return temp;
		}
		return temp;
	}

	@Override
	public int nextIndex() {
		if (hasNext()) {
			return index + 1;
		}
		return -1;
	}

	@Override
	public T previous() {
		if(hasPrevious()) {
			try {
				index--;
				raf.seek(index * ICOURSE_TOTAL_SIZE);
				
				T temp = getNextICourse();
				// set pointer in right place
				raf.seek(index * ICOURSE_TOTAL_SIZE);

				return temp;

			} catch (IOException e1) {
				System.out.println(IO_EXCEPTION);
			}
		}
		return null;
	}


	@Override
	public int previousIndex() {
		if (hasPrevious()) {
			return index - 1;
		}
		return -1;
	}

	@Override
	public void remove() {
		int tempIndex = this.index - 1;
		ArrayList<T> allAfterDeleted = new ArrayList<T>();
		while(hasNext()) {
			allAfterDeleted.add(next());
		}
		try {
			raf.setLength(tempIndex * ICOURSE_TOTAL_SIZE);
			this.index = tempIndex;
			for (T iCourse : allAfterDeleted) {
				add(iCourse);
			}
			
		} catch (IOException e) {
			System.out.println(IO_EXCEPTION);
		}
		
		
		
		
	}

	@Override
	public void set(T e) {
//		try {
//			// set pointer location to current Contact info (without (after) id)
//			// and rewrite it.
//			raf.seek(raf.getFilePointer() - (ICOURSE_TOTAL_SIZE));
//			ICourse temp = (ICourse) e;
//			
//			FixedLengthStringIO.writeFixedLengthString(temp.getName(), Course.NAME_SIZE, raf);
//			FixedLengthStringIO.writeFixedLengthString("" + temp.getScore(), Course.SCORE_SIZE, raf);
//			FixedLengthStringIO.writeFixedLengthString("" + temp.getCreditPoints() , Course.CREDIT_SIZE, raf);
//		} catch (IOException ex) {
//			System.out.println(IO_EXCEPTION);
//		}
	}
	

	
	private T getNextICourse() {
		try {
			String name , scoreString , creditPointsString , yearString , semesterString;
			name = FixedLengthStringIO.readFixedLengthString(Course.NAME_SIZE, raf).trim();
			scoreString = FixedLengthStringIO.readFixedLengthString(Course.SCORE_SIZE, raf).trim();
			creditPointsString= FixedLengthStringIO.readFixedLengthString(Course.CREDIT_SIZE, raf).trim();
			yearString = FixedLengthStringIO.readFixedLengthString(Course.YEAR_SIZE, raf).trim();
			semesterString = FixedLengthStringIO.readFixedLengthString(Course.SEMESTER_SIZE, raf).trim();
			
			double score = Double.valueOf(scoreString);
			double creditPoints = Double.valueOf(creditPointsString);
			int year = Integer.valueOf(yearString);
			char semester = semesterString.charAt(0);
			
			return initCourseWorkAround(name, score, creditPoints, year, semester);
			
		} catch (IOException e) {
			System.out.println(IO_EXCEPTION);
		}
		return null;
	}

	
	
	@SuppressWarnings({ "unchecked" })
	private T initCourseWorkAround(String name, double score , double creditPoints , int year , char semester) {
		return (T) new Course(name , score , creditPoints , year , semester);
	}
	
	

	
}



















