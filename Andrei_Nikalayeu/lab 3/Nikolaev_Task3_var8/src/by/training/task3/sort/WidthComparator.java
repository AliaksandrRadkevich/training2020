package by.training.task3.sort;

import java.util.Comparator;

import by.training.task3.domain.Airplane;

public class WidthComparator implements Comparator<Airplane> {
    @Override
    public int compare(Airplane o1, Airplane o2) {
        return Double.compare(o1.getWidth(), o2.getWidth());
    }
}
