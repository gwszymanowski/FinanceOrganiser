package model;

public class Price {

	private double estimated;
	private double actual;

	public Price() {
		this.estimated = 0.0;
		this.estimated = 0.0;
	}

	public Price(double estimated, double actual) {

		if (estimated < 0 || actual < 0)
			throw new IllegalArgumentException("Values must be > 0");

		this.estimated = estimated;
		this.actual = actual;
	}

	public double getEstimated() {
		return estimated;
	}

	public void setEstimated(double estimated) {

		if (estimated < 0)
			throw new IllegalArgumentException("Value must be > 0");

		this.estimated = estimated;
	}

	public double getActual() {
		return actual;
	}

	public void setActual(double actual) {

		if (actual < 0)
			throw new IllegalArgumentException("Value must be > 0");

		this.actual = actual;
	}

}
