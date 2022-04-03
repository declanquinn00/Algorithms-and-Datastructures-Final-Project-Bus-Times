import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {
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
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
    	
    	try {
	    	File f = new File(filename);
	    	Scanner scan = null;
	    	Intersection tmp;
	    	Street tempStreet;
	    	int intersections;
	    	int streets;
	    	Street streetArray[];
	    	Intersection[] map;
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
			streetArray = new Street[streets];	// create array of streets
			for(int i=0; i<intersections; i++) {
				tmp = new Intersection(i);
				map[i] = tmp; // Store in map array	
			}
			
			int count =0;
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
			this.map = map; // created map is the map for our object
			this.streetArray = streetArray;	// Our street array
			this.streets = streets;	// number of streets
			
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		
		}
    }


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

    	double timeTotal;
    	int slowest;
    	double distance = 0;
    	
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
    	
    	// create dist array
    	double[][] dist = new double[intersections][intersections];
    	//initialize whole array to infinity
    	for(int i=0; i<intersections; i++) {
    		for(int j=0; j<intersections; j++) {
    			dist[i][j] = Double.POSITIVE_INFINITY; // initializes distance to infinity
    		}                                  
    	}
    	
    	floydWarshall(dist); // get shortest paths on array
    	// find the longest possible distance
    	for(int i=0; i<intersections; i++) {
    		for(int j=0; j<intersections; j++) {
    			//if new longest distance is found
    			if(dist[i][j]> distance) {
    				distance = dist[i][j];
    			}
    		}
    	}
    	
    	distance = distance * 1000; // convert to km
    	if(distance == Double.POSITIVE_INFINITY) {
    		return -1;
    	}
    	//timeTotal = slowest * distance;
    	timeTotal = distance / slowest;
    	timeTotal = Math.ceil(timeTotal); // round up to next whole number
    	
        return (int) timeTotal;
    }
    
    // uses floydWarshall algorithm to generate 2d array of distances
    public void floydWarshall(double[][] dist) {
    	
    	Street tmp;
    	int x;
    	int y;
    	// check distances
    	// start at 0 calculate min dist to each Intersection
    	// add each edges distance
    	for(int a=0; a<streets; a++) {
    		 tmp = streetArray[a];
    		 x = tmp.to;
    		 y = tmp.from;
    		 dist[x][y] = tmp.length;	// add length of each street
    	}
    	// initialize distance from a vertex to itself to 0
    	for(int a=0; a<intersections; a++) {
    		dist[a][a] = 0;
    	}
    	for(int k=0; k<intersections; k++) {
    		for(int i=0; i<intersections; i++) {
    			for(int j=0; j<intersections; j++) {
    				if(dist[i][j] > dist[i][k] + dist[k][j]) {
    					dist[i][j] = dist[i][k] + dist[k][j];
    				}
    			}
    		}
    	}
    	
    }

}