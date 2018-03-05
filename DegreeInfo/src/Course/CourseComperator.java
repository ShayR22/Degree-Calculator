package Course;

import java.util.Comparator;

import InterFaces.ICourse;

public class CourseComperator implements Comparator<ICourse>{

	private final int MIN_SORT = 1;
	private final int MAX_SORT = 4;
	
	private int sortBy;
	private boolean ascending;
	
	/**
	 * get an Integer that will determine how to compare
	 * 1 - compare by name
	 * 2 - compare by score
	 * 3 - compare by credit points
	 * @param sortBy
	 */
	public CourseComperator(int sortBy , boolean ascending) {
		if(sortBy < MIN_SORT || sortBy > MAX_SORT) {
			return;
		}
		this.sortBy = sortBy;
		this.ascending = ascending;
	}
	
	
	
	
	// Comparator goes by : 1 - name , 2 - score , 3 - creditPoints , 4 - year , 5 - semester
	@Override
	public int compare(ICourse c1, ICourse c2) {
		switch(sortBy) {
		case(1):{
			if(ascending) {
				return c1.getName().compareTo(c2.getName());
			}
			else {
				return c2.getName().compareTo(c1.getName());
			}
		}
		
		case(2):{
			if(ascending) {
				if(c1.getScore() == c2.getScore()){
					return c1.getName().trim().compareTo(c2.getName().trim());
				}
				else if(c1.getScore() > c2.getScore()) {
					return 1;
				}
				else {
					return -1;
				}
			}
			
			else {
				if(c2.getScore() == c1.getScore()){
					return c1.getName().trim().compareTo(c2.getName().trim());
				}
				else if(c2.getScore() > c1.getScore()) {
					return 1;
				}
				else {
					return -1;
				}
			}	
		}
		
		
		case(3):{
			if(ascending) {
			
				if(c1.getCreditPoints() == c2.getCreditPoints()){
					return c1.getName().trim().compareTo(c2.getName().trim());
				}
				if(c1.getCreditPoints() > c2.getCreditPoints()) {
					return 1;
				}
				else {
					return -1;
				}
			}
			
			else {
				if(c2.getCreditPoints() == c1.getCreditPoints()){
					return c1.getName().trim().compareTo(c2.getName().trim());
				}
				if(c2.getCreditPoints() > c1.getCreditPoints()) {
					return 1;
				}
				else {
					return -1;
				}
			}
		}
		case(4):{
			if(ascending) {
				if(c1.getYear() == c2.getYear()){
					if(c1.getSemester() == c2.getSemester()){
						return c1.getName().trim().compareTo(c2.getName().trim());
					}
					else if(c1.getSemester() > c2.getSemester()){
						return 1;
					}
					else {
						return -1;
					}
				}
				else if(c1.getYear() > c2.getYear()) {
					return 1;
				}
				else {
					return -1;
				}
			}
			else {
				if(c2.getYear() == c1.getYear()){
					if(c2.getSemester() == c1.getSemester()){
						return c1.getName().trim().compareTo(c2.getName().trim());
					}
					else if(c2.getSemester() > c1.getSemester()){
						return 1;
					}
					else{
						return -1;
					}
				}
				else if(c2.getYear() > c1.getYear()) {
					return 1;
				}
				else {
					return -1;
				}
			}
				
		}
		
		}
		// should not ever reach here
		return 0;
	}

	
	
}
