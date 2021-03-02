package jvd.vitebsk.task1.variant2.sweets;

public class Chocolate extends Sweets {

	private String[] allname = new String[] { "Аленка", "Nestle", "Горький шоколад" };
	private String type = "шоколад";

	public Chocolate(int sugarContent, int weight, int costPerKg, String type, String name) {

		super(sugarContent, weight, costPerKg, type, name); 

	}

	public Chocolate() {
	}

	@Override
	public void setName(String name) {
		this.name = name;
		if (name == "Аленка" || name == "Nestle" || name == "Горький шоколад") {
			this.name = name;
		} else {
			this.name = "Название введено некорректно";
		}
	}

	@Override
	public String getType() {
		return type;
	}

	public String[] getAllname() {
		return allname;
	}

	public void setAllname(String[] allname) {

		this.allname = allname;

	}

}
