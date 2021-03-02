package by.training.domain;

public class VisualParameters {
    private String stemColor;
    private String leavesColor;
    private int averageSize;

    public String getStemColor() {
        return stemColor;
    }

    public void setStemColor(String stemColor) {
        this.stemColor = stemColor;
    }

    public String getLeavesColor() {
        return leavesColor;
    }

    public void setLeavesColor(String leavesColor) {
        this.leavesColor = leavesColor;
    }

    public int getAverageSize() {
        return averageSize;
    }

    public void setAverageSize(int averageSize) {
        if (averageSize < 1) {
            throw new UnsupportedOperationException("Average size can't be less than 1");
        } else {
            this.averageSize = averageSize;
        }
    }

    @Override
    public String toString() {
        return "VisualParameters{" +
                "stemColor='" + stemColor + '\'' +
                ", leavesColor='" + leavesColor + '\'' +
                ", averageSize=" + averageSize +
                '}';
    }
}