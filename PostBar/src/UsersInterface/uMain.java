package UsersInterface;

import barMain.barIn;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class uMain extends Application {

    public static void main(String[] args) {
        Application.launch(uMain.class,args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("uinterface.fxml"));
        stage.setTitle("贴论坛");
        stage.getIcons().add(new Image("Image/hi.png"));
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }
}
