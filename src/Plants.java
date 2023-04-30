import java.awt.*;
import java.util.Random;

public class Plants extends GameObject {

    Random r = new Random();
    StopWatch timer = new StopWatch();
    double lastTime = timer.getElapsedTimeInSeconds();
    Projectile shoot;
    Handler handler;

    public Plants(int x, int y, ID id, int HP, Handler handler) {
        super(x, y, id, HP);
        this.HP = 10;
        //TODO Auto-generated constructor stub
        this.handler = handler;
        shoot = new Projectile(this.x, this.y, ID.Protectile, ID.Peashooter, 0, handler);
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 47);
        y = Game.clamp(y, 0, Game.HEIGHT - 70);
        
        double currentTime = timer.getElapsedTimeInSeconds();
        if (roundAvoid(currentTime - lastTime, 1) == 1.0){
            lastTime = currentTime;
            shoot.shoot(this.x, this.y, ID.Peashooter);
        }
        collision();

        if (this.HP == 0) handler.removeObject(this);
    }

    private void collision(){

        for (int i = 0; i < handler.object.size(); i++){

            GameObject temp = handler.object.get(i);

            if (temp.getID() == ID.Zombies || temp.getID() == ID.SmartZombie){
                if (getBounds().intersects(temp.getBounds())){
                    handler.removeObject(temp);
                    for (int j = 0; j < 2; j++){
                        handler.addObject(new Zombies(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT -40), ID.Zombies, 5, handler));
                    }
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
