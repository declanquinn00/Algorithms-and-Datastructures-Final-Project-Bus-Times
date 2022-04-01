
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
	
	Stop(int id, int code, String name, String desc, float lat, float lon, String zoneId, int stopUrl, int locationType, int parentStation){
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
	}
}
