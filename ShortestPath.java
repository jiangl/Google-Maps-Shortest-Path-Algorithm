import java.util.*;


public class ShortestPath {
	List<IntersectionI> shortest;
	PriorityQueue<Vertex> closed;
	PriorityQueue<Vertex> open; 
	HashSet<Vertex> traveled;

	//Constructor for ShortestPath
	public ShortestPath(){
		shortest = new ArrayList<IntersectionI>();
		closed = new PriorityQueue<Vertex>();
		open = new PriorityQueue<Vertex>();
		traveled = new HashSet<Vertex>();
	}
	
	//Creates Vertex class
	class Vertex implements Comparable<Vertex>{
		Point value;
		Vertex previous;
		double heurCost;
		double actualCost;
		
		public Point getValue() {
			return value;
		}
		public void setValue(Point value) {
			this.value = value;
		}
		public Vertex getPrevious() {
			return previous;
		}
		public void setPrevious(Vertex previous) {
			this.previous = previous;
		}
		public double getHeurCost() {
			return heurCost;
		}
		public double getActualCost() {
			return actualCost;
		}
		public void setHeurCost(double heurCost) {
			this.heurCost = heurCost;
		}
		public void setActualCost(double actualCost) {
			this.actualCost = actualCost;
		}
		public int compareTo(Vertex arg0) {
			return Double.compare(actualCost+heurCost, arg0.actualCost + arg0.heurCost);
		}
		
		public boolean equals(Vertex v){
			if(this.value.equals(v.value) ){
				return true;
			}
			else{
				return false;
			}	
		}
	}
	
	//Find shortest path using modified Dijkstra's
	//When calculating costs, also takes into account heuristic values from current node to end
	//Along with priority queue data structure, not needing to traverse through all N nodes reduces running time
	public List<IntersectionI> compute(IntersectionI start, IntersectionI end, Map<Point, HashMap<Point, StreetI>> map, HashMap<Point, IntersectionI> isect){
		//Store first point in open list
		Vertex current = new Vertex();
		current.setValue(start.getLocation());
		current.setPrevious(null);
		current.setActualCost(0);
		current.setHeurCost(computeDist(current.getValue(), end.getLocation()));
		open.add(current);
		//Updates queues based on current point
		while(!open.isEmpty()){
			current = open.poll();
			if(current.getValue().equals(end.getLocation())){
				return construct(current, isect);
			}
			else{
				closed.add(current);
				for(Point p: map.get(current.value).keySet()){
					Point adjacent = new Point(p.getX(), p.getY());
					//Sets values for vertex of adjacent node
					Vertex adj = new Vertex();
					adj.setValue(adjacent);
					adj.setPrevious(current);
					adj.setActualCost(current.actualCost+computeDist(current.getValue(), adjacent));
					adj.setHeurCost(computeDist(adjacent, end.getLocation()));
					//If vertex wasn't visited, adds to open
					if(!open.contains(adj) && !closed.contains(adj)){
						open.add(adj);
					}
					//checks for distance of the adjacent point if already stored in open
					else if(open.contains(adj)){
						Iterator<Vertex> itr = open.iterator(); 
						Vertex openV = new Vertex();
						//Iterates through values stored in open to find adjacent object
						while(itr.hasNext()) {
						    openV = itr.next(); 
						    if (open.equals(adj)){
						    	//checks if new distance less than stored distance and updates
						    	if(adj.compareTo(openV) < 0){
						    		open.remove(openV);
						    		open.add(adj);
						    	}
						    }
						}		
					}
					//checks for distance of the adjacent point if already stored in closed
					else if(closed.contains(adj)){
						Iterator<Vertex> itr = closed.iterator(); 
						Vertex closeV = new Vertex();
						//Iterates through values stored in open to find adjacent object
						while(itr.hasNext()) {
						    closeV = itr.next(); 
						    if (closed.equals(adj)){
						    	//checks if new distance less than stored distance and updates
						    	if(adj.compareTo(closeV) < 0){
						    		closed.remove(closeV);
						    		closeV.setActualCost(adj.getActualCost());
						    		closeV.setPrevious(current);
						    		closed.add(closeV);
						    	}
						    }
						}		
					}

					
					
				}
			}
		}	
		return null;
	}
	
	public double computeDist(Point a, Point b){
		return Math.sqrt(Math.pow(b.getX()-a.getX(), 2)+Math.pow(b.getY()-a.getY(), 2));
	}
	
	//Constructs a list given the end vertex
	public List<IntersectionI> construct(Vertex v, HashMap<Point, IntersectionI> isect){
		Stack<IntersectionI> reverse = new Stack<IntersectionI>();
		while(v !=null){
			IntersectionI temp = new Intersection();
			temp.setPointOfIntersection(v.getValue());
			temp.setStreetList(isect.get(v.value).getStreetList());
			reverse.add(temp);
			v = v.getPrevious();
		}
		while(!reverse.isEmpty()){
			shortest.add(reverse.pop());
		}
		return shortest;
	}

	
}
