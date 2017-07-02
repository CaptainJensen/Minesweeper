package Mine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //DEBUG system
//        System.out.println("[Log]: TIME: " + System.currentTimeMillis());
//        Map<String, String> env = System.getenv();
//        for (String envName : env.keySet()) {
//            System.out.format("%s=%s%n",
//                    envName,
//                    env.get(envName));
//        }

        Parent root = FXMLLoader.load(getClass().getResource("Board.fxml"));
        primaryStage.setTitle("Minesweeper");
        primaryStage.centerOnScreen();
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/Images/bomb.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();





    }


    public static void main(String[] args) {
        launch(args);
    }
}
