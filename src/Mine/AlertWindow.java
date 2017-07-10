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

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Optional;

/**
 * Created by Jensen on 7/10/17.
 */
public class AlertWindow extends Alert {


    private boolean okPressed;

    /**
     * Creates an alert with the given AlertType (refer to the {@link AlertType}
     * documentation for clarification over which one is most appropriate).
     * <p>
     * <p>By passing in an AlertType, default values for the
     * {@link #titleProperty() title}, {@link #headerTextProperty() headerText},
     * and {@link #graphicProperty() graphic} properties are set, as well as the
     * relevant {@link #getButtonTypes() buttons} being installed. Once the Alert
     * is instantiated, developers are able to modify the values of the alert as
     * desired.
     * <p>
     * <p>It is important to note that the one property that does not have a
     * default value set, and which therefore the developer must set, is the
     * {@link #contentTextProperty() content text} property (or alternatively,
     * the developer may call {@code alert.getDialogPane().setContent(Node)} if
     * they want a more complex alert). If the contentText (or content) properties
     * are not set, there is no useful information presented to end users.
     *
     * @param alertType
     */
    public AlertWindow(AlertType alertType) {
        super(alertType);


    }


    public void createNewGameAlert() {
        setAlertType(AlertType.CONFIRMATION);
        setTitle("Abandon Game?");
        setHeaderText("Changing the settings will abandon the current game.");
        setContentText("Are you ok with this?");
        setGraphic(new ImageView(new Image("/Resources/Images/explosionImg.png")));

        Optional<ButtonType> result = showAndWait();
        okPressed = result.get() == ButtonType.OK;
    }

    public void createErrorAlert() {
        setAlertType(AlertType.ERROR);
        setTitle("Error");
        setHeaderText("Look, an Error Dialog");
        setContentText("Ooops, there was an error!");

        showAndWait();
    }

    public boolean isOkPressed() { return okPressed; }




}
