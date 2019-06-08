import java.io.FileInputStream;

public class MapLoader {
    final int map_x = 14;
    final int map_y = 100;
    public static char[][] loadMap(String loc) {
        char map[][] = new char[JavaGraphics.maplength_y][JavaGraphics.maplength_x];
        FileInputStream in = null;

        int x = 0,y = 0;

        try {
            in = new FileInputStream(loc);

            int c;
            while ((c = in.read()) != -1) {
                if (c == 13) {
                    y++;
                    x = 0;
                    continue;
                } else if (c == 10) {
                    continue;
                }
                map[y][x] = (char)c;
                x++;
            }
            if (in != null) {
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
