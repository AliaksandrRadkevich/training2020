package jvd.vitebsk.task1.variant2.view_action;

//Новогодний подарок. Определить иерархию конфет и прочих сладостей. Создать несколько объектов-конфет. Собрать детский
//подарок с определением его веса. Провести сортировку конфет в подарке на основе одного из параметров. Найти конфету 
//в подарке, соответствующую заданному диапазону содержания сахара.

import java.util.ArrayList;
import java.util.Scanner;
import jvd.vitebsk.task1.variant2.sweets.Sweets;

public class ViewAction {

	public ViewAction() {
	}

	public String enterString(String massage) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		String request;

		System.out.println(massage);

		while (!sc.hasNextLine()) {
			sc.next();
			System.out.println(massage);
		}
		request = sc.nextLine();
		sc.nextLine();

		return request;
	}

	public int enterInt(String massage) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		int number;

		System.out.println(massage);

		while (!sc.hasNextInt()) {
			sc.next();
			System.out.println(massage);
		}
		number = sc.nextInt();
		return number;
	}
}

class ShowSweetsInfo {

	public void showAvailableInfo(ArrayList<Sweets> gift, String massage) {

		System.out.println(massage);

		System.out.print(gift.toArray());

	}

	public void printMassage(String massage) {

		System.out.println(massage);

		System.out.println();
	}

	public void printSweetsInfo(ArrayList<Sweets> gift) {

		for (Sweets g : gift) {
			System.out.print(g); // printItemList(g);
			System.out.println();
		}
		System.out.println();
	}

	public void showArrayString(ArrayList<String> arrayString) {

		System.out.println(arrayString);

		System.out.println();
	}

	public void errorMessage(String error) {

		System.out.println(error);

		System.out.println();
	}
}

