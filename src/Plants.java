import java.awt.*;

public class Plants extends GameObject {

    public Plants(int x, int y, ID id) {
        super(x, y, id);
        //TODO Auto-generated constructor stub
        velX = 1;
        velY = 0;
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub
        x += velX;
        y += velY;
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        g.setColor(Color.blue);
        g.fillRect(x, y, 32, 32);
    }
    
}
