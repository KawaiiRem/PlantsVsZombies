import java.awt.*;
import java.util.Random;

public class SmartZombie extends GameObject {

    private Handler handler;
    private GameObject plant;

    public SmartZombie(int x, int y, ID id, int HP, Handler handler) {
        super(x, y, id, HP);
        this.HP = 3;
        //TODO Auto-generated constructor stub
        this.handler = handler;
        for (int i = 0; i < handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            if (temp.getID() == ID.Plants) plant = temp;
        }
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        double diffX = x - plant.getX() - 10;
        double diffY = y - plant.getY() - 10;
        double distance = Math.sqrt(diffX*diffX + diffY*diffY);

        velX = (int) (-3.5*diffX/distance);
        velY = (int) (-3.5*diffY/distance);

        x += velX;
        y += velY;

        if (y < 0 || y > Game.HEIGHT - 52) {
            velY*=-1;
        }
        if (x < 0 || x > Game.WIDTH - 42){
            velX*=-1;
        } 

        if (HP == 0) handler.removeObject(this);
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(Color.red);
        g.fillRect(x, y, 20, 20);
    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return new Rectangle(x, y, 20, 20);
    }
}
