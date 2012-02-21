
public interface StreetI {
	
	public void setIdNumber(int id);
	
	public void setName(String name);

	public String getName();

	public void setPoints(Point firstPoint, Point secondPoint);

	public Point getFirstPoint();

	public Point getSecondPoint();

	public int getIdNumber();
	
	//Distance Formula
	public Double getDistance();

	public String toString();

}
