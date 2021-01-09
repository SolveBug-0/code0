package Login;

import Register.register;
import UsersInterface.uMain;
import barMain.barIn;
import find.findMain;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private Label ris,fin;

    @FXML
    private TextField userName;
    @FXML
    private PasswordField userPass;
    @FXML
    private Button loging;



    public void toris(MouseEvent mouseEvent) {
        Platform.runLater(() -> {
            //获取按钮所在的窗口
            Stage primaryStage = (Stage) ris.getScene().getWindow();
            //窗口隐藏
            primaryStage.hide();
            //加载注册窗口
            try {
                new register().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void tofind(MouseEvent mouseEvent) {
        Platform.runLater(() -> {
            //获取按钮所在的窗口
            Stage primaryStage = (Stage) fin.getScene().getWindow();
            //窗口隐藏
            primaryStage.hide();
            //加载注册窗口
            try {
                new findMain().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void tobar(ActionEvent actionEvent) {
        String admin = userName.getText();
        String adminpass = userPass.getText();
        if("admin0".equals(admin)&&"123456780".equals(adminpass)){
            Platform.runLater(() -> {
                Stage primaryStage = (Stage) loging.getScene().getWindow();
                //窗口关闭
                primaryStage.close();
                //加载注册窗口
                try {
                    new barIn().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        else
            Platform.runLater(() -> {
                Stage primaryStage = (Stage) loging.getScene().getWindow();
                //窗口关闭
                primaryStage.close();
                //加载注册窗口
                try {
                    new uMain().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });


    }
}
