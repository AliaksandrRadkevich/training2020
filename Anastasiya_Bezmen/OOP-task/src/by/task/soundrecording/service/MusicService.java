package by.task.soundrecording.service;

import by.task.soundrecording.domain.MusicComposition;
import by.task.soundrecording.repository.IMusicRepository;
import by.task.soundrecording.repository.StubMusicRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * MusicService.
 * Date: 07/15/2020
 *
 * @author Anastasiya Bezmen
 */
public class MusicService implements IMusicService {

    private static final MusicService musicService = new MusicService();
    private IMusicRepository musicRepository = StubMusicRepository.getInstance();

    private MusicService() {
    }

    public static MusicService getInstance() {
        return musicService;
    }

    @Override
    public List<MusicComposition> getAll() {
        return musicRepository.getAll();
    }

    @Override
    public List<MusicComposition> getByIds(List<Long> musicIds) {
        return musicRepository.getByIds(musicIds);
    }

    @Override
    public List<MusicComposition> getMusicRange(List<MusicComposition> musicOnDisk, int minLength, int maxLength) {
        return musicRepository.getMusicRange(musicOnDisk, minLength, maxLength);
    }

    @Override
    public void fillMusicInfo(List<MusicComposition> musicList) {
        List<Long> musicIds = new ArrayList<>();
        for (MusicComposition musicComposition : musicList) {
            musicIds.add(musicComposition.getId());
        }
        List<MusicComposition> dbMusicCompositions = musicRepository.getByIds(musicIds);
        musicList.removeAll(musicList);
        musicList.addAll(dbMusicCompositions);
    }
}