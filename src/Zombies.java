import java.awt.*;
import java.util.Random;

public class Zombies extends GameObject {

    private Handler handler;
    int accerX = 1, accerY = 1;
    StopWatch timer = new StopWatch();
    int lastTime = timer.intValue();

    public Zombies(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        //TODO Auto-generated constructor stub
        velX = 5;
        velY = 5;
        this.handler = handler;
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        int currentTime = timer.intValue();
        if (currentTime - lastTime == 2){
            lastTime = currentTime;
            if (velY < 0){
                this.setVelY(this.getVelY() - 1);
            }
            else{
                this.setVelY(this.getVelY() + 1);
            }
    
            if (velX < 0){
                this.setVelX(this.getVelX() - 1);
            }
            else{
                this.setVelX(this.getVelX() + 1);
            }
        } 

        x += velX;
        y += velY;

        if (y < 0 || y > Game.HEIGHT - 52) {
            velY*=-1;
        }
        if (x < 0 || x > Game.WIDTH - 42){
            velX*=-1;
        } 
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(Color.white);
        g.fillRect(x, y, 20, 20);
    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return new Rectangle(x, y, 20, 20);
    }
}
