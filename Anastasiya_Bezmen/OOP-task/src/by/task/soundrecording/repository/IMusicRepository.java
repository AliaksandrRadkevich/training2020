package by.task.soundrecording.repository;

import by.task.soundrecording.domain.MusicComposition;

import java.util.List;

/**
 * IMusicRepository.
 * Date: 07/15/2020
 *
 * @author Anastasiya Bezmen
 */
public interface IMusicRepository {

    List<MusicComposition> getAll();

    List<MusicComposition> getByIds(List<Long> musicIds);

    List<MusicComposition> getMusicRange(List<MusicComposition> musicOnDisk, int minLength, int maxLength);
}
