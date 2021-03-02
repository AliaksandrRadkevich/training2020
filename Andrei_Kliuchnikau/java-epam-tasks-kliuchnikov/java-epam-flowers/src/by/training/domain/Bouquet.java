package by.training.domain;

import by.training.domain.florets.Flower;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bouquet implements Serializable {
    private PackingAccessory accessory;
    private List<Flower> flowerList;
    private int price;

    public Bouquet(PackingAccessory accessory, Flower... flowers) {
        if(check(accessory, flowers)) {
            accessory.setUse(true);
            this.accessory = accessory;
            flowerList = new ArrayList<>(Arrays.asList(flowers));
            for(Flower f : flowerList) {
                f.setInBouquet(true);
                this.price += f.getCost();
            }
        }
        else {
            System.out.println("Во время сборки букета произошла ошибка :c");
            throw new UnsupportedOperationException();
        }
    }

    public PackingAccessory getAccessory() {
        return accessory;
    }

    public void setAccessory(PackingAccessory accessory) {
        this.accessory = accessory;
    }

    public List<Flower> getFlowerList() {
        return flowerList;
    }

    public void setFlowerList(List<Flower> flowerList) {
        this.flowerList = flowerList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

     public boolean check(PackingAccessory accessory, Flower... flowers) {
         boolean allOk = true;
         if (flowers.length < accessory.getMinCapacity() || flowers.length > accessory.getMaxCapacity()) {
             System.out.println(String.format("Вы выбрали упаковочный аксессуар неподходящего размера." +
                     "В данную упаковку помещается от %d до %d цветов", accessory.getMinCapacity(), accessory.getMaxCapacity()));
             allOk = false;
         }
         if(accessory.isUse()) {
             System.out.println("Данный аксессуар уже используется!");
             allOk = false;
         }
         for (Flower f : flowers) {
             if (f.isInBouquet()) {
                 System.out.println("Цветок уже в другом букете, так не пойдёт! Удалите из заготовки букета: " + f + System.lineSeparator());
                 allOk = false;
             }
         }
         return allOk;
    }
}
