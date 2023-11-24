package si.model;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player implements Hittable {
    private int x;
    private int y;
    public double h;
    private int r;
    public double vx;
    public double vy;
    private boolean alive = true;
    private Rectangle hitBox;
    private int weaponCountdown;
    private SpaceInvadersGame game;

    public Player(SpaceInvadersGame game) {
        x = SpaceInvadersGame.SCREEN_WIDTH / 2;
        y = SpaceInvadersGame.SCREEN_HEIGHT / 2;
        hitBox = new Rectangle(x, y, 20, 20);
        h = 90;
        r = 10;
        this.game = game;
    }

    public List<Bullet> execute(){
        List<Bullet> executeBullets = new ArrayList<Bullet>();
        for (int i = 0; i <= 360; i+=35){
            Bullet b = null;
            if (weaponCountdown == 0){
                int bx = (int) (x + r * Math.cos(Math.toRadians(h+i)));
                int by = (int) (y + r * Math.sin(Math.toRadians(h+i)));
                b = new Bullet(bx, by, true, "Player", game);
                executeBullets.add(b);
            }
        }return executeBullets;
    }

    public Bullet fire() {
        Bullet b = null;
        if (weaponCountdown == 0) {
            int bx = (int) (x + r * Math.cos(Math.toRadians(h)));
            int by = (int) (y + r * Math.sin(Math.toRadians(h)));
            b = new Bullet(bx, by, true, "Player", game);
        }
        return b;
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
        return true;
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
        return hitBox;
    }

    public void resetDestroyed() {
        alive = true;
        x = SpaceInvadersGame.SCREEN_WIDTH / 2;
        y = SpaceInvadersGame.SCREEN_HEIGHT/2;
        hitBox = new Rectangle(x, y, 20, 20);
    }

    public void nbMove(){
        double x1 = vx;
        double y1 = vy;
        Rectangle newBox = new Rectangle((int)(x + x1), (int)(y + y1), 0, 0);

        if(this.x >= 768 && this.y >= 512){
            hitBox = newBox;
            this.x = 1;
            this.y = 1;
        }else if (this.x >= 768 && this.y != 512){
            if(this.y == 0){
                hitBox = newBox;
                this.x = 1;
                this.y = 511;
            }else{
                hitBox = newBox;
                this.x = 1;
                this.y += vy;
            }

        }else if (this.x != 768 && this.y >= 512){
            if(this.x == 0){
                hitBox = newBox;
                this.x = 511;
                this.y = 1;
            }else{
                hitBox = newBox;
                this.x += vx;
                this.y = 1;
            }

        }else if(this.x != 768 && this.y != 512){
            if(this.x <= 0 && this.y <=0){
                hitBox = newBox;
                this.x = 767;
                this.y = 511;
            }else if(this.x != 0 && this.y <= 0){
                hitBox = newBox;
                this.x += vx;
                this.y = 511;
            }else if(this.x <= 0 && this.y != 0){
                hitBox = newBox;
                this.x = 767;
                this.y += vy;
            }else if(this.x != 0 && this.y != 0){
                hitBox = newBox;
                this.x += vx;
                this.y += vy;
            }
        }
    }
    public void move() {
        double x1 = vx;
        double y1 = vy;
        Rectangle newBox = new Rectangle((int)(x + x1), (int)(y + y1), 10, 10);

            if(this.x >= 768 && this.y >= 512){
                hitBox = newBox;
                this.x = 1;
                this.y = 1;
            }else if (this.x >= 768 && this.y != 512){
                if(this.y == 0){
                    hitBox = newBox;
                    this.x = 1;
                    this.y = 511;
                }else{
                    hitBox = newBox;
                    this.x = 1;
                    this.y += vy;
                }

            }else if (this.x != 768 && this.y >= 512){
                if(this.x == 0){
                    hitBox = newBox;
                    this.x = 511;
                    this.y = 1;
                }else{
                    hitBox = newBox;
                    this.x += vx;
                    this.y = 1;
                }

            }else if(this.x != 768 && this.y != 512){
                if(this.x <= 0 && this.y <=0){
                    hitBox = newBox;
                    this.x = 767;
                    this.y = 511;
                }else if(this.x != 0 && this.y <= 0){
                    hitBox = newBox;
                    this.x += vx;
                    this.y = 511;
                }else if(this.x <= 0 && this.y != 0){
                    hitBox = newBox;
                    this.x = 767;
                    this.y += vy;
                }else if(this.x != 0 && this.y != 0){
                    hitBox = newBox;
                    this.x += vx;
                    this.y += vy;
                }
            }
        }

    public void jump(){
            int i = 0;
            while (i <= 1000){
                boolean jump = true;
                int x = (int) (Math.random() * 750);
                int y = (int) (Math.random() * 500);
                for(int j = 0; j < game.getAsteroids().size(); j++){
                    Rectangle jumpBox = new Rectangle(x - 10, y - 10, 30, 30);
                    if(game.getAsteroids().get(j).getHitBox().intersects(jumpBox)){
                        jump = false;
                    }
                }
                if (jump == true){
                    this.x = x;
                    this.y = y;
                    break;
                }else{
                    i = i + 1;
                }
        }
    }
    public void stop(){
        this.vx = 0;
        this.vy = 0;
    }

    public void timeMachine(){
        game.tt = 200;
        game.tPause = true;
    }

    public void speedup(){
        double a = 0.1;
        this.vx += (a * Math.cos(Math.toRadians(h)));
        this.vy += (a * Math.sin(Math.toRadians(h)));
    }

    public void rightrotate() {
        this.h += 5;
    }

    public void leftrotate() {
        this.h -= 5;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getH() {
        return h;
    }

    public int getR() {
        return r;
    }

    public double getVx(){
        return vx;
    }
    public double getVy(){
        return vy;
    }

}