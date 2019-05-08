import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.event.*;

public class MainGraphics extends JComponent{
    Player p0 = JavaGraphics.p0;
    Player p1 = JavaGraphics.p1;

    Player p;

    public MainGraphics(Player p){
        this.p = p;

        KeyListener listener = new KeyHandler();
        addKeyListener(listener);
        setFocusable(true);
    }

    public void paint(Graphics g) {
        p0.setColor(g);
        p0.drawPlayer(g);
        p1.setColor(g);
        p1.drawPlayer(g);

        //Game Delay
        try {
            Thread.sleep(1);
        }
        catch(InterruptedException ex) {}

        repaint();
    }
}
