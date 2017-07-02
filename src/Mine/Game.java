package Mine;

import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jensen on 6/29/17.
 */
public final class Game {

    private BoardController boardController;
    private ArrayList<Point> bombs;
    private Difficulty difficulty;
    private int numOfBombs;
    private boolean gameOver;

    public Game(Difficulty Difficulty,BoardController c){
        difficulty = Difficulty;
        boardController = c;
        numOfBombs = difficulty.getNumOfBombs();
    }

    public void placeBombs() {
        Random random = new Random();
        bombs = new ArrayList<Point>();
        for (int r = 0; r < numOfBombs; r++) {
            Point p = new Point(random.nextInt(boardController.getGridCols()), random.nextInt(boardController.getGridRows()));
            while (bombs.contains(p)) {
                // there is already a mine at that location, select a different one.
                p = new Point(random.nextInt(boardController.getGridCols()), random.nextInt(boardController.getGridRows()));
            }
            bombs.add(p);
            boardController.setBombValue(p.x,p.y);
        }


    }

    public boolean isSquareaBomb(int r, int c) {
        for (Point bomb : bombs) {
            if (bomb.y == c && bomb.x == r) {
                return true;

            }

        }
        return false;
    }

    public boolean checkSquare(int r, int c) {
        return false;

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
            boardController.getBoard()[r][c].setNumber(nearBombs);
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
        boardController.showBombs();
        boardController.stopTimer();
        for (int r = 0; r < boardController.getBoard().length; r++) {
            for (int c = 0; c < boardController.getBoard()[r].length; c++) {
                boardController.getBoard()[r][c].setDisable(true);
            }
        }
        boardController.newGameButton.setDisable(false);


    }


}
