import java.util.Random;

public class Spawn {
    
    StopWatch timer = new StopWatch();
    Random r = new Random();
    private Handler handler;
    private HUD hud;
    private int lastTime = timer.intValue();

    public Spawn (Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void tick(){
        int currentTime = timer.intValue();
        if (currentTime - lastTime == 5){
            lastTime = currentTime;
            handler.addObject(new Zombies(r.nextInt(Game.WIDTH - 42),r.nextInt(Game.HEIGHT - 52), ID.Zombies, handler));
        }
    }
}
