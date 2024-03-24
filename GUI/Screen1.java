import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class screen1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hotel Management System");

        Button createBookingButton = new Button("Create Booking");
        createBookingButton.setOnAction(e -> {
           
            System.out.println("Create Booking button clicked");
        });

        Button modifyBookingButton = new Button("Modify Booking");
        modifyBookingButton.setOnAction(e -> {
           
            System.out.println("Modify Booking button clicked");
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(createBookingButton, modifyBookingButton);
        layout.setAlignment(Pos.CENTER); 

        StackPane root = new StackPane();
        root.getChildren().add(layout);
        root.setAlignment(Pos.CENTER); 

        Scene scene = new Scene(root, 700, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

