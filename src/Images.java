/**************************************************
 * CLASS: Images
 * NAME: George Trieu
 * Date: 06/12/2019
 * Description: Stores the BufferedImages required for runtime.
 *              Image files is read and stored in this class.
 *****************************************************/

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {

    public static BufferedImage grass;
    public static BufferedImage sky;
    public static BufferedImage flag;
    public static BufferedImage p1[] = new BufferedImage[2];
    public static BufferedImage p2[] = new BufferedImage[2];

    /**************************************************
     * FUNCTION: loadImages
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Loads all the images into memory
     * Inputs: N/A
     * Output: N/A
     *****************************************************/
    public static void loadImages() throws IOException {
        grass = ImageIO.read(new File("src/resources/grass.png"));
        sky = ImageIO.read(new File("src/resources/sky.png"));
        flag = ImageIO.read(new File("src/resources/flag.png"));
        p1[0] = ImageIO.read(new File("src/resources/p1left.png"));
        p1[1] = ImageIO.read(new File("src/resources/p1right.png"));
        p2[0] = ImageIO.read(new File("src/resources/p2left.png"));
        p2[1] = ImageIO.read(new File("src/resources/p2right.png"));
    }
}
