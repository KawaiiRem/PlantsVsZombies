import java.util.Random;

public class Spawn {
    
    StopWatch timer = new StopWatch();
    Random r = new Random();
    private Handler handler;
    private HUD hud;
    private double lastTime = timer.getElapsedTimeInSeconds();

    public Spawn (Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public void tick(){
        double currentTime = timer.getElapsedTimeInSeconds();
        if (roundAvoid(currentTime - lastTime, 1) == 1.0){
            lastTime = currentTime;
            handler.addObject(new Zombies(Game.WIDTH - 42,r.nextInt(5)*172 + 86, ID.Zombies, 5, handler));
        }
    }

    public void setTime(){
        lastTime = timer.getElapsedTimeInSeconds();
    }
}
