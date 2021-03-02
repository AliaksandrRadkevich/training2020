package by.task.soundrecording.domain;

/**
 * MusicComposition.
 * Date: 07/14/2020
 *
 * @author Anastasiya Bezmen
 */
public class MusicComposition {

    private long id;
    private String name;
    private String author;
    private int trackLength;
    private MusicGenre musicGenre;

    private MusicComposition(MusicCompositionBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.author = builder.author;
        this.trackLength = builder.trackLength;
        this.musicGenre = builder.musicGenre;
    }

    /**
     * Gets id.
     *
     * @return the identifier of the music composition
     */
    public long getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets author.
     *
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets trackLength.
     *
     * @return trackLength
     */
    public int getTrackLength() {
        return trackLength;
    }

    /**
     * Gets MusicGenre.
     *
     * @return MusicGenre
     */
    public MusicGenre getMusicGenre() {
        return musicGenre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MusicComposition)) return false;

        MusicComposition that = (MusicComposition) o;

        if (getId() != that.getId()) return false;
        if (getTrackLength() != that.getTrackLength()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getAuthor() != null ? !getAuthor().equals(that.getAuthor()) : that.getAuthor() != null)
            return false;
        return getMusicGenre() == that.getMusicGenre();
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + getTrackLength();
        result = 31 * result + (getMusicGenre() != null ? getMusicGenre().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MusicComposition{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", trackLength=").append(trackLength);
        sb.append(", musicGenre=").append(musicGenre);
        sb.append('}');
        return sb.toString();
    }

    public static class MusicCompositionBuilder {

        private long id;
        private String name;
        private String author;
        private int trackLength;
        private MusicGenre musicGenre;

        public MusicCompositionBuilder(long id) {
            this.id = id;
        }

        public MusicCompositionBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public MusicCompositionBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public MusicCompositionBuilder setTrackLength(int trackLength) {
            this.trackLength = trackLength;
            return this;
        }

        public MusicCompositionBuilder setMusicGenre(MusicGenre musicGenre) {
            this.musicGenre = musicGenre;
            return this;
        }

        public MusicComposition build() {
            return new MusicComposition(this);
        }
    }
}
