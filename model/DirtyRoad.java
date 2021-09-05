package es.ucm.fdi.model;

public class DirtyRoad extends Road{

    public DirtyRoad(String id, int length, int maxSpeed, Junction src, Junction dest) {
        super(id, length, maxSpeed, src, dest);
    }
    
    @Override
    protected int calculateBaseSpeed() {
        // TODO DirtRoad calculateBaseSpeed implementar
        return super.calculateBaseSpeed();
    }
    
    @Override
    protected int reduceSpeedFactor(int j) {
        // TODO DirtRoad reduceSpeedFactor implementar
        return super.reduceSpeedFactor(j);
    }

}
