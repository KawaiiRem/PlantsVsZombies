import java.awt.*;

public class HUD {
    private SunSpawn sunSpawn;
    StopWatch timer = new StopWatch();
    double lastTime = timer.getElapsedTimeInSeconds();

    public void setSunSpawn(SunSpawn sunSpawn){
        this.sunSpawn = sunSpawn;
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public void tick(){
        sunSpawn.tick();
    }

    public void render(Graphics g){
        g.setColor(Color.white);
        sunSpawn.render(g);;
    }

    public void setTime(){
        lastTime = timer.getElapsedTimeInSeconds();
        sunSpawn.setTime();
    }

    public SunSpawn getSunSpawn(){
        return this.sunSpawn;
    }
}
