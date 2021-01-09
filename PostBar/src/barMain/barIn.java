package barMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class barIn extends Application {

    public static void main(String[] args) {
        Application.launch(barIn.class,args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("bar.fxml"));
        stage.setTitle("贴管理");
        stage.getIcons().add(new Image("Image/hi.png"));
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }
}
