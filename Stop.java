
public class Stop {
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
	Edge[] edges = null;
	int index;
	
	Stop(int id, int code, String name, String desc, float lat, float lon, String zoneId, int stopUrl, int locationType, int parentStation, int index){
		this.id = id;
		this.code = code;
		this.name = name;
		this.desc = desc;
		this.lat = lat;
		this.lon = lon;
		this.zoneId = zoneId;
		this.stopUrl = stopUrl;
		this.locationType = locationType;
		this.parentStation = parentStation;
		this.index=index;
	}
	
	public String toString() {
		String s;
		s = "ID: " + id + " Code: " + code + " Name: " + name + " Description: " + desc + " Latitude: " + lat + " Longitude: " + lon + 
				" Zone ID: " + zoneId + " StopURL: " + stopUrl + " Location Type: " + locationType + " Parent Station: " + parentStation
				+ " Index: " + index + "";
		return s;
	}
	
	//
	void addEdge(Edge e){
		Edge[] tmp;
		//If empty
		if(edges==null) {
			tmp = new Edge[1];
			tmp[0] = e;
			edges = tmp;
		}
		// If not empty append
		else {
			tmp = new Edge[edges.length+1];
			System.arraycopy(edges, 0, tmp, 0, edges.length);
			tmp[tmp.length-1] = e;
			edges = tmp;
		}
	}
}
