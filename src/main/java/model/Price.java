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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(actual);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(estimated);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (Double.doubleToLongBits(actual) != Double.doubleToLongBits(other.actual))
			return false;
		if (Double.doubleToLongBits(estimated) != Double.doubleToLongBits(other.estimated))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Price [estimated=" + estimated + ", actual=" + actual + "]";
	}

}
