package by.task.soundrecording.service;

import by.task.soundrecording.domain.Disk;
import by.task.soundrecording.domain.MusicComposition;

import java.util.List;

/**
 * IDiskService.
 * Date: 07/15/2020
 *
 * @author Anastasiya Bezmen
 */
public interface IDiskService {

    List<Disk> getAll();

    void createNewDisk(String diskName);

    void writeMusic(String diskName, List<Long> musicIds);

    boolean isDiskExists(String name);

    List<MusicComposition> getMusicOnDisk(String diskName);

    List<MusicComposition> getSortedMusic(String diskName);

    long getMusicLength(String diskName);

    List<MusicComposition> getMusicRange(String diskName, int minLength, int maxLength);
}
