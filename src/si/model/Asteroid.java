package si.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Asteroid implements Hittable,Movable {
    private boolean alive;
    private double x, y;
    private double vx, vy;
    private double v;
    private double r;
    private AsteroidsType type;
    public static final int ASTEROID_SCALE = 20;
    public static final double ASTEROID_V_SCALE = 0.5;
    private double ran;

    public Asteroid(AsteroidsType type){
        double ranx = Math.random();
        double rany = Math.random();
        Random randx = new Random();
        Random randy = new Random();
        int randNumberx = 0;
        int randNumbery = 0;
        if (ranx <= 0.5 && ranx >= 0){
            randNumberx =randx.nextInt(300 - 1 + 1) + 1;
            if (rany <= 0.5 && rany >= 0){
                randNumbery =randy.nextInt(200 - 1 + 1) + 1;
            }else{
                randNumbery =randy.nextInt(500 - 300 + 1) + 300;
            }
        }else{
            randNumberx =randx.nextInt(750 - 400 + 1) + 400;
            if (rany <= 0.5 && rany >= 0){
                randNumbery =randy.nextInt(200 - 1 + 1) + 1;
            }else{
                randNumbery =randy.nextInt(500 - 300 + 1) + 300;
            }
        }
        /*this.x = Math.random() * 750;
        this.y = Math.random() * 500;*/
        this.x = randNumberx;
        this.y = randNumbery;
        this.v = type.getWeightofvelocity() * ASTEROID_V_SCALE;
        this.r = Math.toRadians(Math.random() * 360);
        this.vx = this.v * (Math.cos(r));
        this.vy = this.v * (Math.sin(r));
        this.type = type;
        this.alive = true;
    }

    public Asteroid(Asteroid asteroid, AsteroidsType type, int x, int y){
        this.x = x;
        this.y = y;
        this.v = type.getWeightofvelocity() * ASTEROID_V_SCALE;
        if (asteroid.getType() == AsteroidsType.L){
            double ranx = Math.random();
            if (ranx <= 0.5 && ranx >= 0){
                Random randy = new Random();
                double ran = randy.nextInt(30 - 10 + 1) + 10;
                this.vx = this.v * (Math.cos(Math.toRadians(asteroid.getR() + ran)));
                this.vy = this.v * (Math.sin(Math.toRadians(asteroid.getR() + ran)));
                this.type = type;
                this.alive = true;
            }else{
                Random randy = new Random();
                double ran = randy.nextInt(30 - 10 + 1) + 10;
                this.vx = this.v * (Math.cos(Math.toRadians(asteroid.getR() - ran)));
                this.vy = this.v * (Math.sin(Math.toRadians(asteroid.getR() - ran)));
                this.type = type;
                this.alive = true;
            }
        }else if (asteroid.getType() == AsteroidsType.M) {
            double ranx = Math.random();
            if (ranx <= 0.5 && ranx >= 0) {
                Random randy = new Random();
                double ran = randy.nextInt(40 - 20 + 1) + 20;
                this.vx = this.v * (Math.cos(Math.toRadians(asteroid.getR() + ran)));
                this.vy = this.v * (Math.sin(Math.toRadians(asteroid.getR() + ran)));
                this.type = type;
                this.alive = true;
            } else {
                Random randy = new Random();
                double ran = randy.nextInt(40 - 20 + 1) + 20;
                this.vx = this.v * (Math.cos(Math.toRadians(asteroid.getR() - ran)));
                this.vy = this.v * (Math.sin(Math.toRadians(asteroid.getR() - ran)));
                this.type = type;
                this.alive = true;
            }
        }
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public int getPoints() {
        return type.getScore();
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

    public boolean isHite(EnemyShip e) {
        boolean hit = getHitBox().intersects(e.getHitBox());
        if (hit) {
            alive = false;
        }
        return hit;
    }

    public void destroy() {alive = false;}

    @Override
    public Rectangle getHitBox() {
        return new Rectangle((int) x, (int) y, ASTEROID_SCALE * type.getWeightofscale(), ASTEROID_SCALE * type.getWeightofscale());
    }
    public void move(double cX, double cY){
        x += cX;
        y += cY;
    }

    public Shape getShape() {
        return new Rectangle();
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public AsteroidsType getType() {
        return type;
    }

    public double getVx(){
        return vx;
    }
    public  double getVy(){
        return vy;
    }

    public double getR() {
        return r;
    }

    @Override
    public void move() {
        if(this.getHitBox().getX() >= 768 && this.getHitBox().getY() >= 512){
            this.x = 1 + vx;
            this.y = 1 + vy;
        }
        if(this.getHitBox().getX() >= 768 && this.getHitBox().getY() != 512){
            if (this.getHitBox().getY() <= 0) {
                this.x = 1 + vx;
                this.y = 511 + vy;
            }else{this.x = 1 + vx; this.y += vy;}
        }
        if(this.getHitBox().getX() != 768 && this.getHitBox().getY() >= 512){
           if (this.getHitBox().getX() <= 0){
               this.x = 511 + vx;
               this.y = 1 + vy;
           }else{ this.x += vx; this.y = 1 + vy;}
        }
        if(this.getHitBox().getX() != 768 && this.getHitBox().getY() != 512){
            if(this.getHitBox().getY() <= 0){
                this.x += vx;
                this.y = 511 + vy;
            }else if (this.getHitBox().getX() <= 0){
                this.x = 767 + vx;
                this.y += vy;
            }
            else{
            this.x += vx;
            this.y += vy;}
        }
    }
}
