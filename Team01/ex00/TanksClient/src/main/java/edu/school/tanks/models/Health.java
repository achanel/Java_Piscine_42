package edu.school.tanks.models;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

public class Health {
    private final ImageView healthImage;
    private int LEVEL = 305;

    public void setHealth(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    private int healthLevel = 100;

    public Health(Group root, double positionX, double positionY, GraphicsContext gc) {
        ImageView border = new ImageView("border.png");
        this.healthImage = new ImageView("life.png");
        border.setFitHeight(60);
        border.setFitWidth(330);
        healthImage.setFitHeight(35);
        healthImage.setFitWidth(LEVEL);
        healthImage.setX(positionX);
        healthImage.setY(positionY);
        border.setX(positionX - 13);
        border.setY(positionY - 13);
        root.getChildren().addAll(healthImage, border);
    }

    public void takeDamage() {
        if (healthLevel >= 0) {
            healthLevel -= 5;
            LEVEL -= 15;
            healthImage.setFitWidth(LEVEL);
        }

    }

    public int getHealthLevel() {
        return healthLevel;
    }
}
