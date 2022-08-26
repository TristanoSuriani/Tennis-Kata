package nl.suriani.tenniskata.domain.enumeration;

public enum SetsToWin {
	TWO(2),
	THREE(3);

	private final int display;

	SetsToWin(int display) {
		this.display = display;
	}

	public int getDisplay() {
		return display;
	}
}
