import java.util.*;

public class MinSpanningTree {
	List<IntersectionI> mst;
	Queue<Edge> q;
	List<Point> marked;
	PriorityQueue<StreetI> spq;
	
	//Comparator for Streets
	Comparator<StreetI> scomp = new Comparator<StreetI>() {
		public int compare(StreetI o1, StreetI o2) {
			return o1.getDistance().compareTo(o2.getDistance());
		}
	};
	
	//Edge class to locate point and street, with weights
	class Edge implements Comparable<Edge>{
    	Point location;
    	StreetI street;
    	
    	public Point getLocation() {
			return location;
		}

		public void setLocation(Point location) {
			this.location = location;
		}

		public StreetI getStreet() {
			return street;
		}

		public void setStreet(StreetI street) {
			this.street = street;
		}
    	

		public int compareTo(Edge arg1) {
			if( street.getDistance() > arg1.getStreet().getDistance()){
				return 1;
			}
			else if( street.getDistance() < arg1.getStreet().getDistance()){
				return -1;
			}
			else{
				return 0;
			}
		}
    	
    }
	
	public MinSpanningTree(){
		mst = new ArrayList<IntersectionI>();
		marked = new ArrayList<Point>();
		q = new PriorityQueue<Edge>();
		
	}
	
	public List<IntersectionI> MST(HashSet<StreetI> streets, HashMap<Point, IntersectionI> isect){
		spq = new PriorityQueue<StreetI>(streets.size(), scomp);
		
		//Finds the street with the least distance
    	for(StreetI s: streets){
    		spq.add(s);
    	}
    	StreetI start = spq.poll();
    	//Makes the edge class with the distances of the streets
		for(StreetI s: streets){
			Edge temp = new Edge();
			temp.setLocation(start.getFirstPoint());
			temp.setStreet(s);
			q.add(temp);
		}
		//While there are still items in the q, removes the items with least distance
		while(!q.isEmpty()){
			Edge current = new Edge();
			current = q.poll();
			//Checks for the adjacent connecting nodes
			if(current.getStreet().getFirstPoint().equals(current.getLocation())){
				if(!marked.contains(current.getStreet().getSecondPoint())){
					for(StreetI s: isect.get(current.getStreet().getSecondPoint()).getStreetList()){
						Edge temp2 = new Edge();
						temp2.setLocation(current.getStreet().getSecondPoint());
						temp2.setStreet(s);
						q.add(temp2);
					}
					marked.add(current.getStreet().getFirstPoint());
					marked.add(current.getStreet().getSecondPoint());
				}
			}
			else if(current.getStreet().getSecondPoint().equals(current.getLocation())){
				if(!marked.contains(current.getStreet().getFirstPoint())){
					for(StreetI s: isect.get(current.getStreet().getFirstPoint()).getStreetList()){
						Edge temp2 = new Edge();
						temp2.setLocation(current.getStreet().getFirstPoint());
						temp2.setStreet(s);
						q.add(temp2);
					}
					marked.add(current.getStreet().getFirstPoint());
					marked.add(current.getStreet().getSecondPoint());

				}
			}
		}
		return toList(marked, isect);
	}

	private List<IntersectionI> toList(List<Point> mark, HashMap<Point, IntersectionI> isect) {
		for(Point p: mark){
			mst.add(isect.get(p));
		}
		return mst;
	}
}
  

