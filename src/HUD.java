import java.awt.*;

public class HUD {
    public static int HP = 100;

    private int greenValue = 255;
    public int score = 0;
    StopWatch timer = new StopWatch();
    int lastTime = timer.intValue();

    public void tick(){
        HP = Game.clamp(HP, 0, 100);
        greenValue = Game.clamp(greenValue, 0, 255);
        greenValue = HP*2;
        int currentTime = timer.intValue();
        if (currentTime - lastTime == 1){
            lastTime = currentTime;
            score++;
        }
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(35, 35, 200, 50);
        g.setColor(new Color (75, greenValue, 0));
        g.fillRect(35, 35, HP*2, 50);
        g.setColor(Color.white);
        g.drawRect(35, 35, 200, 50);

        g.drawString("Score: " + score, Game.WIDTH/2 -32, 50);
    }

    public int getScore(){
        return score;
    }
}
