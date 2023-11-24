package si.display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerListener implements KeyListener {
    private boolean left;
    private boolean right;
    private boolean speedup;
    private boolean fire;
    private boolean jump;
    private boolean pause;
    private boolean stop;
    private boolean execute;
    private boolean timeMachine;

    public boolean isPressingLeft() {
        return left;
    }

    public boolean isPressingRight() {
        return right;
    }

    public boolean isPressingUp() {
        return speedup;
    }

    public boolean hasPressedFire() {
        return fire;
    }

    public boolean isPressingJump() {
        return jump;
    }

    public boolean hasPressedPause() {
        return pause;
    }

    public boolean hasPressedStop() {
        return stop;
    }

    public boolean hasPressedExecute(){
        return execute;
    }

    public boolean hasPressedTime(){
        return timeMachine;
    }



    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'P' || e.getKeyChar() == 'p') {
            pause = true;
        }

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            fire = true;
        }else if (e.getKeyCode() == KeyEvent.VK_UP){
            speedup = true;
        }else if (e.getKeyChar() == 'J' || e.getKeyChar() == 'j'){
            jump = true;
        }else if (e.getKeyChar() == 'S' || e.getKeyChar() == 's'){
            stop = true;
        }else if (e.getKeyChar() == 'E' || e.getKeyChar() == 'e'){
            execute = true;
        }else if (e.getKeyChar() == 'T' || e.getKeyChar() == 't'){
            timeMachine = true;}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }else if (e.getKeyCode() == KeyEvent.VK_UP){
            speedup = false;
        }else if (e.getKeyCode() == KeyEvent.VK_J){
            jump = false;
        }else if (e.getKeyCode() == KeyEvent.VK_S){
            stop = false;
        }else if (e.getKeyCode() == KeyEvent.VK_E){
            execute = false;
        }else if (e.getKeyCode() == KeyEvent.VK_T){
            timeMachine = false;}
    }

    public void resetPause() {
        pause = false;
    }
    public void resetStop(){
        stop = false;
    }
    public void resetJump(){
        jump = false;
    }
    public void resetFire() {
        fire = false;
    }
    public void resetExecute(){execute = false;}

    public void resetTimeMachine(){timeMachine = false;}
}