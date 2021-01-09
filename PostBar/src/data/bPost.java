package data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class bPost {
    private final SimpleStringProperty BarNum;
    private final SimpleStringProperty Post;
    private final SimpleStringProperty Commont;
    private final SimpleStringProperty Likes;

    public bPost(String barnum, String post, String commont, String likes) {
        this.BarNum = new SimpleStringProperty(barnum);
        this.Post = new SimpleStringProperty(post);
        this.Commont = new SimpleStringProperty(commont);
        this.Likes = new SimpleStringProperty(likes);
    }


    public String getBarNum() {
        return BarNum.get();
    }

    public SimpleStringProperty barNumProperty() {
        return BarNum;
    }

    public String getPost() {
        return Post.get();
    }

    public SimpleStringProperty postProperty() {
        return Post;
    }

    public String getCommont() {
        return Commont.get();
    }

    public SimpleStringProperty commontProperty(String newValue) {
        return Commont;
    }

    public String getLikes() {
        return Likes.get();
    }

    public SimpleStringProperty likesProperty(String newValue) {
        return Likes;
    }
}
