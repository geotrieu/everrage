import java.awt.*;

public class Flag {
    private int x, y;
    private int startX, startY;
    private int endX, endY;
    //held variable:
    //0 for not being held
    //1 for being held by opposing team
    //-1 for being held by own team
    private int held = 0;
    private Player heldBy;

    public Flag(int x, int y, int endX, int endY) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.endX = endX;
        this.endY = endY;
    }

    public void setHeld(int held) {
        this.held = held;
    }

    public void setHeldBy(Player p) {
        this.heldBy = p;
    }

    public int returnx() {
        return x;
    }

    public int returny() {
        return y;
    }

    public int returnStartx() {
        return startX;
    }

    public int returnStarty() {
        return startY;
    }

    public int returnheld() {
        return held;
    }

    public void drawFlag(Graphics g, Player ref) {
        if (held != 0) {
            this.x = heldBy.returnx();
            this.y = heldBy.returny();
        }
        if (held == 1) {
            checkEnd();
        } else if (held == -1) {
            checkStart();
        }
        g.drawImage(Images.flag, JavaGraphics.screen_w/4 - (ref.returnx() - x), JavaGraphics.screen_h/3*2 - (ref.returny() - y) - 32,null);
    }

    public void checkStart() {
        if (x/32 == startX/32 && y/32 == startY /32) {
            held = 0;
            heldBy.setCarryingFlag(null);
            heldBy = null;
        }
    }

    public void checkEnd() {
        if (x/32 == endX/32 && y/32 == endY /32) {
            held = 0;
            heldBy.setCarryingFlag(null);
            heldBy = null;
            x = startX;
            y = startY;
            //score++
        }
    }
}
