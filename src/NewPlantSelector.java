import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.*;

public class NewPlantSelector extends MouseAdapter {
    private Game game;
    private Handler handler;
    private ID nextPlant;
    private int[][] checkEmpty = new int[10][6];

    public NewPlantSelector(Game game, Handler handler){
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e){
        if (game.gameState == STATE.GAME){
            int mx = e.getX();
            int my = e.getY();
            
            if (mouseOver(mx, my, 0, 0, 180, 120)){
                nextPlant = ID.Peashooter;
            }
            else if (mouseOver(mx, my, 0, 120, 180, 120)){
                nextPlant = ID.Peashooter;
            }
            else if (mouseOver(mx, my, 0, 120*2, 180, 120)){
                nextPlant = ID.Peashooter;
            }
            else if (mouseOver(mx, my, 0, 120*3, 180, 120)){
                nextPlant = ID.Peashooter;
            }
            else if (mouseOver(mx, my, 0, 120*4, 180, 120)){
                nextPlant = ID.Peashooter;
            }
            else if (mouseOver(mx, my, 0, 120*5, 180, 120)){
                nextPlant = ID.Peashooter;
            }
            else if (mouseOver(mx, my, 0, 120*6, 180, 120)){
                nextPlant = ID.Peashooter;
            }
        }
    }

    public void mouseReleased(MouseEvent e){
        if (game.gameState == STATE.GAME){
            int mx = e.getX();
            int my = e.getY();

            int hereX=0, hereY=0;
            int thisI=0, thisJ=0;

            for (int j = 0; j < 5; j ++){
                for (int i = 0; i < 9; i++){
                    if (mouseOver(mx, my, 180 + i * 140, 172 * j, 140, 172)){
                        hereX = 180 + i * 140 + 70;
                        hereY = 172 * j + 86;
                        thisI = i;
                        thisJ = j;
                    }
                }
            }
            
            if (nextPlant != null && checkEmpty[thisI][thisJ] == 0 && hereX + hereY != 0){
                handler.addObject(new Peashooter(hereX, hereY, ID.Peashooter, 2, handler));
                nextPlant = null;
                checkEmpty[thisI][thisJ] = 1;
            }
        }  
    }

    public boolean mouseOver(int mx, int my, int x, int y, int width, int height){
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

        g.drawRect(0, 0, 180, 120);
        g.drawRect(0, 120, 180, 120);
        g.drawRect(0, 120*2, 180, 120);
        g.drawRect(0, 120*3, 180, 120);
        g.drawRect(0, 120*4, 180, 120);
        g.drawRect(0, 120*5, 180, 120);
        g.drawRect(0, 120*6, 180, 120);
    }
}
