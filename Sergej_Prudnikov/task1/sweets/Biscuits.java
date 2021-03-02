package jvd.vitebsk.task1.variant2.sweets;

public class Biscuits extends Sweets {

	private String[] allname = new String[] { "Кексы", "Вафли", "Графские развалины" };
	private String type = "бисквит";

	public Biscuits(int sugarContent, int weight, int costPerKg, String type, String name) {

		super(sugarContent, weight, costPerKg, type, name); 
	}

	public Biscuits() {
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		if (name == "Кексы" || name == "Вафли" || name == "Графские развалины") {
			this.name = name;
		} else {
			this.name = "Название введено некорректно";
		}
	}

	public String[] getAllname() {
		return allname;
	}

	public void setAllname(String[] allname) {

		this.allname = allname;

	}
}
