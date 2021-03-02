package by.training.console;

import by.training.domain.Bouquet;
import by.training.domain.florets.Flower;

public class PrintBouquet {
    public static void print(Bouquet bouquet) {
        if(bouquet == null) return;
        System.out.println(String.format("Букет упакованный в \"%s\". Цена - %d бел. руб. Внутри %d ед. цветов.",
                bouquet.getAccessory().getName(), bouquet.getPrice(), bouquet.getFlowerList().size()));
        for(Flower f : bouquet.getFlowerList()) {
            PrintFlowerFullInfo.print(f);
        }
    }
}
