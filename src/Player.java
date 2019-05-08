import java.awt.Color;
import java.awt.Graphics;
public class Player {
    private int x, y, health;
    char MOVE_UP;
    char MOVE_DOWN;
    char MOVE_LEFT;
    char MOVE_RIGHT;
    Color color;

    public Player (int x, int y, Color c, char MOVE_UP, char MOVE_DOWN, char MOVE_LEFT, char MOVE_RIGHT) {
        this.x = x;
        this.y = y;
        this.health = 100;
        this.color = c;
        this.MOVE_UP = MOVE_UP;
        this.MOVE_DOWN = MOVE_DOWN;
        this.MOVE_LEFT = MOVE_LEFT;
        this.MOVE_RIGHT = MOVE_RIGHT;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawPlayer(Graphics g) {
        g.fillRect(x,y,50,50);
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
}
