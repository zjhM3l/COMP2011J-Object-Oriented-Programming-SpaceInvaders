package si.model;

import java.awt.*;
import java.util.Random;

public class EnemyShip implements Hittable, Movable{
    private boolean alive;
    private double x, y;
    private double vx, vy;
    private String name;
    private SpaceInvadersGame game;


    public EnemyShip(SpaceInvadersGame game){
        this.alive = true;
        this.x = (int)(Math.random() * 768);
        this.y = 0;
        this.vx = 1;
        this.vy = 1;
        this.game = game;
    }


    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public int getPoints() {
        return 200;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public boolean isHit(Bullet b) {
        boolean hit = getHitBox().intersects(b.getHitBox());
        if (hit) {
            alive = false;
        }
        return hit;
    }

    @Override
    public Rectangle getHitBox() {
        return new Rectangle((int) x - 10, (int) y - 20, 20, 20);
    }

    public Rectangle getEscapeHitBox() {
        return new Rectangle((int)x - 30, (int)y - 40, 60, 60);
    }

    public Bullet fire(){
        Bullet bul = null;
            int a = (int) x;
            int b = (int) y;
            bul = new Bullet(a, b, false, name, game);
        return bul;
    }
    public void stand(){
        this.x += 0;
        this.y += 0;
    }
    @Override
    public void move() {
        if (x > game.getShip().getX()){
            if (y > game.getShip().getY()){
                this.x -= vx;
                this.y -= vy;
            }else{
                this.y += vy;
                this.x -= vx;
            }
        }else{
            if (y > game.getShip().getY()){
                this.x += vx;
                this.y -= vy;
            }else{
                this.y += vy;
                this.x += vx;
            }
        }
    }
    public void destroy() {alive = false;}

    public int getX(){
        return (int)(x);
    }
    public int getY(){
        return (int)(y);
    }
    public double getVx(){
        return vx;
    }
    public double getVy(){
        return vy;
    }
}
