
import org.w3c.dom.css.RGBColor;
//
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.*;
import java.util.Random;

public class PlantSelector extends MouseAdapter {
    StopWatch timer = new StopWatch();
    private double lastTime = timer.getElapsedTimeInSeconds();
    private ID[] selectedPlant = new ID[7];
    private Game game;
    private Handler handler;
    private ID nextPlant;
    private int nextSlot, nextSunCost;
    private int[] slotPlanted = new int[]{0, 0, 0, 0, 0, 0, 0};
    private int[][] checkEmpty = new int[10][6];
    public static int countSun=200;
    private double[] cooldownPlant = new double[]{0,0,0,0,0,0,0};
    private double time;
    private boolean valid = false;
    private HUD hud;

    public PlantSelector(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
        selectedPlant = new ID[]{ID.Sunflower, ID.Peashooter, ID.Peashooter, ID.Peashooter, ID.Peashooter, ID.Peashooter, ID.Peashooter};
    }

    private void countDownTime() {
        if(game.gameState==STATE.GAME){
            double currentTime = timer.getElapsedTimeInSeconds();
            if (roundAvoid(currentTime - lastTime, 1) == 1){
                lastTime=currentTime;
                time=roundAvoid(time+1,1);

                if(cooldownPlant[0]>0 && slotPlanted[0] != 0){
                    cooldownPlant[0] = roundAvoid(cooldownPlant[0] - 1, 1);
                }

                if( cooldownPlant[1]>0 && slotPlanted[1] != 0){
                    cooldownPlant[1] = roundAvoid(cooldownPlant[1] - 1, 1);
                }

                if(cooldownPlant[2]>0 && slotPlanted[2] != 0){
                    cooldownPlant[2] = roundAvoid(cooldownPlant[2] - 1, 1);
                }

                if(cooldownPlant[3]>0 && slotPlanted[3] != 0){
                    cooldownPlant[3] = roundAvoid(cooldownPlant[3] - 1, 1);
                }

                if(cooldownPlant[4]>0 && slotPlanted[4] != 0){
                    cooldownPlant[4] = roundAvoid(cooldownPlant[4] - 1, 1);
                }

                if(cooldownPlant[5]>0 && slotPlanted[5] != 0){
                    cooldownPlant[5] = roundAvoid(cooldownPlant[5] - 1, 1);
                }

                if(cooldownPlant[6]>0 && slotPlanted[6] != 0){
                    cooldownPlant[6] = roundAvoid(cooldownPlant[6] - 1, 1);
                }
            }
        }
    }
    public void mousePressed(MouseEvent e) {
        if (game.gameState == STATE.GAME) {
            int mx = e.getX();
            int my = e.getY();

            if (mouseOver(mx, my, 0, 0, 180, 120) && countSun>=30 && cooldownPlant[0] ==0) {
                nextPlant = selectedPlant[0];
                nextSlot = 0;
                slotPlanted[0] = 1;
                nextSunCost = 30;
            } else if (mouseOver(mx, my, 0, 120, 180, 120) && countSun>=70 && cooldownPlant[1] ==0) {
                nextPlant = selectedPlant[1];
                nextSlot = 1;
                slotPlanted[1] = 1;
                nextSunCost =70;

            } else if (mouseOver(mx, my, 0, 120 * 2, 180, 120) && countSun>=70 && cooldownPlant[2] ==0) {
                nextPlant = selectedPlant[2];
                nextSlot = 2;
                slotPlanted[2] = 1;
                nextSunCost =70;

            } else if (mouseOver(mx, my, 0, 120 * 3, 180, 120)) {
                nextPlant = selectedPlant[3];
                nextSlot = 3;
                slotPlanted[3] = 1;
                nextSunCost =70;
                
            } else if (mouseOver(mx, my, 0, 120 * 4, 180, 120)) {
                nextPlant = selectedPlant[4];
                nextSlot = 4;
                slotPlanted[4] = 1;
                nextSunCost =70;
            } else if (mouseOver(mx, my, 0, 120 * 5, 180, 120)) {
                nextPlant = selectedPlant[5];
                nextSlot = 5;
                slotPlanted[5] = 1;
                nextSunCost =70;
            } else if (mouseOver(mx, my, 0, 120 * 6, 180, 120)) {
                nextPlant = selectedPlant[6];
                nextSlot = 6;
                slotPlanted[6] = 1;
                nextSunCost =70;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (game.gameState == STATE.GAME) {
            int mx = e.getX();
            int my = e.getY();

            int hereX = 0, hereY = 0;
            int thisI = 0, thisJ = 0;


            for (int j = 0; j < 5; j++) {
                for (int i = 0; i < 9; i++) {
                    if (mouseOver(mx, my, 180 + i * 140, 172 * j, 140, 172)) {
                        hereX = 180 + i * 140 + 70;
                        hereY = 172 * j + 86;
                        thisI = i;
                        thisJ = j;
                    }
                }
            }

            valid = false;

            if (nextPlant != null && checkEmpty[thisI][thisJ] == 0 && hereX + hereY != 0) {
                if(nextPlant == selectedPlant[0]){
                    handler.addObject(new Sunflower(hereX, hereY, ID.Plants,5, handler, hud.getSunSpawn()));
                    valid = true;
                }
                else if(nextPlant == selectedPlant[1]){
                    handler.addObject(new Plants(hereX,hereY,ID.Plants,5,handler));
                    valid = true;
                }
                else if(nextPlant == selectedPlant[2]){
                    handler.addObject(new Peashooter(hereX,hereY,ID.Plants,5,handler));
                    valid = true;
                }

                nextPlant = null;
                if (valid == true){
                    countSun -= nextSunCost;
                    cooldownPlant[nextSlot] =5.0;
                    nextSlot = -1;
                    nextSunCost = 0;
                }
                else{
                    nextSunCost = 0;
                    nextSlot = -1;
                }
                checkEmpty[thisI][thisJ] = 1;
            }
        }
    }

    public boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            }
        }
        return false;
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public void tick() {
        countDownTime();
    }

    public void render(Graphics g) {


        Font mainFont = new Font("Times New Roman", Font.BOLD, 32);
        g.setFont(mainFont);
        g.setColor(Color.white);
        g.drawString("Sun: "+countSun +" Time: "+time,185,30);
        g.drawRect(180, 40, 150, 1);
//        sunflower
        if((countSun>=30) && (cooldownPlant[0]==0)){
            g.setColor(Color.orange);
            g.fillRect(0,0,180,120);
        }else{
            g.drawString(cooldownPlant[0]+"s",0,120);
        }

//      Peashooter
        if((countSun>=70) && (cooldownPlant[1]==0)){
            g.setColor(Color.green);
            g.fillRect(0,120,180,120);
        }else{
            g.drawString(cooldownPlant[1]+"s",0,240);
        }
//      Plants
        if((countSun>=70) && (cooldownPlant[2]==0)){
            g.setColor(Color.blue);
            g.fillRect(0,120 * 2,180,120);
        }else{
            g.drawString(cooldownPlant[2]+"s",0,360);
        }

//      border
        g.setColor(Color.white);

        g.drawRect(0, 0, 180, 120);

        g.drawRect(0, 120, 180, 120);

        g.drawRect(0, 120 * 2, 180, 120);

        g.drawRect(0, 120 * 3, 180, 120);

        g.drawRect(0, 120 * 4, 180, 120);

        g.drawRect(0, 120 * 5, 180, 120);

        g.drawRect(0, 120 * 6, 180, 120);
    }

    public void setTime(){
        this.lastTime = timer.getElapsedTimeInSeconds();
    }

    public void setCountSun(int sun){
        this.countSun += sun;
    }
}