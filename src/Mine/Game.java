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
import javafx.scene.paint.ImagePattern;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jensen on 6/29/17.
 */
public final class Game {

    private BoardController boardController;
    private SettingsController settingsController;
    private ScoresController scoresController;
    private directorySearch directorySearch;
    private ArrayList<Point> bombs;
    private Difficulty difficulty;
    private int numOfBombs;
    private int totFlags;
    private int numberofActiveBombs;
    private boolean gameOver;
    private boolean gameWin;

    public Game(Difficulty Difficulty,BoardController c, SettingsController s, ScoresController sc){
        gameWin = false;
        difficulty = Difficulty;
        boardController = c;
        settingsController = s;
        scoresController = sc;
        directorySearch = boardController.getDirectorySearch();
        numOfBombs = difficulty.getNumOfBombs();
        numberofActiveBombs = numOfBombs;
    }

    private void placeBombs(int x, int y) { //exclude first click
        Random random = new Random();
        bombs = new ArrayList<Point>();
        for (int r = 0; r < numOfBombs; r++) {
            Point p = new Point(random.nextInt(boardController.getGridCols()), random.nextInt(boardController.getGridRows()));
            while (bombs.contains(p) || p.x == x && p.y == y) {
                // there is already a mine at that location, select a different one.
                p = new Point(random.nextInt(boardController.getGridCols()), random.nextInt(boardController.getGridRows()));
            }
            bombs.add(p);
            boardController.setBombValue(p.x,p.y);

        }
        boardController.bombsTxt.setText("Bombs: " + numOfBombs);
        totFlags = numOfBombs;
        boardController.setFlagsTxt(totFlags);

    }

    private boolean isSquareaBomb(int r, int c) {
        for (Point bomb : bombs) {
            if (bomb.y == c && bomb.x == r) { return true; }
        }
        return false;
    }

    public void placeFlag(int r, int c) {
        if(totFlags>0) {
            totFlags--;
            setFlagImg(r,c);
            boardController.setFlagsTxt(totFlags);
            if(boardController.getBoard()[r][c].isCovered()) {
                numberofActiveBombs--;
            }
        }
    }
    public void addNumberofActiveBombs() {
        this.numberofActiveBombs++;
    }
    public void addTotFlags() {
        this.totFlags++;
        boardController.setFlagsTxt(totFlags);

    }
    public void checkSuroundings(int r, int c) {
        int nearBombs = 0;

        if(isSquareaBomb(r-1, c-1)) { nearBombs++; } //Top Left
        if(isSquareaBomb(r-1, c)) { nearBombs++; } //Top Center
        if(isSquareaBomb(r-1, c+1)) { nearBombs++; } //Top Right
        if(isSquareaBomb(r, c-1)) { nearBombs++; } //Center Left
        if(isSquareaBomb(r, c+1)) { nearBombs++; } //Center Right
        if(isSquareaBomb(r+1, c-1)) { nearBombs++; } //Bottom Left
        if(isSquareaBomb(r+1, c)) { nearBombs++; } //Bottom Center
        if(isSquareaBomb(r+1, c+1)) { nearBombs++; } //Bottom Right

        boardController.getBoard()[r][c].setDisable(true);
        boardController.getBoard()[r][c].setClicked(true);

        if(nearBombs > 0) {
            setNumber(r, c, nearBombs);
        }
        else {
            boardController.getBoard()[r][c].setFill(Color.LIGHTGRAY);
            if(r > 0) {
                if (!boardController.getBoard()[r - 1][c].isClicked()) checkSuroundings(r-1,c); //Top Center
            }
            if(c < boardController.getBoard()[r].length-1) {
                if (!boardController.getBoard()[r][c + 1].isClicked()) checkSuroundings(r,c+1); //Center Right
            }
            if(r < boardController.getBoard().length-1) {
                if (!boardController.getBoard()[r + 1][c].isClicked()) checkSuroundings(r+1,c); //Bottom center
            }
            if(c > 0) {
                if (!boardController.getBoard()[r][c - 1].isClicked()) checkSuroundings(r,c-1); //Center Left
            }
            if(r > 0 && c < boardController.getBoard()[r].length-1) {
                if (!boardController.getBoard()[r - 1][c + 1].isClicked()) checkSuroundings(r - 1, c + 1); //Top Right
            }
            if(r < boardController.getBoard().length-1 && c < boardController.getBoard()[r].length-1) {
                if (!boardController.getBoard()[r + 1][c + 1].isClicked()) checkSuroundings(r+1,c+1); //Bottom Right
            }
            if(r < boardController.getBoard().length-1 && c > 0) {
                if (!boardController.getBoard()[r + 1][c - 1].isClicked()) checkSuroundings(r+1,c-1); //Bottom Left
            }
            if(r > 0 && c > 0) {
                if (!boardController.getBoard()[r - 1][c - 1].isClicked()) checkSuroundings(r-1,c-1); //Top Left
            }


        }

    }

    public void endGame(){
        gameOver = true;
        boardController.stopTimer();
        for (int r = 0; r < boardController.getBoard().length; r++) {
            for (int c = 0; c < boardController.getBoard()[r].length; c++) {
                boardController.getBoard()[r][c].setDisable(true);
            }
        }

        boardController.newGameButton.setDisable(false);
        boardController.informationTxt.setVisible(true);

        if(gameWin) {
            boardController.informationTxt.setText("Congratulations");
            boardController.informationTxt.setFill(Color.rgb(12, 146, 32));
            scoresController.getScoreshandeler().addScore(boardController.getSettings().getUserName(), boardController.getGameTimer().time.get(), difficulty);
        } else {
            boardController.showBombs();
            boardController.informationTxt.setText("Game Over");
            boardController.informationTxt.setFill(Color.rgb(255, 56, 68));
        }

    }
    public void checkifwin() {
        System.out.println("Num active bombs: " + numberofActiveBombs);
        if(totFlags == 0) {
            gameWin = numberofActiveBombs == 0;
            endGame();

        }
    }

    public void startNewGame(int r, int c) {
        placeBombs(r,c);
        boardController.getGameTimer().timer.start();
    }

    public void setBombImg(int r, int c){ boardController.getBoard()[r][c].setFill(new ImagePattern(settingsController.getBombImg())); }
    public void setFlagImg(int r, int c){ boardController.getBoard()[r][c].setFill(new ImagePattern(settingsController.getSelectedFlagImg())); }
    private void setNumber(int r, int c, int value) { boardController.getBoard()[r][c].setFill(new ImagePattern(settingsController.getValueImage(value))); }
    public SettingsController getSettingsController() {
        return settingsController;
    }



}
