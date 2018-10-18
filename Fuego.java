package Juego;

import javax.swing.*;

public class Fuego extends Sprite {
    private final String FuegoImg = "src/Juego/bomb.png";
    private boolean destroyed;

    public Fuego(int x, int y) {

        initFuego(x, y);
    }

    private void initFuego(int x, int y) {

        setDestroyed(true);
        this.x = x;
        this.y = y;
        ImageIcon ii = new ImageIcon(FuegoImg);
        setImage(ii.getImage());

    }

    public void setDestroyed(boolean destroyed) {

        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {

        return destroyed;
    }

    public void act(int direction) {

        this.x += direction;
    }

}
