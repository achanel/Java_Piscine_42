package edu.school.tanks.models;

import edu.school.tanks.Program;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet extends AnimationTimer {
    private Image image;

    Tank enemy;
    private final double positionX;
    private double positionY;
    private final double speed;
    private double width;
    private double height;
    private final boolean isShooter;
    private final GraphicsContext gc;

    public Bullet(Tank tank, String image, GraphicsContext gc, Tank enemy, double forCorrect) {
        this.positionX = (tank.getPositionX() + tank.getBoundary().getMaxX()) / 2;
        this.positionY = tank.getPositionY() + forCorrect;
        this.isShooter = forCorrect == 0;
        this.enemy = enemy;
        this.gc = gc;
        if (positionY == 850) {
            speed = -10;
        } else {
            speed = 10;
        }
        setImage(new Image(image));
    }

    public double getPositionY() {
        return positionY;
    }

    public void setImage(Image i) {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void render()    {
        gc.drawImage( image, positionX, positionY );
    }

    public void move() {
        positionY += speed;
    }
    public Rectangle2D getBoundary()    {
        return new Rectangle2D(positionX,positionY,width,height);
    }


    @Override
    public void handle(long l) {
        if (this.getPositionY() + 10 >= 1042 || this.getPositionY() - 10 <= 0) {
            this.stop();
        } else if (enemy.getBoundary().intersects(this.getBoundary())) {
            enemy.takeDamage();
            if (isShooter)
                Program.out.println("hit");
            if (!enemy.checkLife()) {
                this.stop();
            }
            this.stop();
        } else {
            this.move();
            this.render();
        }
    }

}
