import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends Thread {

    private Image songInfoImage = new ImageIcon(Main.class.getResource("Images/songInfo.png")).getImage();
    private Image noteBarImage = new ImageIcon(Main.class.getResource("Images/noteBar.png")).getImage();
    private Image noteRouteDImage = new ImageIcon(Main.class.getResource("Images/noteRoute.png")).getImage();
    private Image noteRouteFImage = new ImageIcon(Main.class.getResource("Images/noteRoute.png")).getImage();
    private Image noteRouteJImage = new ImageIcon(Main.class.getResource("Images/noteRoute.png")).getImage();
    private Image noteRouteKImage = new ImageIcon(Main.class.getResource("Images/noteRoute.png")).getImage();
    private Image dividerImage = new ImageIcon(Main.class.getResource("Images/divider.png")).getImage();
    private Image ratingImage = new ImageIcon(Main.class.getResource("Images/good.png")).getImage();
    private String name;
    private String musicTitle;
    private Music gameMusic;


    ArrayList<Note> noteList = new ArrayList<>();

    public Game(String name, String musicTitle){
        this.name=name;
        this.musicTitle = musicTitle;
        gameMusic = new Music("Music/"+this.musicTitle,false);
    }

    public void screenDraw(Graphics2D g){
        g.drawImage(dividerImage,235,30,null);
        g.drawImage(noteRouteDImage, 240, 30, null);
        g.drawImage(dividerImage,340,30,null);

        g.drawImage(dividerImage,395,30,null);
        g.drawImage(noteRouteFImage, 400, 30, null);
        g.drawImage(dividerImage,500,30,null);

        g.drawImage(dividerImage,555,30,null);
        g.drawImage(noteRouteJImage, 560, 30, null);
        g.drawImage(dividerImage,660,30,null);

        g.drawImage(dividerImage,715,30,null);
        g.drawImage(noteRouteKImage, 720, 30, null);
        g.drawImage(dividerImage,820,30,null);

        g.drawImage(songInfoImage,0,660,null);
        g.drawImage(noteBarImage,0,573,null);

        for(int i=0;i<noteList.size();i++){
            Note note = noteList.get(i);
            if(!note.isProceeded()){
                noteList.remove(i);
                i--;
            }else {
                note.screenDraw(g);
            }
        }

        g.setColor(Color.WHITE);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString(name,20,702);
        g.drawString("000000",565,702);
        //g.drawImage(ratingImage,460,420,null);
    }

    public void pressD(){
        noteRouteDImage = new ImageIcon(Main.class.getResource("Images/noteRoutePressed.png")).getImage();
        new Music("Music/kick.mp3",false).start();
        judge(0);
    }
    public void pressF(){
        noteRouteFImage = new ImageIcon(Main.class.getResource("Images/noteRoutePressed.png")).getImage();
        new Music("Music/kick.mp3",false).start();
        judge(1);
    }
    public void pressJ(){
        noteRouteJImage = new ImageIcon(Main.class.getResource("Images/noteRoutePressed.png")).getImage();
        new Music("Music/kick.mp3",false).start();
        judge(2);
    }
    public void pressK(){
        noteRouteKImage = new ImageIcon(Main.class.getResource("Images/noteRoutePressed.png")).getImage();
        new Music("Music/kick.mp3",false).start();
        judge(3);
    }

    public void releaseD(){
        noteRouteDImage = new ImageIcon(Main.class.getResource("Images/noteRoute.png")).getImage();
    }
    public void releaseF(){
        noteRouteFImage = new ImageIcon(Main.class.getResource("Images/noteRoute.png")).getImage();
    }
    public void releaseJ(){
        noteRouteJImage = new ImageIcon(Main.class.getResource("Images/noteRoute.png")).getImage();
    }
    public void releaseK(){
        noteRouteKImage = new ImageIcon(Main.class.getResource("Images/noteRoute.png")).getImage();
    }

    @Override
    public void run(){
        dropNotes();
    }

    public void close(){
        gameMusic.close();
        this.interrupt();
    }

    public void dropNotes(){
        System.out.println(name);
        Beat[] beats = null;
        if(name.equals("Track 1")){
            int startTime = 1460-Main.REACH_TIME*1000;
            int gap = 320;
            beats = new Beat[]{
                    new Beat(startTime, 1),
                    new Beat(startTime + gap*2, 0),
                    new Beat(startTime + gap*4, 1),
                    new Beat(startTime + gap*6, 0),
                    new Beat(startTime + gap*8, 1),
                    new Beat(startTime + gap*10, 0),
                    new Beat(startTime + gap*12, 1),
                    new Beat(startTime + gap*14, 0),
                    new Beat(startTime + gap*16, 1),
            };
        }else if(name.equals("Track 2")){
            int startTime = 500;
            int gap = 100;
            beats = new Beat[]{
                    new Beat(startTime,0),
                    new Beat(startTime + gap*8, 1),
                    new Beat(startTime + gap*10, 0),
                    new Beat(startTime + gap*12, 1),
                    new Beat(startTime + gap*14, 0),
                    new Beat(startTime + gap*16, 1),
            };
        }
        int i=0;
        gameMusic.start();
        while(i< beats.length && !isInterrupted()){
            boolean dropped = false;
            if(beats[i].getTime() <= gameMusic.getTime()){
                Note note = new Note(beats[i].getNoteName());
                note.start();
                noteList.add(note);
                i++;
                dropped = true;
            }
            if(!dropped){
                try{
                    Thread.sleep(5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void judge(int input){
        for(int i=0;i<noteList.size();i++){
            Note note = noteList.get(i);
            if(input==note.getNoteType()){
                note.judge();
                break;
            }
        }
    }
}
