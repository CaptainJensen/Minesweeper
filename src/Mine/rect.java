package Mine;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


/**
 * Created by Jensen on 6/29/17.
 */
public class rect extends Rectangle {

    private SettingsController settings;
    private boolean isClicked;
    private boolean isBomb;
    private Game game;
    private final int row;
    private final int col;




    public rect(Game g, SettingsController s, int row, int col){
        game = g;
        isClicked = false;
        settings = s;
        isBomb = false;
        this.row = row;
        this.col = col;

        setWidth(30);
        setHeight(30);
        setFill(Color.WHITE);


        setOnMouseEntered(t -> {
            if(!isClicked) {
                setFill(Color.LIGHTGRAY);
            }
        });
        setOnMouseExited(t -> {
            if(!isClicked) {
                setFill(Color.WHITE);
            }
        });

        addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

            isClicked = true;

            if (e.getButton() == MouseButton.SECONDARY) {
                setFill(new ImagePattern(settings.getSelectedFlagImg()));


            } else {
                runGamelogic();


            }

            e.consume();


        });






    }

    public void setBombImg(){
        setFill(new ImagePattern(settings.getBombImg()));
    }
    public void setFlagImg(){
        setFill(new ImagePattern(settings.getSelectedFlagImg()));
    }
    public void setNumber(int value) {
        setFill(new ImagePattern(settings.getValueImage(value)));


    }


    public boolean isBomb() {
        return isBomb;
    }
    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }
    public boolean isClicked() { return isClicked; }
    public void setClicked(boolean clicked) { isClicked = clicked; }
    public int getCol() { return col; }
    public int getRow() { return row; }

    private void runGamelogic() {
        if(isBomb) {
            setBombImg();
            game.endGame();
        } else {
            game.checkSuroundings(row,col);
        }

    }
}
