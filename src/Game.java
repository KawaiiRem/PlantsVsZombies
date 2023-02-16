import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.function.DoubleToIntFunction;

import javax.swing.JLabel;

public class Game extends Canvas implements Runnable {
    
    public static final int WIDTH = 1000, HEIGHT = WIDTH/12*9;

    private Thread thread;
    private boolean running = false;

    HUD hp;
    private Handler handler;
    Spawn spawner;
    
    public Game(){
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        
        new Window(WIDTH, HEIGHT, "Plants VS Zombies", this);

        hp = new HUD();
        spawner = new Spawn(handler, hp);

        handler.addObject(new Plants(WIDTH / 2 - 32,HEIGHT / 2 - 32, ID.Plants, handler));
        handler.addObject(new Zombies(1,1, ID.Zombies, handler));
        handler.addObject(new SmartZombie(10, 10,ID.SmartZombie, handler));
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
                //System.out.println("FPS: " + frames);
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
        hp.render(g);

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
        hp.tick();
        spawner.tick();
    }

}
