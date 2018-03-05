package InterFaces;

import Course.CourseData;

public interface IController {
	
	public void registerView(IMyPanel imp);
	public void registerModul(IDataManager idm);
	
	// from view to controller method
	public void notify(CourseData courseData);

	// from module to controller
	public void notify(ICourse course, double[] statistics , boolean clean);
	
	
	
}
