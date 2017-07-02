package Mine;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Jensen on 6/29/17.
 */
public class HelpController {


    public Button closeButton;

    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void openWiki(ActionEvent actionEvent) throws IOException {
        java.awt.Desktop.getDesktop().browse(URI.create("https://en.wikipedia.org/wiki/Microsoft_Minesweeper"));
    }

    public void openWikihow(ActionEvent actionEvent) throws IOException {
        java.awt.Desktop.getDesktop().browse(URI.create("http://www.wikihow.com/Play-Minesweeper"));

    }
}
