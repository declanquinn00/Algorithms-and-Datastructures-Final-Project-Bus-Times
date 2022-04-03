
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
	Transfer[] transfers = null;
	
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
	
	
	//
	void addTransfer(Transfer t){
		Transfer[] tmp;
		//If empty
		if(transfers==null) {
			tmp = new Transfer[1];
			tmp[0] = t;
			transfers = tmp;
		}
		// If not empty append
		else {
			tmp = new Transfer[transfers.length];
			System.arraycopy(t, 0, tmp, 0, tmp.length);
			tmp[tmp.length-1] = t;
			transfers = tmp;
		}
	}
}
