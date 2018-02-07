package screens;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

/**
 * Abstracts Spotify Login Screen details which use sikuli client to access the application. Exposes methods to be used
 * in the tests.
 */
public class LoginScreen extends BaseScreen {
    private final Pattern loginWindow;
    private final Pattern rememberMe;
    private final Pattern rememberMeOn;
    private final Pattern userNameField;
    private final Pattern passwordField;
    private final Pattern loginButton;
    private final Pattern loginWithFb;
    private final Pattern errorMessage;
    private final Region loginScreen;
    private static int WAIT_DELAY = 15;

    public LoginScreen() throws FindFailed {
        loginWindow = new Pattern("logInScreen/loginForm.png");
        rememberMe = new Pattern("logInScreen/rememberMe.png");
        rememberMeOn = new Pattern("logInScreen/rememberMeOn.png");
        // calculated offset value for username filed no matter what was entered to a field in a previous time.
        userNameField = new Pattern("logInScreen/userNameFieldWithOffset.png").targetOffset(0, 15);
        passwordField = new Pattern("logInScreen/passwordField.png");
        loginButton = new Pattern("logInScreen/loginButton.png");
        loginWithFb = new Pattern("logInScreen/loginWithFb.png");
        errorMessage = new Pattern("logInScreen/errorMessage.png");
        loginScreen = getDriver().wait(loginWindow, WAIT_DELAY);
    }

    public boolean isScreenExists() {
        try {
            getDriver().find(loginWindow);
            return true;
        } catch (FindFailed findFailed) {
            return false;
        }
    }

    public LoginScreen fillLoginForm(String userName, String password) throws FindFailed {
        cleanTextField(loginScreen, userNameField);
        loginScreen.type(userNameField, userName);
        loginScreen.type(passwordField, password);
        return this;
    }

    public void clickLogin() throws FindFailed {
        loginScreen.click(loginButton);
    }

    /**
     * Method checks if RememberMe field is ON and turns it to OFF position.
     */
    public void turnRememberMeOffIfNeeded() {
        try {
            loginScreen.find(rememberMeOn);
            clickRememberMe();
        } catch (FindFailed findFailed) {
            return;
        }
    }

    public LoginScreen clickRememberMe() throws FindFailed {
        loginScreen.click(rememberMe);
        return this;
    }

    public SearchScreen clickLoginWithFacebook() throws FindFailed {
        loginScreen.click(loginWithFb);
        getDriver().waitVanish(loginWithFb);
        return new SearchScreen();
    }

    public boolean isErrorMessage() {
        try {
            loginScreen.wait(errorMessage, WAIT_DELAY);
            return true;
        } catch (FindFailed findFailed) {
            return false;
        }
    }

}
