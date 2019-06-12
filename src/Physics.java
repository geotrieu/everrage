import java.util.LinkedList;

public class Physics {

    /* Defines Physics for the game.
       RIGHT is the x positive direction.
       DOWN is the y positive direction.
    */

    final static int GRAVITYACC = 3;
    final static double FRICTIONDIV = 1.1;

    //Handles Gravity + Downwards Collision
    public static void gravityTick(char[][] map, Player p) {
        if (map[(p.returny())/32 + 1][(p.returnx())/32] == '0') {
            p.setSpeed(p.returnVx(), p.returnVy() + GRAVITYACC);
            p.setGround(false);
        } else {
            if (!p.onGround()) {
                p.setSpeed(p.returnVx(), 0);
                p.teleport(p.returnx(), p.returny()/32 * 32);
                p.setGround(true);
            }
        }
    }

    public static void frictionTick(Player p) {
        if (p.onGround()) {
            p.setSpeed((int)(p.returnVx()/FRICTIONDIV), p.returnVy());
        }
    }

    //Handles Collision for Up, Left and Right
    public static void collisionTick(char[][] map, Player p) {
        //check up
        /*if (map[(p.returny())/32][(p.returnx() + 15)/32] != '0') {
            p.setSpeed(p.returnVx(), 0);
            p.teleport(p.returnx(), p.returny()/32 * 32 + 32);
            System.out.println("test");
        }*/
        //check left
        if (map[(p.returny())/32][(p.returnx())/32] != '0') {
            p.setSpeed(0, p.returnVy());
            p.teleport(p.returnx()/32 * 32 + 32, p.returny());
        }
        //check right
        if (map[(p.returny())/32][(p.returnx() + 31)/32] != '0') {
            p.setSpeed(0, 0);
            p.teleport(p.returnx()/32 * 32, p.returny() / 32 * 32);
        }
    }

    //Handles BULLET collision
    public static void bulletCollisionTick(char[][] map, LinkedList<Bullet> bll, Player[] players) {
        for (Bullet b : bll) {
            if (b.returnSpeed() > 0) {
                //CHECK RIGHT
                //Check for Natural Map Obstacles
                if (map[(b.returny())/32][(b.returnx() + b.returnWidth() - 1)/32] != '0') {
                    bll.remove(b);
                    return;
                }
            } else {
                //CHECK LEFT
                //Check for Natural Map Obstacles
                if (map[(b.returny())/32][(b.returnx())/32] != '0') {
                    bll.remove(b);
                    return;
                }
            }
            //Check for Players
            for (Player p : players) {
                if ((b.returnx() + b.returnWidth()) > p.returnx()) {
                    if (b.returnx() < p.returnx() + 31) {
                        if (b.returny() > p.returny() && b.returny() < (p.returny() + 31)) {
                            bll.remove(b);
                            p.die();
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void flagCollisionTick(Player p, Flag f, int held) {
        //check flag
        if (held == -1) {
            if (f.returnx()/32 == f.returnStartx()/32 && f.returny()/32 == f.returnStarty()/32) {
                return;
            }
        }
        if (f.returnheld() == 0 && !p.isDead()) {
            if ((p.returnx() + 15) > f.returnx()) {
                if (p.returnx() + 15 < f.returnx() + 31) {
                    if (p.returny() + 15 > f.returny() && p.returny() + 15 < (p.returny() + 31)) {
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
