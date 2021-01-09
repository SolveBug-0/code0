package find;

import Login.login;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class findMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("find1.fxml"));
        primaryStage.setTitle("Hi贴吧");
        primaryStage.getIcons().add(new Image("file:D:\\IDEAProject\\PostBar\\src\\Image\\hi.png"));
        primaryStage.setScene(new Scene(root, 650, 550));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
