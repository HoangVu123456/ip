package stephen;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stephen.ui.MainWindow;

/**
 * Represents the GUI application of Stephen chatbot.
 */
public class Main extends Application {

    private Stephen stephen = new Stephen();

    /**
     * Starts the GUI application of Stephen chatbot.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setStephen(stephen);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

