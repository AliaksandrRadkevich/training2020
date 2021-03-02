package jvd.vitebsk.task1.variant2.sweets;

// Новогодний подарок. Определить иерархию конфет и прочих сладостей. Создать несколько объектов-конфет. Собрать детский

// подарок с определением его веса. Провести сортировку конфет в подарке на основе одного из параметров. Найти конфету 
// в подарке, соответствующую заданному диапазону содержания сахара.

public class Sweets implements Comparable<Sweets> { // добавляем возможность сравнивать объекты Sweets

	private int sugarContent;
	private int weight;
	private int costPerKg;
	protected String type;
	protected String name;

	public Sweets(int sugarContent, int weight, int costPerKg, String type, String name) {
		this.sugarContent = sugarContent;
		this.weight = weight;
		this.costPerKg = costPerKg;
		this.type = type;
		this.name = name;
	}

	public Sweets() {

	}

	public int getSugarContent() {
		return sugarContent;
	}

	public void setSugarContent(int sugarContent) { // -1 - означает некорректный ввод
		if (sugarContent > 0 && sugarContent <= 100) {
			this.sugarContent = sugarContent;
		} else {
			this.sugarContent = -1;
		}
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		if (weight > -1) {
			this.weight = weight;
		} else {
			this.weight = -1;
		}
	}

	public int getCostPerKg() {
		return costPerKg;
	}

	public void setCostPerKg(int costPerKg) {
		if (costPerKg > 0) {
			this.costPerKg = costPerKg;
		} else {
			this.costPerKg = -1;
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (type == "Candy" || type == "Chocolate" || type == "Biscuits") {
			this.type = type;
		} else {
			this.type = "Тип введен некорректно";
		}
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	// реализуем метод compareTo интерфейса Comparable
	public int compareTo(Sweets o) {

		// используем метод compareTo из класса String для сравнения типов
		int result = this.type.compareTo(o.type);

		// если типы одинаковые - сравниваем названия сладостей,

		if (result == 0) {
			result = this.name.compareTo(o.name);
		}
		return result;
	}

	@Override
	public String toString() {
		return "{" + type + " " + '\'' + name + "', вес '" + weight + '\'' + " гр." + ", стоимость '" + costPerKg
				+ "' руб./кг. " + ", содержание сахара '" + sugarContent + " %" + '\'' + '}';
	}
}
