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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {

    @FXML
    public Text timerLabel;
    public Text playerTxt;
    public Button newGameButton;
    public Button helpButton;
    public Button highscoresButton;
    public Button settingsButton;
    public Pane pane;
    public Text bombsTxt;
    public Text flagsTxt;
    public Text informationTxt;


    //REFRENCE TO OTHER CLASSES
    private Game game;
    private directorySearch directorySearch;
    private ScoresController scoresController = new ScoresController();
    private SettingsController settings = new SettingsController();

    //CLASS DECLARATIONS
    private GridPane grid;
    private GameTimer gameTimer;
    private static rect[][] board;
    private Difficulty difficulty = Difficulty.MEDIUM; //Startup settings

    private final Image helpImg = new Image("/Images/helpImg.png");
    private final Image highscoresImg = new Image("/Images/highscoresImg.png");
    private final Image newGameImg = new Image("/Images/newgameImg.png");
    private final Image settingsImg = new Image("/Images/settingsImg.png");

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
        getGameTimer().timer.stop();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("settings.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setOnCloseRequest(c -> { //on settings close reset board for new game
                createNewGame();
            });
            stage.setTitle("Settings");

            stage.show();
        } catch(Exception e) {

            System.out.println("[Log]: Settings Window failed to open");
            e.printStackTrace();
        }
    }
    public void newGameClick(ActionEvent actionEvent) {
        createNewGame();
    }
    public void boardKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.C && gameTimer.isRunning() && keyEvent.isShiftDown()) {
            showBombs();
        }

    }

    public void stopTimer() {
        gameTimer.timer.stop();
    }

    private void addRectanglesToBoard() {

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                board[r][c] = new rect(game, r,c);
                grid.add(board[r][c],r,c);
            }

        }


    }

    private void setupGrid(Difficulty difficulty) {

        grid = new GridPane();
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setGridLinesVisible(true);
        grid.setLayoutY(150);

        if(difficulty == Difficulty.EASY) {
            grid.setLayoutY(200);
            grid.setLayoutX(370);
        }
        else if (difficulty == Difficulty.MEDIUM) {
            grid.setLayoutX(260);
        }
        else if(difficulty == Difficulty.HARD) {
            grid.setLayoutX(30);
        }
        else if(difficulty == Difficulty.FUN) {
            grid.setLayoutY(200);
            grid.setLayoutX(20);
        }
        else {
            System.err.println("Define a Difficulty");
            return;
        }


        for (int x = 0 ; x < difficulty.getCols() ; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            grid.getColumnConstraints().add(cc);
        }

        for (int y = 0 ; y < difficulty.getRows() ; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            grid.getRowConstraints().add(rc);
        }

        pane.getChildren().add(grid);

    }
    public int getGridRows() {
        return difficulty.getRows();
    }
    public int getGridCols() {
        return difficulty.getCols();
    }
    public rect[][] getBoard() {
        return board;
    }
    public SettingsController getSettings() { return settings; }
    public GameTimer getGameTimer() { return gameTimer; }
    public void showBombs() {

        for (rect[] aBoard : board) {
            for (rect anABoard : aBoard) {
                if(anABoard.isBomb() && !anABoard.isCovered()) {
                    anABoard.setFill(new ImagePattern(game.getSettingsController().getBombImg()));
                } else if(anABoard.isCovered()) {
                    anABoard.setFill(new ImagePattern(game.getSettingsController().getGreenBombImg()));
                }

            }
        }
    }
    public void setBombValue(int r,int c) {
        board[r][c].setBomb(true);
    }

    public void setFlagsTxt(int num) {
        flagsTxt.setText("Flags: " + num);
        switch (num) {
            case 3:
                flagsTxt.setFill(Color.rgb(206, 158, 24));
                break;
            case 2:
                flagsTxt.setFill(Color.rgb(206, 104, 41));
                break;
            case 1:
                flagsTxt.setFill(Color.rgb(206, 25, 57));
                break;
            default:
                flagsTxt.setFill(Color.BLACK);
                break;
        }
    }

    public void createNewGame(){
        difficulty = settings.getDifficulty();
        pane.getChildren().remove(grid);
        newGameButton.setDisable(true);
        game = new Game(difficulty,this, settings, scoresController);
        setupGrid(difficulty);
        board = new rect[difficulty.getCols()][difficulty.getRows()];
        addRectanglesToBoard();
        informationTxt.setVisible(false);
        playerTxt.setText("Player: " + settings.getUserName());
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
        directorySearch = new directorySearch();
        directorySearch.createDirectories();


        playerTxt.setText("Player: " + settings.getUserName());
        informationTxt.setText("Press new game to play");
        informationTxt.setVisible(true);
        newGameButton.setGraphic(new ImageView(newGameImg));
        helpButton.setGraphic(new ImageView(helpImg));
        settingsButton.setGraphic(new ImageView(settingsImg));
        highscoresButton.setGraphic(new ImageView(highscoresImg));

        gameTimer = new GameTimer(this);

        Extra extras = new Extra(this);
        extras.setHolidayGraphic();
    }
}