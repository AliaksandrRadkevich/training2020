package by.task.soundrecording.service;

import by.task.soundrecording.domain.MusicComposition;

import java.util.List;

/**
 * IMusicService.
 * Date: 07/15/2020
 *
 * @author Anastasiya Bezmen
 */
public interface IMusicService {

    List<MusicComposition> getAll();

    List<MusicComposition> getByIds(List<Long> musicIds);

    List<MusicComposition> getMusicRange(List<MusicComposition> musicOnDisk, int minLength, int maxLength);

    void fillMusicInfo(List<MusicComposition> musicList);

}
