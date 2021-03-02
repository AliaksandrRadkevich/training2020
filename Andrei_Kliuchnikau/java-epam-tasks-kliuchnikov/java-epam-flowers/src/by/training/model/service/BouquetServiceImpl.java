package by.training.model.service;

import by.training.console.PrintFlowerFullInfo;
import by.training.domain.Bouquet;
import by.training.domain.florets.Flower;
import java.util.Comparator;

public class BouquetServiceImpl implements BouquetService {
    @Override
    public void sortByFreshness(Bouquet bouquet) {
        bouquet.getFlowerList().sort(new Comparator<Flower>() {
            @Override
            public int compare(Flower f1, Flower f2) {
                return f2.getFreshness().getFreshLevel() - f1.getFreshness().getFreshLevel();
            }
        });
    }

    @Override
    public void findFlowerByStem(Bouquet bouquet, int from, int to) {
        int counter = 0;
        for (Flower f : bouquet.getFlowerList()) {
            if (f.getStemLength() >= from && f.getStemLength() <= to) {
                counter++;
                System.out.print(String.format("Цветок с подходящим стеблем (от %d до %d) найден! ", from, to));
                PrintFlowerFullInfo.print(f);
            }
        }
        if (counter == 0) System.out.println("К сожалению, по Вашему запросу ничего не найдено :(");
    }
}
