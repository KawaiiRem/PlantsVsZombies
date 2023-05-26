import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.*;

public class StateGame extends MouseAdapter {

    public StateGame (){

    }

    public void mousePressed (MouseEvent e){
        int myX = e.getX();
        int myY = e.getY();

        if(Game.gameState == STATE.STATEWIN || Game.gameState == STATE.STATELOSE){
            if (mouseOver(myX, myY, Game.WIDTH/2 - 175, 250, 350, 100)){
                Game.gameState = STATE.LEVEL;
            }
            if (mouseOver(myX, myY, Game.WIDTH/2 - 175, 450, 350, 100)){
                System.exit(1);
            }

        }
    }

    public void mouseReleased (MouseEvent e){

    }

    private boolean mouseOver (int mx, int my, int x, int y, int width, int height){
        if (mx > x && mx < x + width){
            if (my > y && my < y + height){
                return true;
            }
        }
        return false;
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.white);
        Font mainFont = new Font("Times New Roman", Font.BOLD, 64);
        g.setFont(mainFont);
        if(Game.gameState == STATE.STATELOSE){
            g.drawString("LOSE !!!", Game.WIDTH/2 - 110, 150);
        }
        g.drawRect(Game.WIDTH/2 - 175, 250, 350, 100);
        g.drawString("New Game", Game.WIDTH/2 - 150, 320);

        g.drawRect(Game.WIDTH/2 - 175, 450, 350, 100);
        g.drawString("Quit", Game.WIDTH/2 - 70, 520);
    }

}
