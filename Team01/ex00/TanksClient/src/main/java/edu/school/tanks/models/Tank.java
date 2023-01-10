package edu.school.tanks.models;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Tank {
    private Image image;
    private final Health health;
    private double positionX;
    private final double positionY;
    private double width;
    private double height;


    public Tank(String filename, double positionX, double positionY, Group root, double posXll, double posYll, GraphicsContext gc) {
        this.positionX = positionX;
        this.positionY = positionY;
        health = new Health(root, posXll, posYll, gc);
        setImage(new Image(filename));
    }

    public void setImage(Image i) {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }


    public void moveLeft(int speed) {
            positionX -= speed;
    }

    public void moveRight(int speed) {
            positionX += speed;
    }

    public boolean canMove(String move) {
        if (move.equals("right")) {
            return positionX + 10 < 970;
        } else {
            return positionX - 10 > -10;
        }
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void render(GraphicsContext gc)    {
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary()    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public void takeDamage() {
        health.takeDamage();
    }

    public void kill() {
        health.setHealth(-10);
    }

    public boolean checkLeaveGame() {
        return health.getHealthLevel() == -10;
    }

    public boolean checkLife() {
        return health.getHealthLevel() > 0;
    }

}
