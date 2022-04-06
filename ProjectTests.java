import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import java.util.LinkedList;
import java.util.ArrayList;

public class ProjectTests {

    @Test
    public void testDijkstraConstructor() {
    	String stopTimes = "stop_times.txt";
    	String stops = "stops.txt";
    	String transfers = "transfers.txt";
    	Map map = new Map(stopTimes,stops,transfers);
    	map.print();
    	Stop start = map.stops[20];
    	Stop finish = map.stops[30];
    	double dist = map.shortestDist(start, finish);
    	System.out.print(map.outputString);
    	TST newTST = new TST();
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
    		System.out.println(stop.name);
    		System.out.println(append);
    		System.out.println(tmpString);
    		newTST.put(tmpString, stop.id);
    	}
    	
    	//String tmp1 = newTST.keysThatMatch("BARNET");
    	//RETURNS STOP ID WHICH CAN BE SEARCHED
    	int size = newTST.size();
    	String tmp2 = newTST.longestPrefixOf("BARNET");
    	Iterable<String> tmp3 = newTST.keysWithPrefix("SPER");
    	Iterable<String> tmp7 = newTST.keysWithPrefix("E HAS");
    	Iterable<String> tmp8 = newTST.keysWithPrefix("WATERFRONT STN WEST UNLOA");
    	Iterable<String> tmp5 = newTST.keysWithPrefix("SAINT JO");
    	Iterable<String> tmp6 = newTST.keysWithPrefix("INLET");
    	Iterable tmp4 = newTST.keysWithPrefix("B");
    	
    	
    	
    	
    	//Part 3
    	String time = "5:54:46";
    	boolean isValid = validTime(time);
    	
    	if(isValid) {
    		LinkedList<StopTime> list = map.timeMap.get(" 5:54:46");
    		ArrayList<StopTime> stopTimeArray = new ArrayList<StopTime>();
    		for(int i=0; i<list.size(); i++) {
    			StopTime stime = list.get(i);
    			stopTimeArray.add(stime);
    		}
    		// Print full stop Details for each stop
    		for(int i=0; i<stopTimeArray.size();i++) {
    			stopTimeArray.get(i).printTime();
    		}
    	}
    	
    }
    
    boolean validTime(String time) {
    	if(time != null) {
    		String[] stopTime = time.split(":");
    		if(stopTime != null) {
    			int hours = Integer.parseInt(stopTime[0]);
    			int minutes = Integer.parseInt(stopTime[1]);
    			int seconds = Integer.parseInt(stopTime[2]);
    			if(hours<=23 && minutes<=59 && seconds<=59) {
    				return true;
    			}
    			else return false;
    		}
    		else return false;
    	}
    	return false;
    }
    
    
    
    
    @Test
    public void testDijkstraCompetition() {
    	
    }

}
