import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class DynamicMain extends JFrame {

    private Image screenImage;
    private Graphics screenGraphic;


    private Image selectedBackground = new ImageIcon(Main.class.getResource("Images/wall1.jpg")).getImage();
    private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("Images/menuebar.jpg")));
    private ImageIcon exitButtonImagePressed = new ImageIcon(Main.class.getResource("Images/exitPressed.jpg"));
    private ImageIcon exitButtonImage = new ImageIcon(Main.class.getResource("Images/exit.jpg"));
    private ImageIcon endSelectImage = new ImageIcon(Main.class.getResource("Images/endDefault.png"));
    private ImageIcon endSelectImagePressed = new ImageIcon(Main.class.getResource("Images/endPressed.png"));
    private ImageIcon startSelect = new ImageIcon(Main.class.getResource("Images/startDefault.png"));
    private ImageIcon startSelectPressed = new ImageIcon(Main.class.getResource("Images/startPressed.png"));
    private ImageIcon selectRightDefault = new ImageIcon(Main.class.getResource("Images/rightDefault.png"));
    private ImageIcon selectRightSelected = new ImageIcon(Main.class.getResource("Images/rightSelected.png"));
    private ImageIcon selectLeftDefault = new ImageIcon(Main.class.getResource("Images/leftDefault.png"));
    private ImageIcon selectLeftSelected = new ImageIcon(Main.class.getResource("Images/leftSelected.png"));
    private ImageIcon startGameDefault = new ImageIcon(Main.class.getResource("Images/startGameDefault.png"));
    private ImageIcon startGameSelected = new ImageIcon(Main.class.getResource("Images/startGamePressed.png"));
    private ImageIcon backButtonDefault = new ImageIcon(Main.class.getResource("Images/leftDefault.png"));
    private ImageIcon backButtonSelected = new ImageIcon(Main.class.getResource("Images/leftSelected.png"));



    private JButton exitButton = new JButton(exitButtonImage);
    private JButton endSelectButton = new JButton(endSelectImage);
    private JButton startSelectButton = new JButton(startSelect);
    private JButton rightButton = new JButton(selectRightDefault);
    private JButton leftButton = new JButton(selectLeftDefault);
    private JButton startGameButton = new JButton(startGameDefault);
    private JButton backButton = new JButton(backButtonDefault);

    private int mouseX,mouseY;

    private boolean isMainScreen=true;

    ArrayList<Track> trackList = new ArrayList<>();

    private Image background;
    private Image selectedImage;
    private Music selectedMusic;
    private Music introMusic = new Music("Music/01. VIP KID.mp3", true);
    private int nowSelected = 0;

    private boolean isGameScreen = false;

    public static Game game;

    public DynamicMain(){

        trackList.add(new Track("her.png","wall1.jpg","wall2.jpg","07 - Hear The Universe.mp3","07 - Hear The Universe.mp3","Track 1"));
        trackList.add(new Track("wall2.jpg","wall2.jpg","wall2.jpg","08 - GIRAFFE BLUES.mp3","08 - GIRAFFE BLUES.mp3", "Track 2"));

        setUndecorated(true);
        setTitle("Dynamic Beat");
        setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBackground(new Color(0,0,0,0));
        setLayout(null);
        //setFocusable(true);

        background = new ImageIcon(Main.class.getResource("Images/bg.jpg")).getImage();

        addKeyListener(new KeyListener());

        introMusic.start();

        exitButton.setBounds(1245,0,30,30);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exitButtonImagePressed);
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e){
                exitButton.setIcon(exitButtonImage);
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                System.exit(0);
                try{
                    Thread.sleep(1000);
                } catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        });
        add(exitButton);

        startSelectButton.setBounds(40,200,400,100);
        startSelectButton.setBorderPainted(false);
        startSelectButton.setContentAreaFilled(false);
        startSelectButton.setFocusPainted(false);
        startSelectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startSelectButton.setIcon(startSelectPressed);
                startSelectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e){
                startSelectButton.setIcon(startSelect);
                startSelectButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                enterMain();
            }
        });
        add(startSelectButton);

        endSelectButton.setBounds(40,330,400,100);
        endSelectButton.setBorderPainted(false);
        endSelectButton.setContentAreaFilled(false);
        endSelectButton.setFocusPainted(false);
        endSelectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                endSelectButton.setIcon(endSelectImagePressed);
                endSelectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e){
                endSelectButton.setIcon(endSelectImage);
                endSelectButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                System.exit(0);
                try{
                    Thread.sleep(1000);
                } catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        });
        add(endSelectButton);

        leftButton.setVisible(false);
        leftButton.setBounds(140,310,60,60);
        leftButton.setBorderPainted(false);
        leftButton.setContentAreaFilled(false);
        leftButton.setFocusPainted(false);
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                leftButton.setIcon(selectLeftSelected);
                leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e){
                leftButton.setIcon(selectLeftDefault);
                leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                selectLeft();
            }
        });
        add(leftButton);

        rightButton.setVisible(false);
        rightButton.setBounds(1080,310,60,60);
        rightButton.setBorderPainted(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setFocusPainted(false);
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rightButton.setIcon(selectRightSelected);
                rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e){
                rightButton.setIcon(selectRightDefault);
                rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                selectRight();
            }
        });
        add(rightButton);

        startGameButton.setVisible(false);
        startGameButton.setBounds(375,580,250,67);
        startGameButton.setBorderPainted(false);
        startGameButton.setContentAreaFilled(false);
        startGameButton.setFocusPainted(false);
        startGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startGameButton.setIcon(startGameSelected);
                startGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e){
                startGameButton.setIcon(startGameDefault);
                startGameButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                gameStart(nowSelected);
            }
        });
        add(startGameButton);

        backButton.setVisible(false);
        backButton.setBounds(20,50,60,60);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(backButtonSelected);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e){
                backButton.setIcon(backButtonDefault);
                backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e){
                backMain();
            }
        });
        add(backButton);

        menuBar.setBounds(0,0,1280,30);
        menuBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x-mouseX,y-mouseY);
            }
        });
        add(menuBar);
    }

    @Override
    public void paint(Graphics g){
        screenImage = createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
        screenGraphic = screenImage.getGraphics();
        screenDraw((Graphics2D) screenGraphic);
        g.drawImage(screenImage,0,0,null);
    }

    public void screenDraw(Graphics2D g){
        g.drawImage(background,0,0,null);
        if(!isMainScreen){
            g.drawImage(selectedImage,340,100,null);
        }
        if(isGameScreen){
            game.screenDraw(g);
        }
        paintComponents(g);
        try{
            Thread.sleep(5);
        }catch(Exception e){
            e.printStackTrace();}

        this.repaint();
    }

    public void selectTrack(int nowSelected){
        if(selectedMusic != null)
            selectedMusic.close();
        selectedImage = new ImageIcon(Main.class.getResource("Images/"+trackList.get(nowSelected).getTitleImage())).getImage();
        selectedBackground = new ImageIcon(Main.class.getResource("Images/"+trackList.get(nowSelected).getStartImage())).getImage();
        selectedMusic = new Music("Music/"+trackList.get(nowSelected).getStartMusic(),true);
        System.out.println(trackList.get(nowSelected).getStartMusic());
        selectedMusic.start();
    }

    public void selectLeft(){
        if (nowSelected==0)
            nowSelected = trackList.size()-1;
        else
            nowSelected--;
        selectTrack(nowSelected);
    }
    public void selectRight(){
        if (nowSelected== trackList.size()-1)
            nowSelected = 0;
        else
            nowSelected++;
        selectTrack(nowSelected);
    }

    public void gameStart(int nowSelected){
        if(selectedMusic!=null)
            selectedMusic.close();
        isMainScreen=true;
        leftButton.setVisible(false);
        rightButton.setVisible(false);
        startGameButton.setVisible(false);
        background = new ImageIcon(Main.class.getResource("Images/"+trackList.get(nowSelected).getGameImage())).getImage();
        backButton.setVisible(true);
        isGameScreen = true;
        game = new Game(trackList.get(nowSelected).getName(),trackList.get(nowSelected).getGameMusic());
        game.start();
        setFocusable(true);
    }

    public void backMain(){
        isMainScreen = false;
        leftButton.setVisible(true);
        rightButton.setVisible(true);
        startGameButton.setVisible(true);
        background = new ImageIcon(Main.class.getResource("Images/wall1.jpg")).getImage();
        backButton.setVisible(false);
        selectTrack(nowSelected);
        isGameScreen = false;
        game.close();
    }

    public void enterMain(){
        introMusic.close();
        selectTrack(0);
        startSelectButton.setVisible(false);
        endSelectButton.setVisible(false);
        leftButton.setVisible(true);
        rightButton.setVisible(true);
        startGameButton.setVisible(true);
        background = selectedBackground;
        isMainScreen= false;
    }
}
