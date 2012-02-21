import java.util.*;

public class BreadthFirstSearch {
	List<IntersectionI> bfs;
	Queue<Edge> qTemp;
	Queue<Edge> q;
	List<Point> marked;
	
	public BreadthFirstSearch(){
		bfs = new ArrayList<IntersectionI>();
		marked = new ArrayList<Point>();
		qTemp = new PriorityQueue<Edge>();
		q = new LinkedList<Edge>();
	}
	
	public List<IntersectionI> BFS(IntersectionI start, Map<Point, HashMap<Point, StreetI>> map, 
			HashMap<Point, IntersectionI> isect, HashSet<StreetI> streets){
		
		//Creates a priority queue of all the streets from the start node
		for(StreetI s: isect.get(start.getLocation()).getStreetList()){
			Edge temp = new Edge();
			temp.setLocation(start.getLocation());
			temp.setStreet(s);
			qTemp.add(temp);
		}
		//Adds the items in order of distance to the queue
		while(!qTemp.isEmpty()){
			q.add(qTemp.poll());
		}
		marked.add(start.getLocation());
		//Takes the next element from the queue, checks connecting intersections to see if marked
		//If not marked, adds on to queue
		while(!q.isEmpty()){
			Edge current = new Edge();
			current = q.poll();
			marked.add(current.getLocation()); 
			if(current.getStreet().getFirstPoint().equals(current.getLocation())){
				if(!marked.contains(current.getStreet().getSecondPoint())){
					qTemp.clear();
					for(StreetI s: isect.get(current.getStreet().getSecondPoint()).getStreetList()){
						Edge temp2 = new Edge();
						temp2.setLocation(current.getStreet().getSecondPoint());
						temp2.setStreet(s);
						qTemp.add(temp2);
					}
					while(!qTemp.isEmpty()){
						q.add(qTemp.poll());
					}
					marked.add(current.getStreet().getSecondPoint());
				}
			}
			else if(current.getStreet().getSecondPoint().equals(current.getLocation())){
				if(!marked.contains(current.getStreet().getFirstPoint())){
					qTemp.clear();
					for(StreetI s: isect.get(current.getStreet().getFirstPoint()).getStreetList()){
						Edge temp2 = new Edge();
						temp2.setLocation(current.getStreet().getFirstPoint());
						temp2.setStreet(s);
						qTemp.add(temp2);
					}
					while(!qTemp.isEmpty()){
						q.add(qTemp.poll());
					}
					marked.add(current.getStreet().getFirstPoint());
				}
			}
		}
		return toList(marked, isect);
	}

	//Converts the marked list to the Intersection list
	private List<IntersectionI> toList(List<Point> mark, HashMap<Point, IntersectionI> isect) {
		for(Point p: mark){
			bfs.add(isect.get(p));
		}
		return bfs;
	}
	
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
}

