package es.ucm.fdi.control.eventbuilders;

import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.Event;
import es.ucm.fdi.model.NewMostCrowededJunctionEvent;

public class NewMostCrowdedJunctionEventBuilder extends EventBuilder {
    
    
    public NewMostCrowdedJunctionEventBuilder() {
        // TODO NewMostCrowdedJunctionEventBuilder NewMostCrowdedJunctionEventBuilder implementar constructor
    }

    @Override
    public Event parse(IniSection is) {
        // TODO NewMostCrowdedJunctionEventBuilder comprobar la validez de los valores
        if(!is.getTag().equals(this.tag))
            return null;

        String id = is.getValue("id");
        Integer time = Integer.parseInt(is.getValue("time"));
        return new NewMostCrowededJunctionEvent(time, id);
    }
    
    @Override
    public String toString() {
        // TODO NewMostCrowdedJunctionEventBuilder toString necesita especificion
        return super.toString();
    }


}
