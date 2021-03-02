package by.task.soundrecording.repository;

import by.task.soundrecording.domain.Disk;
import by.task.soundrecording.domain.MusicComposition;

import java.util.ArrayList;
import java.util.List;

/**
 * StubDiskRepository.
 * Date: 07/15/2020
 *
 * @author Anastasiya Bezmen
 */
public class StubDiskRepository implements IDiskRepository {

    private static final StubDiskRepository stubDiskRepository = new StubDiskRepository();
    private List<Disk> disks = new ArrayList<>();

    private StubDiskRepository() {
        populate();
    }

    public static StubDiskRepository getInstance() {
        return stubDiskRepository;
    }

    private void populate() {
        disks.add(new Disk.DiskBuilder(1L, "Хит-парад").build());
        disks.add(new Disk.DiskBuilder(2L, "Музыка в машину").build());
        disks.add(new Disk.DiskBuilder(3L, "Топ-10").build());
        disks.add(new Disk.DiskBuilder(4L, "Лучшие хиты").build());
    }

    @Override
    public List<Disk> getAll() {
        return disks;
    }

    @Override
    public void add(Disk disk) {
        disks.add(disk);
    }

    @Override
    public Disk getByName(String diskName) {
        for (Disk disk : disks) {
            if (diskName.equals(disk.getName())) {
                return disk;
            }
        }
        return null;
    }

    @Override
    public void update(Disk disk) {
        for (Disk storedDisk : disks) {
            if (disk.getId() == storedDisk.getId()) {
                storedDisk = disk;
                System.out.println("Диск записан и сохранен");
            }
        }
    }

    @Override
    public List<MusicComposition> getMusicList(String name) {
        for (Disk disk : disks) {
            if (name.equals(disk.getName())) {
                return disk.getMusicList();
            }
        }
        return null;
    }
}
