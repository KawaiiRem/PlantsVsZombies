import java.awt.*;
import java.util.Random;

public class Plants extends GameObject {

    Random r = new Random();
    Handler handler;

    public Plants(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        //TODO Auto-generated constructor stub
        this.handler = handler;
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 47);
        y = Game.clamp(y, 0, Game.HEIGHT - 70);

        collision();
    }

    private void collision(){

        for (int i = 0; i < handler.object.size(); i++){

            GameObject temp = handler.object.get(i);

            if (temp.getID() == ID.Zombies || temp.getID() == ID.SmartZombie){
                if (getBounds().intersects(temp.getBounds())){
                    HUD.HP -= 2;
                }

            }
        }

    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(Color.blue);
        g.fillRect(x, y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return new Rectangle(x, y, 32, 32);
    }
    
}
