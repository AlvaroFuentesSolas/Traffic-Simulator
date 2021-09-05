package es.ucm.fdi.model;

public abstract class JunctionWithTimeSlice extends Junction{
    
    protected int maxTimeSlice;
    protected int minTimeSlice;
    
    public JunctionWithTimeSlice(String id) {
        super(id);
    }
}

class IncomingRoadWithTimeSlice extends IncomingRoad{
    public IncomingRoadWithTimeSlice(Road road) {
        super(road);
    }
        
    @Override
    protected void advanceFirstVehicle() {
        // TODO Auto-generated method stub
        super.advanceFirstVehicle();
    }
    
    @Override
    public Road getRoad() {
        // TODO Auto-generated method stub
        return super.getRoad();
    }
    
    // TODO añadir los metodos del uml restantes
}


