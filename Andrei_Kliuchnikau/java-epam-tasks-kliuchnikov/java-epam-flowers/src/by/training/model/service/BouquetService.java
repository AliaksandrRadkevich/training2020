package by.training.model.service;

import by.training.domain.Bouquet;

public interface BouquetService {
    void sortByFreshness(Bouquet bouquet);
    void findFlowerByStem(Bouquet bouquet, int from, int to);
}
