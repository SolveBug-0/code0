package barMain;
import data.*;
import data.JdbcUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class barController implements Initializable {

    @FXML
    public TextField addname,addpass,addsex,addage,addphone;
    public Button addbutton;

    @FXML
    public Pane deletepane,updatapane,searchBar;

    @FXML
    private Pane addUserMain,upData;

    @FXML
    private TableView<User> addUsersTable,upDatable;
    @FXML
    private TableColumn<User,String> nameCol,passCol,sexCol,ageCol,phoneCol;
    @FXML
    private TableColumn<User,String> name2,pass2,sex2,age2,phone2;
    @FXML
    public Button delUserButton,changeButton;

    @FXML
    public TableView<bPost> barpost;
    public TableColumn<bPost,String> bNum,post,com,fab;
    public Button delPost;



    //显示添加用户界面
    public void showAdd(ActionEvent actionEvent) throws Exception {
        addUserMain.setVisible(true);
        upData.setVisible(false);
        searchBar.setVisible(false);

        //查询导出表中所有
        initUsers(addUsersTable,nameCol,passCol,sexCol,ageCol,phoneCol);
    }



    //显示删除用户界面
    public void toDelete(ActionEvent actionEvent) throws Exception {
        addUserMain.setVisible(false);
        upData.setVisible(true);
        searchBar.setVisible(false);
        deletepane.setVisible(true);
        updatapane.setVisible(false);

        initUsers(upDatable,name2,pass2,sex2,age2,phone2);
    }

    //显示修改用户数据界面
    public void toUpdata(ActionEvent actionEvent) throws Exception {
        addUserMain.setVisible(false);
        upData.setVisible(true);
        searchBar.setVisible(false);
        deletepane.setVisible(false);
        updatapane.setVisible(true);

        initUsers(upDatable,name2,pass2,sex2,age2,phone2);
    }


    //导出数据库Users数据至tableview的方法
    public void initUsers(TableView<User> table,TableColumn<User,String> name,TableColumn<User,String> pass,TableColumn<User,String> sex,TableColumn<User,String> age,TableColumn<User,String> phone) throws Exception {
        ResultSet rst = null;
        Connection connection = null;
        Statement ppst = null;
        String sql = "SELECT * FROM Users";
        ObservableList<User> data = FXCollections.observableArrayList();
        try {
            connection = JdbcUtil.getConnection();
            ppst = connection.createStatement();
            rst = ppst.executeQuery(sql);
            name.setCellValueFactory(new PropertyValueFactory<>("uName"));
            pass.setCellValueFactory(new PropertyValueFactory<>("uPass"));
            sex.setCellValueFactory(new PropertyValueFactory<>("uSex"));
            age.setCellValueFactory(new PropertyValueFactory<>("uAge"));
            phone.setCellValueFactory(new PropertyValueFactory<>("uPhone"));
            //System.out.print("连接成功");
            while(rst.next()) {
                data.add(new
                        User(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5)));
                table.setItems(data);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(connection != null && ppst != null && rst != null) {
                try {
                    connection.close();
                    ppst.close();
                    rst.close();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



    //添加用户
    public void toAddUs(ActionEvent actionEvent) throws Exception {
        String uName = addname.getText();
        String uPass = addpass.getText();
        String uSex = addsex.getText();
        String uAge = addage.getText();
        String uPhone = addphone.getText();
        if(uName!=null && uPass!=null && uSex!=null &&uAge!=null && uPhone!=null){
            JdbcCrud.update("insert into Users VALUES(?,?,?,?,?)",
                    uName,uPass,uSex,uAge,uPhone);
            initUsers(addUsersTable,nameCol,passCol,sexCol,ageCol,phoneCol);
        }
        addname.clear();addsex.clear();
        addage.clear();addphone.clear();addpass.clear();
    }

    //删除用户
    public void todel(ActionEvent actionEvent) throws Exception {
        User user = upDatable.getSelectionModel().getSelectedItem();
        String sql = "Delete from Users where uName=?";
        JdbcCrud.updateDel(sql,user.getuName());

        initUsers(upDatable,name2,pass2,sex2,age2,phone2);
    }

    //修改成员信息
    public void toChange(ActionEvent actionEvent) throws Exception {
        User user = upDatable.getSelectionModel().getSelectedItem();
        String sql = "Delete from Users where uName=?";
        JdbcCrud.updateDel(sql,user.getuName());

        JdbcCrud.update("insert into Users VALUES(?,?,?,?,?)",
                user.getuName(),user.getuPass(),user.getuSex(),
                    user.getuAge(),user.getuPhone());

        initUsers(upDatable,name2,pass2,sex2,age2,phone2);
    }

    //=======================查询界面，删贴
    //显示删贴界面
    public void toCheck(ActionEvent actionEvent) throws Exception {
        addUserMain.setVisible(false);
        upData.setVisible(false);
        searchBar.setVisible(true);

        initBar(barpost,bNum,post,com,fab);
    }

    //删贴
    public void toDelpost(ActionEvent actionEvent) throws Exception {
        bPost bpost = barpost.getSelectionModel().getSelectedItem();
        String sql = "Delete from Bar where BarNum=?";
        JdbcCrud.updateDel(sql,bpost.getBarNum());

        initBar(barpost,bNum,post,com,fab);
    }

    //导出Bar表的数据至TableView中的方法
    public void initBar(TableView<bPost> table,TableColumn<bPost,String> barnum,TableColumn<bPost,String> post,TableColumn<bPost,String> commont,TableColumn<bPost,String> likes) throws Exception {
        ResultSet rst = null;
        Connection connection = null;
        Statement ppst = null;
        String sql = "SELECT * FROM Bar";
        ObservableList<bPost> data = FXCollections.observableArrayList();
        try {
            connection = JdbcUtil.getConnection();
            ppst = connection.createStatement();
            rst = ppst.executeQuery(sql);
            barnum.setCellValueFactory(new PropertyValueFactory<>("BarNum"));
            post.setCellValueFactory(new PropertyValueFactory<>("Post"));
            commont.setCellValueFactory(new PropertyValueFactory<>("Commont"));
            likes.setCellValueFactory(new PropertyValueFactory<>("Likes"));
            //System.out.print("连接成功");
            while(rst.next()) {
                data.add(new
                        bPost(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
                table.setItems(data);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(connection != null && ppst != null && rst != null) {
                try {
                    connection.close();
                    ppst.close();
                    rst.close();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //初始化
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        upDatable.setEditable(true);
        pass2.setCellFactory(TextFieldTableCell.forTableColumn());
        sex2.setCellFactory(TextFieldTableCell.forTableColumn());
        age2.setCellFactory(TextFieldTableCell.forTableColumn());
        phone2.setCellFactory(TextFieldTableCell.forTableColumn());
    }



}
