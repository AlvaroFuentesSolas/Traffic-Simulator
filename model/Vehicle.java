package es.ucm.fdi.model;

import java.util.List;

import es.ucm.fdi.ini.IniSection;

/*
 * - Cuando un vehÃ­culo estÃ¡ averiado no puede circular y 
 * los vehÃ­culos detrÃ¡s de Ã©l en una carretera, van mas despacio.
 * - Un vehÃ­culo averiado se repara despuÃ©s de un determinado 
 * nÃºmero de pasos de simulaciÃ³n.
 */

public class Vehicle extends SimulatedObject {

	protected int maxSpeed; // mÃ¡xima velocidad
	protected int currSpeed; // velocidad actual
	private Road road; // carretera por la que viaja
	private int location; // localizaciÃ³n en la carretera (desde 0)
	protected List<Junction> itinerary; // lista de cruces
	protected int kilometrage; // distancia recorrida por el vehÃ­culo
	protected int faultyTime; // tiempo que resta en estado averiado
	protected boolean atJunction; // true si ha entrado en cola de cruce
	protected boolean arrived; // true cuando el coche llega a su destino 
	private int junctionIndex;


	/**
	 * comprobar que la velocidadMaxima es mayor o igual que 0, y 
	 * que el itinerario tiene al menos dos cruces. 
	 * En caso de no cumplirse lo anterior, lanzar una excepciÃ³n. 
	 * inicializar los atributos teniendo en cuenta los parÃ¡metros. 
	 * al crear un vehÃ­culo su â€œcarreteraâ€� serÃ¡ inicalmene â€œnullâ€�.
	 * 
	 * @param id
	 * @param maxSpeed
	 * @param itinerary
	 */
	public Vehicle(String id, int maxSpeed, List<Junction> itinerary) {
		super(id);
		this.maxSpeed = maxSpeed;
		this.itinerary = itinerary;
		this.junctionIndex = 1;
		moveToNextRoad();
	}

	/**
	 * - con esta operaciÃ³n el vehÃ­culo avanza. Primero comprueba si esta
	 * averiado o no. Si esta averiado decrementa faultyTime en 1 y no se mueve.
	 * 
	 * Si no estÃ¡ averiado (faultyTime es 0) entonces avanza su posicioÃ³n segÃºn
	 * su velocidad actual. La nueva localizaciÃ³n es la localizaciÃ³n anterior
	 * mas la velocidad actual. Si la nueva localizaciÃ³n es igual o mayor a la
	 * longitud de la carretera entonces el vehÃ­culo entrara en la cola del
	 * correspondiente cruce. Los vehÃ­culos que esperan en la cola de un cruce
	 * no pueden avanzar y permanecen en esta cola hasta que el cruce determine
	 * que el vehÃ­culo debe moverse a la siguiente carretera, invocando el
	 * mÃ©todo moveToNextRoad().
	 * 
	 * @param i
	 */
    @Override
    void advance(int time) {
		if (this.faultyTime > 0) {
			this.faultyTime--;
            this.currSpeed = 0;
		} else {
            // TODO Vehicle advance verificar implementaciï¿½n
			if(!atJunction) { //si no esta en el cruce
				this.location = location + currSpeed;
				this.kilometrage += currSpeed;
                if (this.location >= road.getLength()) {
                    this.atJunction = true;
                    this.currSpeed = 0;
                    this.road.getDestination().enter(this);  
                    this.kilometrage-=(this.location - road.getLength());
                    this.location = road.getLength();
                } 
            }
		} 
    }
    
	/**
	 * Rellena los valores de una secciÃ³n ini con los atributos de la carretera
	 * para mostrar un informe de su estado en uno de los pasos de simulaciÃ³n.
	 * 
	 * @return
	 */
	@Override
	protected void fillReportDetails(IniSection iniSection) {
		iniSection.setValue("speed", currSpeed);
		iniSection.setValue("kilometrage", kilometrage);
		iniSection.setValue("faulty", faultyTime);
		iniSection.setValue("location", (arrived) ? "arrived" : "(" + road.getId() + "," + location	+ ")");
	}

	@Override
	protected String getReportSectionTag() {
		return "vehicle_report";
	}

	/**
	 * solicita al vehÃ­culo que se mueva a la siguiente carretera. Para ello el
	 * vehÃ­culo sale de su carretera actual y entra en la siguiente que esta en
	 * su itinerario, en la localizaciÃ³n 0. Observa que la primera vez que se
	 * ejecuta este mÃ©todo no hay ninguna carretera saliente puesto que no ha
	 * entrado todavÃ­a en ninguna carretera. AdemÃ¡s si el vehÃ­culo sale de la
	 * Ãºltima carretera de su itinerario no entra en ninguna y arrived toma el
	 * valor true.
	 */
	void moveToNextRoad() {
		this.location = 0;
		this.currSpeed = 0;
		if (junctionIndex >= this.itinerary.size()){
			this.road = null;
			this.arrived = true;
			this.atJunction = true;
			this.currSpeed = 0;
	    } else { 
	    	if(this.road != null) this.road.exit(this);
	        this.road = this.itinerary
	        		.get(junctionIndex-1)
	                .roadTo(this.itinerary.get(junctionIndex));
	        junctionIndex++;
	        this.location = 0;
	        this.atJunction = false;
	        if(this.road != null) road.enter(this);
	    }

	}

	/**
	 * Pone el estado del vehÃ­culo en modo averÃ­a durante n pasos. Si el
	 * vehÃ­culo ya estÃ¡ averiado acumula n al valor del contador de tiempo de
	 * averÃ­a (faultyTime).
	 * 
	 * Comprobar que â€œcarreteraâ€� no es null. 
	 * Se fija el tiempo de averÃ­a de acuerdo con el enunciado.
	 * Si el tiempo de averÃ­a es finalmente positivo, entonces
	 * la â€œvelocidadActualâ€� se pone a 0
	 * @param time
	 */
	void makeFaulty(int time) {
		faultyTime += time;
		this.currSpeed = 0;
	}

	/**
	 * Pone la velocidad actual del vehÃ­culo a la que se indica por parÃ¡metro.
	 * Este mÃ©todo es utilizado por las carreteras para fijar la velocidad de
	 * los vehÃ­culos. Si la velocidad que se desea poner excede la velocidad
	 * mÃ¡xima del vehÃ­culo se pone igual a dicha velocidad mÃ¡xima.
	 * 
	 * @param i
	 */
	void setSpeed(int newSpeed) {
		if(newSpeed <= this.maxSpeed) this.currSpeed = newSpeed;
		else this.currSpeed = this.maxSpeed;
	}

	public Road getRoad() {
		return this.road;
	}

	public int getMaxSpeed() {
		return this.maxSpeed;
	}

	public int getSpeed() {
		if (atJunction)
			return 0;
		return this.currSpeed;
	}

	public int getLocation() {
		return this.location;
	}

	public int getKilometrage() {
		return this.kilometrage;
	}

	public List<Junction> getItinerary() {
		return this.itinerary;
	}

	/**
	 * Ha llegado al cruce
	 * @return
	 */
	public boolean atDestination() {
		return this.arrived;
	}

	public int getFaultyTime() {
		return faultyTime;
	}

}
