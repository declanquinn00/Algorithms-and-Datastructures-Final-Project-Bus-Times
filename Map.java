import java.io.File;
import java.util.Scanner;

public class Map {
	String name;
	int id;
	int[] nextStop;
	Stop[] stops;
	
	Map(String stoptimes, String stops, String transfers){	
		try {
			Stop stop;
			File ftimes = new File(stoptimes);
			File fstops = new File(stops);
			File ftransfers = new File(transfers);
	    	Scanner scanf = null;
	    	Scanner scanf2 = null;
	    	Scanner scanf3 = null;
	    	Scanner counter = null;
	    	
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
	    	
	    	
	    	scanf = new Scanner(ftimes).useDelimiter(",");
	    	scanf2 = new Scanner(fstops).useDelimiter(",");
	    	counter = new Scanner(fstops);
	    	scanf3 = new Scanner(ftransfers).useDelimiter(",");
	    	
	    	// Calculate number of Stops
	    	int count = -1; // Account for first line of file not containing a stop
	    	while(counter.hasNextLine()) {
	    		count++;
	    		counter.nextLine();
	    	}
	    	scanf2.nextLine();	//Skip first line of code (description)
	    	// Create array of stops
	    	String tmp;
	    	Stop[] stopArray = new Stop[count];
	    	count = 0;
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
	    		if(tmp.equals(",12034")) {
	    			System.out.print("Error Zone");
	    		}
	    		if(!tmp.equals(",")) {
	    			tmp = tmp.replace(",", ""); // remove comma from string
	    			parentStation = Integer.parseInt(tmp);
	    		}
	    		else parentStation = -9999;
	    		
	    		stop = new Stop(id, code, name, desc, lat, lon, zoneId, stopUrl, locationType, parentStation);
	    		stopArray[count] = stop; 
	    		count++;
	    	}
	    	
	    	count = 0;
	    	
		} catch (Exception e) {
			System.out.println("Exception: file not found");
			e.printStackTrace();
		}
    	
		
	}
	
	public void print() {
		System.out.print("Test");
	}
}
