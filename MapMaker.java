import java.util.*;

public class MapMaker {

	public static void main(String[] args) {
		GraphMaker test = new GraphMaker();
		//Tests file parsing
		test.readFile("src/roadSet1Formatted.txt");
		test.saveFile(test.getMap());
		//Make HashSet of intersections
		test.setIsect(test.getMap());
		
		//Runs shortest path
		ShortestPath dijk = new ShortestPath();
		List<IntersectionI> dpath = new ArrayList<IntersectionI>();
		//First argument is the starting intersection
		dpath = dijk.compute(test.getIsect().get(test.getStart()), 
				test.getIsect().get(test.getEnd()), test.getMap(), test.getIsect());

		//Runs minimum spanning tree
		MinSpanningTree mst = new MinSpanningTree();
		List<IntersectionI> mpath = new ArrayList<IntersectionI>();
		mpath = mst.MST(test.getStreets(), test.getIsect());

		//Runs breadth first search
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		List<IntersectionI> bpath = new ArrayList<IntersectionI>();
		//First argument is the starting intersection
		bpath = bfs.BFS(test.getIsect().get(test.getStart()), test.getMap(), test.getIsect(), test.getStreets());
		
		//Write to file
		JavaScriptPointsWriter.writeJSPoints(dpath, "points1.js");
		JavaScriptPointsWriter.writeJSPoints(mpath, "points2.js");
		JavaScriptPointsWriter.writeJSPoints(bpath, "points3.js");

	}

}
