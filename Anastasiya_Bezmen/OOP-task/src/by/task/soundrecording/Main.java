package by.task.soundrecording;

import by.task.soundrecording.domain.Disk;
import by.task.soundrecording.domain.MusicComposition;
import by.task.soundrecording.exception.SystemInputException;
import by.task.soundrecording.service.DiskService;
import by.task.soundrecording.service.IDiskService;
import by.task.soundrecording.service.IMusicService;
import by.task.soundrecording.service.MusicService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static IMusicService musicService = MusicService.getInstance();
    private static IDiskService diskService = DiskService.getInstance();
    private static boolean isRunning = true;

    public static void main(String[] args) {
        while (isRunning) {
            printActionList();
            handleSelection(enterIntValue());
        }
    }

    private static void printActionList() {
        System.out.println("==============================");
        System.out.println("Выберите необходимое действие:");
        System.out.println();
        System.out.println("1. Вывести всю музыку");
        System.out.println("2. Вывести всe имеющиеся диски");
        System.out.println("3. Создать чистый диск");
        System.out.println("4. Записать сборку на диск");
        System.out.println("5. Вывести весь список композиций на диске");
        System.out.println("6. Сортировать композиции на диске по жанрам");
        System.out.println("7. Рассчитать общую продолжительность треков на диске");
        System.out.println("8. Фильтровать композиции на диске по заданному диапазону длины треков");
        System.out.println("9. Выход");
    }

    private static int enterIntValue() {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextInt()) {
            return scan.nextInt();
        }
        return 0;
    }

    private static String enterStringValue() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    private static void handleSelection(int choice) {
        if (choice == 1) {
            printAllMusic();
        } else if (choice == 2) {
            printAllDisks();
        } else if (choice == 3) {
            createDisc();
        } else if (choice == 4) {
            writeDiskWithMusic();
        } else if (choice == 5) {
            printMusicOnDisk();
        } else if (choice == 6) {
            printMusicSortedByGenre();
        } else if (choice == 7) {
            getMusicLength();
        } else if (choice == 8) {
            filterByLength();
        } else if (choice == 9) {
            isRunning = false;
        } else {
            System.out.println("Выберите один из предложенных вариантов!!!");
        }
    }

    private static String enterDiscName() throws SystemInputException {
        System.out.println("Введите название необходимого диска");
        String diskName = selectDisk();
        if (diskName == null) {
            throw new SystemInputException("Вы ввели не правильное имя диска");
        }
        return diskName;
    }

    private static void filterByLength() {
        try {
            String discName = enterDiscName();
            System.out.println("Введите минимальное значение продолжительности композиций");
            int minLength = enterIntValue();
            System.out.println("Введите максимальное значение продолжительности композиций");
            int maxLength = enterIntValue();
            List<MusicComposition> musicRange = diskService.getMusicRange(discName, minLength, maxLength);
            for (MusicComposition musicComposition : musicRange) {
                System.out.println(musicComposition);
            }
        } catch (SystemInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void getMusicLength() {
        try {
            System.out.println(diskService.getMusicLength(enterDiscName()));
        } catch (SystemInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void printMusic(List<MusicComposition> musicCompositions) {
        if (musicCompositions == null || musicCompositions.isEmpty()) {
            System.out.println("Диск пуст");
        } else {
            for (MusicComposition musicComposition : musicCompositions) {
                System.out.println(musicComposition);
            }
        }
    }

    private static void printMusicSortedByGenre() {
        try {
            String discName = enterDiscName();
            List<MusicComposition> sortedMusic = diskService.getSortedMusic(discName);
            printMusic(sortedMusic);
        } catch (SystemInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void printMusicOnDisk() {
        try {
            String discName = enterDiscName();
            List<MusicComposition> musicOnDisk = diskService.getMusicOnDisk(discName);
            printMusic(musicOnDisk);
        } catch (SystemInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void printAllDisks() {
        List<Disk> disks = diskService.getAll();
        disks.forEach(System.out::println);
    }

    private static void printAllMusic() {
        List<MusicComposition> allMusic = musicService.getAll();
        allMusic.forEach(System.out::println);
    }

    private static void createDisc() {
        System.out.println("Введите название диска");
        String diskName = enterStringValue();
        while (diskName.trim().equals("")) {
            System.out.println("Введите название диска (не может быть пустым).");
            diskName = enterStringValue();
        }
        diskService.createNewDisk(diskName);
    }

    private static void writeDiskWithMusic() {
        try {
            String discName = enterDiscName();
            List<Long> musicIds = selectMusic();
            if (musicIds.isEmpty()) {
                System.out.println("Выберите композиции для записи");
            } else {
                writeMusic(discName, musicIds);
            }
        } catch (SystemInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static List<Long> selectMusic() throws SystemInputException {
        System.out.println("Введите через пробел Id музыкальных композиций");
        String musicIds = enterStringValue();
        String[] musicIdsArray = musicIds.split("");
        List<Long> result = new ArrayList<>();
        try {
            for (int i = 0; i < musicIdsArray.length; i++) {
                result.add(Long.valueOf(musicIdsArray[i]));
            }
        } catch (NumberFormatException e) {
            throw new SystemInputException("id композиций введены не корректно");
        }

        return result;
    }

    private static void writeMusic(String diskName, List<Long> musicIds) {
        diskService.writeMusic(diskName, musicIds);
    }

    private static String selectDisk() {
        String diskName = enterStringValue().trim();
        if (diskName.equals("")) {
            return null;
        }
        if (diskService.isDiskExists(diskName)) {
            return diskName;
        } else {
            return null;
        }
    }
}




