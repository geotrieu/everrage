import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class KeyHandler implements KeyListener {
    Player p[] = {JavaGraphics.p0, JavaGraphics.p1};
    public static LinkedList<Integer> keysPressed = new LinkedList<Integer>();

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (!keysPressed.contains(e.getKeyCode())) {
            keysPressed.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keysPressed.contains(e.getKeyCode())) {
            keysPressed.remove(new Integer(e.getKeyCode()));
        }
    }
}