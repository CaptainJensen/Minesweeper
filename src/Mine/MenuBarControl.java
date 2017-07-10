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

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCombination;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Jensen on 7/10/17.
 */
public class MenuBarControl extends MenuBar {


    private final Menu fileMenu = new Menu("File");
    private final Menu viewMenu = new Menu("View");
    private final Menu helpMenu = new Menu("Help");

    private BoardController boardController;



    public MenuBarControl(BoardController b) {
        boardController = b;
        setupMenuBar();

        getMenus().addAll(fileMenu, viewMenu, helpMenu);
        setUseSystemMenuBar(true);
    }


    private void setupMenuBar() {
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();


        //file menu
        MenuItem newGameMenuItem = new MenuItem("New");
        newGameMenuItem.setAccelerator(KeyCombination.keyCombination("META+N"));
        //TODO: Create a new game dialogue using http://code.makery.ch/blog/javafx-dialogs-official/


        MenuItem screenshot = new MenuItem("Take Screenshot");
        screenshot.setAccelerator(KeyCombination.keyCombination("META+S"));
        screenshot.setOnAction(e -> {

            WritableImage image = boardController.pane.snapshot(new SnapshotParameters(), null);
            File file = new File(boardController.getDirectorySearch().getScreenshotsDirectory() + "/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd'_'HH-mm-ss"))+ ".png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException l) {
                System.out.println("[Log]: Error in saving screenshot");
                l.printStackTrace();
            }


        });

        fileMenu.getItems().addAll(newGameMenuItem,separatorMenuItem, screenshot);


        //Options menu

        MenuItem screenshotMenuItem = new MenuItem("Open Screenshots");
        screenshotMenuItem.setOnAction(e -> {

                File file = new File(boardController.getDirectorySearch().getScreenshotsDirectory());
                try {
                    Runtime.getRuntime().exec(new String[]{"/usr/bin/open", file.getAbsolutePath()});
                } catch (IOException l) {
                    System.out.println("[Log]: Screenshot folder directory cannot be opened");
                    l.printStackTrace();
                }


        });

        MenuItem scoresMenuItem = new MenuItem("Highscores");
        scoresMenuItem.setOnAction(e -> boardController.scoresClick(e));

        viewMenu.getItems().addAll(scoresMenuItem, screenshotMenuItem);

        //Help menu
        MenuItem helpMenuItem = new MenuItem("Controls");
        helpMenuItem.setOnAction(e -> {
            boardController.helpClick(e);
        });
        MenuItem logsMenuItem = new MenuItem("Open Logs");
        logsMenuItem.setOnAction(e -> {

            File file = new File(boardController.getDirectorySearch().getLogsDirectory());
            try {
                Runtime.getRuntime().exec(new String[]{"/usr/bin/open", file.getAbsolutePath()});
            } catch (IOException l) {
                System.out.println("[Log]: Logs folder directory cannot be opened");
                l.printStackTrace();
            }


        });

        helpMenu.getItems().addAll(helpMenuItem,logsMenuItem);
    }

}
