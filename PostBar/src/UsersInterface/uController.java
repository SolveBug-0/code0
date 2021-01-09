package UsersInterface;


import data.JdbcCrud;
import data.JdbcUtil;
import data.User;
import data.bPost;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class uController implements Initializable {

    @FXML
    public TableView<bPost> displayUser;
    public TableColumn<bPost,String> bnum,posts,comm,fabs;
    public Button commButton;



    //添加评论
    public void tocommont(ActionEvent actionEvent) throws Exception {
        JdbcCrud.update("Delete from Bar");
        for(bPost temp : displayUser.getItems()) {
            JdbcCrud.update("insert into Bar VALUES(?,?,?,?)",
                    temp.getBarNum(),temp.getPost(),temp.getCommont(),
                    temp.getLikes());
        }
        initBar(displayUser,bnum,posts,comm,fabs);
    }

    public void initBar(TableView<bPost> table,TableColumn<bPost,String> barnum,TableColumn<bPost,String> post,TableColumn<bPost,String> comm,TableColumn<bPost,String> likes) throws Exception {
        ResultSet rst = null;
        Connection connection = null;
        Statement ppst = null;
        String sql = "SELECT * FROM Bar";
        ObservableList<bPost> data = FXCollections.observableArrayList();
        try {
            connection = JdbcUtil.getConnection();
            ppst = connection.createStatement();
            rst = ppst.executeQuery(sql);

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


    //刷新
    public void torefresh(ActionEvent actionEvent) throws Exception {
        initBar(displayUser,bnum,posts,comm,fabs);
    }

    //点赞
    public void tofabs(ActionEvent actionEvent) throws Exception {
       bPost bp = displayUser.getSelectionModel().getSelectedItem();
       String like = String.valueOf(Integer.parseInt(bp.getLikes())+1);
       JdbcCrud.update("update Bar set Likes=? where BarNum=?",like,bp.getBarNum());
        initBar(displayUser,bnum,posts,comm,fabs);
    }

    //用户界面初始化
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUser.setEditable(true);


        bnum.setCellValueFactory(new PropertyValueFactory<>("BarNum"));
        posts.setCellValueFactory(new PropertyValueFactory<>("Post"));
        comm.setCellValueFactory(new PropertyValueFactory<>("Commont"));
        fabs.setCellValueFactory(new PropertyValueFactory<>("Likes"));

        comm.setCellFactory(TextFieldTableCell.forTableColumn());
        try {
            initBar(displayUser,bnum,posts,comm,fabs);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
