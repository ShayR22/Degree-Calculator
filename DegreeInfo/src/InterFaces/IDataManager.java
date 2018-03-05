package InterFaces;

public interface IDataManager {

	public void notify(String[] data , boolean cd);
	public void notify(int sortType , boolean ascending);
	public void notify(int year , char semester);
	
	public void loadExistingData();

}
