package es.ucm.fdi.model;

import es.ucm.fdi.ini.IniSection;

public abstract class SimulatedObject {
	protected String id;

	public SimulatedObject(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public String generateReport(int time) {
		IniSection iniSection = new IniSection(getReportSectionTag());
		iniSection.setValue("id", id);
		iniSection.setValue("time", time);
		fillReportDetails(iniSection);
		return iniSection.toString();
	}

	@Override
	public String toString() {
		return id;
	}

	protected abstract void fillReportDetails(IniSection inisection);
	protected abstract String getReportSectionTag();
	abstract void advance(int time);

}
