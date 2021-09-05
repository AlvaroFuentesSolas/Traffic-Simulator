package es.ucm.fdi.model;

public class LanesRoad extends Road {

	public LanesRoad(String s, int i, int j, Junction j1, Junction j2) {
		super(s, i, j, j1, j2);
	}

	public int getNumLanes() {
		return length;
	}

	int calculateBaseSpeed(int i) {
		return i;
	}

	protected int reduceSpeedFactor(int i) {
		return i;
	}
}
