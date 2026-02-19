package stephen.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle("-fx-background-color: #d1fae5;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 8 10 8 10;"
                + "-fx-text-fill: #065f46;"
                + "-fx-font-size: 13px;"
                + "-fx-font-weight: bold;"
                + "-fx-wrap-text: true;"
                + "-fx-max-width: 260px;");
        return db;
    }

    /**
     * Creates a dialog box for Stephen.
     */
    public static DialogBox getStephenDialog(String text, Image img, boolean isError) {
        var db = new DialogBox(text, img);
        db.flip();
        if (isError) {
            db.dialog.setStyle("-fx-background-color: #fecaca;"
                    + "-fx-background-radius: 12;"
                    + "-fx-padding: 8 10 8 10;"
                    + "-fx-text-fill: #991b1b;"
                    + "-fx-font-size: 13px;"
                    + "-fx-font-weight: bold;"
                    + "-fx-wrap-text: true;"
                    + "-fx-max-width: 260px;");
        } else {
            db.dialog.setStyle("-fx-background-color: #fed7aa;"
                    + "-fx-background-radius: 12;"
                    + "-fx-padding: 8 10 8 10;"
                    + "-fx-text-fill: #9a3412;"
                    + "-fx-font-size: 13px;"
                    + "-fx-font-weight: bold;"
                    + "-fx-wrap-text: true;"
                    + "-fx-max-width: 260px;");
        }
        return db;
    }
}


