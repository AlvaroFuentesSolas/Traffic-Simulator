package es.ucm.fdi.model;

import java.util.List;

import es.ucm.fdi.ini.IniSection;

public class Bike extends Vehicle {

    public Bike(String id, int maxSpeed, List<Junction> itinerary) {
        super(id, maxSpeed, itinerary);
    }
    
    @Override
    protected void fillReportDetails(IniSection iniSection) {
        // TODO Bike fillReportDetails implementar
        super.fillReportDetails(iniSection);
    }
    
    @Override
    void makeFaulty(int time) {
        // TODO Auto-generated method stub
        super.makeFaulty(time);
    }
}
