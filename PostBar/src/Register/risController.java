package Register;

import Login.login;
import data.JdbcCrud;
import data.JdbcUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.junit.Test;

import javax.swing.*;
import java.sql.Connection;
import java.util.Optional;

public class risController {
    @FXML
    public TextField username,phone;

    @FXML
    public ChoiceBox age;

    @FXML
    public PasswordField pass;

    @FXML
    public RadioButton male,female;

    @FXML
    public ToggleGroup sex;

    @FXML
    private Button returnlog2,submit;


    
    public void tologin(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            //获取按钮所在的窗口
            Stage primaryStage = (Stage) returnlog2.getScene().getWindow();
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

    public void todata(ActionEvent actionEvent) throws Exception {
        String uName = username.getText();
        String uPass = pass.getText();
        String uPhone = phone.getText();
        String uSex = null;
        String uAge = age.getValue().toString();

        if(male.isSelected())
            uSex = "male";
        else
            if(female.isSelected())
                uSex = "female";

        if(uName==null || uPass==null || uPhone==null || uSex==null || uAge==null){
            JOptionPane.showMessageDialog(null,"信息请填写完整","警告",JOptionPane.PLAIN_MESSAGE);
        }
        else{
            JdbcCrud.update("insert into Users VALUES(?,?,?,?,?)",uName,uPass,uSex,uAge,uPhone);
            JOptionPane.showMessageDialog(null,"注册成功","提示",JOptionPane.PLAIN_MESSAGE);
            Platform.runLater(() -> {
                //获取按钮所在的窗口
                Stage primaryStage = (Stage) returnlog2.getScene().getWindow();
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

}
