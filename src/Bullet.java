import java.awt.*;

public class Bullet {
    private int x, y;
    private int width = 9;
    private int height = 6;
    private int speed;

    public Bullet(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void drawBullet(Graphics g, Player ref) {
        g.setColor(Color.BLACK);
        g.fillRect(JavaGraphics.screen_w/4 - (ref.returnx() - x), JavaGraphics.screen_h/3*2 - (ref.returny() - y),width,height);
        x += speed;
    }

    public int returnx() {
        return x;
    }

    public int returny() {
        return y;
    }

    public int returnWidth() {
        return width;
    }

    public int returnHeight() {
        return height;
    }

    public int returnSpeed() {
        return speed;
    }
}
