/**************************************************
 * CLASS: Physics
 * NAME: George Trieu
 * Date: 06/12/2019
 * Description: Defines game physics constants, and processes
 *              game physics behaviour, such as collisions,
 *              gravity, and friction.
 *****************************************************/
import java.util.LinkedList;

public class Physics {

    /* Defines Physics for the game.
       RIGHT is the x positive direction.
       DOWN is the y positive direction.
    */

    final static int GRAVITYACC = 3;
    final static double FRICTIONDIV = 1.1;

    /**************************************************
     * FUNCTION: gravityTick
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Handles gravity acceleration and downwards collisions
     * Inputs: char[][] map, Player p
     * Output: N/A
     *****************************************************/
    public static void gravityTick(char[][] map, Player p) {
        if (map[(p.returnY())/32 + 1][(p.returnX())/32] == '0') {
            p.setSpeed(p.returnVx(), p.returnVy() + GRAVITYACC);
            p.setGround(false);
        } else {
            if (!p.onGround()) {
                p.setSpeed(p.returnVx(), 0);
                p.teleport(p.returnX(), p.returnY()/32 * 32);
                p.setGround(true);
            }
        }
    }

    /**************************************************
     * FUNCTION: frictionTick
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Handles the friction acceleration.
     * Inputs: Player p
     * Output: N/A
     *****************************************************/
    public static void frictionTick(Player p) {
        if (p.onGround()) {
            p.setSpeed((int)(p.returnVx()/FRICTIONDIV), p.returnVy());
        }
    }

    /**************************************************
     * FUNCTION: collisionTick
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Handles the rest of the collisions excluding downwards
     * Inputs: char[][] map, Player p
     * Output: N/A
     *****************************************************/
    public static void collisionTick(char[][] map, Player p) {
        //check up
        /*if (map[(p.returnY())/32][(p.returnX() + 15)/32] != '0') {
            p.setSpeed(p.returnVx(), 0);
            p.teleport(p.returnX(), p.returnY()/32 * 32 + 32);
            System.out.println("test");
        }*/
        //check left
        if (map[(p.returnY())/32][(p.returnX())/32] != '0') {
            p.setSpeed(0, p.returnVy());
            p.teleport(p.returnX()/32 * 32 + 32, p.returnY());
        }
        //check right
        if (map[(p.returnY())/32][(p.returnX() + 31)/32] != '0') {
            p.setSpeed(0, 0);
            p.teleport(p.returnX()/32 * 32, p.returnY() / 32 * 32);
        }
    }

    /**************************************************
     * FUNCTION: bulletCollisionTick
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Handles the bullet collisions with natural
     *              obstacles and players
     * Inputs: char[][] map, LinkedList<Bullet> bll, Player[] players
     * Output: N/A
     *****************************************************/
    public static void bulletCollisionTick(char[][] map, LinkedList<Bullet> bll, Player[] players) {
        for (Bullet b : bll) {
            if (b.returnSpeed() > 0) {
                //CHECK RIGHT
                //Check for Natural Map Obstacles
                if (map[(b.returnY())/32][(b.returnX() + b.returnWidth() - 1)/32] != '0') {
                    bll.remove(b);
                    return;
                }
            } else {
                //CHECK LEFT
                //Check for Natural Map Obstacles
                if (map[(b.returnY())/32][(b.returnX())/32] != '0') {
                    bll.remove(b);
                    return;
                }
            }
            //Check for Players
            for (Player p : players) {
                if ((b.returnX() + b.returnWidth()) > p.returnX()) {
                    if (b.returnX() < p.returnX() + 31) {
                        if (b.returnY() > p.returnY() && b.returnY() < (p.returnY() + 31)) {
                            bll.remove(b);
                            p.die();
                            return;
                        }
                    }
                }
            }
        }
    }

    /**************************************************
     * FUNCTION: flagCollisionTick
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Handles the collision between the players and a flag
     * Inputs: Player p, Flag f, int held
     * Output: N/A
     *****************************************************/
    public static void flagCollisionTick(Player p, Flag f, int held) {
        //check flag
        if (held == -1) {
            if (f.returnX()/32 == f.returnStartX()/32 && f.returnY()/32 == f.returnStartY()/32) {
                return;
            }
        }
        if (f.returnheld() == 0 && !p.isDead()) {
            if ((p.returnX() + 15) > f.returnX()) {
                if (p.returnX() + 15 < f.returnX() + 31) {
                    if (p.returnY() + 15 > f.returnY() && p.returnY() + 15 < (p.returnY() + 31)) {
                        f.setHeld(held);
                        f.setHeldBy(p);
                        p.setCarryingFlag(f);
                        return;
                    }
                }
            }
        }
    }
}
