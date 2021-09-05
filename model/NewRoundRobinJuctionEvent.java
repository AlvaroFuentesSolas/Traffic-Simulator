package es.ucm.fdi.model;

public class NewRoundRobinJuctionEvent extends NewJunctionEvent {
    
    protected int maxTimeSlice;
    protected int minTimeSlice;

    public NewRoundRobinJuctionEvent(int time, String id, int minTimeSlice, int maxTimeSlice) {
        super(time, id);
        this.minTimeSlice = minTimeSlice;
        this.maxTimeSlice = maxTimeSlice;
    }

    @Override
    public void execute(RoadMap roadMap, int time) {
        // TODO NewRoundRobinJunctionEvent execute implementar inserted by daniel [13 mar. 2018 2:10:32]
        super.execute(roadMap, time);
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}
