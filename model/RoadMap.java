package es.ucm.fdi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoadMap {
	
	//Listas para almacenar los objetos
	private List<Vehicle> vehicles;
	private List<Road> roads;
	private List<Junction> junctions;
	
	//Estructura para agilizar la busqueda (id, valor)
	private Map<String, Vehicle> vehiclesMap;
	private Map<String, Road> roadsMap;
	private Map<String, Junction> junctionsMap;

	RoadMap() {
		vehicles = new ArrayList<>();
		roads = new ArrayList<>();
		junctions = new ArrayList<>();
		vehiclesMap = new HashMap<>();
		roadsMap = new HashMap<>();
		junctionsMap = new HashMap<>();
	}

	public SimulatedObject getSimulatedObject(String s) {
        if(vehiclesMap.containsKey(s)) return getVehicle(s);
        else if(roadsMap.containsKey(s)) return getRoad(s);
        else if(junctionsMap.containsKey(s)) return getJunction(s);
        return null;
	}
	
	public Vehicle getVehicle(String vehicleId) {
		return vehiclesMap.get(vehicleId);
	}

	public Road getRoad(String roadId) {
		return roadsMap.get(roadId);
	}

	public Junction getJunction(String junctionId) {
		return junctionsMap.get(junctionId);
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public List<Road> getRoads() {
		return roads;
	}

	public List<Junction> getJunctions() {
		return junctions;
	}

	//TODO RoadMap mirar bien el lanzamiento de excepciones al añadir los objetos
	/**
	 * comprueba que “idCruce” no existe en el mapa.
	 * Si no existe, lo añade a “cruces” y a “mapaDeCruces”. 
	 * Si existe lanza una excepción.
	 * 
	 * @param junction
	 */
	void addJuction(Junction junction){
		if(junctionsMap.containsKey(junction.getId()))
			throw new SimulatorError("The Junction exist!!");
		junctions.add(junction);
		junctionsMap.put(junction.getId(), junction);
	}

	/**
	 * comprueba que “idVehiculo” no existe en el mapa.
	 * Si no existe, lo añade a “vehiculos” y a “mapaDeVehiculos”, 
	 * y posteriormente solicita al vehiculo que se mueva a la siguiente
	 * carretera de su itinerario (moverASiguienteCarretera).
	 * Si existe lanza una excepción.
	 * 
	 * @param vehicle
	 */
	void addVehicle(Vehicle vehicle) {
		if(vehiclesMap.containsKey(vehicle.getId()))
			throw new SimulatorError("The Vehicle exist!!");
		vehicles.add(vehicle);
		vehiclesMap.put(vehicle.getId(), vehicle);
	}

	/**
	 * comprueba que “idCarretera” no existe en el mapa.
	 * Si no existe, lo añade a “carreteras” y a “mapaDeCarreteras”,
	 * y posteriormente actualiza los cruces origen y destino como sigue:
	 * 	- Añade al cruce origen la carretera, como “carretera saliente” 
	 * 	- Añade al crude destino la carretera, como “carretera entrante”
	 * Si existe lanza una excepción.
	 * 
	 * @param road
	 */
	void addRoad(Road road) {
		if(roadsMap.containsKey(road.getId()))
			throw new SimulatorError("The Road exist!!");
		roads.add(road);
		roadsMap.put(road.getId(), road);
	}

	/**
	 * limpia todas las colecciones
	 */
	void clear() {
		roads.clear();
		vehicles.clear();
		junctions.clear();
		roadsMap.clear();
		vehiclesMap.clear();
		junctionsMap.clear();
	}

	/**
	 * llama a generateReport() de cada objeto simulado
	 * 
	 * @param time
	 * @return
	 */
	public String generateReport(int time) {
        StringBuilder sb = new StringBuilder();
        for (Junction junction : junctions) {
            sb.append(junction.generateReport(time));
            sb.append(System.lineSeparator());
        }
        for (Road road : roads) {
        	sb.append(road.generateReport(time));
        	sb.append(System.lineSeparator());
        }
        for (Vehicle vehicle : vehicles) {
            sb.append(vehicle.generateReport(time));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
	}
}
