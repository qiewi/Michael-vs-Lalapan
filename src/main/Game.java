package main;

import javax.swing.JFrame;

import scenes.Help;
import scenes.Menu;
import scenes.PlantsList;
import scenes.Playing;
import scenes.Settings;
import scenes.ZombiesList;

public class Game extends JFrame implements Runnable{
    private static GameScreen gameScreen;
    private Thread gameThread;

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    // Classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;
    private Help help;
    private PlantsList plantsList;
    private ZombiesList zombiesList;

    public Game() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setLocationRelativeTo(null);

        initClasses();

        setResizable(false);
        add(gameScreen);
        pack();
        
        setVisible(true);
    }

    private void initClasses() {
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
        help = new Help(this);
        plantsList = new PlantsList(this);
        zombiesList = new ZombiesList(this);
    }
    
    public static void update() {
        gameScreen.update();
    }


    private void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void updateGame() {
        // System.out.println("Game Updated!");
    }

    public static void main(String[] args) {
        
        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();
    }

    @Override
    public void run() {

        double timePerframe = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long lastframe = System.nanoTime();
        long lastUpdate = System.nanoTime();

        long lastTimeCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        long now;
        
        
        while (true) {
            
            now = System.nanoTime();
            // Render
            if (now - lastframe >= timePerframe) {
                repaint();
                lastframe = now;
                frames++;
            } else {
                // do nothing
            }

            // Update
            if (now - lastUpdate >= timePerUpdate) {
                updateGame();
                lastUpdate = now;
                updates++;
            } else {
                repaint();
            }

            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    // Getters and Setters
    public Render getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Settings getSettings() {
        return settings;
    }

    public Help getHelp() {
        return help;
    }

    public PlantsList getPlantsList() {
        return plantsList;
    }

    public ZombiesList getZombiesList() {
        return zombiesList;
    }
}
