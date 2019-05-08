import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    Player p[] = {JavaGraphics.p0, JavaGraphics.p1};

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        for (int i = 0; i < p.length; i++) {
            if (e.getKeyCode() == p[i].MOVE_RIGHT) p[i].move(p[i].returnx() + 1, p[i].returny());
            if (e.getKeyCode() == p[i].MOVE_DOWN) p[i].move(p[i].returnx(), p[i].returny() + 1);
            if (e.getKeyCode() == p[i].MOVE_UP) p[i].move(p[i].returnx(), p[i].returny() - 1);
            if (e.getKeyCode() == p[i].MOVE_LEFT) p[i].move(p[i].returnx() - 1, p[i].returny());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}