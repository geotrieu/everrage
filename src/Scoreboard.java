/**************************************************
 * CLASS: Scoreboard
 * NAME: George Trieu
 * Date: 06/12/2019
 * Description: Methods and Functions to set and display the score.
 *****************************************************/
import java.awt.*;

public class Scoreboard {
    private static Integer score[] = {0,0};

    /**************************************************
     * FUNCTION: setScore
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Sets the score
     * Inputs: int s, int p
     * Output: N/A
     *****************************************************/
    public static void setScore(int s, int p) {
        score[p] = s;
    }

    /**************************************************
     * FUNCTION: getScore
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Returns score of the player
     * Inputs: int p
     * Output: the score of the player
     *****************************************************/
    public static int getScore(int p) {
        return score[p];
    }

    /**************************************************
     * FUNCTION: drawScore
     * NAME: George Trieu
     * Date: 06/12/2019
     * Description: Draws the score.
     * Inputs: Graphics g, int p
     * Output: N/A
     *****************************************************/
    public static void drawScore(Graphics g, int p) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 36));
        g.drawString(score[p].toString(), 100,100);
    }
}
