import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Game extends Canvas implements Runnable {
    
    public static final int WIDTH = 1540, HEIGHT = 860;

    private Thread thread;
    private boolean running = false;

    public HUD hp;
    private Handler handler;
    private SunSpawn sunSpawn;
    private Spawn spawner;
    private Menu menu;
    private Music music;
    private PlantSelector selector;

    public STATE gameState = STATE.MENU;
    
    public Game() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        handler = new Handler();
        hp = new HUD();
        
        selector = new PlantSelector(this, handler, hp);
        sunSpawn = new SunSpawn(selector);
        hp.setSunSpawn(sunSpawn);
        spawner = new Spawn(handler, hp);
        menu = new Menu(this, handler, spawner, hp, selector);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);
        this.addMouseListener(selector);
        this.addMouseMotionListener(sunSpawn);
        music = new Music(this);

        new Window(WIDTH, HEIGHT, "Plants VS Zombies", this);
    }

    public static void main(String[] args) throws Exception{
        new Game();
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override

    public void run() {
        // TODO Auto-generated method stub
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1){
                tick();
                delta--;
            }

            if (running) 
                render();

            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        this.requestFocus();
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);

        if (gameState == STATE.GAME){
            hp.render(g);
            selector.render(g);
        }
        else {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int y, int x){
        if (var >= x){
            return var = x;
        }
        else if (var <= y){
            return var = y;
        }
        else return var;
    }

    private void tick() {
        handler.tick();
        if (gameState == STATE.GAME){
            hp.tick();
            spawner.tick();
            selector.tick();
        }
    }

}
