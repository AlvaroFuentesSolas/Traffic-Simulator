package es.ucm.fdi.model;

public class NewJunctionEvent extends Event {
	String id;

	public NewJunctionEvent(Integer time, String id) {
		super(time);
		this.id = id;
	}

	/**
	 * Conecta carreteras y organiza el trafico a través de semaforos
	 * cruce = coleccion(carreteras entrantes)
	 * !*solo se puede acceder a traves de una carretera
	 */
	public void execute(RoadMap roadMap, int time) {
		if(this.time == time) {
			roadMap.addJuction(new Junction(this.id));
		}
	}

	public String toString() {
		return "New Juction";
	}
}
