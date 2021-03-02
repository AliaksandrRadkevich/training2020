package jvd.vitebsk.task1.variant2.view_action;

import java.util.ArrayList;

import jvd.vitebsk.task1.variant2.gift.Gift;
import jvd.vitebsk.task1.variant2.logic.Logic;
import jvd.vitebsk.task1.variant2.sweets.Sweets;

public class UserViewAction {

		private ViewAction act = new ViewAction();
		private ShowSweetsInfo show = new ShowSweetsInfo();
		private Gift gift = new Gift();
		private Logic logic = new Logic();

		public void selectAndShowInformation() {

			int choice, a, b;
			ArrayList<Sweets> newGift;
			ArrayList<Sweets> gift2;
			

			show.printMassage("Программа сформировала следующий подарок: ");

			newGift = gift.createAGift();

			show.printSweetsInfo(newGift);

			show.printMassage("Вес подарка: " + logic.giftWeight(newGift) + " гр.");

			choice = -1;
			
			while(choice < 1 || choice > 3) {
			choice = act.enterInt(
					"Введите соответствующую цифру: \r\n 1 - отсортировать по типу и названию сладостей; \r\n 2 - отсортировать по удельному весу в подарке; \r\n 3 - отсортировать по содержанию сахара; >> ");
			}
			
			switch (choice) {
			case 1:
				logic.sortNameSweets(newGift);
				show.printSweetsInfo(newGift);
				break;

			case 2:
				logic.sortingByWeight(newGift);
				;
				show.printSweetsInfo(newGift);
				break;

			case 3:
				logic.sortSugarContent(newGift);
				;
				;
				show.printSweetsInfo(newGift);
				break;

			default:
				show.errorMessage("invalid request");
				break;
			}

			show.printMassage("Выбор сладости по диапазону содержания сахара: ");

			a = -1;
			b = -1;
			
			while(a < 0 || a > 100) {
				a = act.enterInt("введите нижний диапазон (от 0 до 100) >> ");
			}
			
			while(b < 0 || b > 100) {
				b = act.enterInt("введите верхний диапазон (от 0 до 100) >> ");
			}
			

			gift2 = logic.findSweetsBySugarContent(newGift, a, b);

			if (gift2.size() != 0) {
				show.printSweetsInfo(gift2);
			} else {
				show.errorMessage("Сладостей с заданными параметрами нет в подарке.");
			}
		}
	}

