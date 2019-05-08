import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class JavaGraphics extends JApplet {
    public int width;
    public int height;
    public static Player p0 = new Player(50,50 , new Color(125,0,140),'W', 'S', 'A', 'D');
    public static Player p1 = new Player(100,100, new Color(125,200,140),'T', 'G', 'F', 'H');

    public JavaGraphics() {
        JFrame window = new JFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        width = dim.width;
        height = dim.height;
        window.setSize(width, height);
        window.setBackground(Color.yellow);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        MainGraphics graphicsLeft = new MainGraphics(p0);
        graphicsLeft.setPreferredSize(new Dimension(width / 2, height));
        MainGraphics graphicsRight = new MainGraphics(p1);
        graphicsRight.setPreferredSize(new Dimension(width / 2, height));
        panel.add(graphicsLeft, BorderLayout.WEST);
        panel.add(graphicsRight, BorderLayout.EAST);
        window.add(panel);
        window.setVisible(true);
    }
}
