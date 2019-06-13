/**************************************************
 * CLASS: Flag
 * NAME: George Trieu
 * Date: 06/12/2019
 * Description: The Flag object defines methods and functions
 *              for the movement and behaviour of each flag.
 *****************************************************/
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

    /**************************************************
     * CONSTRUCTOR: Flag
     * NAME: George Trieu
     * Date: 06/12/2019
     * Inputs: int x, int y, int endX, int endY
     *****************************************************/
    public Flag(int x, int y, int endX, int endY) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.endX = endX;
        this.endY = endY;
    }

    /**************************************************
     * FUNCTION: setHeld
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Sets if the flag is being held, and the status of whom
     * Inputs: int held
     * Output: N/A
     *****************************************************/
    public void setHeld(int held) {
        this.held = held;
    }

    /**************************************************
     * FUNCTION: setHeldBy
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Sets the player who is holding the flag
     * Inputs: Player p
     * Output: N/A
     *****************************************************/
    public void setHeldBy(Player p) {
        this.heldBy = p;
    }

    /**************************************************
     * FUNCTION: returnX
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the X Coordinate.
     * Inputs: N/A
     * Output: The x position of the Flag
     *****************************************************/
    public int returnX() {
        return x;
    }

    /**************************************************
     * FUNCTION: returnY
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the Y Coordinate.
     * Inputs: N/A
     * Output: The y position of the Flag
     *****************************************************/
    public int returnY() {
        return y;
    }

    /**************************************************
     * FUNCTION: returnStartX
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the startX Coordinate.
     * Inputs: N/A
     * Output: The starting x position of the Flag
     *****************************************************/
    public int returnStartX() {
        return startX;
    }

    /**************************************************
     * FUNCTION: returnStartY
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the startY Coordinate.
     * Inputs: N/A
     * Output: The starting y position of the Flag
     *****************************************************/
    public int returnStartY() {
        return startY;
    }

    /**************************************************
     * FUNCTION: returnHeld
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the status of the flag.
     * Inputs: N/A
     * Output: The status of the flag.
     *****************************************************/
    public int returnheld() {
        return held;
    }

    /**************************************************
     * FUNCTION: drawFlag
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Draws the flag.
     * Inputs: Graphics g, Player ref
     * Output: N/A
     *****************************************************/
    public void drawFlag(Graphics g, Player ref) {
        if (held != 0) {
            this.x = heldBy.returnX();
            this.y = heldBy.returnY();
        }
        if (held == 1) {
            checkEnd();
        } else if (held == -1) {
            checkStart();
        }
        g.drawImage(Images.flag, JavaGraphics.screen_w/4 - (ref.returnX() - x), JavaGraphics.screen_h/3*2 - (ref.returnY() - y) - 32,null);
    }

    /**************************************************
     * FUNCTION: checkStart
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Checks whether the flag has returned to the start.
     * Inputs: N/A
     * Output: N/A
     *****************************************************/
    public void checkStart() {
        if (x/32 == startX/32 && y/32 == startY /32) {
            held = 0;
            heldBy.setCarryingFlag(null);
            heldBy = null;
        }
    }

    /**************************************************
     * FUNCTION: checkEnd
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Checks whether the flag has reached the opponent's end
     * Inputs: N/A
     * Output: N/A
     *****************************************************/
    public void checkEnd() {
        if (x/32 == endX/32 && y/32 == endY /32) {
            Scoreboard.setScore(Scoreboard.getScore(heldBy.returnPlayerNum())+1, heldBy.returnPlayerNum());
            held = 0;
            heldBy.setCarryingFlag(null);
            heldBy = null;
            x = startX;
            y = startY;
        }
    }
}
