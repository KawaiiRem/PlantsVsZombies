import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Peashooter extends GameObject{

    private StopWatch timer = new StopWatch();
    private double lastTime = timer.getElapsedTimeInSeconds(), immuneTime = timer.getElapsedTimeInSeconds();
    private Projectile shoot;
    private Handler handler;
    private boolean getHit = true;

    public Peashooter(int x, int y, ID id, int HP, Handler handler) {
        super(x, y, id, HP);
        //TODO Auto-generated constructor stub
        this.handler = handler;
        this.shoot = new Projectile(this.x, this.y, ID.Protectile, ID.Peashooter, 1, handler);
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        double currentTime = timer.getElapsedTimeInSeconds();
        if (roundAvoid(currentTime - lastTime, 1) == 2.0){
            lastTime = currentTime;
            shoot.shoot(this.x, this.y, ID.Peashooter);
        }
        collision();
    }

    private void collision(){

        for (int i = 0; i < handler.object.size(); i++){

            GameObject temp = handler.object.get(i);

            if (temp.getID() == ID.Zombies || temp.getID() == ID.SmartZombie){
                if (getBounds().intersects(temp.getBounds())){
                    if (getHit){
                        HP--;
                        System.out.println("Ouch!");
                        if (this.HP == 0){
                            handler.removeObject(this);
                        }
                        getHit = false;
                        immuneTime = timer.getElapsedTimeInSeconds();
                    }
                    else{
                        double waitTime = timer.getElapsedTimeInSeconds();
                        if ( roundAvoid(waitTime - immuneTime, 1) >= 0.5 ){
                            getHit = true;
                        }
                    }
                }

            }
        }

    }


    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(Color.green);
        g.fillRect(this.x, this.y, 20, 20);
    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return new Rectangle(this.x, this.y, 20, 20);
    }
    
}
