import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {
	Intersection[] map;
	Street[] streetArray;
	int intersections;
	int streets;
	int sA;
	int sB;
	int sC;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC){
    	
    	
    	try {
	    	File f = new File(filename);
	    	Scanner scan = null;
	    	Intersection tmp;
	    	Street tempStreet;
	    	int intersections;
	    	int streets;
	    	Intersection[] map;
	    	Street streetArray[];
	    	int from;
	    	int to;
	    	double length;
	    	this.sA = sA;
	    	this.sB = sB;
	    	this.sC = sC;
    	
    	
    	
    	
			scan = new Scanner(f);
			intersections = Integer.parseInt(scan.next());
			this.intersections = intersections;
			streets = Integer.parseInt(scan.next());
			
			// Create a map of intersections
			map = new Intersection[intersections];
			for(int i=0; i<intersections; i++) {
				tmp = new Intersection(i);
				map[i] = tmp; // Store in map array	
			}
			streetArray = new Street[streets];	// create array of streets
			int count = 0;
			//Add streets to the map (adds to each intersection object)
			while(scan.hasNextLine()) {
				from = Integer.parseInt(scan.next());	//from
				to = Integer.parseInt(scan.next());	//to
				length = Double.parseDouble(scan.next());	//length (double)
				
				tempStreet = new Street(from, to, length);	// create the street described
				tmp = map[from];			// get the intersection street is going from
				tmp.appendTo(tempStreet);	// add a street to an intersection in the map
				
				streetArray[count] = tempStreet;
				count++;
			}
			this.streetArray = streetArray; // array of streets
			this.streets = streets;	//number of streets
			this.map = map; // created map is the map for our object
			
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
    	
    }

    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    
    //Find the max dist from point for the slowest person and compute time it takes to travel
    public int timeRequiredforCompetition(){
    	int slowest;
    	double distance;
    	double longestDist = -1;
    	
    	// Ensure valid number of intersections
    	if(intersections<=0) {
    		return -1;
    	}
       	// Find slowest time
    	slowest = Math.min(Math.min(sA, sB), Math.min(sB, sC));
   		
   		// check if slowest time is valid
    	if(slowest<50 || slowest >100) {
    		return -1;
   		}
    	// check for invalid speed
    	else if(sC>100 || sB>100) {
    		return-1;
    	}

    		// run Dijkstra for each intersection record longest path found
    	for(int i=0; i<intersections; i++) {
    		distance = DijkstraLong(i);
   			if(distance==-1.0) {	// if error distance recorded	
   				return -1;
   			}
   		
   			if(distance>longestDist) {
   				longestDist = distance;
    		}
    	}
    	
    	
    	longestDist = longestDist*1000; // convert to km
    	longestDist = longestDist/slowest;
    	longestDist = Math.ceil(longestDist); // round up to nearest whole number

    	
    	
        return (int) longestDist;
    }
    
      
    // returns the longest possible shortest path found from using dijkstras algorithm
    public double DijkstraLong(int s) {
    	double longestDist = -1;
    	try {
    	IndexMinPQ<Double> pq;    // priority queue of vertices
    	Street edge;
    	double[] distTo = new double[intersections]; // distance from initial vertex to other vertices
    	Street[] edgeTo = streetArray;
    	Intersection tmp;
    	double dist = 0;
    	//double longestDist = 0;
    	pq = new IndexMinPQ<Double>(intersections); 		// New PQ size intersections
    	
    	//initialize distTo to infinity
    	for(int i=0; i<intersections; i++) {
    		distTo[i] = Double.POSITIVE_INFINITY;
    	}
    	distTo[s] = 0; // dist to given vertex is 0
        pq.insert(s, distTo[s]); // add vertex and distance to vertex to priority queue
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            tmp = map[v]; //Intersection at position v
            
            //For each street connected to intersection
            for (int i = 0; i<tmp.to.length; i++) { // for each edge to other intersection
                edge = tmp.to[i];
                relax(edge, distTo, edgeTo, pq);
            }
        }
    	
        // Check each element to see if it is longest distance
        for(int i=0; i<distTo.length;i++) {
        	dist = distTo[i];
        	if(dist>longestDist) {
        		longestDist=dist;
        	}
        	int j = 0; //test
        }   
    	}catch(Exception e) {
    		System.out.print("Djikstra Long error");
    		e.printStackTrace();
    		return -1.0; //Error value
    	}
    	return longestDist;
    }
    
    // update the distances of vertices to shortest path given an edge
    public void relax(Street edge, double[] distTo, Street[] edgeTo, IndexMinPQ<Double> pq) {
    	try {
	    	int v = edge.from;
	    	int w = edge.to;
	    	if (distTo[w] > distTo[v] + edge.length) {
	            distTo[w] = distTo[v] + edge.length;
	            edgeTo[w] = edge;
	            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
	            else                pq.insert(w, distTo[w]);
	        }
    	}catch(Exception e) {
    		System.out.print("relax error");
    		e.printStackTrace();
    	}
    }

}

