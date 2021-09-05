package es.ucm.fdi.model;

public class NewRoadEvent extends Event {

	String id, src, dest;
	int maxSpeed, length;

	public NewRoadEvent(Integer time, String id, String src, String dest, int maxSpeed, int length) {
		super(time);
		this.id = id;
		this.src = src;
		this.dest = dest;
		this.maxSpeed = maxSpeed;
		this.length = length;
	}

	/**
	 * direccion unica
	 * compuesta por: cruce fuente y destino
	 */
	public void execute(RoadMap roadMap, int time) {
		if (this.time == time) {
            Junction src = roadMap.getJunction(this.src);
            Junction dest = roadMap.getJunction(this.dest);
            Road road = new Road(this.id, this.length, this.maxSpeed, src, dest);
            if(src != null && dest != null) {
                roadMap.addRoad(road);
                dest.addIncommingRoad(road);
            }

		}
	}

	public String toString() {
		return "New Road";
	}
}
