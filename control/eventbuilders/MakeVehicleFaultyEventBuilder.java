package es.ucm.fdi.control.eventbuilders;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.Event;
import es.ucm.fdi.model.MakeVehicleFaultyEvent;
import es.ucm.fdi.model.SimulatorError;

public class MakeVehicleFaultyEventBuilder extends EventBuilder{
	
	/**
	 * Generador de eventos defectuosos para vehículos
	 */
	public MakeVehicleFaultyEventBuilder(){
		tag = "make_vehicle_faulty";
	}
	
	/**
	 * Análisis del evento
	 */
	public Event parse(IniSection is){
		if(!is.getTag().equalsIgnoreCase(this.tag)) return null;
		String strTime = is.getValue("time");
		if(!isValidNumber(strTime)) throw new SimulatorError("invalid number"); 
		Integer time = Integer.parseInt(strTime);
		String[] vehiclesArr = is.getValue("vehicles").split("[ ,]+");
		for (String vehicleId : vehiclesArr) {
			if(!isValid(vehicleId)) throw new SimulatorError("invalid vehicle list");
		}
		String strDuration  = is.getValue("duration");
		if(!isValidNumber(strTime)) throw new SimulatorError("invalid number");
		Integer duration = Integer.parseInt(strDuration);
		if(duration < 1) throw new SimulatorError("number not positive");
		return new MakeVehicleFaultyEvent(time, duration, vehiclesArr);
	}
	
	/**
	 * 
	 */
	public String toString(){
		return "new MakeVehicleFaultyEvent";
	}
}
