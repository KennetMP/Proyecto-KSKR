package Juego;
import javax.swing.ImageIcon;

public class Shot extends Sprite {

    private final String shotImg = "src/Juego/shot.png";
    private final int H_SPACE = 6;
    private final int V_SPACE = 1;

    public Shot() {
    }

    public Shot(int x, int y) {

        initShot(x, y);
    }

    private void initShot(int x, int y) {

        ImageIcon ii = new ImageIcon(shotImg);
        setImage(ii.getImage());

        setX(x + 20);
        setY(y - V_SPACE);
    }
}