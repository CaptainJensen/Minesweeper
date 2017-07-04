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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by Jensen on 6/29/17.
 */
public class SettingsController implements Initializable {

    public ToggleGroup diff;
    public Text versionLabel;
    public TextField nameboxEdit;
    public Text infoTxt;
    public RadioButton hardToggle;
    public RadioButton medToggle;
    public RadioButton easyToggle;

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


    private Properties properties = new Properties();


    private static String version = "v17.1.8-beta"; //TODO: ADD A WAY FOR VERSION CONTROLL

    private static final String[] INFO_TXT = {
            "Have a wonderful day",
            "There are 99 Bombs on hard",
            "There are more than 4000 lines of code",
            "Have fun!",
            "Not all who wander are lost",
            "Sometimes a cigar, is just a cigar",
            "The cake is a lie!",
            "There is a cheat code hidden here",
            "You are playing version " + version,
    };

    public Image getSelectedFlagImg() { return redFlagImg; }
    public Image getBombImg() { return bombImg; }
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
        medToggle.setSelected(true);
        nameboxEdit.setText(System.getenv("LOGNAME"));
        infoTxt.setFill(Color.BLACK);
        Random random = new Random();
        infoTxt.setText(INFO_TXT[random.nextInt(INFO_TXT.length)]);
        setDefaultSettings();
    }
    public void checkUpdateClick(ActionEvent actionEvent) {
        try {
            java.awt.Desktop.getDesktop().browse(URI.create("https://github.com/CaptainJensen/Minesweeper/releases"));
        } catch (IOException e) {
            infoTxt.setFill(Color.MAROON);
            infoTxt.setText("Could not open link");
            e.printStackTrace();
        }
    }

    public void editNameBox(ActionEvent actionEvent) {

    }

    public void easyClickToggle(ActionEvent actionEvent) {

        try (OutputStream output = new FileOutputStream("Resources/settings.properties")) {
            // set the properties value
            properties.setProperty("easyToggle", String.valueOf(true));
            properties.setProperty("medToggle", String.valueOf(false));
            properties.setProperty("hardToggle", String.valueOf(false));

            // save properties to project root folder.
            properties.store(output, null);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void medClickToggle(ActionEvent actionEvent) {
        try (OutputStream output = new FileOutputStream("Resources/settings.properties")) {

            properties.setProperty("easyToggle", String.valueOf(false));
            properties.setProperty("medToggle", String.valueOf(true));
            properties.setProperty("hardToggle", String.valueOf(false));

            properties.store(output, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void hardClickToggle(ActionEvent actionEvent) {
        try (OutputStream output = new FileOutputStream("Resources/settings.properties")) {

            properties.setProperty("easyToggle", String.valueOf(false));
            properties.setProperty("medToggle", String.valueOf(false));
            properties.setProperty("hardToggle", String.valueOf(true));

            properties.store(output, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void versionClick(MouseEvent mouseEvent){
        try {
            java.awt.Desktop.getDesktop().browse(URI.create("https://captainjensen.github.io/"));
        } catch (IOException e) {
            infoTxt.setFill(Color.MAROON);
            infoTxt.setText("Could not open link");
            e.printStackTrace();
        }

    }


    public Difficulty getDifficulty(){
        try (InputStream input = new FileInputStream("Resources/settings.properties")) {

            properties.load(input);
            System.out.println("easyToggle - " + properties.getProperty("easyToggle"));
            System.out.println("medToggle - " + properties.getProperty("medToggle"));
            System.out.println("hardToggle - " + properties.getProperty("hardToggle"));

            if(properties.getProperty("easyToggle").equals("true")) {
                return Difficulty.EASY;
            } else if(properties.getProperty("hardToggle").equals("true")) {
                return Difficulty.HARD;
            } else {
                return Difficulty.MEDIUM;
            }

        } catch (IOException ex) {

            ex.printStackTrace();
        }
        return Difficulty.MEDIUM; //DEFAULT
    }
    public String getUserName() {
        try (InputStream input = new FileInputStream("Resources/settings.properties")) {

            properties.load(input);
            if(properties.getProperty("username") != null) {
                return properties.getProperty("username");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return System.getenv("LOGNAME"); //DEFAULT
    }
    private void setDefaultSettings() {
        try (OutputStream output = new FileOutputStream("Resources/settings.properties")) {

            properties.setProperty("easyToggle", String.valueOf(false));
            properties.setProperty("medToggle", String.valueOf(true));
            properties.setProperty("hardToggle", String.valueOf(false));
            properties.setProperty("username", System.getenv("LOGNAME"));

            properties.store(output, null);

        } catch (IOException e) {
            //TODO: If no file found create one with these settings
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
        if(getDifficulty() == Difficulty.EASY) {
            easyToggle.setSelected(true);
        } else if(getDifficulty() == Difficulty.HARD) {
            hardToggle.setSelected(true);
        } else medToggle.setSelected(true);

        versionLabel.setText("Jensen " + version);
        Random random = new Random();
        infoTxt.setText(INFO_TXT[random.nextInt(INFO_TXT.length)]);
    }


}
