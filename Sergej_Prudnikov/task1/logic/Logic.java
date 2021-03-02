package jvd.vitebsk.task1.variant2.logic;

import java.util.ArrayList;
import java.util.Collections;

import jvd.vitebsk.task1.variant2.sweets.Sweets;

//Новогодний подарок. Определить иерархию конфет и прочих сладостей. Создать несколько объектов-конфет. Собрать детский
//подарок с определением его веса. Провести сортировку конфет в подарке на основе одного из параметров. Найти конфету 
//в подарке, соответствующую заданному диапазону содержания сахара.

public class Logic {

	public Logic() {
	}

	public void sortSugarContent(ArrayList<Sweets> gift) {
		int i, content1, content2;
		i = 1;

		while (i < gift.size() - 1) {

			content1 = gift.get(i).getSugarContent(); // масса сладости в в списке под номером i
			content2 = gift.get(i + 1).getSugarContent();

			if (content1 > content2) {

				Sweets temp = new Sweets();

				temp = gift.get(i); // temp = mas[i];

				gift.set(i, gift.get(i + 1)); // mas[i] = mas[i + 1];
				gift.set(i + 1, temp); // mas[i + 1] = temp;
				i--;
				if (i < 0) {
					i = 0;
				}
			} else {
				i++;
			}
		}
	}

	public void sortingByWeight(ArrayList<Sweets> gift) { // sortShella

		int i, weight1, weight2;
		i = 1;

		while (i < gift.size() - 1) {

			weight1 = gift.get(i).getWeight(); // масса сладости в в списке под номером i
			weight2 = gift.get(i + 1).getWeight();

			if (weight1 > weight2) {

				Sweets temp = new Sweets();

				temp = gift.get(i); // temp = mas[i];

				gift.set(i, gift.get(i + 1)); // mas[i] = mas[i + 1];
				gift.set(i + 1, temp); // mas[i + 1] = temp;
				i--;
				if (i < 0) {
					i = 0;
				}
			} else {
				i++;
			}
		}
	}

	public int giftWeight(ArrayList<Sweets> gift) {

		int i, weightAll, weightI;
		weightAll = 0;

		for (i = 0; i < gift.size(); i++) {

			weightI = gift.get(i).getWeight(); // масса сладости в в списке под номером i
			weightAll = weightAll + weightI;
		}
		return weightAll;
	}

	public ArrayList<Sweets> findSweetsBySugarContent(ArrayList<Sweets> gift, int a, int b) {

		ArrayList<Sweets> gift2 = new ArrayList<Sweets>();

		int i, j, sugarContent;
		sugarContent = 0;
		j = 0;

		for (i = 0; i < gift.size(); i++) {

			sugarContent = gift.get(i).getSugarContent();
			if (sugarContent >= a && sugarContent <= b) {

				gift2.add(j, gift.get(i));
				j++;
			}
		}
		return gift2;
	}

	public void sortNameSweets(ArrayList<Sweets> gift) {

		Collections.sort(gift);
	}
}
