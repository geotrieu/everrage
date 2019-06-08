import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.event.*;

public class MainGraphics extends JComponent{
    Player p[] = {JavaGraphics.p0, JavaGraphics.p1};
    int c_player = 0;
    //DOWN IS THE POSITIVE DIRECTION
    int gravAcc = 3;

    char[][] map = JavaGraphics.map;
    int sh = JavaGraphics.screen_h;
    int sw = JavaGraphics.screen_w;
    int bh = JavaGraphics.block_h;
    int nbh = JavaGraphics.numblock_h;
    int nbw = JavaGraphics.numblock_w;
    int mly = JavaGraphics.maplength_y;

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
            for (int x = 0; x < nbw; x++) {
                if (map[y][x] == '1') {
                    g.setColor(new Color(0,255,50));
                } else if (map[y][x] == '6') {
                    g.setColor(new Color(0,123,50));
                } else {
                    g.setColor(new Color(0,150,255));
                }
                g.fillRect(x*32 + sw/4 - p[c_player].returnx(), screeny*32 + sh/3*2 - p[c_player].returny(), 32,32);
            }
            screeny++;
        }

        if (c_player == 0) {
            p[1].setColor(g);
            p[1].drawPlayer(g, p[0]);
        } else {
            p[0].setColor(g);
            p[0].drawPlayer(g, p[1]);
        }
        p[c_player].setColor(g);
        g.fillRect(sw/4, sh/3 * 2,32,32);

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
            for (int key : KeyHandler.keysPressed) {
                if (key == 27) {
                    System.exit(0);
                } else if (key == p[i].MOVE_RIGHT) {
                    p[i].setSpeed(30, p[i].returnVy());
                } else if (key == p[i].MOVE_UP) {
                    if (p[i].onGround()) {
                        p[i].setSpeed(p[i].returnVx(), -100);
                    }
                } else if (key == p[i].MOVE_LEFT) {
                    p[i].setSpeed(-30, p[i].returnVy());
                }
            }

            //Collision
            Physics.collisionTick(map, p[i]);

            //Move
            p[i].move();
        }

        //Game Delay
        try {
            Thread.sleep(1);
        }
        catch(InterruptedException ex) {}

        repaint();
    }
}
