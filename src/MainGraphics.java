import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import java.awt.event.*;
import java.io.File;
import java.util.LinkedList;

public class MainGraphics extends JComponent{
    Player p[] = {JavaGraphics.p0, JavaGraphics.p1};
    int c_player = 0;

    char[][] map = JavaGraphics.map;
    int sh = JavaGraphics.screen_h;
    int sw = JavaGraphics.screen_w;
    int bh = JavaGraphics.block_h;
    int nbh = JavaGraphics.numblock_h;
    int nbw = JavaGraphics.numblock_w;
    int mlx = JavaGraphics.maplength_x;
    int mly = JavaGraphics.maplength_y;


    static LinkedList<Bullet> bll = new LinkedList<Bullet>();

    public MainGraphics(int i){
        c_player = i;

        KeyListener listener = new KeyHandler();
        addKeyListener(listener);
        setFocusable(true);
    }

    public void paint(Graphics g) {
        //Draw Map
        //Side Scroller
        int screeny = 0;
        for (int y = 0; y < mly; y++) {
            for (int x = 0; x < mlx; x++) {
                if (map[y][x] == '1') {
                    g.drawImage(Images.grass, x*32 + sw/4 - p[c_player].returnx(), screeny*32 + sh/3*2 - p[c_player].returny(), 32, 32, null);
                    //g.setColor(new Color(0,255,50));
                    //g.fillRect(x*32 + sw/4 - p[c_player].returnx(), screeny*32 + sh/3*2 - p[c_player].returny(), 32,32);
                } else if (map[y][x] == '6') {
                    g.setColor(new Color(0,123,50));
                    g.fillRect(x*32 + sw/4 - p[c_player].returnx(), screeny*32 + sh/3*2 - p[c_player].returny(), 32,32);
                } else if (map[y][x] == 'B') {
                    g.setColor(new Color(0,0,0));
                    g.fillRect(x*32 + sw/4 - p[c_player].returnx(), screeny*32 + sh/3*2 - p[c_player].returny(), 32,32);
                } else {
                    g.drawImage(Images.sky, x*32 + sw/4 - p[c_player].returnx(), screeny*32 + sh/3*2 - p[c_player].returny(), 32, 32, null);
                }
            }
            screeny++;
        }

        if (c_player == 0) {
            p[1].drawPlayer(g, p[0]);
        } else {
            p[0].drawPlayer(g, p[1]);
        }
        if (p[c_player].returnLastDirection() == 1) {
            g.drawImage(p[c_player].returnImages()[1], sw/4, sh/3 * 2, 32, 32, null);
        } else {
            g.drawImage(p[c_player].returnImages()[0], sw/4, sh/3 * 2, 32, 32, null);
        }
        //g.fillRect(sw/4, sh/3 * 2,32,32);

        //Draw Dividing Line
        g.setColor(new Color(0,0,0));
        g.drawLine(0,0, 0, sh);
        g.drawLine(sw/2 - 1,0, sw/2 - 1, sh);

        for (int i = 0; i < 2; i++) {
            //Gravity
            Physics.gravityTick(map, p[i]);
            //Friction
            Physics.frictionTick(p[i]);

            //Processes Key Data
            //Load the controls for the player
            int[] controls = p[i].returnControls();
            for (int key : KeyHandler.keysPressed) {
                if (key == 27) {
                    //Esc, exit game
                    System.exit(0);
                } else if (key == controls[0]) {
                    //MOVE_UP
                    if (p[i].onGround()) {
                        p[i].setSpeed(p[i].returnVx(), -100);
                    }
                } else if (key == controls[2]) {
                    //MOVE_LEFT
                    p[i].setSpeed(-30, p[i].returnVy());
                } else if (key == controls[3]) {
                    //MOVE_RIGHT
                    p[i].setSpeed(30, p[i].returnVy());
                } else if (key == controls[4]) {
                    //SHOOT
                    if (p[i].returnShootCool() == 0) {
                        if (p[i].returnLastDirection() == 1) {
                            //Fire out of the right side
                            bll.add(new Bullet(p[i].returnx() + 31, p[i].returny() + 13, 5));
                        } else {
                            //Fire out of the left side
                            bll.add(new Bullet(p[i].returnx() - 9, p[i].returny() + 13, -5));
                        }
                        p[i].setShootCool(60);
                    }
                }
            }

            //Player Tick
            p[i].tickPlayer();

            //Collision
            Physics.collisionTick(map, p[i]);

            //Move
            p[i].move();
        }

        //Process Bullets
        for (Bullet b : bll) {
            b.drawBullet(g, p[c_player]);
        }
        Physics.bulletCollisionTick(map, bll, p);

        //Game Delay
        try {
            Thread.sleep(1);
        }
        catch(InterruptedException ex) {}

        repaint();
    }
}
