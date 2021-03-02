package by.training.console;

import by.training.domain.florets.*;

public class PrintFlowerFullInfo {
    public static void print(Flower flower) {
        StringBuilder stringBuilder = new StringBuilder(String.format("Цветок: %s. Свежесть - %s, " +
                        "длинна стебля - %d см, стоимость - %d бел. руб., в букете - ", flower.getName(),
                flower.getFreshness().getCyrillicFreshness(), flower.getStemLength(), flower.getCost()));
        stringBuilder.append(flower.isInBouquet() ? "да" : "нет");
        if (flower instanceof Carnation) {
            stringBuilder.append(", колличество цветов в бутоне - ").append(((Carnation) flower).getNumberFlowersInBud());
        } else if (flower instanceof Peony) {
            stringBuilder.append(", диаметр цветка - ").append(((Peony) flower).getFlowerDiameter()).append(" см.");
        } else if (flower instanceof Rose) {
            stringBuilder.append(", с шипами - ").append(((Rose) flower).isHasThorns() ? "да" : "нет");
        } else if (flower instanceof Tulip) {
            stringBuilder.append(", форма цветка - ").append(((Tulip) flower).getFlowerShape().getCyrillicForm());
        }
        System.out.println(stringBuilder);
    }
}
