
public class Chilly extends Plants {

    public Chilly(int x, int y, ID id, int HP, Handler handler) {
        super(x, y, id, HP, handler);
        constant = 7;
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
            this.HP = 0;
        }
     }

}

