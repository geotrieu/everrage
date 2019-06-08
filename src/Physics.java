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
        //check left
        if (map[(p.returny())/32][(p.returnx())/32] != '0') {
            p.setSpeed(0, p.returnVy());
        }
    }
}
