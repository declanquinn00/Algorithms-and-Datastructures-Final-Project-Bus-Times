import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class ProjectTests {

    @Test
    public void testDijkstraConstructor() {
    	String stopTimes = "stop_times.txt";
    	String stops = "stops.txt";
    	String transfers = "transfers.txt";
    	Map map = new Map(stopTimes,stops,transfers);
    	map.print();
    }
    
    @Test
    public void testDijkstraCompetition() {
    	
    }

}
