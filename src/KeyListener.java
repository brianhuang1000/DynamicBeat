import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        if(DynamicMain.game == null){
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            DynamicMain.game.pressD();
        }else if(e.getKeyCode() == KeyEvent.VK_F){
            DynamicMain.game.pressF();
        }else if(e.getKeyCode() == KeyEvent.VK_J){
            DynamicMain.game.pressJ();
        }else if(e.getKeyCode() == KeyEvent.VK_K){
            DynamicMain.game.pressK();
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        if(DynamicMain.game == null){
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            DynamicMain.game.releaseD();
        }else if(e.getKeyCode() == KeyEvent.VK_F){
            DynamicMain.game.releaseF();
        }else if(e.getKeyCode() == KeyEvent.VK_J){
            DynamicMain.game.releaseJ();
        }else if(e.getKeyCode() == KeyEvent.VK_K){
            DynamicMain.game.releaseK();
        }
    }
}
