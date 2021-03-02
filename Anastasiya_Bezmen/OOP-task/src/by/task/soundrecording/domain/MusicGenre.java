package by.task.soundrecording.domain;

/**
 * MusicGenre.
 * Date: 07/14/2020
 *
 * @author Anastasiya Bezmen
 */
public enum MusicGenre {

    ROCK,
    POP,
    RAP,
    BLUES,
    ELECTRONIC_MUSIC,
    HIP_HOP;

    public static MusicGenre getMusicGenre(String genre) {
        for (MusicGenre musicGenre : MusicGenre.values()) {
            if (musicGenre.name().equalsIgnoreCase(genre)) {
                return musicGenre;
            }
        }
        throw new RuntimeException(String.format("Жанра с именем '%s' не существуе", genre));
    }
}

