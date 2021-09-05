package es.ucm.fdi.model;

import java.util.ArrayList;
import java.util.List;

public class NewVehicleEvent extends Event {
	private String id;
	private Integer maxSpeed;
	private String[] itinerary;

	public NewVehicleEvent(Integer time, String id, Integer maxSpeed, String itinerary[]) {
		super(time);
		this.id = id;
		this.maxSpeed = maxSpeed;
		this.itinerary = itinerary;
	}

	/**
	 * viajan por carretera
	 * se pueden estropear
	 * tienen un itinerario de cruces por los que pasar
	 */
	public void execute(RoadMap roadMap, int time) {
		if(this.time == time) {
			List<Junction> itinerary = new ArrayList<Junction>();
			for (String str : this.itinerary) {
				itinerary.add(roadMap.getJunction(str));
			}
			Vehicle vehicle = new Vehicle(id, maxSpeed, itinerary);
			roadMap.addVehicle(vehicle );
			//vehicle.moveToNextRoad();
		}
	}

	public String toString() {
		return "New Vehicle";
	}
}
