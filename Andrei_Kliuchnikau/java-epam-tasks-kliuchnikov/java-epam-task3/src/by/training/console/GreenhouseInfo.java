package by.training.console;

import by.training.domain.Greenhouse;
import by.training.domain.Plant;

public class GreenhouseInfo {
    public static void printFullInfo(Greenhouse greenhouse) {
        String sep = System.lineSeparator();
        StringBuilder info = new StringBuilder();
        for(Plant plant : greenhouse.getPlantList()) {
            info.append("Растение под номером ").append(plant.getId()).append(sep);
            info.append("Название: ").append(plant.getName()).append(sep);
            info.append("Необходимая для произростания почва: ").append(plant.getSoil().getSoilType()).append(sep);
            info.append("Страна происхождения: ").append(plant.getOrigin()).append(sep);
            info.append("Внешние параметры: ").append(sep);
            info.append("- цвет стебля - ").append(plant.getVisualParameters().getLeavesColor()).append(sep);
            info.append("- цвет листьев - ").append(plant.getVisualParameters().getStemColor()).append(sep);
            info.append("- средний размер растения - ").append(plant.getVisualParameters().getAverageSize()).append(sep);
            info.append("Предпочитаемые условия произрастания: ").append(sep);
            String lightning = plant.getGrowingTips().isLighting() ? "да" : "нет";
            info.append("- температура (в градусах) - ").append(plant.getGrowingTips().getTemperature()).append(sep);
            info.append("- необходимо ли освещение - ").append(lightning).append(sep);
            info.append("- полив (мл в неделю) - ").append(plant.getGrowingTips().getIrrigation()).append(sep);
            info.append("Размножение: ").append(plant.getMultiplying().getMultiplyingType()).append(sep).append(sep);
        }
        System.out.println(info);
    }
}