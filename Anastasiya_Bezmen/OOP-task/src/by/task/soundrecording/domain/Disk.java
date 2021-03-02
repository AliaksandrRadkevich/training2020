package by.task.soundrecording.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Disk.
 * Date: 07/14/2020
 *
 * @author Anastasiya Bezmen
 */
public class Disk {

    private long id;
    private String name;
    private List<MusicComposition> musicList;
    private int musicLength;

    private Disk(DiskBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.musicList = builder.musicList;
        this.musicLength = builder.musicLength;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets musicList.
     *
     * @return musicList
     */
    public List<MusicComposition> getMusicList() {
        return musicList;
    }

    public void addMusicList(List<MusicComposition> musicList) {
        this.musicList.addAll(musicList);
        this.musicLength = calculateMusicLength(this.musicList);
    }

    public long getMusicLength() {
        return musicLength;
    }

    private static int calculateMusicLength(List<MusicComposition> musicList) {
        int counter = 0;
        if (musicList != null && !musicList.isEmpty()) {
            for (MusicComposition musicComposition : musicList) {
                counter += musicComposition.getTrackLength();
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Disk{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", musicList=").append(musicList);
        sb.append(", musicLength=").append(musicLength);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disk)) return false;

        Disk disk = (Disk) o;

        if (getId() != disk.getId()) return false;
        if (getMusicLength() != disk.getMusicLength()) return false;
        if (getName() != null ? !getName().equals(disk.getName()) : disk.getName() != null) return false;
        return getMusicList() != null ? getMusicList().equals(disk.getMusicList()) : disk.getMusicList() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getMusicList() != null ? getMusicList().hashCode() : 0);
        result = 31 * result + (int) (getMusicLength() ^ (getMusicLength() >>> 32));
        return result;
    }

    public void recalculateMusicLength() {
        this.musicLength = calculateMusicLength(this.musicList);
    }

    public static class DiskBuilder {

        private long id;
        private String name;
        private List<MusicComposition> musicList = new ArrayList<>();
        private int musicLength;

        public DiskBuilder(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public DiskBuilder setMusicList(List<MusicComposition> musicList) {
            this.musicList = musicList;
            this.musicLength = calculateMusicLength(musicList);
            return this;
        }

        public Disk build() {
            return new Disk(this);
        }
    }
}
