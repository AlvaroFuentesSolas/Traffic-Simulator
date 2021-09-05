package es.ucm.fdi.control.eventbuilders;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.Event;

//para coger el valor asociado a cada clave = ini.getValue(key)
//String[] list = sec.getValue(ini.getValue(key)).split("[ ,]+")

public abstract class EventBuilder {
	protected String tag;
	protected String[] keys;
	protected String[] defaultValues;

	public EventBuilder() {

	}

	public abstract Event parse(IniSection is);

    static boolean isValid(String id){
        return id!= null && id.matches("[a-z0-9_]+");
    }
    
    static boolean isValidNumber(String str) {
    	return str.chars().allMatch(Character::isDigit);
    }

}
