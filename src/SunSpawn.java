import java.util.Random;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class SunSpawn extends MouseAdapter{

    private Random r = new Random();
    private StopWatch timer = new StopWatch();
    private double lastTime = timer.getElapsedTimeInSeconds();
    private Handler sun = new Handler();
    private PlantSelector plantSelector;


    public SunSpawn(PlantSelector plantSelector){
        this.plantSelector = plantSelector;
    }

    public void addSun(int x, int y, ID id ,int HP, Handler sun){
        sun.addObject(new Sun(x, y, id, 1, this.sun));
    }

    public Handler getSunHandler(){
        return this.sun;
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public void tick(){
        double currentTime = timer.getElapsedTimeInSeconds();
        if (roundAvoid(currentTime - lastTime, 1) == 2.0){
            lastTime = currentTime;
            sun.addObject(new Sun(r.nextInt(1140) + 200, 0, ID.Sun, 1, sun));
        }
        sun.tick();
    }

    public void setTime(){
        lastTime = timer.getElapsedTimeInSeconds();
    }

    public void render(Graphics g){
        sun.render(g);
    }

    public void mouseMoved(MouseEvent e){
        for (int i = 0; i < this.sun.object.size(); i++){
            int sunX = this.sun.object.get(i).getX();
            int sunY = this.sun.object.get(i).getY();
            if (e.getX() > sunX && e.getX() < sunX + 20){
                if (e.getY() > sunY && e.getY() < sunY +20){
                    this.sun.object.remove(i);
                    plantSelector.setCountSun(25);
                }
            }
        }
    }
}
