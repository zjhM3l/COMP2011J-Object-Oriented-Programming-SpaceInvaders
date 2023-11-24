package si.model;

import java.awt.*;

public class Bonus{
    private int x;
    private int y;
    private Rectangle hitBox;
    private SpaceInvadersGame game;

    public Bonus(int x, int y, SpaceInvadersGame game){
        this.x = x;
        this.y = y;
        hitBox = new Rectangle(x, y, 20, 20);
        this.game = game;
    }

    public Rectangle getHitBox() {
        return new Rectangle(hitBox);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
