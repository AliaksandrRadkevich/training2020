package by.task.soundrecording.service;

import by.task.soundrecording.domain.Disk;
import by.task.soundrecording.domain.MusicComposition;
import by.task.soundrecording.repository.IDiskRepository;
import by.task.soundrecording.repository.StubDiskRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Comparator;
import java.util.List;

/**
 * DiskService.
 * Date: 07/15/2020
 *
 * @author Anastasiya Bezmen
 */
public class DiskService implements IDiskService {

    private static final DiskService diskService = new DiskService();
    private IDiskRepository diskRepository = StubDiskRepository.getInstance();
    private IMusicService musicService = MusicService.getInstance();

    private DiskService() {
    }

    public static DiskService getInstance() {
        return diskService;
    }

    @Override
    public List<Disk> getAll() {
        List<Disk> disks = diskRepository.getAll();
        for (Disk disk : disks) {
            musicService.fillMusicInfo(disk.getMusicList());
        }
        return disks;
    }

    @Override
    public void createNewDisk(String diskName) {
        long counter = getCount();
        Disk disk = new Disk.DiskBuilder(counter, diskName).build();
        counter++;
        putCount(counter);
        System.out.println("Диск создан: " + disk);
        diskRepository.add(disk);
    }

    private long getCount() {
        long counter = 1000L;
        try (BufferedReader bf = new BufferedReader(new FileReader("myCount.txt"))) {
            String count = bf.readLine();
            counter = Long.valueOf(count);
        } catch (Exception e) {
            System.out.println("Ошибка чтения из файла");
        }
        return counter;
    }

    public void putCount(long counter) {
        try (FileWriter fileWriter = new FileWriter("myCount.txt")) {
            fileWriter.write(Long.toString(counter));
        } catch (Exception e) {
            System.out.println("Ошибка записи в файл");
        }
    }

    @Override
    public void writeMusic(String diskName, List<Long> musicIds) {
        Disk disk = diskRepository.getByName(diskName);
        List<MusicComposition> musicCompositions = musicService.getByIds(musicIds);
        disk.addMusicList(musicCompositions);
        diskRepository.update(disk);
    }

    @Override
    public boolean isDiskExists(String name) {
        List<Disk> disks = diskRepository.getAll();
        for (Disk disk : disks) {
            if (name.equals(disk.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<MusicComposition> getMusicOnDisk(String diskName) {
        List<MusicComposition> musicList = diskRepository.getMusicList(diskName);
        musicService.fillMusicInfo(musicList);
        return musicList;
    }

    @Override
    public List<MusicComposition> getSortedMusic(String diskName) {
        List<MusicComposition> musicOnDisk = getMusicOnDisk(diskName);
        musicOnDisk.sort(new Comparator<MusicComposition>() {

            @Override
            public int compare(MusicComposition o1, MusicComposition o2) {
                return o1.getMusicGenre().name().compareTo(o2.getMusicGenre().name());
            }
        });
        return musicOnDisk;
    }

    @Override
    public long getMusicLength(String diskName) {
        Disk disk = diskRepository.getByName(diskName);
        musicService.fillMusicInfo(disk.getMusicList());
        disk.recalculateMusicLength();
        return disk.getMusicLength();

    }

    @Override
    public List<MusicComposition> getMusicRange(String diskName, int minLength, int maxLength) {
        List<MusicComposition> musicOnDisk = getMusicOnDisk(diskName);
        return musicService.getMusicRange(musicOnDisk, minLength, maxLength);
    }
}
