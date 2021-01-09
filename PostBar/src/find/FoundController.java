package find;

import Login.login;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FoundController {

    @FXML
    private Button returnlog1;

    public void tologin(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            //获取按钮所在的窗口
            Stage primaryStage = (Stage) returnlog1.getScene().getWindow();
            //窗口关闭
            primaryStage.close();
            //加载注册窗口
            try {
                new login().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
