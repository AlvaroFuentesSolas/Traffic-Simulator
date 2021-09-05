package es.ucm.fdi.model;

public class NewLanesRoadEvent extends NewRoadEvent
{

    public NewLanesRoadEvent(int time, String id, String src, String dest, int max_speed, int length)
    {
        super(time, id, src, dest, max_speed, length);
    }

    public void execute(RoadMap roadmap, int i)
    {
    	
    }

    public String toString()
    {
        return "New Lanes Road Event";
    }
}
