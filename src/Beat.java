public class Beat {

    private int time;
    private int noteName;

    public int getTime() {
        return time;
    }

    public int getNoteName() {
        return noteName;
    }

    public void setNoteName(int noteName) {
        this.noteName = noteName;
    }

    public Beat(int time, int noteName) {
        super();
        this.time = time;
        this.noteName = noteName;
    }
}
