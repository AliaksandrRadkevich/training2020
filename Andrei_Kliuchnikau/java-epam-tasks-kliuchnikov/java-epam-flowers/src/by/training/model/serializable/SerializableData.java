package by.training.model.serializable;

import by.training.domain.Bouquet;

public interface SerializableData {
    void saveBouquet(Bouquet bouquet, String relativePath);
    Bouquet loadBouquet(String relativePath);
}
