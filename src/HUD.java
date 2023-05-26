import java.awt.*;

public class HUD {
    private SunSpawn sunSpawn;
    private double time=0;
    StopWatch timer = new StopWatch();
    double lastTime = timer.getElapsedTimeInSeconds();

    public void setSunSpawn(SunSpawn sunSpawn){
        this.sunSpawn = sunSpawn;
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
    private void countDownTime() {
        double currentTime = timer.getElapsedTimeInSeconds();

        if(Game.gameState==STATE.GAME){
            
            if (roundAvoid(currentTime - lastTime, 1) >= 1){
                lastTime=currentTime;
                time=roundAvoid(time+1,1);
            }
        }
    }
    
    public void tick(){
        sunSpawn.tick();
        countDownTime();
        if (time >= 60){
            Game.gameState = STATE.STATEWIN;
        }
    }

    public void render(Graphics g){
        if(time < 20){
            g.setColor(Color.green);
            g.fillRect(Game.WIDTH /2 -300, 35, (int) time*10, 20);
        }
        if(time<40 && time>=20){
            g.setColor(Color.blue);
            g.fillRect(Game.WIDTH /2 -300, 35, (int) time*10, 20);
        }
        if(time<=60 && time>=40){
            g.setColor(Color.red);
            g.fillRect(Game.WIDTH /2 -300, 35, (int) time*10, 20);
        }

        g.setColor(Color.BLACK);
        g.drawRect(Game.WIDTH /2 -300, 35, 600, 20);
        sunSpawn.render(g);;
    }

    public void setTime(){
        lastTime = timer.getElapsedTimeInSeconds();
        time =0;
        sunSpawn.setTime();
    }
    public double getTime(){
        return time;
    }

    public SunSpawn getSunSpawn(){
        return this.sunSpawn;
    }
}
