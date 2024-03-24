import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

           
            Stage userInfoStage = new Stage();
            userInfoStage.setTitle("User Info");

           
            Label nameLabel = new Label("Name:");
            TextField nameField = new TextField();
            Label emailLabel = new Label("Email:");
            TextField emailField = new TextField();
            Label phoneLabel = new Label("Phone Number:");
            TextField phoneField = new TextField();
            Label idLabel = new Label("Unique ID:");
            TextField idField = new TextField();

            
            VBox formLayout = new VBox(10);
            formLayout.getChildren().addAll(nameLabel, nameField, emailLabel, emailField, phoneLabel, phoneField, idLabel, idField);
            formLayout.setAlignment(Pos.CENTER);

            
            Scene formScene = new Scene(formLayout, 300, 400);
            userInfoStage.setScene(formScene);
            userInfoStage.show();
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


