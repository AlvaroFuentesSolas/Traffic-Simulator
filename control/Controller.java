package es.ucm.fdi.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import es.ucm.fdi.control.eventbuilders.EventBuilder;
import es.ucm.fdi.control.eventbuilders.MakeVehicleFaultyEventBuilder;
import es.ucm.fdi.control.eventbuilders.NewJunctionEventBuilder;
import es.ucm.fdi.control.eventbuilders.NewRoadEventBuilder;
import es.ucm.fdi.control.eventbuilders.NewVehicleEventBuilder;
import es.ucm.fdi.ini.Ini;
import es.ucm.fdi.ini.IniSection;
import es.ucm.fdi.model.*;

/*
 * Guarda información sobre los flujos de entrada y de salida (intput stream y output stream) 
 * e invoca a TrafficSimulator una vez establecido el número de pasos de simulación (ticks).
 */
public class Controller {
	protected TrafficSimulator sim; // simulador que usa
	protected OutputStream outputStream; // flujo de salida
	protected InputStream inputStream; // flujo de entrada
	protected int ticks; // pasos de la simulacion dados por el usuario
	EventBuilder[] eventBuilders = { 
			new NewJunctionEventBuilder(), 
			new MakeVehicleFaultyEventBuilder(),
			new NewVehicleEventBuilder(), 
			new NewRoadEventBuilder() }; // array de constructores de eventos

	public Controller(TrafficSimulator simulator, int ticks, InputStream inputStream, OutputStream outputStream) {
		this.sim = simulator;
		this.ticks = ticks;
		this.inputStream = inputStream;
		this.outputStream = outputStream;
	}

	/**
	 * llama a loadEvents() y al método run() del simulador
	 */
	public void run() {
		this.loadEvents(this.inputStream); // cargo los eventos del fichero
		//TODO C1
		this.sim.run(ticks); // ejecutamos la simulacion pasandole el numero de pasos
	}

	/**
	 * llama a loadEvents() y al método run() del simulador
	 */
	public void run(int ticks) {
		this.ticks = ticks;
		loadEvents(inputStream); // cargo los eventos del fichero
		sim.run(ticks); // pasamos los pasos de simulacion al metodo run de TraficSimulation
		run();
	}

	/**
	 * crea un objeto Ini(inStream) y llama al método parse() de los EventBuilder de
	 * su array para parsear todos los eventos. Cada evento parseado lo suma al
	 * simulador.
	 * 
	 * @param inputStream 
	 */
	
	public void loadEvents(InputStream inputStream) { 
		try {
			// creamos un objeto de tipo Ini al que le pasamos el fichero de entrada
			Ini ini = new Ini(inputStream); 
			// recorremos las secciones
			for (IniSection section : ini.getSections()) {
				// llamamos al parse
				Event event = parseEvent(section);
				if(event != null) sim.addEvent(event);
			}
		} catch(FileNotFoundException ex) {
            throw new SimulatorError("Fichero no encontrado");
        } catch (IOException ex) {
            // TODO Controller loadEvents controlar IOException
            throw new SimulatorError("Error de E/S formato invalido");
		}
	}

	/**
	 * devuelve el objeto Event que corresponde a una seccion INI
	 * 
	 * @param section
	 * @return
	 */
	private Event parseEvent(IniSection section) {
		for (EventBuilder eventBuilder : eventBuilders) {
			Event event = eventBuilder.parse(section);
			if (event != null)
				return event;
		}
		return null;
	}

	public void reset() {
		// TODO Controller reset es para la interfaz grafica
		sim.reset();
	}

	public EventBuilder[] getEventBuilders() {
		return eventBuilders;
	}

    public void setEventBuilders(EventBuilder[] eventBuilders) {
        this.eventBuilders = eventBuilders;
    }

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

}
