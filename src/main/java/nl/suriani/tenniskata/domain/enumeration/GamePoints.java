package nl.suriani.tenniskata.domain.enumeration;

public enum GamePoints {
	LOVE("0"),
	FIFTEEN("15"),
	THIRTY("30"),
	FOURTY("40"),
	ADVANTAGE_IN("40A");

	private final String display;

	GamePoints(String display) {
		this.display = display;
	}

	public String display() {
		return display;
	}
}
