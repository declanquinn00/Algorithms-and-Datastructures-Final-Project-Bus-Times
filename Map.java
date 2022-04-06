import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;

public class Map {
	//String name;
	//int id;
	//int[] nextStop;
	Stop[] stops;
	Transfer[] transfers; //
	HashMap<Integer, Stop> stopMap;
	HashMap<Integer, LinkedList> edgeMap;
	HashMap<String, LinkedList> timeMap;
	StopTime[] times;
	String outputString;
	
	Map(String stoptimes, String stops, String transfers){	
		try {
			Stop stop;
			Transfer tmpTransfer;
			StopTime stopTime;
			StopTime tmpStopTime;
			StopTime lastStopTime;
			File ftimes = new File(stoptimes);
			File fstops = new File(stops);
			File ftransfers = new File(transfers);
	    	Scanner scanf = null;
	    	Scanner scanf2 = null;
	    	Scanner scanf3 = null;
	    	Scanner counter = null;
	    	
	    	// Time data
	    	int tripId;
	    	String arrivalTime;
	    	String departureTime;
	    	int stopId;
	    	int stopSequence;
	    	int stopHeadsign;
	    	int pickupType;
	    	int dropOffType;
	    	float shapeDistTravelled;
	    	HashMap<String, LinkedList> timeMap = new HashMap<String, LinkedList>();
	    	LinkedList<StopTime> timeList;
	    	
	    	// Transfer data
	    	int fromStop;
	    	int toStop;
	    	int transferType;
	    	int minTransferTime;
	    	
	    	
	    	//Stop data
	    	String tmp;
	    	int id;
	    	int code;
	    	String name;
	    	String desc;
	    	float lat;
	    	float lon;
	    	String zoneId;
	    	int stopUrl;
	    	int locationType;
	    	int parentStation;
	    	HashMap<Integer, Stop> stopMap = new HashMap<Integer, Stop>();
	    	
	    	
	    	//Edge hashmap
	    	Edge edge;
	    	HashMap<Integer, LinkedList> edgeMap = new HashMap<Integer, LinkedList>();	// Edge objec Integer stop
	    	LinkedList<Edge> list;
	    	
	    	
	    	scanf = new Scanner(ftimes).useDelimiter(",");
	    	scanf2 = new Scanner(fstops).useDelimiter(",");
	    	scanf3 = new Scanner(ftransfers).useDelimiter(",");
	    	counter = new Scanner(ftransfers);
	    	
	    	// Count number of transfers
	    	int count = -1; // Account for first line of file not containing a transfer
	    	while(counter.hasNextLine()) {
	    		count++;
	    		counter.nextLine();
	    	}
	    	scanf3.nextLine(); // skip first line
	    	
	    	// Create array of Transfers
	    	Transfer[] transferArray = new Transfer[count];
	    	count = 0;
	    	while(scanf3.hasNextLine()) {
	    		tmp = scanf3.next();
	    		if(!tmp.equals(" ")) {
	    			fromStop = Integer.parseInt(tmp);
	    		}
	    		else fromStop = -9999;
	    		tmp = scanf3.next();
	    		if(!tmp.equals(" ")) {
	    			toStop = Integer.parseInt(tmp);
	    		}
	    		else toStop = -9999;
	    		tmp = scanf3.next();
	    		if(!tmp.equals(" ")) {
	    			transferType = Integer.parseInt(tmp);
	    		}
	    		else transferType = -9999;
	    		tmp = scanf3.nextLine();
	    		if(!tmp.equals(",")) {
	    			tmp = tmp.replace(",", ""); // remove comma from string
	    			minTransferTime = Integer.parseInt(tmp);
	    		}
	    		else minTransferTime = 2;
	    		
	    		//Create new edge and add to edgeMap
	    		edge = new Edge(toStop, fromStop, "A", minTransferTime/100 );
	    		if(edgeMap.get(edge)==null) {
	    			list = new LinkedList<Edge>();
	    			list.add(edge);
	    			edgeMap.put(fromStop, list);
	    		}
	    		else {
	    			list = edgeMap.get(fromStop);
	    			list.add(edge);
	    			edgeMap.put(fromStop, list);
	    		}
	    		tmpTransfer = new Transfer(fromStop, toStop, transferType, minTransferTime);
	    		transferArray[count] = tmpTransfer;
	    		count++;
	    	}
	    	
	    	// Calculate number of Times
	    	counter = new Scanner(ftimes);
	    	// Calculate number of Times
	    	count = -1; // Account for first line of file not containing a Time
	    	while(counter.hasNextLine()) {
	    		count++;
	    		counter.nextLine();
	    	}
	    	
	    	
	    	
	    	// Create array of StopTimes
	    	StopTime[] timeArray = new StopTime[count];
	    	count = 0;
	    	scanf.nextLine();	//Skip first line of code (description)
	    	while(scanf.hasNextLine()) {
	    		tmp = scanf.next();
	    		if(!tmp.equals("")) {
	    			tripId = Integer.parseInt(tmp);
	    		}
	    		else tripId = -9999;
	    		tmp = scanf.next();
	    		if(!tmp.equals("")) {
	    			arrivalTime = tmp;
	    		}
	    		else arrivalTime = null;
	    		tmp = scanf.next();
	    		if(!tmp.equals("")) {
	    			departureTime = tmp;
	    		}
	    		else departureTime = null;
	    		tmp = scanf.next();
	    		if(!tmp.equals("")) {
	    			stopId = Integer.parseInt(tmp);
	    		}
	    		else stopId = -9999;
	    		tmp = scanf.next();
	    		if(!tmp.equals("")) {
	    			stopSequence = Integer.parseInt(tmp);
	    		}
	    		else stopSequence = -9999;
	    		tmp = scanf.next();
	    		if(!tmp.equals("")) {
	    			stopHeadsign = Integer.parseInt(tmp);
	    		}
	    		else stopHeadsign = -9999;
	    		tmp = scanf.next();
	    		if(!tmp.equals("")) {
	    			pickupType = Integer.parseInt(tmp);
	    		}
	    		else pickupType = -9999;
	    		tmp = scanf.next();
	    		if(!tmp.equals("")) {
	    			dropOffType = Integer.parseInt(tmp);
	    		}
	    		else dropOffType = -9999;
	    		tmp = scanf.nextLine();
	    		if(!tmp.equals(",")) {
	    			tmp = tmp.replace(",", ""); // remove comma from string
	    			shapeDistTravelled = Float.parseFloat(tmp);
	    		}
	    		else shapeDistTravelled = -9999;
	    		// Create StopTime
	    		stopTime = new StopTime(tripId, arrivalTime, departureTime, stopId, stopSequence,  stopHeadsign,  pickupType,  dropOffType, shapeDistTravelled);
	    		// Add arrival time to hashmap
	    		if(timeMap.get(stopTime.arrivalTime)==null) {
	    			timeList = new LinkedList<StopTime>();
	    			timeList.add(stopTime);
	    			timeMap.put(stopTime.arrivalTime, timeList);
	    		}
	    		else {
	    			timeList = timeMap.get(stopTime.arrivalTime);
	    			timeList.add(stopTime);
	    			timeMap.put(stopTime.arrivalTime, timeList);
	    		}
	    		
	    		// Add Edge to hashtable		
	    		if(timeArray[0]!=null) {
	    			lastStopTime = timeArray[count-1];
	    			// If same trip Id and next in sequence add edge
	    			if(lastStopTime.tripId == stopTime.tripId && stopTime.stopSequence - lastStopTime.stopSequence == 1) {
	    				edge = new Edge(stopTime.stopId, lastStopTime.stopId, "B", 1);
	    				if(edgeMap.get(lastStopTime.stopId) == null) {
	    					list = new LinkedList<Edge>();
	    					list.add(edge);
	    					edgeMap.put(lastStopTime.stopId, list);
	    				}
	    				else {
	    					list = edgeMap.get(lastStopTime.stopId);
	    					list.add(edge);
	    					edgeMap.put(lastStopTime.stopId, list);
	    				}
	    			}
	    		}
	    		// add to array
	    		timeArray[count] = stopTime;
	    		count++;
	    				
	    	}
	    	
	    	
	    	// Calculate number of Stops
	    	counter = new Scanner(fstops);
	    	count = -1; // Account for first line of file not containing a stop
	    	while(counter.hasNextLine()) {
	    		count++;
	    		counter.nextLine();
	    	}
	    		
	    	// Create array of stops
	    	
	    	Stop[] stopArray = new Stop[count];
	    	count = 0;
	    	scanf2.nextLine();	//Skip first line of code (description)
	    	while(scanf2.hasNextLine()) {
	    		tmp = scanf2.next();
	    		if(!tmp.equals(" ")) {
	    			id = Integer.parseInt(tmp);
	    		}
	    		else id = -9999;
	    		tmp = scanf2.next();
	    		if(!tmp.equals(" ")) {
	    			code = Integer.parseInt(tmp);
	    		}
	    		else code = -9999;
	    		tmp = scanf2.next();
	    		if(!tmp.equals(" ")) {
	    			name = tmp;
	    		}
	    		else name = null;
	    		tmp = scanf2.next();
	    		if(!tmp.equals(" ")) {
	    			desc = tmp;
	    		}
	    		else desc = null;
	    		tmp = scanf2.next();
	    		if(!tmp.equals(" ")) {
	    			lat = Float.parseFloat(tmp);
	    		}
	    		else lat = -9999;
	    		tmp = scanf2.next();
	    		if(!tmp.equals(" ")) {
	    			lon = Float.parseFloat(tmp);
	    		}
	    		else lon = -9999;
	    		tmp = scanf2.next();
	    		if(!tmp.equals(" ")) {
	    			zoneId = tmp;
	    		}
	    		else zoneId = null;
	    		tmp = scanf2.next();
	    		if(!tmp.equals(" ")) {
	    			stopUrl = Integer.parseInt(tmp);
	    		}
	    		else stopUrl = -9999;
	    		tmp = scanf2.next();
	    		if(!tmp.equals(" ")) {
	    			locationType = Integer.parseInt(tmp);
	    		}
	    		else locationType = -9999;
	    		tmp = scanf2.nextLine();
	    		if(!tmp.equals(",")) {
	    			tmp = tmp.replace(",", ""); // remove comma from string
	    			parentStation = Integer.parseInt(tmp);
	    		}
	    		else parentStation = -9999;
	    		// Create Stop
	    		stop = new Stop(id, code, name, desc, lat, lon, zoneId, stopUrl, locationType, parentStation, count);    		
	    		
	    		// Put stop into hashmap
	    		stopMap.put(stop.id, stop);
	    		
	    		stopArray[count] = stop; 
	    		count++;
	    	}
	    	
	    	// close scanners
	    	scanf.close();
	    	scanf2.close();
	    	scanf3.close();
	    	counter.close();
	    	
	    	
	    	this.times = timeArray;
	    	this.transfers = transferArray;
	    	this.stops = stopArray;
	    	this.edgeMap = edgeMap;
	    	this.stopMap = stopMap;
	    	this.timeMap = timeMap;
	    	
		} catch (Exception e) {
			System.out.println("Exception: file not found");
			e.printStackTrace();
		}
    	
		
	}
	
	public void print() {
		System.out.print("Test");
	}
	
	
	

    // Takes two stops and returns the shortest path found from using dijkstras algorithm
    public double shortestDist(Stop start, Stop finish) {
    	double errorDist = -1;
    	LinkedList<Edge> list;
    	Stop tmp;
    	try {
    	IndexMinPQ<Double> pq;    // priority queue of vertices
    	Edge edge;
    	double[] distTo = new double[stops.length]; // distance from initial vertex to other vertices
    	Edge[] edgeTo = new Edge[stops.length];				//!!!!!!!!!!!!!!!!!!! edgeTo=streetArray;
    	//Intersection tmp;
    	double dist = 0;
    	//double longestDist = 0;
    	pq = new IndexMinPQ<Double>(stops.length); 		// New PQ size of stops
    	
    	//initialize distTo to infinity for all stops
    	for(int i=0; i<distTo.length; i++) {
    		distTo[i] = Double.POSITIVE_INFINITY;
    	}
    	distTo[start.index] = 0; // dist to starting vertex is 0
    	
    	// Send In ID
        pq.insert(start.index, distTo[start.index]); // add starting vertex and distance to starting vertex to priority queue
        int count = 0;
        while (!pq.isEmpty()) {
            int v = pq.delMin(); //StopID
            if(count%340==0) {
            	System.out.print("Debug");
            }
            // get stop from index and read id from edgeMap
            tmp = stops[v];
            list = edgeMap.get(tmp.id);
            
            // for each edge from Stop relax edge
            if(list!=null) {
	            for(int i =0; i<list.size(); i++) {
	            	edge = list.get(i);
	            	relax(edge, distTo, edgeTo, pq);
	            	if(i%10==0) {
	            		System.out.print("Hello");
	            	}
	            }
            }
            
            count++;
        }
        
        //Get vertex
        tmp = stopMap.get(finish.id);
        dist = distTo[finish.index];	//May run into type conversion error here
        if(dist != Double.POSITIVE_INFINITY) {
	        //Trace back shortest Path
	        ArrayList<Stop> traceback = new ArrayList<Stop>();//!!
	        int lastStopId;
	        count = 0;
	        while(tmp.id!=start.id) {
	        	edge = edgeTo[tmp.index];
	        	lastStopId = edge.from;
	        	tmp = stopMap.get(lastStopId);
	        	traceback.add(tmp);
	        	count++;
	        }
	        
	        //Print out Stops (array is in reverse Order)
	        String output = "Stops:\n";
	        for(int i = traceback.size()-1; i>=0; i--) {//for(int i= 0; i<traceback.size();i++){		
	        	output += "\n";
	        	output += traceback.get(i).name;
	        }
	        output += finish.name + "\n";
	        
	        System.out.flush();
	        //System.out.println(output);
	        //System.out.flush();
	        this.outputString = output;
	        return dist;
	      }
        else return -1.0;
    	}catch(Exception e) {
    		System.out.print("Djikstra Long error");
    		e.printStackTrace();
    		return -1.0; //Error value
    	}
    	
    }
    
    // update the distances of vertices to shortest path given an edge
    public void relax(Edge edge, double[] distTo, Edge[] edgeTo, IndexMinPQ<Double> pq) {
    	Stop tmp1;
    	Stop tmp2;
    	try {
    		// Get stops from edges				
	    	int v = edge.from;
	    	tmp1 = stopMap.get(edge.from);
	    	int w = edge.to;
	    	tmp2 = stopMap.get(edge.to);
	    	if (distTo[tmp2.index] > distTo[tmp1.index] + edge.cost) {
	            distTo[tmp2.index] = distTo[tmp1.index] + edge.cost;
	            edgeTo[tmp2.index] = edge; // array of edges pointing toward shortest path to vertex
	            if (pq.contains(tmp2.index)) pq.decreaseKey(tmp2.index, distTo[tmp2.index]);	//if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
	            else                pq.insert(tmp2.index, distTo[tmp2.index]);	//else                pq.insert(w, distTo[w]);
	        }
    	}catch(Exception e) {
    		System.out.print("relax error");
    		e.printStackTrace();
    	}
    }
}
