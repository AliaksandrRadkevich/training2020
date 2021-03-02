package by.task.soundrecording.repository;

import by.task.soundrecording.domain.Disk;
import by.task.soundrecording.domain.MusicComposition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileDiskRepository.
 * Date: 07/29/2020
 *
 * @author Anastasiya Bezmen
 */
public class FileDiskRepository implements IDiskRepository {

    private static final FileDiskRepository fileDiskRepository = new FileDiskRepository();

    private FileDiskRepository() {
    }

    public static FileDiskRepository getInstance() {
        return fileDiskRepository;
    }

    @Override
    public List<Disk> getAll() {
        List<Disk> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Disks.txt"))) {
            String disks;
            while ((disks = reader.readLine()) != null) {
                String[] diskArray = disks.trim().split(";");
                List<MusicComposition> musicList = new ArrayList<>();
                if (diskArray.length == 3) {
                    String musicIds = diskArray[2];
                    musicList = getMusicCompositions(musicIds);
                }
                Disk disk = new Disk.DiskBuilder(Long.valueOf(diskArray[0]), diskArray[1])
                        .setMusicList(musicList)
                        .build();
                result.add(disk);
            }
        } catch (IOException e) {
            System.out.println("Ошибка считывания из файла");
        }
        return result;
    }

    private List<MusicComposition> getMusicCompositions(String musicIds) {
        String[] musicIdsArray = musicIds.trim().split(",");
        List<MusicComposition> musicOnDisk = new ArrayList<>();
        for (int i = 0; i < musicIdsArray.length; i++) {
            MusicComposition musicComposition = new MusicComposition.MusicCompositionBuilder(Long.valueOf
                    (musicIdsArray[i])).build();
            musicOnDisk.add(musicComposition);
        }
        return musicOnDisk;
    }

    @Override
    public void add(Disk disk) {
        StringBuilder discMusicBuilder = new StringBuilder();
        for (MusicComposition musicComposition : disk.getMusicList()) {
            discMusicBuilder.append(musicComposition.getId());
            discMusicBuilder.append(",");
        }
        String recordedDisk = String.format("%s;%s;%s\n", disk.getId(), disk.getName(), discMusicBuilder.toString());
        try (FileWriter fileWriter = new FileWriter("Disks.txt", true)) {
            fileWriter.write(recordedDisk);
            fileWriter.flush();
        } catch (
                IOException e)

        {
            System.out.println("Ошибка записи в файл");
        }

    }

    @Override
    public Disk getByName(String diskName) {
        for (Disk disk : getAll()) {
            if (diskName.equals(disk.getName())) {
                return disk;
            }
        }
        return null;
    }

    @Override
    public void update(Disk disk) {
        // TODO нужно реализовать;
    }

    @Override
    public List<MusicComposition> getMusicList(String name) {
        for (Disk disk : getAll()) {
            if (name.equals(disk.getName())) {
                return disk.getMusicList();
            }
        }
        return new ArrayList<>();
    }
}