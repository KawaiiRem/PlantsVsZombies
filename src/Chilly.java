import java.awt.*;

public class Chilly extends Plants {

    public Chilly(int x, int y, ID id, int HP, Handler handler) {
        super(x, y, id, HP, handler);
    }

    @Override
    public void tick() {

        collision();
        if (this.HP == 0) handler.removePObject(this);
    }

    @Override
    public void collision(){
        double currentTime = timer.getElapsedTimeInSeconds();
        if ( roundAvoid(currentTime - lastTime, 1) >= 2 ){
            for (int i = 0; i < handler.ZList.size(); i++){
                GameObject temp = handler.ZList.get(i);
                if (temp.getY() == this.getY()){
                    temp.setHP(0);
                }
            }
            this.HP = 1;
        }
        if ( roundAvoid(currentTime - lastTime, 1) >= 3 ){
            this.HP = 0;
        }

     }

    @Override
    public void render(Graphics g) {
        if(this.HP>1){
            g.drawImage(animation[7][aniIndex], this.x-30, this.y-30,100,100, null);
            updateAnimationTick(7);
        }
        if(this.HP ==1){
            g.drawImage(animation[7][aniIndex], this.x-30, this.y-60,100,100, null);
            // updateAnimationTick(7);
            g.drawImage(chillyFire, 250, this.y, null);
        }

    }

    @Override
    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        return new Rectangle(x, y, 32, 32);
    }
}
