package si.model;

import si.display.GameScreen;
import si.display.PlayerListener;
import ucd.comp2011j.engine.Game;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame implements Game{
    public static final int SCREEN_WIDTH = 768;
    public static final int SCREEN_HEIGHT = 512;
    private static final Rectangle SCREEN_BOUNDS = new Rectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    private static final int NO_LEVELS = 99999;

    private int playerLives;
    private int playerLivesScores;
    private int playerScore;
    private int executeTime;
    private int timemachine;
    private boolean pause = true;
    public boolean tPause = false;
    private PlayerListener listener;
    private Player player;
    private List<Bullet> playerBullets;
    private List<Bullet> enemyBullets;
    private List<Bonus> bonus;
    private List<Hittable> hittables;
    private Level[] level;
    private int currentLevel = 0;
    public int jt;
    public int tt;
    private int nbTime;
    private int levelNum;
    private int asNum;


    public SpaceInvadersGame(PlayerListener listener) {
        this.listener = listener;
        startNewGame();
    }

    @Override
    public int getPlayerScore() {
        return playerScore;
    }

    @Override
    public int getLives() {
        return playerLives;
    }

    public int getExecuteTime(){
        return  executeTime;
    }

    @Override
    public void startNewGame() {
        this.jt = 0;
        this.tt = 0;
        player = new Player(this);
        playerScore = 0;
        playerLivesScores = 0;
        enemyBullets = new ArrayList<Bullet>();
        playerBullets = new ArrayList<Bullet>();
        bonus = new ArrayList<Bonus>();
        hittables = new ArrayList<Hittable>();
        playerLives = 3;
        executeTime = 0;
        timemachine = 0;
        level = new Level[99999];
        levelNum = 0;
        asNum = 3;
        level[0] = new Level(3, this);
        /*level[1] = new Level(4, this);
        level[2] = new Level(5, this);
        level[3] = new Level(6, this);
        level[4] = new Level(7, this);*/
    }

    @Override
    public void updateGame() {
        if (!isPaused()) {
            if(tPause == true){
                jt ++;
                tt --;
                this.level[currentLevel].createEne();
                playerBullets();
                movePlayer();
                AsteroidsRemaining();
                moveToNextLevel();
                asHitShip();
                livesCheck();
                bonusRemaining();
                nbTime --;
                if(tt <= 0){
                    tPause = false;
                }
            }else{
            jt ++;
            tt --;
            this.level[currentLevel].createEne();
            playerBullets();
            enenmyBullets();
            movePlayer();
            moveAsteroids();
            moveEnemyShip();
            AsteroidsRemaining();
            moveToNextLevel();
            asHitShip();
            livesCheck();
            bonusRemaining();
            nbTime --;}
            if(tt <= 0){
                tPause = false;
            }
        }
    }
    public void moveAsteroids(){
        List<Asteroid> ass = this.getAsteroids();
        for(int i = 0; i < ass.size(); i++){
            ass.get(i).move();
        }
    }
    public List<Asteroid> getAsteroids(){
        return (this.level[currentLevel].getAss());
    }

    public List<Hittable> getHittables(){
        this.hittables.addAll(this.getEnemyShip());
        this.hittables.addAll(this.getAsteroids());
        this.hittables.addAll(playerBullets);
        this.hittables.addAll(enemyBullets);
        return hittables;
    }

    public List<EnemyShip> getEnemyShip(){
        return (this.level[currentLevel].getEne());
    }

    public List<Bonus> getBonus(){
        return bonus;
    }

    public void bonusRemaining(){
        List<Bonus> remove = new ArrayList<Bonus>();
        for (int i = 0; i < bonus.size(); i++){
            if (player.getHitBox().intersects(bonus.get(i).getHitBox())){
                double ran = Math.random();
                if (ran <= 0.5 && ran >= 0){
                    timemachine += 1;
                    remove.add(bonus.get(i));
                }else{
                    executeTime += 3;
                    remove.add(bonus.get(i));
                }
            }
            bonus.removeAll(remove);
        }
    }

    public void livesCheck(){
        if (playerLivesScores >= 10000){
            playerLives ++;
            playerLivesScores -= 10000;
        }
    }
    private void asHitShip(){
        for (int i = 0; i < this.getAsteroids().size(); i++){
            Asteroid a = this.getAsteroids().get(i);
            if (a.getHitBox().intersects(player.getHitBox())){
                playerLives--;
                pause = true;
                player.resetDestroyed();
                nbTime  = 200;
            }
        }
    }
    private void movePlayer() {
        if (nbTime >= 0) {
            if (listener.hasPressedFire()) {
                Bullet b = player.fire();
                if (b != null) {
                    playerBullets.add(b);
                    listener.resetFire();
                }
            }
            if (listener.hasPressedStop()) {
                player.stop();
                listener.resetStop();
            }
            if (listener.isPressingLeft()) {
                player.leftrotate();
            } else if (listener.isPressingRight()) {
                player.rightrotate();
            } else if (listener.isPressingUp()) {
                player.speedup();

            } else if (listener.isPressingJump()) {
                player.jump();
                listener.resetJump();
            } else if (listener.hasPressedExecute()) {
                if (executeTime != 0) {
                    List<Bullet> eb = player.execute();
                    if (eb != null) {
                        playerBullets.addAll(eb);
                        listener.resetExecute();
                        executeTime -= 1;
                    }
                }
            }else if(listener.hasPressedTime()){
                if (timemachine > 0){
                    player.timeMachine();
                    timemachine -= 1;
                    listener.resetTimeMachine();
                }
            }
            player.nbMove();
        } else {
            if (listener.hasPressedFire()) {
                Bullet b = player.fire();
                if (b != null) {
                    playerBullets.add(b);
                    listener.resetFire();
                }
            }
            if (listener.hasPressedStop()) {
                player.stop();
                listener.resetStop();
            }
            if (listener.isPressingLeft()) {
                player.leftrotate();
            } else if (listener.isPressingRight()) {
                player.rightrotate();
            } else if (listener.isPressingUp()) {
                player.speedup();

            } else if (listener.isPressingJump()) {
                player.jump();
                listener.resetJump();
            } else if (listener.hasPressedExecute()) {
                if (executeTime != 0) {
                    List<Bullet> eb = player.execute();
                    if (eb != null) {
                        playerBullets.addAll(eb);
                        listener.resetExecute();
                        executeTime -= 1;
                    }
                }
            }else if(listener.hasPressedTime()){
                if (timemachine > 0){
                    player.timeMachine();
                    timemachine -= 1;
                    listener.resetTimeMachine();
                }
            }
            player.move();
        }
    }

    public List<Bullet> getBullets(){
        ArrayList<Bullet> bullets = new ArrayList<Bullet>();
        bullets.addAll(playerBullets);
        bullets.addAll(enemyBullets);
        return bullets;
    }

    private void AsteroidsRemaining(){
        for (int i = 0; i < level[currentLevel].ass.size(); i++){
            if (level[currentLevel].getAss().get(i).getType() == AsteroidsType.L) {
                List<Asteroid> remove = new ArrayList<Asteroid>();
                if (!level[currentLevel].getAss().get(i).isAlive()) {
                    this.level[currentLevel].ass.add(new Asteroid(level[currentLevel].getAss().get(i), AsteroidsType.M, level[currentLevel].getAss().get(i).getX(), level[currentLevel].getAss().get(i).getY()));
                    this.level[currentLevel].ass.add(new Asteroid(level[currentLevel].getAss().get(i), AsteroidsType.M, level[currentLevel].getAss().get(i).getX(), level[currentLevel].getAss().get(i).getY()));
                    remove.add(level[currentLevel].getAss().get(i));
                }
                this.level[currentLevel].ass.removeAll(remove);
            }
            if (level[currentLevel].getAss().get(i).getType() == AsteroidsType.M) {
                List<Asteroid> remove = new ArrayList<Asteroid>();
                if (!level[currentLevel].getAss().get(i).isAlive()) {
                    this.level[currentLevel].ass.add(new Asteroid(level[currentLevel].getAss().get(i), AsteroidsType.S, level[currentLevel].getAss().get(i).getX(), level[currentLevel].getAss().get(i).getY()));
                    this.level[currentLevel].ass.add(new Asteroid(level[currentLevel].getAss().get(i), AsteroidsType.S, level[currentLevel].getAss().get(i).getX(), level[currentLevel].getAss().get(i).getY()));
                    remove.add(level[currentLevel].getAss().get(i));
                }
                this.level[currentLevel].ass.removeAll(remove);
            }
            if (level[currentLevel].getAss().get(i).getType() == AsteroidsType.S) {
                List<Asteroid> remove = new ArrayList<Asteroid>();
                if (!level[currentLevel].getAss().get(i).isAlive()) {
                    remove.add(level[currentLevel].getAss().get(i));
                }
                this.level[currentLevel].ass.removeAll(remove);
            }
        }
    }

    public  void moveEnemyShip(){
        List<EnemyShip> ene = this.getEnemyShip();
        for(EnemyShip e : ene){
            boolean stand = false;
            for(Asteroid a: this.getAsteroids()){
                if(e.getEscapeHitBox().intersects(a.getHitBox())){
                    stand = true;
                }
            }
            if (stand == true){
                e.stand();;
            }else{
                e.move();
            }
        }
        List<EnemyShip> removea = new ArrayList<EnemyShip>();
            for(Asteroid a : this.getAsteroids()){
                for(EnemyShip e : ene){
                    if (a.isHite(e)){
                        e.destroy();
                        removea.add(e);
                    }
                }this.level[currentLevel].ene.removeAll(removea);
            }
        List<EnemyShip> removeb = new ArrayList<EnemyShip>();
            for(Bullet b : playerBullets){
                for(EnemyShip e : ene){
                    if (e.isHit(b)){
                        e.destroy();
                        playerScore += 500;
                        playerLivesScores += 500;
                        removeb.add(e);
                        bonus.add(new Bonus(e.getX(), e.getY(), this));
                    }
                }this.level[currentLevel].ene.removeAll(removeb);
            }
    }
    private void playerBullets(){
        List<Bullet> remove = new ArrayList<Bullet>();
        for (int i = 0; i < playerBullets.size(); i++) {
            if (playerBullets.get(i).isAlive() && playerBullets.get(i).getHitBox().intersects(SCREEN_BOUNDS)) {
                playerBullets.get(i).move();
                for(Asteroid a : this.getAsteroids()){
                    for(Bullet b : playerBullets){
                        if (a.isHit(b)){
                            b.destroy();
                            playerScore += a.getPoints();
                            playerLivesScores += a.getPoints();
                            remove.add(b);
                        }
                    }
                }
            }else{
                remove.add(playerBullets.get(i));
            }
        }
        playerBullets.removeAll(remove);
    }

    public void enenmyBullets(){
        if (jt % 100 == 0) {
            for (EnemyShip ene : this.level[currentLevel].getEne()) {
                enemyBullets.add(ene.fire());
            }
        }
        List<Bullet> remove = new ArrayList<Bullet>();
        for (int i = 0; i < enemyBullets.size(); i++){
            if(enemyBullets.get(i).isAlive() && enemyBullets.get(i).getHitBox().intersects(SCREEN_BOUNDS)){
                enemyBullets.get(i).move();
                if(player.isHit(enemyBullets.get(i))){
                    enemyBullets.get(i).destroy();
                    playerLives--;
                    pause = true;
                    player.resetDestroyed();
                    remove.add(enemyBullets.get(i));
                    nbTime = 200;
                }
            }else{
                remove.add(enemyBullets.get(i));
            }
        }
        enemyBullets.removeAll((remove));
    }


    @Override
    public boolean isPaused() {
        return pause;
    }

    @Override
    public void checkForPause() {
        if (listener.hasPressedPause()) {
            pause = !pause;
            listener.resetPause();
        }
    }

    @Override
    public boolean isLevelFinished() {
        if (currentLevel < NO_LEVELS){
            int noAst = level[currentLevel].getAss().size();
            if (noAst == 0){
                return true;
            }else{;return false;}

        }else{
            return true;
        }
    }

    @Override
    public int getTargetFPS() {
        return 0;
    }

    @Override
    public boolean isPlayerAlive() {
        return player.isAlive();
    }

    @Override
    public void resetDestroyedPlayer() {
        player.resetDestroyed();
        playerBullets = new ArrayList<Bullet>();
    }

    @Override
    public void moveToNextLevel() {
        if(this.isLevelFinished() == true){
            pause = true;
            jt = 0;
            currentLevel++;
            levelNum++;
            asNum++;
            level[levelNum] = new Level(asNum, this);
            player.resetDestroyed();
            nbTime = 200;
            playerBullets = new ArrayList<Bullet>();

        }else{
        }

    }

    @Override
    public boolean isGameOver() {
        return !(playerLives > 0 && currentLevel <= NO_LEVELS);
    }

    public int getTimemachine(){
        return timemachine;
    }

    @Override
    public int getScreenWidth( ) {
        return SCREEN_WIDTH;
    }

    public int getNbTime(){
        return nbTime;
    }
    @Override
    public int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static Rectangle getScreenBounds() {
        return new Rectangle(SCREEN_BOUNDS);
    }

    public Player getShip(){
        return player;
    }

    public List<Bullet> getPlayerBullets(){
        return  playerBullets;
    }


}