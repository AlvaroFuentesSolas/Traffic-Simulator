package es.ucm.fdi.model;

public class NewCarEvent extends NewVehicleEvent
{

    public NewCarEvent(Integer time, String id, Integer max_speed, String itinerary[])
    {
        super(time, id, max_speed, itinerary);
    }

    public void execute(RoadMap roadmap, int i)
    {
        throw new Error("Unresolved compilation problem: \n\tThis method requires a body instead of a semicolon\n");
    }

    public String toString()
    {
        return "New Car Event";
    }
}
