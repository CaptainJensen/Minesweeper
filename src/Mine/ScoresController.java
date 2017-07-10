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

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jensen on 6/29/17.
 */
public class ScoresController implements Initializable {

    public ListView easyScoresList;
    public ListView medScoresList;
    public ListView hardScoresList;
    public Text informationTxt;
    public Button clearButton;

    private Scoreshandeler scoreshandeler;
    private final Image BROOM_ICON = new Image("/Images/clearImg.png");


    public ScoresController() {
        scoreshandeler = new Scoreshandeler();
    }

    public Scoreshandeler getScoreshandeler() {
        return scoreshandeler;
    }



    public void clearHighscoresClick(ActionEvent actionEvent) {
        scoreshandeler.deleteScores();
        easyScoresList.setItems(FXCollections.observableArrayList(scoreshandeler.getScoreArray(Difficulty.EASY)));
        medScoresList.setItems(FXCollections.observableArrayList(scoreshandeler.getScoreArray(Difficulty.MEDIUM)));
        hardScoresList.setItems(FXCollections.observableArrayList(scoreshandeler.getScoreArray(Difficulty.HARD)));
        informationTxt.setVisible(true);
        informationTxt.setFill(Color.rgb(211, 160, 12));
        informationTxt.setText("Scores cleared!");
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
        easyScoresList.setItems(FXCollections.observableArrayList(scoreshandeler.getScoreArray(Difficulty.EASY)));
        medScoresList.setItems(FXCollections.observableArrayList(scoreshandeler.getScoreArray(Difficulty.MEDIUM)));
        hardScoresList.setItems(FXCollections.observableArrayList(scoreshandeler.getScoreArray(Difficulty.HARD)));
        clearButton.setGraphic(new ImageView(BROOM_ICON));
        informationTxt.setVisible(true);
        informationTxt.setText(scoreshandeler.getErrorMessage());
        informationTxt.setFill(Color.MAROON);




    }

}
