import java.awt.*;

public class Projectile extends GameObject {

    private int damage = 1;
    private boolean active = true;
    private Handler handler = new Handler();

    public Projectile (int x, int y, ID id , ID plantID, int HP, Handler handler)
    {
        super(x, y, id, HP);
        getDamage(plantID);
        this.handler = handler;
    }

    public void getDamage(ID plantID){
        if (plantID == ID.Peashooter){
            this.damage = 1;
        }
        else if (plantID == ID.Plants){
            this.damage = 2;
        }
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(Color.green);
        g.fillRect(x, y, 15, 15);
    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return new Rectangle(x, y, 15, 15);
    }
    
    @Override
    public void tick() 
    {
        x += 10;
        if(x >= Game.WIDTH - 40 || this.HP == 0)
        {
            handler.removeObject(this);
        }
        collision();
    }

    public void collision(){
        for (int i = 0; i < handler.object.size(); i++){

            GameObject temp = handler.object.get(i);

            if (temp.getID() == ID.Zombies || temp.getID() == ID.SmartZombie){
                if ( this.getBounds().intersects( temp.getBounds() ) ){
                    temp.setHP(temp.getHP() - this.damage);
                    this.HP--;
                }
                if (this.HP == 0){
                    handler.removeObject(this);
                }
            }
        }
    }

    public void shoot(int x, int y, ID plantID){
        handler.addObject(new Projectile(x + 8, y + 8, ID.Protectile, plantID, 1, handler));
    }
    
    public void setActive(boolean active)
    {
        this.active = active;
    }
    
    public boolean isActive()
    {
        return active;
    }
} 
