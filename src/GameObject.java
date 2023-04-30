import java.awt.*;

public abstract class GameObject {
    
    protected int x, y;
    protected ID id;
    protected int velX, velY;
    protected int HP;

    public GameObject(int x, int y, ID id, int HP){
        this.x = x;
        this.y = y;
        this.id = id;
        this.HP = HP;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public void setID(ID id){
        this.id = id;
    }
    public ID getID(){
        return this.id;
    }

    public void setVelX(int x){
        this.velX = x;
    }
    public int getVelX(){
        return velX;
    }

    public void setVelY(int y){
        this.velY = y;
    }
    public int getVelY(){
        return velY;
    }
    public void setHP(int HP){
        this.HP = HP;
    }
    public int getHP(){
        return HP;
    }
}
