import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
    	String name = "tinyEWD.txt";
    	int sA = 1;
    	int sB = 1;
    	int sC = 2;
    	
    	
    	CompetitionDijkstra test = new CompetitionDijkstra(name, sA, sB, sC);
    	assertEquals("Test Dijkstra Constructor", "112150", "" + test.sA + test.sB + test.sC + test.streets + test.map[0].id + "");
    }
    
    @Test
    public void testDijkstraCompetition() {
    	String name = "tinyEWD.txt";
    	int sA = 50;
    	int sB = 50;
    	int sC = 100;
    	
    	CompetitionDijkstra test = new CompetitionDijkstra(name, sA, sB, sC);
    	int rslt = test.timeRequiredforCompetition();
    	assertEquals("Test Dijkstra Competition", "38", "" + rslt + "");
    	
    	sA = 50;
    	sB = 100;
    	sC = 50;
    	
    	test = new CompetitionDijkstra(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test Dijkstra Competition", "38", "" + rslt + "");
    	
    	sA = 50;
    	sB = 50;
    	sC = 100;
    	
    	test = new CompetitionDijkstra(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test Dijkstra Competition", "38", "" + rslt + "");
    	
    	
    	// Test wrong file
    	name = "input-Az.txt";
    	
    	sA = 50;
    	sB = 80;
    	sC = 60;
    	
    	test = new CompetitionDijkstra(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test Dijkstra Competition", "-1", "" + rslt + "");
    	
    	
    	
    	// Test A
    	name = "input-A.txt";
    	
    	sA = 50;
    	sB = 80;
    	sC = 60;
    	
    	test = new CompetitionDijkstra(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test Dijkstra Competition", "-1", "" + rslt + "");
    	
    	// Test B
    	name = "input-B.txt";
    	
    	sA = 60;
    	sB = 80;
    	sC = 50;
    	
    	test = new CompetitionDijkstra(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test Dijkstra Competition", "10000", "" + rslt + "");
    	
    	// Test wrong min speed
    	sA = 1;
    	sB = 80;
    	sC = 50;
    	
    	test = new CompetitionDijkstra(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test Dijkstra Competition", "-1", "" + rslt + "");
    	
    	sA = 100;
    	sB = 100;
    	sC = 101;
    	
    	test = new CompetitionDijkstra(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test Dijkstra Competition", "-1", "" + rslt + "");
    	
    	
    	// Test D
    	name = "input-D.txt";
    	
    	sA = 50;
    	sB = 80;
    	sC = 60;
    	
    	test = new CompetitionDijkstra(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test Dijkstra Competition", "38", "" + rslt + "");
    	
    	// Test J
    	name = "input-J.txt";
    	
    	sA = 60;
    	sB = 75;
    	sC = 61;
    	
    	test = new CompetitionDijkstra(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test Dijkstra Competition", "-1", "" + rslt + "");
    	
    }
    

    

    @Test
    public void testFWConstructor() {
    	String name = "tinyEWD.txt";
    	int sA = 1;
    	int sB = 1;
    	int sC = 2;
    	
    	CompetitionFloydWarshall test = new CompetitionFloydWarshall(name, sA, sB, sC);
    	assertEquals("Test Dijkstra Constructor", "112150", "" + test.sA + test.sB + test.sC + test.streets + test.map[0].id + "");
    }

    @Test
    public void testFWCompetition() {
    	String name = "tinyEWD.txt";
    	int sA = 50;
    	int sB = 50;
    	int sC = 100;
    	
    	CompetitionFloydWarshall test = new CompetitionFloydWarshall(name, sA, sB, sC);
    	int rslt = test.timeRequiredforCompetition();
    	assertEquals("Test FW Competition", "38", "" + rslt + "");
    	
    	sA = 50;
    	sB = 100;
    	sC = 50;
    	
    	test = new CompetitionFloydWarshall(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test FW Competition", "38", "" + rslt + "");
    	
    	sA = 100;
    	sB = 50;
    	sC = 50;
    	
    	test = new CompetitionFloydWarshall(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test FW Competition", "38", "" + rslt + "");
    	
    	// Test wrong file
    	name = "input-Az.txt";
    	
    	sA = 50;
    	sB = 80;
    	sC = 60;
    	
    	test = new CompetitionFloydWarshall(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test FW Competition", "-1", "" + rslt + "");
    	
    	
    	
    	
    	// Test A
    	name = "input-A.txt";
    	
    	sA = 50;
    	sB = 80;
    	sC = 60;
    	
    	test = new CompetitionFloydWarshall(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test FW Competition", "-1", "" + rslt + "");
    	
    	// Test wrong min speed
    	name = "input-B.txt";
    	
    	sA = 1;
    	sB = 80;
    	sC = 50;
    	
    	test = new CompetitionFloydWarshall(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test Dijkstra Competition", "-1", "" + rslt + "");
    	
    	sA = 60;
    	sB = 101;
    	sC = 101;
    	
    	test = new CompetitionFloydWarshall(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test Dijkstra Competition", "-1", "" + rslt + "");
    	
    	name = "input-D.txt";
    	
    	sA = 50;
    	sB = 80;
    	sC = 60;
    	
    	test = new CompetitionFloydWarshall(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test FW Competition", "38", "" + rslt + "");
    	
    	
    	// Test J
    	name = "input-J.txt";
    	
    	sA = 60;
    	sB = 75;
    	sC = 61;
    	
    	test = new CompetitionFloydWarshall(name, sA, sB, sC);
    	rslt = test.timeRequiredforCompetition();
    	assertEquals("Test FW Competition", "-1", "" + rslt + "");

    }

}
