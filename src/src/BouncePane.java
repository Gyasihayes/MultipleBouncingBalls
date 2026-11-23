package src;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Random;

public class BouncePane extends Pane {

    ArrayList<Ball> balls = new ArrayList<>();
    private MyTimer timer = new MyTimer();
    private Random rand = new Random();

    public BouncePane() {
        setPrefSize(500, 500);
        this.setOnMousePressed(new MouseHandler());
        timer.start();
    }

    private class MyTimer extends AnimationTimer {
        public void handle(long current) {
            moveBalls();
            checkCollision();
        }
    }

    public void moveBalls() {
        for (Ball b : balls) {
            b.move();
        }
    }

    private void checkCollision() {
        for (Ball b : balls) {
            if (b.getCenterY() + b.getRadius() >= this.getHeight()) {
                b.setCenterY(this.getHeight() - b.getRadius() - 1);
                reflectUpDown(b);
            }
            else if (b.getCenterY() - b.getRadius() <= 0) {
                b.setCenterY(b.getRadius() + 1);
                reflectUpDown(b);
            }
            else if (b.getCenterX() + b.getRadius() >= this.getWidth()) {
                b.setCenterX(this.getWidth() - b.getRadius() - 1);
                reflectLeftRight(b);
            }
            else if (b.getCenterX() - b.getRadius() <= 0) {
                b.setCenterX(b.getRadius() + 1);
                reflectLeftRight(b);
            }
        }
    }

    private void reflectUpDown(Ball ball) {
        double dir = 360 - ball.getDirection();

        if (dir > 360) {
            dir -= 360;
        } else if (dir < 0.0) {
            dir += 360;
        }
        ball.setDirection(dir);
    }

    private void reflectLeftRight(Ball ball) {
        double dir = 180 - ball.getDirection();

        if (dir > 360) {
            dir -= 360;
        } else if (dir < 0.0) {
            dir += 360;
        }
        ball.setDirection(dir);
    }

    private void randomizeBall(Ball b) {
        b.setFill(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        b.setRadius(5 + Math.random() * 25);
        b.setSpeed(1 + rand.nextInt(10));
        b.setDirection(Math.random() * 360);
    }

    public void addNewBall() {
        Ball newBall = new Ball(this.getPrefWidth() / 2.0, this.getPrefHeight() / 2.0);
        randomizeBall(newBall);
        balls.add(newBall);
        this.getChildren().add(newBall);
    }

    public void scramble() {
        for (Ball b : balls) {
            randomizeBall(b);
        }
    }

    private boolean isInside(Ball b, double x, double y) {
        double distance = Math.sqrt(Math.pow(x - b.getCenterX(), 2) + Math.pow(y - b.getCenterY(), 2));
        return distance < b.getRadius();
    }

    private class MouseHandler implements javafx.event.EventHandler<javafx.scene.input.MouseEvent> {
        @Override
        public void handle(javafx.scene.input.MouseEvent event) {
            double mouseX = event.getX();
            double mouseY = event.getY();

            for (Ball b : balls) {
                if (isInside(b, mouseX, mouseY)) {
                    randomizeBall(b);
                    break;
                }
            }
        }
    }

    public void pauseTimer() {
        timer.stop();
    }

    public void startTimer() {
        timer.start();
    }
}