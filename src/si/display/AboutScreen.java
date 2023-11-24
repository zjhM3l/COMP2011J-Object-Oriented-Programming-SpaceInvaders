package si.display;

import si.model.SpaceInvadersGame;

import javax.swing.*;
import java.awt.*;

public class AboutScreen extends JPanel {
    private static final long serialVersionUID = -1264717778772722118L;
    private boolean menu = false;
    private PlayerListener listener;

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, SpaceInvadersGame.SCREEN_WIDTH, SpaceInvadersGame.SCREEN_HEIGHT);
        g.setColor(Color.green);
        drawString(g, "Space Invader Controls", new Rectangle(0, 0, SpaceInvadersGame.SCREEN_WIDTH, 64), 36);
        g.drawString("Rotate Left", 1 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 0 * 32);
        g.drawString("left arrow", 4 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 0 * 32);
        g.drawString("Rotate Right", 1 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 1 * 32);
        g.drawString("right arrow", 4 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 1 * 32);
        g.drawString("Fire", 1 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 2 * 32);
        g.drawString("space bar", 4 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 2 * 32);
        g.drawString("Play/Pause", 1 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 3 * 32);
        g.drawString("p", 4 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 3 * 32);
        g.drawString("Speed up", 1 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 4 * 32);
        g.drawString("up arrow", 4 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 4 * 32);
        g.drawString("Jump", 1 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 5 * 32);
        g.drawString("J", 4 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 5 * 32);
        g.drawString("Stop", 1 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 6 * 32);
        g.drawString("S", 4 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 6 * 32);
        g.drawString("Execute Shoot!", 1 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 7 * 32);
        g.drawString("E", 4 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 7 * 32);
        g.drawString("Time Machine! ", 1 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 8 * 32);
        g.drawString("T", 4 * SpaceInvadersGame.SCREEN_WIDTH / 6, 96 + 8 * 32);
        drawString(g, "Press 'M' to return to the Main Menu", new Rectangle(0, 416, SpaceInvadersGame.SCREEN_WIDTH, 96), 24);
    }

    private void drawString(Graphics g, String text, Rectangle rect, int size) {
        Graphics2D g2d = (Graphics2D) g.create();

        Font font = new Font("Arial", Font.BOLD, size);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics();
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g2d.setColor(Color.GREEN);
        g2d.drawString(text, x, y);
    }
}