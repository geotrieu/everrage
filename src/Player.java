import java.awt.Color;
import java.awt.Graphics;
public class Player {
    //X and Ys are measured in PIXELS
    private int x, y, health, Vx, Vy;
    private boolean onGround = false;
    int MOVE_UP;
    int MOVE_DOWN;
    int MOVE_LEFT;
    int MOVE_RIGHT;
    Color color;

    public Player (int x, int y, Color c, int MOVE_UP, int MOVE_DOWN, int MOVE_LEFT, int MOVE_RIGHT) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.color = c;
        this.MOVE_UP = MOVE_UP;
        this.MOVE_DOWN = MOVE_DOWN;
        this.MOVE_LEFT = MOVE_LEFT;
        this.MOVE_RIGHT = MOVE_RIGHT;
    }

    public void move() {
        x = x + Vx/10;
        y = y + Vy/10;
    }

    public void setSpeed(int Vx, int Vy) {
        this.Vx = Vx;
        this.Vy = Vy;
    }

    public void teleport(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setGround(boolean s) {
        onGround = s;
    }

    public void drawPlayer(Graphics g, Player ref) {
        g.fillRect(JavaGraphics.screen_w/4 - (ref.returnx() - x), JavaGraphics.screen_h/3*2 - (ref.returny() - y),32,32);
    }

    public void setColor(Graphics g) {
        g.setColor(color);
    }

    public int returnx(){
        return this.x;
    }

    public int returny(){
        return this.y;
    }

    public int returnVx() { return this.Vx; }

    public int returnVy() { return this.Vy; }

    public boolean onGround() { return this.onGround; }
}
