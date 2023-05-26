import java.awt.*;
import java.util.Random;

public class Sunflower extends Plants{
    
    private int flower = 0;
    private Random r = new Random();
    private SunSpawn sunSpawn;

    public Sunflower(int x, int y, ID id, int HP, Handler handler, SunSpawn sunSpawn) {
        super(x, y, id, HP, handler);
        this.sunSpawn = sunSpawn;
        constant = 2;
    }


    @Override
    public void tick() {
        double currentTime = timer.getElapsedTimeInSeconds();
        if (roundAvoid(currentTime - lastTime, 1) >= 3.0){
            lastTime = currentTime;
            sunSpawn.addSun(this.getX() - r.nextInt(20), this.getY() + r.nextInt(15), ID.Sun ,1 ,handler);
        }
        super.collision();
    }

    public int getFlower(){
        return flower;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 30);
    }

}