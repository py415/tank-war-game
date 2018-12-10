package objects;

import window.Game;

import java.awt.*;

/**
 * @author Philip Yu
 */
public class HealthBar {

    Player p1, p2;
    int WINDOW_WIDTH = Game.getWindowWidth();
    int WINDOW_HEIGHT = Game.getWindowHeight();

    public HealthBar(Player p1, Player p2) {

        this.p1 = p1;
        this.p2 = p2;

    }

    public void render(Graphics g) {

        // PLAYER HEALTH BAR
        g.setColor(Color.RED);
        g.fillRect(15, 15, 200, 50);

        if (p1.getHealth() > 0) {
            g.setColor(Color.GREEN);
            g.fillRect(15, 15, p1.getHealth() * 2, 50);
        } else if (p1.getHealth() <= 0) {
            g.setColor(Color.RED);
//            State = GameState.END;
        }

        g.drawString("Player Health: " + p1.getHealth(), 15, 90);

        g.setColor(Color.WHITE);
        g.drawRect(15, 15, 200, 50);

        // ENEMY HEALTH BAR
        g.setColor(Color.BLUE);
        g.fillRect(WINDOW_WIDTH - 215, 15, 200, 50);

        if (p2.getHealth() > 0) {
            g.setColor(Color.GREEN);
            g.fillRect(WINDOW_WIDTH - 215, 15, p2.getHealth() * 2, 50);
        } else if (p2.getHealth() <= 0) {
            g.setColor(Color.BLUE);
//            State = GameState.END;
        }

        g.drawString("Enemy Health: " + p2.getHealth(), WINDOW_WIDTH - 215, 90);

        g.setColor(Color.WHITE);
        g.drawRect(WINDOW_WIDTH - 215, 15, 200, 50);

    }

} // end class HealthBar