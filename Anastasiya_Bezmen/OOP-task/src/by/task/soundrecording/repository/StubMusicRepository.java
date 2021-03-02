package by.task.soundrecording.repository;

import by.task.soundrecording.domain.MusicComposition;
import by.task.soundrecording.domain.MusicGenre;

import java.util.ArrayList;
import java.util.List;

/**
 * StubMusicRepository.
 * Date: 07/15/2020
 *
 * @author Anastasiya Bezmen
 */
public class StubMusicRepository implements IMusicRepository {

    private static final StubMusicRepository stubMusicRepository = new StubMusicRepository();
    private List<MusicComposition> listCompositions = new ArrayList<>();

    private StubMusicRepository() {
        populate();
    }

    public static StubMusicRepository getInstance() {
        return stubMusicRepository;
    }

    private void populate() {
        listCompositions.add(new MusicComposition.MusicCompositionBuilder(1L)
                .setName("Звезда")
                .setAuthor("Кино")
                .setTrackLength(270)
                .setMusicGenre(MusicGenre.ROCK)
                .build());
        listCompositions.add(new MusicComposition.MusicCompositionBuilder(2L)
                .setName("Студент")
                .setAuthor("Руки Вверх")
                .setTrackLength(231)
                .setMusicGenre(MusicGenre.POP)
                .build());
        listCompositions.add(new MusicComposition.MusicCompositionBuilder(3L)
                .setName("Выпускной")
                .setAuthor("Баста")
                .setTrackLength(335)
                .setMusicGenre(MusicGenre.RAP)
                .build());
        listCompositions.add(new MusicComposition.MusicCompositionBuilder(4L)
                .setName("Варвара")
                .setAuthor("Би-2")
                .setTrackLength(301)
                .setMusicGenre(MusicGenre.ROCK)
                .build());
        listCompositions.add(new MusicComposition.MusicCompositionBuilder(5L)
                .setName("Серебро")
                .setAuthor("Би-2")
                .setTrackLength(280)
                .setMusicGenre(MusicGenre.ROCK)
                .build());
        listCompositions.add(new MusicComposition.MusicCompositionBuilder(6L)
               .setName("Где спит твое сердце")
                .setAuthor("Billy's Band")
                .setTrackLength(294)
                .setMusicGenre(MusicGenre.BLUES)
                .build());
        listCompositions.add(new MusicComposition.MusicCompositionBuilder(7L)
               .setName("Потрачу")
                .setAuthor("Егор Крид")
                .setTrackLength(184)
                .setMusicGenre(MusicGenre.ELECTRONIC_MUSIC)
                .build());
        listCompositions.add(new MusicComposition.MusicCompositionBuilder(8L)
               .setName("Dev")
                .setAuthor("Mango")
                .setTrackLength(158)
                .setMusicGenre(MusicGenre.HIP_HOP)
                .build());
    }

    @Override
    public List<MusicComposition> getAll() {
        return listCompositions;
    }

    @Override
    public List<MusicComposition> getByIds(List<Long> musicIds) {
        List<MusicComposition> musicCompositions = new ArrayList<>();
        for (MusicComposition musicComposition : listCompositions) {
            for (Long musicId : musicIds) {
                if (musicId.equals(musicComposition.getId())) {
                    musicCompositions.add(musicComposition);
                }
            }
        }
        return musicCompositions;
    }

    @Override
    public List<MusicComposition> getMusicRange(List<MusicComposition> musicOnDisk, int minLength, int maxLength) {
        List<MusicComposition> musicCompositions = new ArrayList<>();
        for (MusicComposition musicComposition : musicOnDisk) {
            if (musicComposition.getTrackLength() > minLength && musicComposition.getTrackLength() < maxLength) {
                musicCompositions.add(musicComposition);
            }
        }
        return musicCompositions;
    }
}
