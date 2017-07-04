/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2017 Hunter Jensen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/

package Mine;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Jensen on 6/29/17.
 */
public class SettingsController implements Initializable {

    public ToggleGroup diff;
    public Text versionLabel;

    //ALL OF THE IMAGES ARE PUT INTO SETTINGS FOR FASTER LOAD TIME. Recive the image when called upon. Speeds up INCREDIBLY!!
    private final Image zero = new Image("/Images/0.png");
    private final Image one = new Image("/Images/1.png");
    private final Image two = new Image("/Images/2.png");
    private final Image three = new Image("/Images/3.png");
    private final Image four = new Image("/Images/4.png");
    private final Image five = new Image("/Images/5.png");
    private final Image six = new Image("/Images/6.png");
    private final Image seven = new Image("/Images/7.png");
    private final Image eightBall = new Image("/Images/8.png");
    private final Image redFlagImg = new Image("/Images/redflag.png");
    private final Image bombImg = new Image("/Images/bomb.png");
    private final Image greenBombImg = new Image("/Images/greenBomb.png");

    private Properties properties = new Properties(); //TODO: SET UP THESE SETTINGS
    private InputStream in = getClass().getResourceAsStream("/Resources/settings.properties");

    private Image selectedFlagImg;

    private String version = "v17.1.8-beta"; //TODO: ADD A WAY FOR VERSION CONTROLL


    public Image getSelectedFlagImg() {
        if(selectedFlagImg == null) {
            return redFlagImg;
        } else return selectedFlagImg;
    }
    public Image getBombImg() {
        return bombImg;
    }
    public Image getGreenBombImg() { return greenBombImg; }
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
        diff.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {

            if (diff.getSelectedToggle() != null) {

            }

        });
    }


}
