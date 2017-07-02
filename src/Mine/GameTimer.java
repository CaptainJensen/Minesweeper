package Mine;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by Jensen on 6/29/17.
 */
public class GameTimer {


    private BoardController boardController;
    DoubleProperty time = new SimpleDoubleProperty();
    public AnimationTimer timer = new AnimationTimer() {

        private long startTime ;

        @Override
        public void start() {
            startTime = System.currentTimeMillis();
            super.start();
        }

        @Override
        public void stop() {
            super.stop();
        }

        @Override
        public void handle(long timestamp) {
            long now = System.currentTimeMillis();
            time.set((now - startTime) / 1000.0);
            if(((now - startTime) / 1000.0) / 60 > 1) {

            }
        }

    };




    public GameTimer(BoardController b) {
        //get the board controller for updating the text;
        boardController = b;
        boardController.timerLabel.textProperty().bind(time.asString("%.1f s"));

    }









}