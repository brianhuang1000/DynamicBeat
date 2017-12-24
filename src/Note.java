import javax.swing.*;
import java.awt.*;

public class Note extends Thread {

    private Image noteImage = new ImageIcon(Main.class.getResource("Images/noteBasic.png")).getImage();
    private int x,y = 0;
    private int noteType;
    private boolean proceeded = true;

    public int getNoteType(){
        return noteType;
    }

    public boolean isProceeded(){
        return proceeded;
    }

    public void close(){
        proceeded = false;
    }

    public Note(int noteType){
        super();
        switch (noteType) {
            case 0:
                x=240;
                this.noteType=0;
                break;
            case 1:
                x=400;
                this.noteType=1;
                break;
            case 2:
                x=560;
                this.noteType=2;
                break;
            case 3:
                x=720;
                this.noteType=3;
                break;
        }
    }

    public void screenDraw(Graphics2D g){
            g.drawImage(noteImage,x,y,null);
            if(y>700){
                System.out.println("Miss");
                close();
            }
    }

    public void drop(){
        y += Main.NOTE_SPEED;
        if(y>620){
            System.out.println("Miss");
            close();
        }
    }

    @Override
    public void run(){
        long beforeTime,timeDiff,sleep;
        beforeTime = System.currentTimeMillis();
        try{
            while (true){
                drop();
                if(proceeded){
                    timeDiff = System.currentTimeMillis()-beforeTime;
                    sleep = Main.SLEEP_TIME-timeDiff;
                    if(sleep<0){
                        sleep=1;
                    }
                    Thread.sleep(sleep);
                } else{
                    interrupt();
                    break;
                }
                beforeTime=System.currentTimeMillis();
            }
        } catch(Exception e) {
            System.out.println("err");
            e.printStackTrace();
        }
    }

    public void judge(){
        if(y>=613) {
            System.out.println("Late");
            close();
        } else if(y>=600) {
            System.out.println("Good");
            close();
        } else if(y>=587) {
            System.out.println("Great");
            close();
        } else if(y>=573) {
            System.out.println("Perfect");
            close();
        } else if(y>=565) {
            System.out.println("Great");
            close();
        } else if(y>=550) {
            System.out.println("Good");
            close();
        } else if(y>=535) {
            System.out.println("Early");
            close();
        }
    }
}
