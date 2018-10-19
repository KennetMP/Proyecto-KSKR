package Juego;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Sprite implements Commons {

    private final int START_Y = 50;
    private final int START_X = 10;

    private final String playerImg = "src/Juego/player.png";
    private int width;

    public Player() {

        initPlayer();
    }

    private void initPlayer() {

        ImageIcon ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);

        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
    }

    public void act() {

        y += dx;

        if (y <= 2) {
            y = 2;
        }

        if (y >= BOARD_WIDTH - 2 * width) {
            y = BOARD_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {

            dx = -2;
        }

        if (key == KeyEvent.VK_DOWN) {

            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {

            dx = 0;
        }

        if (key == KeyEvent.VK_DOWN) {

            dx = 0;
        }
    }
}