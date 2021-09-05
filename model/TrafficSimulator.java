package es.ucm.fdi.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import es.ucm.fdi.misc.SortedArrayList;

/*
 * TrafficSimulator realiza los pasos de simulación procesando los eventos (Events) 
 * y avanzando los objetos simulados (SimulatedObjects y AdavancedObjects) 
 * con colecciones (Maps) que se mantienen en una estructura RoadMap.
 */
public class TrafficSimulator {

	private int contadorTiempo;
	private int limiteTiempo;
	private int pasosSimulacion;
	private OutputStream outputStream;
	private RoadMap roadMap;
	List<Event> events;

	public TrafficSimulator(OutputStream os) {
		events = new SortedArrayList<Event>(); // lista ordenada de eventos
		roadMap = new RoadMap();
		setOutputeStream(os);
	}

	public int getPasosSimulacion() {
		return pasosSimulacion;
	}

	/*
	 * avanzar estados - ejecutar eventos - añadir nuevo vehiculo - añadir nuevo
	 * cruce - añadir nueva carretera
	 * if(cruce_origen.exist()&&cruce_destino.exist())//añado - actializar
	 * carreteras - actualizar cruces incrementar tiempos sacar report
	 */
	public void run(int pasosSimulacion) {
		limiteTiempo = this.contadorTiempo + pasosSimulacion - 1;
		while (this.contadorTiempo <= limiteTiempo) {
			try {
				// 1. ejecutar los eventos correspondientes a ese tiempo
				executeEvents();
				// 2. invocar al método avanzar de las carreteras
				updateRoads();
				// 3. invocar al método avanzar de los cruces
				updateJunctions();
			} catch (SimulatorError e) { e.printStackTrace(); }
				// 4. avanzamos al siguiente paso de simulacion
				this.contadorTiempo++;
				// 5. escribir un informe en OutputStream
				// en caso de que no sea null
				// reportes
				printReports();
		}
	}

	/**
	 * ejecuta los eventos de la lista de un tiempo determinado llamando a su
	 * método execute(). Por ejemplo, el método execute() de NewVehicleEvent
	 * suma un vehículo a map.
	 */
	private void executeEvents() {
		for (Event event : events) {
			event.execute(roadMap, contadorTiempo);
		}
	}

	/**
	 * llama al método advance() de las carreteras de map
	 */
	private void updateRoads() {
		for (Road road : roadMap.getRoads()) {
			road.advance(contadorTiempo);
		}
	}

	/**
	 * llama al método advance() de los cruces de map
	 */
	private void updateJunctions() {
		for (Junction junction : roadMap.getJunctions()) {
			junction.advance(contadorTiempo);
		}
	}

	/**
	 * invoca al metodo generateReport() de cada uno de los objetos contenidos
	 * en la estructura map.
	 */
	private void printReports() {
		try {
			if(outputStream != null)
				outputStream.write(roadMap.generateReport(this.contadorTiempo).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addEvent(Event e) {
		if (contadorTiempo <= e.time)
			events.add(e); // añadimos el evento
	}

	public void setOutputStream(OutputStream os) {
		this.outputStream = os;
	}

	public void reset() {
		contadorTiempo = 0;
		// TODO TrafficSimulator reset continuar implementando
	}

	public void setOutputeStream(OutputStream os) {
		this.outputStream = os;
	}

	// public void addObserver(TrafficSimulatorObserver simob){
	//
	// }
	//
	// public void removeObserver(TrafficSimulatorObserver simob){
	//
	// }

	public String toString() {
		return null;

	}

}
