package es.ucm.fdi.control.eventbuilders;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.Event;
import es.ucm.fdi.model.NewJunctionEvent;

public class NewJunctionEventBuilder extends EventBuilder{

	/**
	 * Nuevo constructor de eventos de carretera de uniones
	 */
	public NewJunctionEventBuilder(){
		//tag = "new_junction";
		tag = "new_junction";
		keys = new String[] {"id", "time"};
	}
	
    @Override
	public Event parse(IniSection is){
    	if(!is.getTag().equals(this.tag))
			return null;
		String id = is.getValue("id");
		Integer time = Integer.parseInt(is.getValue("time"));
		return new NewJunctionEvent(time, id);
	}
	
    @Override
	public String toString(){
		return null;
		
	}
}
