package by.training;

import by.training.console.PrintBouquet;
import by.training.domain.Bouquet;
import by.training.domain.PackingAccessory;
import by.training.domain.florets.*;
import by.training.model.serializable.SerializableData;
import by.training.model.serializable.SerializableDataImpl;
import by.training.model.service.BouquetService;
import by.training.model.service.BouquetServiceImpl;

import static by.training.enums.Freshness.*;
import static by.training.enums.TulipFlowerShape.*;


public class Runnable {
    public static void main(String[] args) {

        Flower rose = new Rose(NORMAL, 40, 2, true);
        Flower tulip = new Tulip(NORMAL, 44, 1, GOBLET);
        Flower peony = new Peony(BEST, 50, 3, 25);
        Flower carnation = new Carnation(BAD, 34, 1, 1);
        PackingAccessory accessory = new PackingAccessory("Средняя упаковка", 3, 6);
        Bouquet bouquetSerExample = new Bouquet(accessory, rose, tulip, peony, carnation); // собираем букет

        SerializableData serializableData = new SerializableDataImpl();
        serializableData.saveBouquet(bouquetSerExample, "data/BouquetMiddle.dat"); // сохраняем букет

        Bouquet bouquet = serializableData.loadBouquet("data/BouquetMiddle.dat"); // загружаем букет
        PrintBouquet.print(bouquet);
        System.out.println();

        BouquetService service = new BouquetServiceImpl();
        service.sortByFreshness(bouquet); // сортируем по уровню свежести
        PrintBouquet.print(bouquet);
        System.out.println();

        service.findFlowerByStem(bouquet, 35, 50); // проводим поиск по длинне стебля
    }
}
