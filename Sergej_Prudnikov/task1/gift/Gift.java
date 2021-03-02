package jvd.vitebsk.task1.variant2.gift;

import java.util.ArrayList;

import jvd.vitebsk.task1.variant2.sweets.Biscuits;
import jvd.vitebsk.task1.variant2.sweets.Candy;
import jvd.vitebsk.task1.variant2.sweets.Chocolate;
import jvd.vitebsk.task1.variant2.sweets.Sweets;

// Новогодний подарок. Определить иерархию конфет и прочих сладостей. Создать несколько объектов-конфет. Собрать детский
// подарок с определением его веса. Провести сортировку конфет в подарке на основе одного из параметров. Найти конфету 
// в подарке, соответствующую заданному диапазону содержания сахара.

public class Gift {

	public Gift() {
	}

	private Candy candy = new Candy();
	private Chocolate chocolate = new Chocolate();
	private Biscuits biscuits = new Biscuits();

	public ArrayList<Sweets> createAGift() {

		ArrayList<Sweets> gift = new ArrayList<Sweets>();

		for (int i = 0; i < 9; i++) { // чтобы не вносить данные вручную составил цикл

			int j, sugarContent, weight, costPerKg;
			String type, name;

			sugarContent = (int) (Math.random() * 100);

			weight = (int) (Math.random() * 1000);

			costPerKg = (int) (Math.random() * 10 + 2);

			name = "";
			type = "";

			if (i >= 0 && i < 3) { // if для того, чтобы достать с массивов названий сладостей их названия
				j = i;
				name = candy.getAllname()[j];
				type = candy.getType();
				Sweets candy = new Candy(sugarContent, weight, costPerKg, type, name);
				gift.add(i, candy);

			} else if (i >= 3 && i < 6) {
				j = i - 3;
				name = chocolate.getAllname()[j];
				type = chocolate.getType();
				Sweets chocolate = new Candy(sugarContent, weight, costPerKg, type, name);
				gift.add(i, chocolate);

			} else {
				j = i - 6;
				name = biscuits.getAllname()[j];
				type = biscuits.getType();
				Sweets biscuits = new Candy(sugarContent, weight, costPerKg, type, name);
				gift.add(i, biscuits);
			}
		}

		return gift;
	}

}
