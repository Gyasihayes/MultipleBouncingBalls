package src;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    private final static double TWO_PI = 2 * Math.PI;
    private int speed = 8;
    private double direction = 45;


    public Ball( double xLoc, double yLoc) {
        this.setCenterX(xLoc);
        this.setCenterY(yLoc);
        this.setStroke(Color.RED);
        this.setFill(Color.RED);
        this.setRadius(15);

    }


    public void move() {
        // Pane pane = (Pane) this.getParent();
        this.setCenterX( this.getCenterX() + getSpeed() * Math.cos( getDirection() /360 * TWO_PI));
        this.setCenterY( this.getCenterY() + getSpeed() * Math.sin( getDirection() /360 * TWO_PI));
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }
}
