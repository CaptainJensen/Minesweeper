package Mine;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jensen on 6/29/17.
 */
public class SettingsController implements Initializable {


    public RadioButton origToggle;
    public RadioButton greenToggle;
    public RadioButton yellowToggle;
    public ToggleGroup flags;
    public ToggleGroup diff;
    public Text versionLabel;

    //ALL OF THE IMAGES ARE PUT INTO SETTINGS FOR FASTER LOAD TIME. Recive the image when called upon. Speeds up INCREDABLY!!
    private final Image zero = new Image("/Resources/0.png");
    private final Image one = new Image("/Resources/1.png");
    private final Image two = new Image("/Resources/2.png");
    private final Image three = new Image("/Resources/3.png");
    private final Image four = new Image("/Resources/4.png");
    private final Image five = new Image("/Resources/5.png");
    private final Image six = new Image("/Resources/6.png");
    private final Image seven = new Image("/Resources/7.png");
    private final Image eightBall = new Image("/Resources/8ball.png");

    private final Image greenFlagImg = new Image("/Resources/spotifyflag.png");
    private final Image redFlagImg = new Image("/Resources/redflag.png");
    private final Image yellowFlagImg = new Image("/Resources/snapchatflag.png");
    private final Image bombImg = new Image("/Resources/bomb.png");
    private Image selectedFlagImg;

    private String version = "v2017.1.0 (ALPHA)"; //TODO: ADD A WAY FOR VERSION CONTROLL


    public Image getSelectedFlagImg() {
        if(selectedFlagImg == null) {
            return redFlagImg;
        } else return selectedFlagImg;
    }
    public Image getBombImg() {
        return bombImg;
    }
    public Image getValueImage(int value) {
        switch (value) {
            case 1:
                return one;
            case 2:
                return two;
            case 3:
                return three;
            case 4:
                return four;
            case 5:
                return five;
            case 6:
                return six;
            case 7:
                return seven;
            case 8:
                return eightBall;
            default:
                System.out.println("[Log]: Error in setting value for ball");
                return zero;
        }
    }

    public void restoreDefaultsClick(ActionEvent actionEvent) {

    }
    public void clearHighscoresClick(ActionEvent actionEvent) {

    }
    public void versionClick(MouseEvent mouseEvent) throws IOException { java.awt.Desktop.getDesktop().browse(URI.create("https://captainjensen.github.io/"));}





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
        flags.selectToggle(origToggle);
        origToggle.setUserData(redFlagImg);
        greenToggle.setUserData(greenFlagImg);
        yellowToggle.setUserData(yellowFlagImg);

        flags.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {

            if (flags.getSelectedToggle() != null) {
                selectedFlagImg = (Image) flags.getSelectedToggle().getUserData();
            }

        });
    }


}
