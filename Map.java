import java.io.File;
import java.util.Scanner;

public class Map {
	//String name;
	//int id;
	//int[] nextStop;
	Stop[] stops;
	Transfer[] transfers; //
	StopTime[] times;
	
	Map(String stoptimes, String stops, String transfers){	
		try {
			Stop stop;
			Transfer tmpTransfer;
			StopTime stopTime;
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
	    		else minTransferTime = -9999;
	    		
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
	    		stopTime = new StopTime(tripId, arrivalTime,stopId, stopSequence,  stopHeadsign,  pickupType,  dropOffType, shapeDistTravelled);
	    		
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
	    		stop = new Stop(id, code, name, desc, lat, lon, zoneId, stopUrl, locationType, parentStation);    		
	    		
	    		stopArray[count] = stop; 
	    		count++;
	    	}
	    	this.times = timeArray;
	    	this.transfers = transferArray;
	    	this.stops = stopArray;
	    	
	    	
		} catch (Exception e) {
			System.out.println("Exception: file not found");
			e.printStackTrace();
		}
    	
		
	}
	
	public void print() {
		System.out.print("Test");
	}
}
