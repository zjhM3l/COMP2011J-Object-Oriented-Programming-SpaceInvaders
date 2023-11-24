package si.display;

import si.model.*;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private static final long serialVersionUID = -8282302849760730222L;
    private SpaceInvadersGame game;

    public GameScreen(SpaceInvadersGame game) {
        this.game = game;
    }

    private void drawEnemyShip(Graphics2D gc, EnemyShip e){
        int xHead = e.getX();
        int yHead = e.getY();
        int xLeft = xHead - 10;
        int yLeft = yHead - 20;
        int xRight = xHead + 10;
        int yRight = yHead - 20;
        Polygon filledPolygon = new Polygon();
        filledPolygon.addPoint(xHead, yHead);
        filledPolygon.addPoint(xLeft, yLeft);
        filledPolygon.addPoint(xRight, yRight);
        gc.setColor(Color.RED);
        gc.drawPolygon(filledPolygon);

    }

    private void drawPlayerShip(Graphics2D gc, Player p) {
        int x = p.getX();
        int y = p.getY();
        double h = p.getH();
        int r = p.getR();
        double d1 = (h +120) * (Math.PI/180);
        double dx1 = x + r * Math.cos(d1);
        double dy1 = y + r * Math.sin(d1);
        int x1 = (int) dx1;
        int y1 = (int) dy1;
        double d2 = h * (Math.PI/180);
        double dx2 = x + r * Math.cos(d2);
        double dy2 = y + r * Math.sin(d2);
        int x2 = (int) dx2;
        int y2 = (int) dy2;
        double d3 = (h + 240) * (Math.PI/180);
        double dx3 = x + r * Math.cos(d3);
        double dy3 = y + r * Math.sin(d3);
        int x3 = (int) dx3;
        int y3 = (int) dy3;
        Polygon filledPolygon = new Polygon();
        filledPolygon.addPoint(x2, y2);
        filledPolygon.addPoint(x1, y1);
        filledPolygon.addPoint(x3, y3);
        gc.setColor(Color.WHITE);
        gc.drawPolygon(filledPolygon);
    }

    private void drawBullet(Graphics2D gc, Bullet b){
        gc.setColor(Color.GREEN);
        gc.fillRect(b.getX(), b.getY(), b.BULLET_WIDTH, b.BULLET_HEIGHT);
    }

    private void drawBonus(Graphics2D gc, Bonus bo){
        gc.setColor(Color.YELLOW);
        gc.fillRect(bo.getX(), bo.getY(), 20, 20);
    }

    private void drawAsteroid(Graphics2D gc, Asteroid as){
        if (as.getType() == AsteroidsType.L) {
            drawAsteroidL(gc, as);
        } else if (as.getType() == AsteroidsType.M) {
            drawAsteroidM(gc, as);
        } else {
            drawAsteroidS(gc, as);
        }
    }

    private void drawAsteroidL(Graphics2D gc, Asteroid as){
        double x = as.getX();
        double y = as.getY();
        Polygon filledPolygon = new Polygon();
        filledPolygon.addPoint((int)(x + 60), (int)(y + 60));
        filledPolygon.addPoint((int)(x + 60), (int)(y));
        filledPolygon.addPoint((int)(x), (int)(y));
        filledPolygon.addPoint((int)(x), (int)(y + 60));
        gc.setColor(Color.GREEN);
        gc.drawPolygon(filledPolygon);
    }

    private void drawAsteroidM(Graphics2D gc, Asteroid as){
        double x = as.getX();
        double y = as.getY();
        Polygon filledPolygon = new Polygon();
        filledPolygon.addPoint((int)(x + 40), (int)(y + 40));
        filledPolygon.addPoint((int)(x + 40), (int)(y));
        filledPolygon.addPoint((int)(x), (int)(y));
        filledPolygon.addPoint((int)(x), (int)(y + 40));
        gc.setColor(Color.GREEN);
        gc.drawPolygon(filledPolygon);
    }

    private void drawAsteroidS(Graphics2D gc, Asteroid as){
        double x = as.getX();
        double y = as.getY();
        Polygon filledPolygon = new Polygon();
        filledPolygon.addPoint((int)(x + 20), (int)(y + 20));
        filledPolygon.addPoint((int)(x + 20), (int)(y));
        filledPolygon.addPoint((int)(x), (int)(y));
        filledPolygon.addPoint((int)(x), (int)(y + 20));
        gc.setColor(Color.GREEN);
        gc.drawPolygon(filledPolygon);
    }

    protected void paintComponent(Graphics g) {
        if (game != null) {
            Graphics2D g2 = (Graphics2D) g;
            g.setColor(Color.black);
            g.fillRect(0, 0, SpaceInvadersGame.SCREEN_WIDTH, SpaceInvadersGame.SCREEN_HEIGHT);
            g.setColor(Color.green);
            g.drawString("Lives: " + game.getLives(), 0, 20);
            g.drawString("Score: " + game.getPlayerScore(), SpaceInvadersGame.SCREEN_WIDTH / 2, 20);
            g.drawString("Execute Shoot Ammo:" + game.getExecuteTime(), 600, 20);
            g.drawString("Un hittable Time:" + game.getNbTime(), 0, 40);
            g.drawString("Time Machine:" + game.getTimemachine(), 600, 40);

            drawPlayerShip(g2,game.getShip());

            for (Bonus bonus : game.getBonus()){
                drawBonus(g2, bonus);
            }

            for (EnemyShip enemyShip : game.getEnemyShip()) {
                drawEnemyShip(g2, enemyShip);
            }

            for (Bullet bullet : game.getBullets()) {
                drawBullet(g2, bullet);
            }
            for (Asteroid a: game.getAsteroids()){
                drawAsteroid(g2, a);
            }

            if (game.isPaused() && !game.isGameOver()) {
                g.setColor(Color.GREEN);
                g.drawString("Press 'p' to continue ", 256, 256);
            } else if (game.isGameOver()) {
                g.setColor(Color.GREEN);
                g.drawString("Game over ", 480, 256);
            }
        }
    }
}