import java.util.*;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    Scanner input = new Scanner(System.in);
	    boolean quit = false;
	    LinkedList<Stop> tmpList;
	    String response;
	    TST newTST = new TST();
	    System.out.println("Welcome\nInitializing data ");
	    Map map = new Map("stop_times.txt", "stops.txt", "transfers.txt");
	    fillTst(map, newTST);	// Fill the TST
	    System.out.println("Initialization finished\n");
	    while(!quit) {
	    	System.out.println("Please enter the number for what you would like to do:\n1. Find shortest distance between two stops\n"
	    			+ "2. Search for details of a particular stop\n3. Serch for trips by arrival time\n4. exit");
	    	response = input.next();
	    	if(response.equals("1")) {
	    		try {
		    		System.out.println("Please enter a start and finish stop ID\nStart ID: ");
		    		String startString = input.next(); 
		    		int startID = Integer.parseInt(startString);
		    		System.out.println("Stop ID: ");
		    		String finishString = input.next(); 
		    		int stopID = Integer.parseInt(finishString);
		    		
		    		// get start and finish stops
		    		Stop start = map.stopMap.get(startID);
		    		Stop finish = map.stopMap.get(stopID);
		    		if(start!=null && finish!= null) {
		    			double dist = map.shortestDist(start, finish);
		    			// If valid distance
		    			if(dist != -1.0) {
		    				System.out.println("Distance: " + dist + "");
		        			System.out.println("Stops list: " + map.outputString + "");
		    			}
		    			else System.out.println("Error: No path found between stops");
		    		}
		    		else System.out.println("Error: Stop ID not found please try again with correct stop ID");
	    		}catch(Exception e){
	    			System.out.println("Error: Invalid input detected please ensure a valid stop ID is passed");
	    		}
	    	}
	    	else if(response.equals("2")) {
	    		boolean quit2 = false;
	    		Iterable<String> nameList = null;
	    		String output = "";
	    		while(!quit2) {
		    		System.out.println("Please enter a stop name or part of a stop name, or type quit to stop");
		    		String name = input.next();
		    		name = name.toUpperCase();
		    		if(name.equals("QUIT")) {
		    			quit2 = true;
		    		}
		    		else {
		    			nameList = newTST.keysWithPrefix(name);
		    			Queue<String> tmpQ = (Queue<String>) newTST.keysWithPrefix(name); 
		    			while(tmpQ.size()!=0) {
		    				String stop = tmpQ.dequeue();
		    				int stopId = (int) newTST.get(stop);
		    				Stop tmpStop = map.stopMap.get(stopId);
		    				output += tmpStop.toString() + "\n";
		    			}
		    		}
	    		}
	    		if(output.compareTo(" ")!= 0) {
	    			System.out.println(output);
	    		}
	    		else System.out.println("Please add a valid stop name before exiting");
	    	}
	    	else if(response.equals("3")) {
	    		System.out.println("Please enter an arrival time in (hh:mm:ss): ");
	    		String inputTime = input.next();
	    		String time = "";
	    		// If time < 10:00:00 (length 8) (length 7 we add a space to string) 
	    		if(inputTime.length()== 7) {
	    			time += " ";
	    		}
	    		time += inputTime;
	    		boolean isValid = validTime(inputTime);
	        	if(isValid) {
	        		LinkedList<StopTime> list = map.timeMap.get(time);
	        		if(list != null) {
		        		ArrayList<StopTime> stopTimeArray = new ArrayList<StopTime>();
		        		for(int i=0; i<list.size(); i++) {
		        			StopTime stime = list.get(i);
		        			stopTimeArray.add(stime);
		        		}
		        		// Sort arrayList by id
		        		Collections.sort(stopTimeArray, (o1,o2)-> Integer.compare(o1.tripId, o2.tripId));	// Should sort array
		        		
		        		// Print full stop Details for each stop
		        		for(int i=0; i<stopTimeArray.size();i++) {
		        			stopTimeArray.get(i).printTime();
		        		}
	        		}
	        		else System.out.println("No trips data at that time, please try another time");
	        	}
	    		else System.out.println("Error: please enter a valid time");
	    	}
	    	else if(response.equals("4")) {
	    		quit=true;
	    	}
	    	else {
	    		System.out.println("Error please type a valid number (1,2,3,4)");
	    	}
	    }
	    

	}
	
	
	// Fills ternary search tree with data from map object
	public static void fillTst(Map map, TST newTST){
		// Fill stop names
    	String tmpString;
    	String append = "";
    	// For each stop name add to TST
    	for(int i=0; i<map.stops.length; i++) {
    		//System.out.print(""+ map.stops.length + "");
    		Stop stop = map.stops[i];
    		// format string
    		tmpString = stop.name;
    		
    		if(stop.name.contains("FLAGSTOP")) {
    			tmpString = tmpString.substring(8);
    			tmpString += " FLAGSTOP";
    		}
    		else if(stop.name.contains("WB")|| stop.name.contains("NB")|| stop.name.contains("SB")|| stop.name.contains("EB")) {
    			append = tmpString.substring(0, 2);
    			tmpString = tmpString.substring(3);
    			tmpString += " " + append;
    		}
    		//System.out.println(stop.name);
    		//System.out.println(append);
    		//System.out.println(tmpString);
    		newTST.put(tmpString, stop.id);
    	}
	}
	
	// Checks if given time is a valid time given in hh:mm:ss
	static boolean validTime(String time) {
    	if(time != null) {
    		String[] stopTime = time.split(":");
    		// if not empty and separated by three ":"
    		if(stopTime != null&&stopTime.length==3) {
    			try {
    			int hours = Integer.parseInt(stopTime[0]);
    			int minutes = Integer.parseInt(stopTime[1]);
    			int seconds = Integer.parseInt(stopTime[2]);
    			if(hours<=23 && minutes<=59 && seconds<=59) {
    				return true;
    			}
    			else return false;
    			}catch (Exception e) {
    				System.out.println("Invalid input detected");
    				return false;
    			}
    		}
    		else return false;
    	}
    	return false;
    }

}
