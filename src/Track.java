public class Track {

    private String titleImage;
    private String startImage;
    private String gameImage;
    private String startMusic;
    private String gameMusic;
    private String name;

    public String getName() {
        return name;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public String getStartImage() {
        return startImage;
    }

    public String getGameImage() {
        return gameImage;
    }

    public String getStartMusic() {
        return startMusic;
    }

    public String getGameMusic() {
        return gameMusic;
    }

    public Track(String titleImage, String startImage, String gameImage, String startMusic, String gameMusic, String name) {
        this.titleImage = titleImage;
        this.startImage = startImage;
        this.gameImage = gameImage;
        this.startMusic = startMusic;
        this.gameMusic = gameMusic;
        this.name = name;
    }
}
