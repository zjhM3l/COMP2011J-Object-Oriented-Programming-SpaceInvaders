package si.model;

import java.awt.*;

public class Bullet implements Movable, Hittable{
    private int x, y;
    private String name;
    public static final int BULLET_HEIGHT = 4;
    public static final int BULLET_WIDTH = 4;
    private static final int BULLET_SPEED = 5;
    private static int bulletCounter = 0;
    private Rectangle hitBox;
    private double h;
    private boolean direction;
    private boolean alive = true;
    private SpaceInvadersGame game;

    public Bullet(int x, int y, boolean direction, String name, SpaceInvadersGame game){
        this.x = x;
        this.y = y;
        this.h = game.getShip().getH();
        this.direction = direction;
        this.name = name + " " + bulletCounter++;
        hitBox = new Rectangle(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        this.game = game;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public Rectangle getHitBox() {
        return new Rectangle(hitBox);
    }

    public void destroy() {alive = false;}

    @Override
    public void move() {
        if(direction){
            y -= BULLET_SPEED * (Math.cos(Math.toRadians(h+90)));
            x += BULLET_SPEED * (Math.sin(Math.toRadians(h+90)));
        }else{
            y += BULLET_SPEED;
        }
        hitBox.setLocation(x, y);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public boolean isHit(Bullet b) {
        if (hitBox.intersects(b.hitBox)) {
            alive = false;
            b.alive = false;
        }
        return hitBox.intersects(b.hitBox);
    }
    public int getBulletSpeed(){
        return BULLET_SPEED;
    }
}