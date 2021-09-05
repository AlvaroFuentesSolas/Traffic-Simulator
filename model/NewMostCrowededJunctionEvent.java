package es.ucm.fdi.model;

public class NewMostCrowededJunctionEvent extends NewJunctionEvent {

    public NewMostCrowededJunctionEvent(Integer time, String id) {
        super(time, id);
    }

    @Override
    public void execute(RoadMap roadMap, int time) {
        // TODO NewMostCrowdedJunctionEvent execute implementar inserted by daniel [13 mar. 2018 2:10:15]
        super.execute(roadMap, time);
    }
    
    @Override
    public String toString() {
        return etiqueta;
    }

}
