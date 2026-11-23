package src;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainPane extends BorderPane {

    public MainPane() {
        BouncePane bouncePane = new BouncePane();
        this.setCenter(bouncePane);

        bouncePane.addNewBall();
        bouncePane.addNewBall();
        Button btnAdd = new Button("Add Ball");
        Button btnScramble = new Button("Scramble");
        Button btnExit = new Button("Exit");

        HBox hbox = new HBox(10, btnAdd, btnScramble, btnExit);
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-padding: 10px;");
        this.setBottom(hbox);


        btnAdd.setOnAction(e -> bouncePane.addNewBall());
        btnScramble.setOnAction(e -> bouncePane.scramble());
        btnExit.setOnAction(e -> Platform.exit());
    }
}
