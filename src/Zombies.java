import java.awt.*;
import java.util.Random;

public class Zombies extends GameObject {

    private Handler handler;

    public Zombies(int x, int y, ID id, int HP, Handler handler) {
        super(x, y, id, HP);
        //TODO Auto-generated constructor stub
        this.HP = 2;
        velX = 5;
        this.handler = handler;
    }

    private boolean collision(){
        for (int i = 0; i < handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            if (temp.getID() == ID.Plants || temp.getID() == ID.Peashooter){
                if (getBounds().intersects(temp.getBounds()))
                    return true;
            }
        }
        return false;
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        if(!collision()){
            x -= velX;
        }

        if (x <= 0 || this.HP == 0){
            handler.removeObject(this);
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
