
public class Street implements StreetI {
	int id;
	String name;
	Point first;
	Point second; 
	
	public void setIdNumber(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPoints(Point firstPoint, Point secondPoint) {
		this.first = firstPoint;
		this.second = secondPoint;
	}

	public Point getFirstPoint() {
		return first;
	}

	public Point getSecondPoint() {
		return second; 
	}

	public int getIdNumber() {
		return id;
	}

	public Double getDistance() {
		return Math.sqrt(Math.pow((second.getX()-first.getX()), 2) + Math.pow((second.getY()-first.getY()), 2));
	}

}
