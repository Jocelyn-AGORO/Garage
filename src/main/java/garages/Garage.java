package garages;

import java.util.Objects;

public class Garage {

	private String name;

	public Garage(String s) {
		name = s;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (null == name) {
			throw new IllegalArgumentException("name is null");
		}

		this.name = name;
	}

	@Override
	public String toString() {
		return "Garage " + name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Garage garage = (Garage) o;
		return name.equals(garage.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
