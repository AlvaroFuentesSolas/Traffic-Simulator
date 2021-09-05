package es.ucm.fdi.control.eventbuilders;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.Event;
import es.ucm.fdi.model.NewVehicleEvent;
import es.ucm.fdi.model.SimulatorError;

public class NewVehicleEventBuilder extends EventBuilder{
	
	/**
	 * Nuevo constructor de eventos de vehículos
	 */
	public NewVehicleEventBuilder(){
		tag = "new_vehicle";
		keys = new String[] {"id", "time", "max_speed", "itinerary"};
		//defaultValues = new String[] { "v1", "0", "40", "j1" };
	}
    @Override
	public Event parse(IniSection is){
		// TODO NewVehicleEventBuilder comprobar la validez de los valores
    	if(!is.getTag().equalsIgnoreCase(this.tag)) return null;
		String id = is.getValue("id");
		if(!isValid(id)) throw new SimulatorError("id is not valid");
		String timestr = is.getValue("time");
   		if(!isValidNumber(timestr)) throw new SimulatorError("time not a valid number");
   		Integer time = Integer.parseInt(timestr);
   		if(time < 0) throw new SimulatorError("time is negative");
        String[] itinerary = is.getValue("itinerary").split("[ ,]+");
        if(itinerary.length < 2) throw new SimulatorError("itinerary must be greater than 2");
		Integer max_speed = Integer.parseInt(is.getValue("max_speed"));
		return new NewVehicleEvent(time, id, max_speed, itinerary);
	}
	
    @Override
	public String toString(){
		return "new NewVehicleEventBuilder";
	}
}
