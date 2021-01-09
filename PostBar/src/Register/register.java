package Register;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class register extends Application {
    public static void main(String[] args) {
        Application.launch(register.class,args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("rigisterfx.fxml"));
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        primaryStage.setTitle("Hi贴吧");
        primaryStage.getIcons().add(new Image("file:D:\\IDEAProject\\PostBar\\src\\Image\\hi.png"));
        primaryStage.setScene(new Scene(root, 650, 550));
        primaryStage.show();
    }


}
