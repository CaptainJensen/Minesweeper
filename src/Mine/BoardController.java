package Mine;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {

    @FXML
    public ToggleButton T0011;
    public ToggleButton T0010;
    public Text versionLabel;
    public Button newGameButton;
    public Rectangle timerBox;
    public Text timerLabel;
    public Button helpButton;


    private String version = "v2017.1.0 (ALPHA)";

    private Image spotifyFlagImg = new Image("/Resources/spotifyflag.png");
    private Image bombImg = new Image("/Resources/bomb.png");
    private Image redFlagImg = new Image("/Resources/redflag.png");
    private Image snapchatFlagImg = new Image("/Resources/snapchatflag.png");
    private Image helpImg = new Image("/Resources/helpImg.png");
    private Image highscoresImg = new Image("/Resources/highscoresImg.png");
    private Image newGameImg = new Image("/Resources/newgameImg.png");
    private Image settingsImg = new Image("/Resources/settingsImg.png");
    private Image timeImg = new Image("/Resources/timeImg.png");




    public void squareRightClick(ContextMenuEvent contextMenuEvent) {
        System.out.println("Right CLicked");


    }

    public void squareClick(MouseEvent mouseEvent) {
        System.out.println("Clicked");
        Rectangle clicked = (Rectangle) mouseEvent.getSource();
        clicked.setFill(new ImagePattern(redFlagImg));

    }




    public void versionClick(MouseEvent mouseEvent) throws IOException {
        java.awt.Desktop.getDesktop().browse(URI.create("https://captainjensen.github.io/"));
    }

    public void scoresClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scores.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Highscores");
            stage.show();
        } catch(Exception e) {
            System.out.println("[Log]: Highscores Window failed to open");
            e.printStackTrace();
        }
    }

    public void helpClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("help.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Help");
            stage.show();
        } catch(Exception e) {
            System.out.println("[Log]: Help Window failed to open");
            e.printStackTrace();
        }
    }

    public void settingsClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("settings.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Settings");
            stage.show();
        } catch(Exception e) {
            System.out.println("[Log]: Settings Window failed to open");
            e.printStackTrace();
        }
    }




    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        versionLabel.setText("Jensen " + version);
        timerBox.setFill(new ImagePattern(timeImg));


    }



}
