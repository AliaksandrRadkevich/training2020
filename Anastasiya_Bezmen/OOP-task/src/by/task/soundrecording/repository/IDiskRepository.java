package by.task.soundrecording.repository;

import by.task.soundrecording.domain.Disk;
import by.task.soundrecording.domain.MusicComposition;

import java.util.List;

/**
 * IDiskRepository.
 * Date: 07/15/2020
 *
 * @author Anastasiya Bezmen
 */
public interface IDiskRepository {

    List<Disk> getAll();

    void add(Disk disk);

    Disk getByName(String diskName);

    void update(Disk disk);

    List<MusicComposition> getMusicList(String name);

}

