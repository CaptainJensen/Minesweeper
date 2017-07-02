package Mine;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Created by Jensen on 6/29/17.
 */
public class rect extends Rectangle {

    private boolean isClicked;
    private boolean isBomb;
    private boolean placedFlag;
    private boolean isCovered;
    private static boolean firstClicked;
    private Game game;
    private final int row;
    private final int col;


    public rect(Game g, int row, int col){
        game = g;
        isClicked = false;
        placedFlag = false;
        isCovered = false;
        isBomb = false;
        firstClicked = true;
        this.row = row;
        this.col = col;

        setWidth(30);
        setHeight(30);
        setFill(Color.WHITE);


        setOnMouseEntered(t -> { if(!isClicked) { setFill(Color.LIGHTGRAY); } });
        setOnMouseExited(t -> { if(!isClicked) { setFill(Color.WHITE); } });

        addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

            isClicked = true;
            if(e.getButton() == MouseButton.PRIMARY && firstClicked) {
                game.startNewGame(row, col);
                firstClicked = false;
                game.checkSuroundings(row,col);
            }
            else if(e.getButton() == MouseButton.SECONDARY && firstClicked) { //click secondary on first move to disable end game
                isClicked = false;
                return;
            }
            else if (e.getButton() == MouseButton.SECONDARY && !placedFlag) { //place a flag if is empty
                placedFlag = true;
                if(isBomb && placedFlag) {
                    isCovered = true;
                }
                game.placeFlag(row,col);


            }
            else if (e.getButton() == MouseButton.SECONDARY && placedFlag) { //remove a flag is one is on
                isClicked = false;
                setFill(Color.WHITE);
                game.addTotFlags();
                if(isBomb) {
                    game.addNumberofActiveBombs();
                }
                isCovered = false;
                placedFlag = false;
            }
            else if(e.getButton() == MouseButton.PRIMARY && placedFlag) { //click when a flag is placed
                //DO nothing so it would not remove unless right click
            }
            else {
                if(isBomb && placedFlag) {
                    isCovered = true;
                }
                if(isBomb) {
                    game.setFlagImg(row,col);
                    game.endGame();
                } else {
                    game.checkSuroundings(row,col);
                }



            }

            game.checkifwin();



            e.consume();


        });

    }

    public boolean isBomb() {
        return isBomb;
    }
    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }
    public boolean isClicked() { return isClicked; }
    public boolean isCovered() { return isCovered; }
    public void setClicked(boolean clicked) { isClicked = clicked; }

}
