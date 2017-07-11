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

import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jensen on 7/4/17.
 */
public class Extra {

    BoardController boardController;

    public Extra(BoardController boardController) {
        this.boardController = boardController;

    }

    public void setHolidayGraphic() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd");
        LocalDate localDate = LocalDate.now();
        switch (dtf.format(localDate)) {
            case "07/04":
                boardController.splashText.setVisible(true);
                boardController.splashText.setText("Happy Fourth!");
                boardController.pane.setStyle("-fx-background-color: linear-gradient(to top right, rgba(155,12,40,0.9), #9b9b9b, #12669b)\n 0%");
                break;
            case "12/25":
                boardController.splashText.setVisible(true);
                boardController.splashText.setText("Happy Holidays!");
                boardController.pane.setStyle("-fx-background-color: linear-gradient(to top right, rgba(155,16,44,0.9), #159b1c)\n 0%");
                break;
            case "10/31":
                boardController.splashText.setVisible(true);
                boardController.splashText.setText("Boo!");
                boardController.pane.setStyle("-fx-background-color: linear-gradient(to top right, rgba(0,0,0,0.8), #9b560c)\n 0%");
        }
    }


    /**
     * Sets the splash image on the board to one from the text
     */
    public void setSplash() {

        ArrayList<String> splashText = new ArrayList<>();
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(boardController.getDirectorySearch().getSplashtextpath()));
            while((line = reader.readLine()) != null){
                if (line.length() <=22) {
                    splashText.add(line);
                }
            }
        } catch (Exception e) {
            System.out.println("[Log]: Error in getting splashes from file");
            e.printStackTrace();
        }

        boardController.splashText.setVisible(true);
        boardController.splashText.setFill(Color.BLACK);
        Random random = new Random();
        boardController.splashText.setText(splashText.get(random.nextInt(splashText.size())));

        if(boardController.splashText.getText().equals("Koalas are cool")) {
            boardController.splashText.setFill(Color.ORANGE);
        }


    }

    /**
     * Rotates the game board upside down
     */
    public void rotateBoard() {
        if(boardController.pane.getRotate() == 0) {
            boardController.pane.setRotate(180);
        } else {
            boardController.pane.setRotate(0);
        }

    }






}
