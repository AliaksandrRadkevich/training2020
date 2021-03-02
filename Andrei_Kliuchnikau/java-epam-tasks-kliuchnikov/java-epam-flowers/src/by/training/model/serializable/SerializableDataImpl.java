package by.training.model.serializable;

import by.training.domain.Bouquet;
import java.io.*;

public class SerializableDataImpl implements SerializableData {
    @Override
    public void saveBouquet(Bouquet bouquet, String relativePath) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(relativePath))) {
            outputStream.writeObject(bouquet);
        } catch (IOException e) {
            System.out.println("Во время записи данных произошла ошибка!");
            e.printStackTrace();
        }
    }

    @Override
    public Bouquet loadBouquet(String relativePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(relativePath))) {
            return (Bouquet) inputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Во время чтения данных произошла ошибка!");
            e.printStackTrace();
            return null;
        }
    }
}
