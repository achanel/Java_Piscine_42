package edu.school21.reflection.models.Car;
import java.util.StringJoiner;

public class Car {
    private String model;
    private int maxSpeed;
    private int price;

    public Car() {
        this.model = "Default model";
        this.maxSpeed = 200;
        this.price = 250;
    }
    public Car(String model, int maxSpeed, int price) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.price = price;
    }

    public int increaseCost(int value) {
        this.price += value;
        return price;
    }

    public String changeModel(String model) {
        this.model = model;
        return model;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                . add("model='" + model + "'")
                . add("maxSpeed='" + maxSpeed + "'")
                . add("price=" + price)
                . toString();
    }
}