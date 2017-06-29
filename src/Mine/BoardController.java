package Mine;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {

    @FXML
    public ToggleButton T0011;
    public ToggleButton T0010;

    Image spotifyFlagImg = new Image("/Resources/spotifyflag.png");
    Image bombImg = new Image("/Resources/bomb.png");
    Image redFlagImg = new Image("/Resources/redflag.png");
    Image snapchatFlagImg = new Image("/Resources/snapchatflag.png");




    public void squareRightClick(ContextMenuEvent contextMenuEvent) {
        System.out.println("Right CLicked");


    }

    public void squareClick(MouseEvent mouseEvent) {
        System.out.println("Clicked");
        Rectangle clicked = (Rectangle) mouseEvent.getSource();
        System.out.println(clicked.getWidth());
        clicked.setFill(new ImagePattern(bombImg));

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

        T0010.setStyle("-fx-graphic: url('/Resources/spotifyflag.png');");


    }


}
