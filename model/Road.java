package es.ucm.fdi.model;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.misc.SortedArrayList;

public class Road extends SimulatedObject {

	protected int length; 		// longitud de la carretera
	protected int maxSpeed; 	// limite máximo de velocidad
	protected Junction srcJunc; // cruce origen de la carretera
	protected Junction destJunc;// cruce destino de la carretera
	protected List<Vehicle> vehicles; 	// lista de vehículos que circulan por la carretera
								// 	ordenados por su localización (la
								// 	localización 0 es el último vehículo)
	private Comparator<Vehicle> vehicleComparator = new Comparator<Vehicle>() {
		@Override
		public int compare(Vehicle o1, Vehicle o2) {
			if(o1.getLocation() == o2.getLocation()) {
				return 0;
			} else if(o1.getLocation() > o2.getLocation()) {
				return -1;
			} else { return 1; }
		}
	};

	public Road(String id, int length, int maxSpeed, Junction src, Junction dest) {
		super(id);
		this.length = length;
		this.maxSpeed = maxSpeed;
		this.srcJunc = src;
		this.destJunc = dest;
		vehicles = new SortedArrayList<Vehicle>(vehicleComparator);
	}

	/**
	 * Este método es invocado por el simulador para dar un paso en el estado de
	 * la carretera y, en particular, para decir a cada coche de esa carretera
	 * que avance.
	 * 
	 * @param i
	 */
	void advance(int time) {
		int baseSpeed = calculateBaseSpeed();
		
		for (int i = 0; i < vehicles.size(); i++) {
			int reductionFactor = reduceSpeedFactor(i);
			vehicles.get(i).setSpeed(baseSpeed / reductionFactor);
		}
		for (int j = 0; j < vehicles.size(); j++) {
			vehicles.get(j).advance(time);
		}
		vehicles.sort(vehicleComparator);
	}

	/**
	 * Añade un vehículo a la lista de vehículos de la carretera. Recuerda
	 * ordenar los vehículos después de sumar a la lista. Este método lo invocan
	 * los vehículos cuando necesitan entrar en la carretera.
	 * 
	 * @param vehicle
	 */
	void enter(Vehicle vehicle) {
		// TODO Vehicle enter completar
		vehicles.add(vehicle);
	}

	/**
	 * Elimina un vehículo de la carretera. Este método también lo invocan los
	 * vehículos cuando tienen que abandonar una carretera.
	 * 
	 * @param vehicle
	 */
	public void exit(Vehicle vehicle) {
		vehicles.remove(vehicle);
	}

	/**
	 * Rellena los valores de una sección ini con los atributos de la carretera
	 * para mostrar un informe de su estado en uno de los pasos de simulación.
	 * 
	 * @return
	 */
	@Override
	protected void fillReportDetails(IniSection iniSection) {
		iniSection.setValue("state", vehicleListToString());
	}
	
	private String vehicleListToString() {
		Iterator<Vehicle> it = vehicles.iterator();
		StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {
			Vehicle v = it.next();
			sb.append("(" + v.getId() + "," + v.getLocation() + ")");
			if(it.hasNext()) sb.append(",");
		}
		return sb.toString();
	}
	
	@Override
	protected String getReportSectionTag() {
		return "road_report";
	}

	protected int calculateBaseSpeed() {
		return Math.min(maxSpeed, (int)(maxSpeed / Math.max(vehicles.size(), 1) + 1));
	}

	protected int reduceSpeedFactor(int vehicleIndex) {
		while(--vehicleIndex >= 0)
			if (vehicles.get(vehicleIndex).getFaultyTime() > 0)
				return 2;
		return 1;
	}

	public Junction getSource() {
		return srcJunc;
	}

	public Junction getDestination() {
		return destJunc;
	}

    public int getLength() {
    	return length;
    }
    
	public int getMaxSpeed() {
		return maxSpeed;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	
	@Override
	public String toString() {
		return id;
	}

}
