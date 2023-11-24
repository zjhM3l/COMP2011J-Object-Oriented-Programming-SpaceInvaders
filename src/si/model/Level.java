package si.model;



import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private SpaceInvadersGame game;
    private int asNum;
    private Asteroid asteroid;
    public List<Asteroid> ass;
    public List<EnemyShip> ene;
    private int jumpTime;


    public Level(int Num, SpaceInvadersGame g) {
        game = g;
        asNum = Num;
        this.ene = new ArrayList<EnemyShip>();
        this.jumpTime = (int)(Math.random() * 1000);
        this.ass = new ArrayList<Asteroid>();
        int num = this.getAsNum();
        int m = 0;
        while(m <= num){
            ass.add(new Asteroid(AsteroidsType.L));
            m = m + 1;
        }
    }

    public List<EnemyShip> createEne(){
        if (jumpTime == game.jt){
            ene.add(new EnemyShip(game));
        }
        return ene;
    }
    public List<EnemyShip> getEne(){
        return ene;
    }

    public int getAsNum()

    {
        return asNum;
    }

    public List<Asteroid> getAss() {
        return ass;
    }
    public int getJumpTime(){
        return jumpTime;
    }

}