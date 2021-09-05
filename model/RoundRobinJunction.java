package es.ucm.fdi.model;

import es.ucm.fdi.ini.IniSection;

public class RoundRobinJunction extends JunctionWithTimeSlice {

    public RoundRobinJunction(String id, int minTimeSlice, int maxTimeSlice) {
        super(id);
        this.minTimeSlice = minTimeSlice;
        this.maxTimeSlice = maxTimeSlice;
    }
    
    @Override
    protected IncomingRoad createIncommingRoadQueue(Road road) {
        // TODO RoundRobinWithTimeSlice createIncommingRoadQueue implementar
        return super.createIncommingRoadQueue(road);
    }
    
    @Override
    protected void switchLights() {
        // TODO RoundRobinWithTimeSlice switchLights implementar
        super.switchLights();
    }
    
    @Override
    protected void fillReportDetails(IniSection iniSection) {
        // TODO RoundRobinWithTimeSlice fillReportDetails implementar
        super.fillReportDetails(iniSection);
    }
    
    protected void turnLightOff(IncomingRoadWithTimeSlice incomingRoadWithTimeSlice) {
        
    }
    
    protected void turnLightOn(IncomingRoadWithTimeSlice incomingRoadWithTimeSlice) {
        
    }
    
}
