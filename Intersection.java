import java.util.ArrayList;
import java.util.List;


public class Intersection implements IntersectionI {
	List<StreetI> streets;
	Point loc; 
	
	public Intersection(){
		streets = new ArrayList<StreetI>();
		loc = new Point(0,0);
	}
	
	public Point getLocation() {
		return loc;
	}

	public List<StreetI> getStreetList() {
	    return streets;
	}

	public void setPointOfIntersection(Point p) {
		this.loc = p;
	}

	public void setStreetList(List<StreetI> list) {
		this.streets = list;

	}

	public StreetI isConnected(IntersectionI intersection) {
		for(StreetI s: intersection.getStreetList()){
			if(streets.contains(s)){
				return s;
			}
			else{
				return null;
			}
		}
		return null;
	}
	
	public boolean equals(IntersectionI i){
		if(loc.equals(i.getLocation())){
			return true;
		}
		else{
			return false;
		}
			
	}

}
	


