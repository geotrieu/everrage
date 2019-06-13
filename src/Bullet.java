/**************************************************
 * CLASS: Bullet
 * NAME: George Trieu
 * Date: 06/12/2019
 * Description: The Bullet object defines methods and functions
 *              for the movement and behaviour of each bullet.
 *****************************************************/
import java.awt.*;

public class Bullet {
    private int x, y;
    private int width = 9;
    private int height = 6;
    private int speed;

    /**************************************************
     * CONSTRUCTOR: Bullet
     * NAME: George Trieu
     * Date: 06/12/2019
     * Inputs: int x, int y, int speed
     *****************************************************/
    public Bullet(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    /**************************************************
     * FUNCTION: drawBullet
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Draws the bullet.
     * Inputs: Graphics g, Player ref
     * Output: N/A
     *****************************************************/
    public void drawBullet(Graphics g, Player ref) {
        g.setColor(Color.BLACK);
        g.fillRect(JavaGraphics.screen_w/4 - (ref.returnX() - x), JavaGraphics.screen_h/3*2 - (ref.returnY() - y),width,height);
        x += speed;
    }

    /**************************************************
     * FUNCTION: returnX
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the X Coordinate.
     * Inputs: N/A
     * Output: The x position of the Bullet
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
     * Output: The y position of the Bullet
     *****************************************************/
    public int returnY() {
        return y;
    }

    /**************************************************
     * FUNCTION: returnWidth
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the width of the bullet.
     * Inputs: N/A
     * Output: The width of the Bullet
     *****************************************************/
    public int returnWidth() {
        return width;
    }

    /**************************************************
     * FUNCTION: returnSpeed
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the speed of the bullet.
     * Inputs: N/A
     * Output: The speed of the Bullet
     *****************************************************/
    public int returnSpeed() {
        return speed;
    }
}
