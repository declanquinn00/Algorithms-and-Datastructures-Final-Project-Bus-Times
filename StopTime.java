
public class StopTime {
	int tripId;
	String arrivalTime;
	String departureTime;
	int stopId;
	int stopSequence;
	int stopHeadsign;
	int pickupType;
	int dropOffType;
	float shapeDistTravelled;
	
	StopTime(int tripId, String arrivalTime,int stopId,int stopSequence, int stopHeadsign, int pickupType, int dropOffType, float shapeDistTravelled){
		this.tripId = tripId;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.stopId = stopId;
		this.stopSequence = stopSequence;
		this.stopHeadsign = stopHeadsign;
		this.pickupType = pickupType;
		this.dropOffType = dropOffType;
		this.shapeDistTravelled = shapeDistTravelled;
	}
}
