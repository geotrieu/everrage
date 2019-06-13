/**************************************************
 * CLASS: KeyHandler
 * NAME: George Trieu
 * Date: 06/12/2019
 * Description: Listens for, and processes key input.
 *****************************************************/
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class KeyHandler implements KeyListener {
    Player p[] = {JavaGraphics.p0, JavaGraphics.p1};
    public static LinkedList<Integer> keysPressed = new LinkedList<Integer>();

    /**************************************************
     * FUNCTION: keyTyped
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Called when a key is typed
     * Inputs: KeyEvent e
     * Output: N/A
     *****************************************************/
    @Override
    public void keyTyped(KeyEvent e) {}

    /**************************************************
     * FUNCTION: keyPressed
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Called when a key is pressed
     * Inputs: KeyEvent e
     * Output: N/A
     *****************************************************/
    @Override
    public void keyPressed(KeyEvent e) {
        if (!keysPressed.contains(e.getKeyCode())) {
            keysPressed.add(e.getKeyCode());
        }
    }

    /**************************************************
     * FUNCTION: keyReleased
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Called when a key is released
     * Inputs: KeyEvent e
     * Output: N/A
     *****************************************************/
    @Override
    public void keyReleased(KeyEvent e) {
        if (keysPressed.contains(e.getKeyCode())) {
            keysPressed.remove(new Integer(e.getKeyCode()));
        }
    }
}