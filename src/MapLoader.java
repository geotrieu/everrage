/**************************************************
 * CLASS: MapLoader
 * NAME: George Trieu
 * Date: 06/12/2019
 * Description: Functions and methods contributing to the loading,
 *              processing, and drawing of the game map.
 *****************************************************/
import java.awt.*;
import java.io.FileInputStream;

public class MapLoader {
    static int sh = JavaGraphics.screen_h;
    static int sw = JavaGraphics.screen_w;
    static int bh = JavaGraphics.block_h;
    static int nbh = JavaGraphics.numblock_h;
    static int nbw = JavaGraphics.numblock_w;
    static int mlx = JavaGraphics.maplength_x;
    static int mly = JavaGraphics.maplength_y;

    /**************************************************
     * FUNCTION: loadMap
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Loads the map file into memory
     * Inputs: String loc
     * Output: A 2D array containing the map
     *****************************************************/
    public static char[][] loadMap(String loc) {
        char map[][] = new char[JavaGraphics.maplength_y][JavaGraphics.maplength_x];
        int flagLoc[][] = new int[2][2];
        FileInputStream in = null;

        int x = 0,y = 0;

        try {
            in = new FileInputStream(loc);

            int c;
            while ((c = in.read()) != -1) {
                if (c == 13) {
                    continue;
                } else if (c == 10) {
                    y++;
                    x = 0;
                    continue;
                }
                //Flags
                if ((char)c == 'F') {
                    //Player 1's Flag
                    flagLoc[0][0] = x*32;
                    flagLoc[0][1] = y*32;
                    map[y][x] = '0';
                } else if ((char)c == 'G'){
                    //Player 2's Flag
                    flagLoc[1][0] = x*32;
                    flagLoc[1][1] = y*32;
                    map[y][x] = '0';
                } else {
                    map[y][x] = (char)c;
                }
                x++;
            }
            if (in != null) {
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //process flags
        JavaGraphics.f1 = new Flag(flagLoc[0][0], flagLoc[0][1], flagLoc[1][0], flagLoc[1][1]);
        JavaGraphics.f2 = new Flag(flagLoc[1][0], flagLoc[1][1], flagLoc[0][0], flagLoc[0][1]);

        return map;
    }

    /**************************************************
     * FUNCTION: drawMap
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Draws the map to the screen
     * Inputs: Graphics g, char[][] map, Player[] p, int c_player
     * Output: N/A
     *****************************************************/
    public static void drawMap(Graphics g, char[][] map, Player[] p, int c_player) {
        int screeny = 0;
        for (int y = 0; y < mly; y++) {
            for (int x = 0; x < mlx; x++) {
                if (map[y][x] == '1') {
                    g.drawImage(Images.grass, x*32 + sw/4 - p[c_player].returnX(), screeny*32 + sh/3*2 - p[c_player].returnY(), 32, 32, null);
                    //g.setColor(new Color(0,255,50));
                    //g.fillRect(x*32 + sw/4 - p[c_player].returnX(), screeny*32 + sh/3*2 - p[c_player].returnY(), 32,32);
                } else if (map[y][x] == '6') {
                    g.setColor(new Color(0,123,50));
                    g.fillRect(x*32 + sw/4 - p[c_player].returnX(), screeny*32 + sh/3*2 - p[c_player].returnY(), 32,32);
                } else if (map[y][x] == 'B') {
                    g.setColor(new Color(0,0,0));
                    g.fillRect(x*32 + sw/4 - p[c_player].returnX(), screeny*32 + sh/3*2 - p[c_player].returnY(), 32,32);
                } else {
                    g.drawImage(Images.sky, x*32 + sw/4 - p[c_player].returnX(), screeny*32 + sh/3*2 - p[c_player].returnY(), 32, 32, null);
                }
            }
            screeny++;
        }
    }
}
