package jvd.vitebsk.task1.variant2.sweets;

public class Candy extends Sweets {

	private String[] allname = new String[] { "Ириски", "Антошка", "Трюфель" };
	private String type = "конфеты";

	public Candy(int sugarContent, int weight, int costPerKg, String type, String name) {

		super(sugarContent, weight, costPerKg, type, name); // проверить работу

	}

	public Candy() {
	}

	@Override
	public void setName(String name) {
		this.name = name;
		if (name == "Ириски" || name == "Антошка" || name == "Трюфель") {
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
