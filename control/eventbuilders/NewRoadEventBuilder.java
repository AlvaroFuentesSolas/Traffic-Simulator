package es.ucm.fdi.control.eventbuilders;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.Event;
import es.ucm.fdi.model.NewRoadEvent;

public class NewRoadEventBuilder extends EventBuilder{

	/**
	 * Nuevo constructor de eventos de carretera
	 */
	public NewRoadEventBuilder(){
		tag = "new_road";
		keys = new String[] {"time", "id", "src", "dest", "length", "max_speed"};
	}

	@Override
	public Event parse(IniSection is){
		// TODO NewRoadEventBuilder comprobar valor de los valores
		if(!is.getTag().equals(this.tag))
			return null;
		String id = is.getValue("id");
		Integer time = Integer.parseInt(is.getValue("time"));
		Integer max_speed = Integer.parseInt(is.getValue("max_speed"));
		String src = is.getValue("src");
		String dest = is.getValue("dest");
		Integer length = Integer.parseInt(is.getValue("length"));
		return new NewRoadEvent(time, id, src, dest, max_speed, length);
	}

	@Override
	public String toString(){
		return null;
	}
}
