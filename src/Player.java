/**************************************************
 * CLASS: Player
 * NAME: George Trieu
 * Date: 06/12/2019
 * Description: The player object defines the position, and behaviour
 *              of the player.
 *****************************************************/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {
    //X and Ys are measured in PIXELS
    private int x, y, health, vX, vY;
    private int startX, startY;
    private boolean onGround = false;
    private int controls[] = new int[5];
    //RIGHT is the Positive Direction
    private int lastDirection = 1;
    private BufferedImage pImg[];
    private int shootCool = 0;
    private int respawnCool = 0;
    private int playerNum = 0;
    private Flag carryingFlag;

    /**************************************************
     * CONSTRUCTOR: Player
     * NAME: George Trieu
     * Date: 06/12/2019
     * Inputs: int x, int y, Color c,
     *         int moveUpC, int moveDownC, int moveLeftC, int moveRightC, int shootC
     *         BufferedImage p[img], int playerNum
     *****************************************************/
    public Player (int x, int y, Color c, int moveUpC, int moveDownC, int moveLeftC, int moveRightC, int shootC, BufferedImage pImg[], int playerNum) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.health = 100;
        this.pImg = pImg;
        this.controls[0] = moveUpC;
        this.controls[1] = moveDownC;
        this.controls[2] = moveLeftC;
        this.controls[3] = moveRightC;
        this.controls[4] = shootC;
        this.playerNum = playerNum;
    }

    /**************************************************
     * FUNCTION: move
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Moves the player with speed.
     * Inputs: N/A
     * Output: N/A
     *****************************************************/
    public void move() {
        x = x + vX/10;
        y = y + vY/10;
    }

    /**************************************************
     * FUNCTION: setSpeed
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Sets the speed in x and y directions of the player
     * Inputs: int vX, int vY
     * Output: N/A
     *****************************************************/
    public void setSpeed(int vX, int vY) {
        this.vX = vX;
        this.vY = vY;
        if (vX > 0) {
            lastDirection = 1;
        } else if (vX < 0) {
            lastDirection = - 1;
        }
    }

    /**************************************************
     * FUNCTION: teleport
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Teleports the player to (x,y)
     * Inputs: int x, int y
     * Output: N/A
     *****************************************************/
    public void teleport(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**************************************************
     * FUNCTION: teleportStart
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Teleports the player to the start position
     * Inputs: N/A
     * Output: N/A
     *****************************************************/
    public void teleportStart() {
        this.x = startX;
        this.y = startY;
    }

    /**************************************************
     * FUNCTION: die
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Called when the player has died
     * Inputs: N/A
     * Output: N/A
     *****************************************************/
    public void die() {
        if (carryingFlag != null) {
            carryingFlag.setHeld(0);
            carryingFlag.setHeldBy(null);
            System.out.println("test");
        }
        carryingFlag = null;
        respawnCool++;
    }

    /**************************************************
     * FUNCTION: setGround
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Changes the onGround variable
     * Inputs: boolean s
     * Output: N/A
     *****************************************************/
    public void setGround(boolean s) {
        onGround = s;
    }

    /**************************************************
     * FUNCTION: setShootCool
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Sets the shooting cooldown variable
     * Inputs: int cool
     * Output: N/A
     *****************************************************/
    public void setShootCool(int cool) {
        shootCool = cool;
    }

    /**************************************************
     * FUNCTION: setCarryingFlag
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Sets the carrying flag variable
     * Inputs: Flag f
     * Output: N/A
     *****************************************************/
    public void setCarryingFlag(Flag f) { carryingFlag = f; }

    /**************************************************
     * FUNCTION: drawPlayer
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Draws the player.
     * Inputs: Graphics g, Player ref
     * Output: N/A
     *****************************************************/
    public void drawPlayer(Graphics g, Player ref) {
        //If player is dead
        if (respawnCool != 0) {
            return;
        }
        if (lastDirection == 1) {
            g.drawImage(pImg[1], JavaGraphics.screen_w/4 - (ref.returnX() - x), JavaGraphics.screen_h/3*2 - (ref.returnY() - y), 32, 32, null);
        } else {
            g.drawImage(pImg[0], JavaGraphics.screen_w/4 - (ref.returnX() - x), JavaGraphics.screen_h/3*2 - (ref.returnY() - y), 32, 32, null);
        }
        /*g.setColor(color);
        g.fillRect(JavaGraphics.screen_w/4 - (ref.returnx() - x), JavaGraphics.screen_h/3*2 - (ref.returny() - y),32,32);
        */
    }

    /**************************************************
     * FUNCTION: tickPlayer
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Processes cooldown countdowns
     * Inputs: N/A
     * Output: N/A
     *****************************************************/
    public void tickPlayer() {
        //Tick down shoot cooldown
        if (shootCool > 0) shootCool--;
        if (respawnCool > 0) respawnCool++;
        if (respawnCool >= 300) {
            respawnCool = 0;
            teleportStart();
        }
    }

    /**************************************************
     * FUNCTION: returnX
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the X coordinate.
     * Inputs: N/A
     * Output: X coordinate of the player
     *****************************************************/
    public int returnX(){
        return this.x;
    }

    /**************************************************
     * FUNCTION: returnY
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the Y coordinate.
     * Inputs: N/A
     * Output: Y coordinate of the player
     *****************************************************/
    public int returnY(){
        return this.y;
    }

    /**************************************************
     * FUNCTION: returnVx
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the speed in the x direction.
     * Inputs: N/A
     * Output: X speed of the player
     *****************************************************/
    public int returnVx() { return this.vX; }

    /**************************************************
     * FUNCTION: returnVy
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the speed in the y direction.
     * Inputs: N/A
     * Output: Y speed of the player
     *****************************************************/
    public int returnVy() { return this.vY; }

    /**************************************************
     * FUNCTION: returnLastDirection
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the last direction the player is facing
     * Inputs: N/A
     * Output: The last direction the player is facing
     *****************************************************/
    public int returnLastDirection() { return this.lastDirection; }

    /**************************************************
     * FUNCTION: returnRespawnCool
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the respawn cooldown
     * Inputs: N/A
     * Output: RespawnCooldown timer
     *****************************************************/
    public int returnRespawnCool() { return this.respawnCool; }

    /**************************************************
     * FUNCTION: returnShootCool
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the shoot cooldown
     * Inputs: N/A
     * Output: ShootCooldown timer
     *****************************************************/
    public int returnShootCool() { return this.shootCool; }

    /**************************************************
     * FUNCTION: returnControls
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the controls mapping
     * Inputs: N/A
     * Output: An array containing all the controls for the player
     *****************************************************/
    public int[] returnControls() { return this.controls; }

    /**************************************************
     * FUNCTION: returnPlayerNum
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the player number
     * Inputs: N/A
     * Output: The player number
     *****************************************************/
    public int returnPlayerNum() { return this.playerNum; }

    /**************************************************
     * FUNCTION: returnImages
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the bufferedImages of the player
     * Inputs: N/A
     * Output: BufferedImages array
     *****************************************************/
    public BufferedImage[] returnImages() { return this.pImg; }

    /**************************************************
     * FUNCTION: isDead
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns if the player is dead
     * Inputs: N/A
     * Output: true or false
     *****************************************************/
    public boolean isDead() {
        if (respawnCool == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**************************************************
     * FUNCTION: onGround
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns the ground status
     * Inputs: N/A
     * Output: true or false
     *****************************************************/
    public boolean onGround() { return this.onGround; }
}
