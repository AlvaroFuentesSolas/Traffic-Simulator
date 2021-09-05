package es.ucm.fdi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.ini.IniSection;

public class Junction extends SimulatedObject {

	protected List<IncomingRoad> roads; // lista de carreteras entrantes
	protected Map<Junction, Road> incomingRoads; // mapa de carreteras entrantes indicando cual es su cruce origen
	private int greenRoadIndex;

	public Junction(String id) {
		super(id);
		roads = new ArrayList<>();
		incomingRoads = new HashMap<>();
		greenRoadIndex = -1;
	}

	public Road roadTo(Junction junction) {
		return junction.roadFrom(this);
	}

	public Road roadFrom(Junction junction) {
		return incomingRoads.get(junction);
	}

	public List<IncomingRoad> getRoadsInfo() {
		return roads;
	}

	void addIncommingRoad(Road road) {
		IncomingRoad newIncomingRoad = createIncommingRoadQueue(road);
		roads.add(newIncomingRoad);
		incomingRoads.put(road.getSource(), road);
	}

	void addOutGoingRoad(Road road) {
		// REV Junction addOutGoingRoad redundante(?
        // REV Junction addOutGoingRoad sin uso, buscar especificaciones
	}

	/**
	 * método invocado por los vehículos para entrar en la cola de una carretera
	 * entrante al cruce cuando llegan al cruce. Los vehículos en la cola del cruce
	 * están ordenados según su orden de llegada (el primero que llega al cruce es
	 * el primero que sale).
	 * 
	 * @param vehicle
	 */
	void enter(Vehicle vehicle) {
		vehicle.setSpeed(0);
		for (IncomingRoad incomingRoad : roads) {
			incomingRoad.addVehicle(vehicle);
		}
	}

	void advance(int time) {
		if(!roads.isEmpty()) {
			if(greenRoadIndex!=-1) roads.get(greenRoadIndex).advanceFirstVehicle();
			switchLights();
		}
	}

	protected void switchLights() {
		if(greenRoadIndex == -1) roads.get(++greenRoadIndex).setGreen(true);
		else{
			roads.get(greenRoadIndex).setGreen(false);
			greenRoadIndex = ++greenRoadIndex % roads.size();
			roads.get(greenRoadIndex).setGreen(true);
		}
	}

	protected IncomingRoad createIncommingRoadQueue(Road road) {
		return new IncomingRoad(road);
	}

	protected String getReportSectionTag() {
		return "junction_report";
	}

	/**
	 * Rellena los valores de una sección ini con los atributos del cruce para
	 * mostrar un informe de su estado en uno de los pasos de simulación.
	 * 
	 * @param inisection
	 */
	@Override
	protected void fillReportDetails(IniSection iniSection) {
		String queues = roads.toString();
		iniSection.setValue("queues", queues.substring(1, queues.length() - 1).replaceAll(" ", ""));
	}

}

class IncomingRoad {
	protected Road road; // carretera
	protected List<Vehicle> queue; // cola de vehiculos
	protected boolean green; // true si su semaforo esta verde

	protected IncomingRoad(Road road) {
		this.road = road;
		this.queue = new ArrayList<Vehicle>();
		this.green = false; // redundante
	}

	/**
	 * avanza primero de la cola
	 */
	protected void advanceFirstVehicle() {
		Vehicle vehicle;
		if (green && !queue.isEmpty()) {
			vehicle = queue.remove(0);
			vehicle.moveToNextRoad();
			road.exit(vehicle);
		}
	}

	/**
	 * suma vehiculo a la cola
	 * 
	 * @param v
	 */
	protected void addVehicle(Vehicle vehicle) {
		if(road.getId().equalsIgnoreCase(vehicle.getRoad().getId()))
			queue.add(vehicle);
	}

	public boolean hasGreenLight() {
		return green;
	}

	public void setGreen(boolean green) {
		this.green = green;
	}

	public String printQueue() {
		return queue.toString();
	}
	
    public Road getRoad() {
        return road;
    }

	@Override
	public String toString() {
		return "(" + road.getId() + "," + ((green) ? "green" : "red") + "," + printQueue() + ")";
	}

}
