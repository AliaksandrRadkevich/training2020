package by.task.soundrecording.repository;

import by.task.soundrecording.domain.MusicComposition;
import by.task.soundrecording.domain.MusicGenre;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileMusicRepository.
 * Date: 07/15/2020
 *
 * @author Anastasiya Bezmen
 */
// TODO реализовать чтение из файла
public class FileMusicRepository implements IMusicRepository {

    private static final FileMusicRepository fileMusicRepository = new FileMusicRepository();

    private FileMusicRepository() {

    }

    public static FileMusicRepository getInstance() {
        return fileMusicRepository;
    }

    @Override
    public List<MusicComposition> getAll() {
        List<MusicComposition> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("MusicCompositionList.txt"));
            String musicList;
            while ((musicList = reader.readLine()) != null) {
                String[] musicArray = musicList.trim().split(";");
                MusicComposition musicComposition = new MusicComposition.MusicCompositionBuilder(Long.valueOf(musicArray[0]))
                        .setName(musicArray[1])
                        .setAuthor(musicArray[2])
                        .setTrackLength(Integer.valueOf(musicArray[3]))
                        .setMusicGenre(MusicGenre.getMusicGenre(musicArray[4]))
                        .build();
                result.add(musicComposition);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Укажите правильный путь к файлу");
        } catch (IOException e) {
            System.out.println("Ошибка считывания из файла");
        }
        return result;
    }

    @Override
    public List<MusicComposition> getByIds(List<Long> musicIds) {
        List<MusicComposition> result = new ArrayList<>();
        for (MusicComposition musicComposition : getAll()) {
            for (Long musicId : musicIds) {
                if (musicId.equals(musicComposition.getId())) {
                    result.add(musicComposition);
                }
            }
        }
        return result;
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
