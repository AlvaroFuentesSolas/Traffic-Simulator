package es.ucm.fdi.model;

public class MakeVehicleFaultyEvent extends Event {

	private String[] vehicles;
	private Integer duration;

	public MakeVehicleFaultyEvent(Integer time, Integer duration, String[] vehicles) {
		super(time);
		this.duration = duration;
		this.vehicles = vehicles;
	}

	public void execute(RoadMap roadMap, int time) {
		if(this.time == time) {
			for (String vehicleId : vehicles) { 
				if(roadMap.getVehicle(vehicleId) == null) throw new SimulatorError("vehicle not found"); 
				roadMap.getVehicle(vehicleId).makeFaulty(this.duration);
			}
		}
	}

	public String toString() {
		return "Make Vehicle Faulty Event";
	}
}
