package es.ucm.fdi.model;

import es.ucm.fdi.ini.IniSection;

public class MostCrowdedJunction extends JunctionWithTimeSlice {

    public MostCrowdedJunction(String id) {
        super(id);
    }
    
    @Override
    protected IncomingRoad createIncommingRoadQueue(Road road) {
        // TODO Auto-generated method stub
        return super.createIncommingRoadQueue(road);
    }
    
    @Override
    protected void switchLights() {
        // TODO Auto-generated method stub
        super.switchLights();
    }
    
    @Override
    protected void fillReportDetails(IniSection iniSection) {
        // TODO Auto-generated method stub
        super.fillReportDetails(iniSection);
    }
    
    protected void turnLightOff() {}
    
    protected void turnLightOn() {}

}
