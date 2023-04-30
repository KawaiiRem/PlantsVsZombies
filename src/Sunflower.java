import java.awt.*;
import java.util.Random;

public class Sunflower extends GameObject{
    
    private int flower = 0;
    private Random r = new Random();
    private StopWatch timer = new StopWatch();
    private double lastTime = timer.getElapsedTimeInSeconds(), immuneTime = timer.getElapsedTimeInSeconds();
    private boolean getHit = true;
    private Handler handler;
    private Handler sunHandler;
    private SunSpawn sunSpawn;

    public Sunflower(int x, int y, ID id, int HP, Handler handler, SunSpawn sunSpawn) {
        super(x, y, id, HP);
        //TODO Auto-generated constructor stub
        this.handler = handler;
        this.sunHandler = sunSpawn.getSunHandler();
        this.sunSpawn = sunSpawn;
    }


    @Override
    public void tick() {
        // TODO Auto-generated method stub
        double currentTime = timer.getElapsedTimeInSeconds();
        if (roundAvoid(currentTime - lastTime, 1) == 3.0){
            lastTime = currentTime;
            sunSpawn.addSun(this.getX() -10, this.getY() + 10, ID.Sun ,1 ,sunHandler);
        }
        collision();
    }



    public int getFlower(){
        return flower;
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
        g.setColor(Color.orange);
        g.fillRect(x, y, 30, 30);

    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return new Rectangle(x, y, 30, 30);
    }

}