Problem 3: Dijkstra's Algorithm
Based on the roadset files, there aren't a dense amount of points. So, I decided to use the adjacency list 
representation of a graph instead of the matrix. The running time to add a new 
edge is O(1). Although the running time to remove an edge is O(Vertices), once datapoints are added they
won't need to be removed. Since there is an original data file that will add all 
data points to the graph at once, there won't need to be dynamic insertion/deletion. 
In order to make the program run faster, I used a priority queue. It 
automatically sorts, and you don't have to iterate through the values in an array. Instead of 
recomputing the distances in every step, only the ones related to a new intersection are updated. Then,
the minimum values are pulled. It takes linear time to construct the queue, and the cost of updating
the values is O(logn). This results in a total time of O(nlongn), which is greater than O(n^2).In addition,
a heuristic function that checked the unofficial distance from the current node to the end node
was also taken into account. This allowed the program to keep from going through all the nodes, because
it always moves toward the end node and stops when it reaches it. 


Problem 4: Kruskal's Minimum Spanning Tree
In implementing the algorithm, the edges are already sorted with the priority queue
using a comparison sort of O(nlogn) time. This way, when edges are removed based on minimum weight, it 
operates in constant time. The lists keep track of whether or not a component has been marked, 
which takes time of O(n). So, the total time is O(nlogn). 


Problem 5: Breadth First Search
The implementation of the breadth first search algorithm also uses the priority queue. This 
allows the program to choose edges based on Euclidian distance. After the first edge is moved, the 
neighbors are stored back on the queue and automatically sorted. 