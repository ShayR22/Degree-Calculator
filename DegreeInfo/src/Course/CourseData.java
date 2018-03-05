package Course;

import InterFaces.ICourse;

public class CourseData {

	private String name;
	private double score;
	private double creditPoints;
	private int year;
	private char semester;
	
	private boolean addOrDelete;
	
	private boolean sort;
	private int sortyType;
	private boolean ascending;
	
	private boolean statistics;
	private int[] allStats;
	
	
	public CourseData(ICourse c, boolean add , boolean sort , boolean statistics) {

		if(c == null) {
			this.name = null;	
		}
		else {
			this.name = c.getName();
			this.score = c.getScore();
			this.creditPoints = c.getCreditPoints();
			this.year = c.getYear();
			this.semester = c.getSemester();
		}
		
		this.addOrDelete = add; 
		this.sort = sort;
		this.statistics = statistics;
		this.allStats = new int[3];
	}
	
	
	public void setSortType(int sortType , boolean asecnding) {
		this.sortyType = sortType;
		this.ascending = asecnding;	
	}
	
	public void setStatistics(int year , char semester) {
		this.year = year;
		this.semester = semester;
	}
	
	public void statistics(int[] stats) {
		if(stats.length == 3) {
			allStats = stats;	
		}
	}
	public String getName() {
		return name;
	}


	public double getScore() {
		return score;
	}

	public double getCreditPoints() {
		return creditPoints;
	}

	public int getYear() {
		return year;
	}

	public char getSemester() {
		return semester;
	}

	public boolean isAddOrDelete() {
		return addOrDelete;
	}
	
	public void setAddOrDelete(boolean add) {
		this.addOrDelete = add;
	}


	public boolean isSort() {
		return sort;
	}


	public int getSortyType() {
		return sortyType;
	}

	
	public boolean getAscending() {
		return ascending;
	}

	public boolean isStatistics() {
		return statistics;
	}

	public int[] getAllStats() {
		return allStats;
	}
	
		
}
