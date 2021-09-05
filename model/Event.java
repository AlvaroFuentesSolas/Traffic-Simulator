package es.ucm.fdi.model;

import java.util.List;

public abstract class Event implements Comparable<Event>{
	protected Integer time; // tiempo de ejecución del evento
	//TODO Event: se supone que solo necesita el atributo time
	protected String etiqueta;
	protected String clave[];

	Event(Event e) {
		this.time = e.time;
		this.etiqueta = e.etiqueta;
		this.clave = e.clave;
	}

	public Event(Integer time) {
		this.time = time;
	}

	public int getScheduledTime(int time) {
		return time;
	}

	public int compareTo(Event event) {
		if(time > event.time) return 1;
		else if (time < event.time ) return -1;
		return 0;
	}

	protected Junction checkifJunctionExists(RoadMap map, String text) {
		return null;
	}

	protected Vehicle checkifVehicleExists(RoadMap map, String text) {
		return null;
	}

	protected Road checkifRoadExists(RoadMap map, String text) {
		return null;
	}

	protected SimulatedObject checkifSimObjExists(RoadMap map, String text) {
		return null;
	}

	protected List<Junction> parseListOfJunctionExists(RoadMap map, String text) {
		return null;
	}

	protected List<Vehicle> parseListOfVehicleExists(RoadMap map, String text) {
		return null;
	}

	protected List<Road> parseListOfRoadExists(RoadMap map, String text) {
		return null;
	}

	protected List<SimulatedObject> parseListOfSimObjExists(RoadMap map, String text) {
		return null;
	}

	public abstract void execute(RoadMap roadMap, int time);

}
